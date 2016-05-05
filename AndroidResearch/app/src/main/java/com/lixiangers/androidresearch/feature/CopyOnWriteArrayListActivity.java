package com.lixiangers.androidresearch.feature;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.lixiangers.androidresearch.R;

public class CopyOnWriteArrayListActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_on_write_array_list);

        findViewById(R.id.bt_test_array_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CopyOnWriteArrayListDemo(false).run();
            }
        });

        findViewById(R.id.bt_test_copy_on_write_array_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CopyOnWriteArrayListDemo(true).run();
            }
        });
    }
}
