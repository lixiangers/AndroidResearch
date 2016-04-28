package com.leethink.otherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StandardActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);

        findViewById(R.id.bt_single_top).setOnClickListener(this);
        findViewById(R.id.bt_single_task).setOnClickListener(this);
        findViewById(R.id.bt_single_instance).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_single_top:
                startActivity(new Intent(getApplicationContext(), SingleTopActivity.class));
                break;
            case R.id.bt_single_task:
                startActivity(new Intent(getApplicationContext(), SingleTaskActivity.class));
                break;
            case R.id.bt_single_instance:
                startActivity(new Intent(getApplicationContext(), SingleInstanceActivity.class));
                break;
        }
    }
}
