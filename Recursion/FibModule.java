package modules;

import utility.FibFunction;

public class FibModule {

    public static long fib(int n) {

        // BASE CASE: TERMINATING CONDITION
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;

        // RECURSIVE CASE: CALLING THE FUNCTION WITHIN ITSELF
        else
            return fib(n - 1) + fib(n - 2);
    }

    public static long ifib(int n) {

        // BASE CASE
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;

        // Save current and previous values
        long fibValue = 1;
        long prevFibValue = 1;

        for (int i = 2; i < n; i++) {
            long temp = fibValue;
            fibValue += prevFibValue;
            prevFibValue = temp;
        }
        return fibValue;
    }

    public static long mfib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            long[] x = new long[n + 1];
            x[0] = 0;
            x[1] = 1;

            return memo(n, x);
        }
    }

    private static long memo(int n, long[] x) {
        if (x[n] == 0) {
            x[n] = memo(n - 1, x) + x[n - 2];
        }

        return x[n];
    }

    public static void print(FibFunction f, int n){

        // PRINTS : count of nth Fibonacci
        System.out.print("------------------------- nth Fibonacci ");
        System.out.println("-----------------------");
        for (int i = 0; i <= n; i++ ) {
            System.out.print(i + "\t");
            System.out.println(f.fib(i) + "\t");
        }
    }

}
