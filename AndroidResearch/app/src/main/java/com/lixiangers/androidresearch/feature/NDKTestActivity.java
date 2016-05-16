package com.lixiangers.androidresearch.feature;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.lixiangers.androidresearch.R;

public class NDKTestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk);

        findViewById(R.id.bt_test).setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), NDKUtil.getStringFormC(), Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.bt_c_call_java_1).setOnClickListener(v -> {
            new NDKUtil().cCallFromMethod1();
        });

        findViewById(R.id.bt_c_call_java_2).setOnClickListener(v -> {
            new NDKUtil().cCallFromMethod2();
        });

        findViewById(R.id.bt_c_call_java_3).setOnClickListener(v -> {
            new NDKUtil().cCallFromMethod3();
        });
    }
}
