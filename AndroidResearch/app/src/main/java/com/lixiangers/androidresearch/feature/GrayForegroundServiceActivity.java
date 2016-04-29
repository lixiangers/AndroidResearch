package com.lixiangers.androidresearch.feature;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.lixiangers.androidresearch.R;
import com.lixiangers.androidresearch.service.AIDLService;

/**
 * 通过灰色技术实现不在通知栏显示的前台Service
 */
public class GrayForegroundServiceActivity extends Activity {

    private ServiceConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gray_service);

        findViewById(R.id.bt_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getApplicationContext().startService(new Intent(getApplication(), AIDLService.class));
            }
        });

        findViewById(R.id.bt_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getApplicationContext().stopService(new Intent(getApplication(), AIDLService.class));
            }
        });

        findViewById(R.id.bt_bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conn = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {

                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                };
                bindService(new Intent(getApplication(), AIDLService.class), conn, Context.BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.bt_unbind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);
            }
        });
    }
}
