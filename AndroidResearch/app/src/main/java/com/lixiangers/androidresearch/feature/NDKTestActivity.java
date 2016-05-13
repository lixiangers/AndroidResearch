package com.lixiangers.androidresearch.feature;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.lixiangers.androidresearch.R;

public class NDKTestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        findViewById(R.id.bt_test).setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), NDKUtil.getStringFormC(), Toast.LENGTH_SHORT).show();
        });
    }
}
