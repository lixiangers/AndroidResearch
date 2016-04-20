package com.lixiangers.androidresearch.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lixiangers.androidresearch.aidl.ITestService;
import com.lixiangers.androidresearch.aidl.Person;

public class AIDLService extends Service {
    private static final String TAG = "AIDLService";

    private void Log(String str) {
        Log.d(TAG, "------ " + str + "------");
    }


    @Override
    public void onCreate() {
        Log("service create");
    }


    @Override
    public void onStart(Intent intent, int startId) {
        Log("service start id=" + startId);
    }


    @Override
    public void onDestroy() {
        Log("service on destroy");
        super.onDestroy();
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log("service on unbind");
        return super.onUnbind(intent);
    }


    public void onRebind(Intent intent) {
        Log("service on rebind");
        super.onRebind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private ITestService.Stub mBinder = new ITestService.Stub() {
        @Override
        public Person getPerson() throws RemoteException {
            return new Person(30, "lixiang", "test Address");
        }
    };

}
