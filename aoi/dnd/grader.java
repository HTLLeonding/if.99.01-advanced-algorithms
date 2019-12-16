import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class grader
{
    static final int MAX_N = 1005;
    static final int MAX_NOTE = 42;
    static final int MAX_STEP = 4000000;

    static final int AC = 0;
    static final int INVALID_DOOR = 1;
    static final int TOO_MANY_STEPS = 2;
    static final int NOTE_TOO_LONG = 3;
    static final int NOT_FOUND = 4;
    static final int EXCEPTION_CAUGHT = 5;

    static class Edge
    {
        public int to;
        public int otherDoor;
        Edge(int t, int d) { to = t; otherDoor = d; }
    }

    static int n;
    static ArrayList<ArrayList<Edge>> adjl;
    static boolean[] d;
    static boolean currentlyDead = false;

    static class State
    {
        public int position = 1;
        public int steps = 0;
        public String[] notes = new String[n];
    }
    static State st;

    static void judge(int status)
    {
        switch(status)
        {
            case AC:
                System.out.println("Du bist entkommen, " + st.steps + " Schritte");
                break;
            case INVALID_DOOR:
                System.out.println("Diese Tuer gibt es nicht");
                break;
            case TOO_MANY_STEPS:
                System.out.println("Zu viele Schritte");
                break;
            case NOTE_TOO_LONG:
                System.out.println("Notiz zu lang");
                break;
            case NOT_FOUND:
                System.out.println("Ausgang nicht gefunden");
                break;
            case EXCEPTION_CAUGHT:
                System.out.println("DeathException darf nicht gefangen werden");
                break;
        }
        System.exit(0);
    }

    static public int numOfDoors()
    {
        return adjl.get(st.position).size();
    }

    static public int takeDoor(int k)
    {
        if(currentlyDead)
            judge(EXCEPTION_CAUGHT); // someone caught the exception?

        if (k <= 0 || k > adjl.get(st.position).size())
            judge(INVALID_DOOR);
        st.steps++;
        if (st.steps > MAX_STEP)
            judge(TOO_MANY_STEPS);
        Edge e = adjl.get(st.position).get(k-1);
        st.position = e.to;
        if (st.position == n)
        {
            judge(AC);
        }
        if (d[st.position])
        {
            currentlyDead = true;
            throw new DeathException();
        }
        return e.otherDoor;
    }
    
    static public void takeNote(String s)
    {
        if (s.length() > MAX_NOTE)
		    judge(NOTE_TOO_LONG);
	    st.notes[st.position] = s;
    }

    
    static public String readNote()
    {
        String s = st.notes[st.position];
        return s != null ? s : "";
    }

    static class DeathException extends RuntimeException {}

    public static void main(String[] args)
    {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        n = in.nextInt();
        adjl = new ArrayList<ArrayList<Edge>>();
        adjl.add(null);
        for (int i = 0; i < n; i++)
            adjl.add(new ArrayList<Edge>());
        d = new boolean[n+1];
        for (int m = in.nextInt(); m > 0; m--)
        {
            int a = in.nextInt();
            int b = in.nextInt();
            adjl.get(a).add(new Edge(b, adjl.get(b).size() + 1));
            adjl.get(b).add(new Edge(a, adjl.get(a).size()));
        }
        for (int m = in.nextInt(); m > 0; m--)
        {
            d[in.nextInt()] = true;
        }

        st = new State();
        for(;;)
        {
            try
            {
                st.position = 1;
                new DND().findExit();
                judge(NOT_FOUND);
            }
            catch (DeathException e)
            {
                currentlyDead = false;
            }
        }
    }
}
