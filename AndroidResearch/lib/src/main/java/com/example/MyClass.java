package com.example;

/**
 * 算法
 */
public class MyClass {
    public static void main(String[] arg) {
        String testString = "abcdefgh";
        System.out.print("reverse data:" + reverse(testString));

        int originArray[] = new int[]{1, 2, 3, 4, 5, 6, 1, 3};
        System.out.print("after remove Repeat:" + removeRepeat(originArray));
    }

    private static int[] removeRepeat(int[] originArray) {
        return null;
    }

    /**
     * 字符串的反转
     *
     * @param testString
     * @return
     */
    private static String reverse(String testString) {
        char[] array = testString.toCharArray();
        char temp;
        int length = testString.length();
        for (int i = 0; i < length / 2; i++) {
            temp = testString.charAt(i);
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = temp;
        }
        return String.valueOf(array);
    }

}
