package com.lixiangers.androidresearch.Device;

import android.content.Context;

import java.lang.reflect.Method;

public class DeviceUtils {
    /*
     打开通知栏 需要EXPAND_STATUS_BAR 权限
     */
    public static void OpenNotify(Context context) {
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        try {
            Object service = context.getSystemService("statusbar");
            Class<?> statusbarManager = Class
                    .forName("android.app.StatusBarManager");
            Method expand = null;
            if (service != null) {
                if (currentApiVersion <= 16) {
                    expand = statusbarManager.getMethod("expand");
                } else {
                    expand = statusbarManager
                            .getMethod("expandNotificationsPanel");
                }
                expand.setAccessible(true);
                expand.invoke(service);
            }

        } catch (Exception e) {
        }

    }
}
