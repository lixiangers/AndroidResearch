package com.example;

public class TryCatchMain {
    public static void main(String[] args) {
        System.out.println("test result:" + test());
    }

    private static int test() {
        try {
            int i = 1 / 1;
            return i;
        } catch (Exception ex) {
            System.out.println("catch exception");
            return -1;
        } finally {
            System.out.println("finally");
        }
    }
}
