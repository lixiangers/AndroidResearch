package com.lixiangers.androidresearch.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lixiangers.androidresearch.aidl.ITestService;
import com.lixiangers.androidresearch.aidl.Person;

/**
 * 灰色保活，这种保活手段是应用范围最广泛。它是利用系统的漏洞来启动一个前台的Service进程，
 * 与普通的启动方式区别在于，它不会在系统通知栏处出现一个Notification，看起来就如同运行着一个后台Service进程一样。
 * 这样做带来的好处就是，用户无法察觉到你运行着一个前台进程（因为看不到Notification）,但你的进程优先级又是高于普通后台进程的。
 * 那么如何利用系统的漏洞呢，大致的实现思路和代码如下：
 * 思路一：API < 18，启动前台Service时直接传入new Notification()；
 * 思路二：API >= 18，同时启动两个id相同的前台Service，然后再将后启动的Service做stop处理；
 * <p/>
 * <p/>
 * dumpsys activity services PackageName
 * 打印出指定包名的所有进程中的Service信息，看下有没有 isForeground=true 的关键信息。
 * 如果通知栏没有看到属于App的 Notification 且又看到 isForeground=true 则说明了，此App利用了这种灰色保活的手段。微信、QQ、支付宝、陌陌
 */
public class AIDLService extends Service {
    private static final String TAG = "AIDLService";
    public static final int GRAY_SERVICE_ID = 101;

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
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "GrayService->onStartCommand");
        if (Build.VERSION.SDK_INT < 18) {
            startForeground(GRAY_SERVICE_ID, new Notification());//API < 18 ，此方法能有效隐藏Notification上的图标
        } else {
            Intent innerIntent = new Intent(this, GrayInnerService.class);
            startService(innerIntent);
            startForeground(GRAY_SERVICE_ID, new Notification());
        }

        //发送唤醒广播来促使挂掉的UI进程重新启动起来
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent alarmIntent = new Intent();
//        alarmIntent.setAction(WakeReceiver.GRAY_WAKE_ACTION);
//        PendingIntent operation = PendingIntent.getBroadcast(this, WAKE_REQUEST_CODE, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), ALARM_INTERVAL, operation);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log("service on destroy");
        super.onDestroy();
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log("service on unbind");
        return true; //如果在重新bind的时候触发onRebind则返回true
    }

    @Override
    public void onRebind(Intent intent) {
        Log("service on rebind");
        super.onRebind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log("service onBind");
        return mBinder;
    }

    private ITestService.Stub mBinder = new ITestService.Stub() {
        @Override
        public Person getPerson() throws RemoteException {
            return new Person(30, "lixiang", "test Address");
        }
    };


    /**
     * API >= 18 的平台上用的灰色保活手段
     */
    public static class GrayInnerService extends Service {

        @Override
        public void onCreate() {
            Log.i(TAG, "InnerService -> onCreate");
            super.onCreate();
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Log.i(TAG, "InnerService -> onStartCommand");
            startForeground(GRAY_SERVICE_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public IBinder onBind(Intent intent) {
            // TODO: Return the communication channel to the service.
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public void onDestroy() {
            Log.i(TAG, "InnerService -> onDestroy");
            super.onDestroy();
        }
    }

}
