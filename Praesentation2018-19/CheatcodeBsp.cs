using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IOIPraesentationGrader
{
    class Program
    {
        static string GuessCheatcode(int N)
        {

            //Dummy Code
            if (PressButtons("ABXY") > 1)
                return "ABX";
            else
                return "BXY";
        }










        #region GRADER



        static int PressButtons(string buttons)
        {
            return _Pb(buttons);
        }

        //obfuscation is intentional!
        private class GraderException : Exception
        {
            public GraderException(string message) : base(message)
            {}
        }

        delegate int _pbD(string buttons);

        private static _pbD _Pb;

        static void Main(string[] args)
        {
            Console.Write("Cheatcode: ");
            string __cheatcode = Console.ReadLine();
            if (__cheatcode.Any(c => c != 'A' && c != 'B' && c != 'X' && c != 'Y'))
                throw new GraderException("The Cheatcode contains invalid Characters");
            int __presses = 0;
            _Pb = buttons =>
            {
                if (buttons.Length > __cheatcode.Length * 4)
                {
                    throw new GraderException("The length of the Cheatcode exceeds 4N");
                }
                if (buttons.Any(c => c != 'A' && c != 'B' && c != 'X' && c != 'Y'))
                {
                    throw new GraderException("The pressed buttons contain invalid Cheatcode Characters!");
                }
                __presses++;
                int cheatcodeIndex = 0;
                int result = 0;
                for (int i = 0; i < buttons.Length; i++)
                {
                    if (buttons[i] == __cheatcode[cheatcodeIndex])
                    {
                        cheatcodeIndex++;
                    }
                    else
                    {
                        cheatcodeIndex = 0;
                    }
                    if (cheatcodeIndex == __cheatcode.Length)
                    {
                        return cheatcodeIndex;
                    }
                    result = Math.Max(cheatcodeIndex, result);
                }
                return result;
            };
            string guess = GuessCheatcode(__cheatcode.Length);
            if (__cheatcode != guess)
            {
                throw new GraderException($"Wrong guess. Expected: {__cheatcode}; Got:  {guess}");
            }
            if (__presses < __cheatcode.Length)
            {
                throw new GraderException("Cheating detected!");
            }
            Console.WriteLine("Successfully guessed Cheatcode!");
            Console.WriteLine("Needed guesses: {0}", __presses);
            if (__presses > 4 * __cheatcode.Length)
            {
                Console.WriteLine("Points: 10");
            }
            else if (__presses > 2 * __cheatcode.Length + 1)
            {
                Console.WriteLine("Points: 30");
            }
            else if (__presses >= 2 * __cheatcode.Length)
            {
                Console.WriteLine("Points: 50");
            }
            else if (__presses > __cheatcode.Length + 2)
            {
                Console.WriteLine("Points: 70");
            }
            else
            {
                Console.WriteLine("Points: 100!");
            }
            Console.WriteLine("Press any Button to continue...");
            Console.ReadKey();
        }


        #endregion GRADER

    }
}
