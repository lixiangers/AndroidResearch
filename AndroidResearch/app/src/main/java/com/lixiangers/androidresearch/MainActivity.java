package com.lixiangers.androidresearch;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.lixiangers.androidresearch.Device.OpenNotificationActivity;
import com.lixiangers.androidresearch.Reflection.ReflecationTestActivity;
import com.lixiangers.androidresearch.feature.AIDLActivity;
import com.lixiangers.androidresearch.feature.CopyOnWriteArrayListActivity;
import com.lixiangers.androidresearch.feature.GrayForegroundServiceActivity;
import com.lixiangers.androidresearch.feature.InstanceOfAssignableTest;
import com.lixiangers.androidresearch.feature.LambdaActivity;
import com.lixiangers.androidresearch.feature.NDKTestActivity;
import com.lixiangers.androidresearch.feature.PhoneNumberActivity;
import com.lixiangers.androidresearch.feature.SoftWeakPhantomReference;
import com.lixiangers.androidresearch.feature.TestEmptyViewActivity;
import com.lixiangers.androidresearch.feature.TestLauncherModeActivity;
import com.lixiangers.androidresearch.feature.ThreadLocalTestActivity;
import com.lixiangers.androidresearch.feature.TransientKeyWordActivity;


public class MainActivity extends ListActivity {
    public static String TAG = "AndroidResearch";

    private DemoDetails[] demos = {
            new DemoDetails(R.string.empty, R.string.empty,
                    TestEmptyViewActivity.class),
            new DemoDetails(R.string.openNotification, R.string.openNotification,
                    OpenNotificationActivity.class),
            new DemoDetails(R.string.reflecation, R.string.reflecation,
                    ReflecationTestActivity.class),
            new DemoDetails(R.string.aidl, R.string.aidl,
                    AIDLActivity.class),
            new DemoDetails(R.string.gray_service, R.string.gray_service,
                    GrayForegroundServiceActivity.class),
            new DemoDetails(R.string.phone_number, R.string.phone_number,
                    PhoneNumberActivity.class),
            new DemoDetails(R.string.lambda, R.string.lambda,
                    LambdaActivity.class),
            new DemoDetails(R.string.test_launch, R.string.test_launch,
                    TestLauncherModeActivity.class),
            new DemoDetails(R.string.soft_weak_phantom_reference, R.string.soft_weak_phantom_reference,
                    SoftWeakPhantomReference.class),
            new DemoDetails(R.string.copy_on_write_array_list, R.string.copy_on_write_array_list,
                    CopyOnWriteArrayListActivity.class),
            new DemoDetails(R.string.transient_key_word, R.string.transient_key_word,
                    TransientKeyWordActivity.class),
            new DemoDetails(R.string.thread_local_test, R.string.thread_local_test,
                    ThreadLocalTestActivity.class),
            new DemoDetails(R.string.instance_assignable_from_test, R.string.instance_assignable_from_test,
                    InstanceOfAssignableTest.class),
            new DemoDetails(R.string.ndk_test, R.string.ndk_test,
                    NDKTestActivity.class)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListAdapter adapter = new CustomArrayAdapter(
                this.getApplicationContext(), demos);
        setListAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        testFix();
    }

    private void testFix() {
        Toast.makeText(getApplication(), "before fix", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        DemoDetails demo = (DemoDetails) getListAdapter().getItem(position);
        startActivity(new Intent(this.getApplicationContext(),
                demo.activityClass));
    }
}
