package com.lixiangers.androidresearch;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;
import com.lixiangers.androidresearch.feature.VerifyApplicationCannotSaveData;

import java.io.IOException;

public class MyApplication extends Application {
    //    private PatchManager patchManager;
    private static final String APATCH_PATH = "/out.apatch";
    private PatchManager patchManager;
    public String name;
    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(VerifyApplicationCannotSaveData.TestCompanion.getTAG(), "MyApplication onCreate:"+this.toString());

        instance=this;
        patchManager = new PatchManager(this);
        patchManager.init("2.0");
        // load patch
        patchManager.loadPatch();
//         add patch at runtime
        try {
            // .apatch file path
            String patchFileString = Environment.getExternalStorageDirectory().getAbsolutePath().concat(APATCH_PATH);
            Log.e("TAG", "patch file is " + patchFileString);
            patchManager.addPatch(patchFileString);
        } catch (IOException e) {
            Log.e("TAG", e.getMessage());
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
