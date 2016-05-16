package com.lixiangers.androidresearch.feature;

public class NDKUtil {
    public static native String getStringFormC();

    public native void cCallFromMethod1();

    public native void cCallFromMethod2();

    public native void cCallFromMethod3();

    public native void cCallFromMethod4();

    static {
        System.loadLibrary("NdkJniDemo");
    }


    //C调用java中空方法
    public void Method1() {
        System.out.println("Method1: hello from java");
    }

    //C调用java中的带两个int参数的方法
    public int Method2(int x, int y) {
        int sum = x + y;
        System.out.println("Method2: x+y=" + sum);
        return sum;
    }

    //C调用java中参数为string的方法
    public void Method3(String s) {
        System.out.println("Method3:" + s);
    }

    //C调用static方法
    public static String Method4(String s1, String s2) {
        String result = "";
        result = s1 + s2 + s2;
        System.out.println("Method4: result=" + result);
        return result;
    }
}
