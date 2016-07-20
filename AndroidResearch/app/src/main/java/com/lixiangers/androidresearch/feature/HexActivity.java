package com.lixiangers.androidresearch.feature;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lixiangers.androidresearch.R;

import java.util.Arrays;

public class HexActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        findViewById(R.id.bt_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char[] buf = {0x02, 0x01, 0x03, 0xE8, 0x00, 0x02, 0x02, 0x03, 0xE8, 0x00, 0x02};
                String test = String.valueOf(buf);
                char[] chars = test.toCharArray();
                byte funcationCode = (byte) chars[0];
                char[] contentChar = Arrays.copyOfRange(chars, 1, chars.length);
                Log.d("", "");
            }
        });
    }
}
