package com.lixiangers.androidresearch.feature;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lixiangers.androidresearch.R;
import com.lixiangers.androidresearch.aidl.ITestService;
import com.lixiangers.androidresearch.aidl.Person;
import com.lixiangers.androidresearch.service.AIDLService;

public class AIDLActivity extends Activity {
    private static final String TAG = "AIDLService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);

        findViewById(R.id.bt_bind_service).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplication(), AIDLService.class);
                        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
                    }
                }
        );

        findViewById(R.id.bt_unbind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(mConnection);
            }
        });
    }

    private IBinder.DeathRecipient mGrayBinderDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() { //进程死亡或者Binder键发生断裂会产生回调
            Log.d(TAG, "binderDied");
        }
    };


    private ITestService mService;
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.d(TAG, "connect service");
            mService = ITestService.Stub.asInterface(service);
            try {
                service.linkToDeath(mGrayBinderDeathRecipient, 0);
                Person person = mService.getPerson();
                Toast.makeText(getApplicationContext(), "Person:" + person.getName(), Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }


        public void onServiceDisconnected(ComponentName className) {
            Log.d(TAG, "disconnect service");
            mService.asBinder().unlinkToDeath(mGrayBinderDeathRecipient, 0);
            mService = null;
        }
    };

    @Override
    protected void onDestroy() {
        try {
            unbindService(mConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
