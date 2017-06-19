package com.lixiangers.androidresearch.feature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lixiangers.androidresearch.R;

public class LifeCycleActivity2 extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_2);
        Log.d(LifeCycleActivity1.TAG, "LifeCycleActivity2:onCreate");

        findViewById(R.id.bt_go_to_life_cycle_1).setOnClickListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LifeCycleActivity1.TAG, "LifeCycleActivity2:onRestart");

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(LifeCycleActivity1.TAG, "LifeCycleActivity2:onNewIntent");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LifeCycleActivity1.TAG, "LifeCycleActivity2:onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LifeCycleActivity1.TAG, "LifeCycleActivity2:onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LifeCycleActivity1.TAG, "LifeCycleActivity2:onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LifeCycleActivity1.TAG, "LifeCycleActivity2:onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LifeCycleActivity1.TAG, "LifeCycleActivity2:onDestroy");

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), LifeCycleActivity1.class);
        //通过FLAG 模拟Single Task launch mode。如果不加Intent.FLAG_ACTIVITY_SINGLE_TOP。会被重新实例化Activity
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}
