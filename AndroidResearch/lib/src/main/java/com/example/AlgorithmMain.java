package com.example;

/**
 * 斐波拉契 0,1,1,2,3,5,8,13...
 */
public class AlgorithmMain {
    public static void main(String[] args) {
//        int fibonacci = Fibonacci2(2000);
        int fibonacci = Fibonacci(200);
        System.out.println("Fibonacci:" + fibonacci);
    }

    private static int Fibonacci(int n) {
        if (n < 2)
            return n;
        else
            return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    private static int Fibonacci2(int n) {
        if (n < 2) {
            return n;
        }

        int n1 = 0, n2 = 1, sn = 0;
        for (int i = 0; i < n - 1; i++) {
            sn = n1 + n2;
            n1 = n2;
            n2 = sn;
        }
        return sn;
    }
}
