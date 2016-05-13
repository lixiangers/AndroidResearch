package com.lixiangers.androidresearch.feature;

public class NDKUtil {
    public static native String getStringFormC();

    static {
        System.loadLibrary("NdkJniDemo");
    }
}
