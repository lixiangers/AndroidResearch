package com.lixiangers.androidresearch;

import android.app.Activity;

public class DemoDetails {
    public int titleId;
    public int descriptionId;
    public final Class<? extends Activity> activityClass;

    public DemoDetails(int titleId, int descriptionId,
                       Class<? extends Activity> activityClass) {
        super();
        this.titleId = titleId;
        this.descriptionId = descriptionId;
        this.activityClass = activityClass;
    }
}
