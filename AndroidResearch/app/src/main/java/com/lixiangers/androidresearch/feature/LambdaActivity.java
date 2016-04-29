package com.lixiangers.androidresearch.feature;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.internal.util.Predicate;
import com.lixiangers.androidresearch.R;

/**
 *  retrolambda Lambda 演示
 */
public class LambdaActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);
        new Thread(
                () -> {
                    System.out.println("this is:" + this.toString());
                }
        ).start();

        Predicate<String> p = (String s) -> s == null;

        execute(() -> Toast.makeText(getApplicationContext(), "this is:" + this.toString(), Toast.LENGTH_SHORT).show());

        findViewById(R.id.bt_test).setOnClickListener((v -> Toast.makeText(getApplicationContext(), v.getId() + "", Toast.LENGTH_SHORT).show()));
    }

    public static void execute(WorkerInterfaceTest worker) {
        worker.doSomeWork();
    }
}
