package com.lixiangers.androidresearch.feature;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lixiangers.androidresearch.R;

/**
 * Activity 生命周期
 * 1. 调转的时候，先执行Activity2 onResume 再执行Activity onStop
 * 2. activity show activity 的时候 不会执行任何生命周期方法
 * <p>
 * android:noHistory Activity 栈不保留Activity。测试发现A->B的时候，如果onHistory=true 的时候A会destroy
 */
public class LifeCycleActivity1 extends Activity implements View.OnClickListener {

    public static final String TAG = "LifeCycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_1);
        Log.d(TAG, "LifeCycleActivity1:OnCreate");

        findViewById(R.id.bt_go_to_life_cycle_2).setOnClickListener(this);
        findViewById(R.id.bt_show_dialog).setOnClickListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "LifeCycleActivity1:onRestart");

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "LifeCycleActivity1:onNewIntent");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "LifeCycleActivity1:onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "LifeCycleActivity1:onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "LifeCycleActivity1:onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "LifeCycleActivity1:onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "LifeCycleActivity1:onDestroy");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_go_to_life_cycle_2:
                startActivity(new Intent(getApplication(), LifeCycleActivity2.class));
                break;
            case R.id.bt_show_dialog:
                new AlertDialog.Builder(this).setTitle("Test show dialog").create().show();
                break;
        }
    }
}
