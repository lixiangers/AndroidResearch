package com.lixiangers.androidresearch.feature;

public class NDKUtil {
    public static native String getStringFormC();
    public static native String test();
    static {
        System.loadLibrary("NdkJniDemo");
    }
}
