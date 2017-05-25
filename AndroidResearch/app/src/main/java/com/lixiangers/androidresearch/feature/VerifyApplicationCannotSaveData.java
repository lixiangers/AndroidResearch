package com.lixiangers.androidresearch.feature;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.lixiangers.androidresearch.MyApplication;
import com.lixiangers.androidresearch.R;

/**
 * 验证Application 不能存放全局的数据。因为App长期在后台会有被kill。如果
 * 被kill掉后我们再从最近运行列表中启动当前app，此时Application会重新实例化，而启动的activity
 * 会是kill时候的activity。
 * 如果这个activity 保持有对Application的变量的使用，会出现nullPointerException
 * 注意>>>>>>>如果我们是从Launcher中重新启动App，App的启动流程是正常的，如果是用最近任务列表中
 * 启动app，app会重kill之前的activity启动。（国内手机Rom可能对这个机制修改，效果会不一样。在Android 原始4.4.4上验证是会出现的）
 *
 * 同理>>>>>single instance ,static instance 都会遇到同样的问题。所以重要的数据最好持久化，加强nullPointerException的检查
 */

public class VerifyApplicationCannotSaveData extends Activity {

    public static final String TAG = "VerifyApplication";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(VerifyApplicationCannotSaveData.TAG, "VerifyApplicationCannotSaveData onCreate" + this.toString());

        setContentView(R.layout.activity_verify_application_can_not_save_data);
        ((TextView) findViewById(R.id.tv_name)).setText(MyApplication.getInstance().name.toUpperCase());
    }
}
