package com.lixiangers.androidresearch.Device;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OpenNotificationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = new Button(getApplicationContext());
        setContentView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeviceUtils.OpenNotify(getApplicationContext());
            }
        });
    }
}
