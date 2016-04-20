package com.lixiangers.androidresearch;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.lixiangers.androidresearch.Device.OpenNotificationActivity;
import com.lixiangers.androidresearch.Reflection.ReflecationTestActivity;
import com.lixiangers.androidresearch.feature.TestEmptyViewActivity;


public class MainActivity extends ListActivity {
    public static String TAG = "AndroidResearch";

    private DemoDetails[] demos = {
            new DemoDetails(R.string.empty, R.string.empty,
                    TestEmptyViewActivity.class),
            new DemoDetails(R.string.openNotification, R.string.openNotification,
                    OpenNotificationActivity.class),
            new DemoDetails(R.string.reflecation, R.string.reflecation,
                    ReflecationTestActivity.class)
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
    protected void onListItemClick(ListView l, View v, int position, long id) {
        DemoDetails demo = (DemoDetails) getListAdapter().getItem(position);
        startActivity(new Intent(this.getApplicationContext(),
                demo.activityClass));
    }
}
