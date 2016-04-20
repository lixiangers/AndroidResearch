package com.lixiangers.androidresearch.feature;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.lixiangers.androidresearch.R;
import com.lixiangers.androidresearch.view.EmptyView;

import java.util.Random;

public class TestEmptyViewActivity extends Activity {

    private EmptyView mEmptyView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_empty_view);

        mEmptyView = (EmptyView) findViewById(R.id.empty_view);
        mTextView = (TextView) findViewById(R.id.name);
        mEmptyView.bindView(mTextView); // 设置bindView
        mEmptyView.buttonClick(this, "loadData"); // 当button点击时调用哪个方法
        loadData();
    }

    private void loadData() {
        mEmptyView.loading(); // 加载中
        // 2s后出结果
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();
                int res = r.nextInt(2);
                // 失败
                if (res == 0) {
                    mEmptyView.empty(); // 显示失败
                } else {
                    // 成功
                    mEmptyView.success();
                    mTextView.setText("success");
                }
            }
        }, 2000);
    }
}
