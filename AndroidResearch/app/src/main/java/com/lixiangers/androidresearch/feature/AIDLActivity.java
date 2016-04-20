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
    }


    private ITestService mService;
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.d(TAG, "connect service");
            mService = ITestService.Stub.asInterface(service);
            try {
                Person person = mService.getPerson();
                Toast.makeText(getApplicationContext(), "Person:" + person.getName(), Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }


        public void onServiceDisconnected(ComponentName className) {
            Log.d(TAG, "disconnect service");
            mService = null;
        }
    };

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }
}
