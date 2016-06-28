package com.lixiangers.androidresearch.touchstudy;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.lixiangers.androidresearch.R;

public class StudyTouchActivity extends Activity {
    //事件处理 dispatchTouchEvent--->onInterceptTouchEvent-->onTouchEvent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_touch);
    }

    /**
     * onInterceptTouchEvent  拦截事件
     * dispatchTouchEvent 分派事件
     * onTouchEvent 处理事件
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.printLog(getClass(), "【开发经理】来一任务<%s>:需要{分派}给下一级", ev);
        return super.dispatchTouchEvent(ev);

        //一般不重写该方法
        //返回 true，false 事件都不再向下传递。true：事件由当前的dispatchTouch 消费。 false: 事件由上级onTouchEvent 消费
        //返回 super.dispatchTouchEvent 教给该ViewGroup 的onInterceptTouch 处理(如果Activiy则直接教给子View dispatchTouch处理。View 则教给OnTouchEvent处理)
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean is = true;
        LogUtils.printLog(getClass(), "【开发经理】自己{处理}任务<%s>:连组长都不会，算了，这任务我还是自己来吧！" + is, event);
        return is;
    }


}
