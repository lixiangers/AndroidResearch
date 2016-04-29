package com.lixiangers.androidresearch.feature;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lixiangers.androidresearch.R;

/**
 * 测试各种Launch mode的Task分布
 */
public class TestLauncherModeActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_launch_mode);

        findViewById(R.id.bt_standard).setOnClickListener(this);
        findViewById(R.id.bt_single_top).setOnClickListener(this);
        findViewById(R.id.bt_single_task).setOnClickListener(this);
        findViewById(R.id.bt_single_instance).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        ComponentName comp;
        switch (v.getId()) {
            case R.id.bt_standard:
                intent = new Intent();
                comp = new ComponentName("com.leethink.otherapp", "com.leethink.otherapp.StandardActivity");
                intent.setComponent(comp);
                startActivity(intent);
                break;
            case R.id.bt_single_top:
                intent = new Intent();
                comp = new ComponentName("com.leethink.otherapp", "com.leethink.otherapp.SingleTopActivity");
                intent.setComponent(comp);
                startActivity(intent);
                break;
            case R.id.bt_single_task:
                intent = new Intent();
                comp = new ComponentName("com.leethink.otherapp", "com.leethink.otherapp.SingleTaskActivity");
                intent.setComponent(comp);
                startActivity(intent);
                break;
            case R.id.bt_single_instance:
                intent = new Intent();
                comp = new ComponentName("com.leethink.otherapp", "com.leethink.otherapp.SingleInstanceActivity");
                intent.setComponent(comp);
                startActivity(intent);
                break;
        }
    }
}
