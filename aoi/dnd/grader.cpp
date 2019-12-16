#include "dnd.h"
#include <cstdio>
#include <cstdlib>
#include <vector>
#include <utility>
#include <csetjmp>

using namespace std;

static const int MAX_N = 1005;
static const int MAX_NOTE = 42;
static const int MAX_STEP = 4000000;

#ifdef LOG
	#define log(...) fprintf(stderr,__VA_ARGS__)
#else
	#define log(...)
#endif

static int n;
static vector<pair<int,int>> adjl[MAX_N];
static bool d[MAX_N];
static jmp_buf restart;
static int p,steps;
static string note[MAX_N];

static void raise(string e)
{
	fprintf(stderr,"%s\n",e.c_str());
	exit(1);
}

int numOfDoors() { return adjl[p].size(); }

int takeDoor(int k)
{
	if (k<=0||k>adjl[p].size())
		raise("Diese Tuer gibt es nicht");
	steps++;
	if (steps>MAX_STEP)
		raise("Zu viele Schritte");
	auto e=adjl[p][k-1];
	log("Von %d zu %d\n",p,e.first);
	p=e.first;
	if (p==n)
	{
		printf("Du bist entkommen, %d Schritte\n",steps);
		exit(0);
	}
	if (d[p])
	{
		log("Tod\n");
		longjmp(restart,1);
	}
	log("Bei %d\n",p);
	return e.second;
}

void takeNote(string s)
{
	if (s.size()>MAX_NOTE)
		raise("Notiz zu lang");
	note[p]=s;
}

string readNote() { return note[p]; }

int main()
{
	int m,a,b;
	scanf("%d %d",&n,&m);
	while (m--)
	{
		scanf("%d %d",&a,&b);
		adjl[a].push_back(make_pair(b,adjl[b].size()+1));
		adjl[b].push_back(make_pair(a,adjl[a].size()));
	}
	scanf("%d",&m);
	while (m--)
	{
		scanf("%d",&a);
		d[a]=true;
	}
	setjmp(restart);
	p=1;
	log("Bei 1\n");
	findExit();
	raise("Ausgang nicht gefunden");
	return 0;
}
