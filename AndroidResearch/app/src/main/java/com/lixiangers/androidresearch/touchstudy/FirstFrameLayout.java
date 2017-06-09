package com.lixiangers.androidresearch.touchstudy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class FirstFrameLayout extends FrameLayout {


    public FirstFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.printLog(getClass(), "【开发组长】来一任务<%s>:需要{分派}给下一级", ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean is = false;
        LogUtils.printLog(getClass(), "【开发组长】自己分派一任务<%s>:需要{拦截}吗？" + is, ev);
        return is;

        //true：拦截事件，由onTouchEvent处理
        //false: 不拦截 教给子View dispatchTouchEvent 处理
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean is = true;
        LogUtils.printLog(getClass(), "【开发组长】自己{处理}任务<%s>:这任务怎么这么难，底下人都不会，还是自己干吧。可是任务能解决嘛？" + is, event);
        return is;

        //false:事件从当前View向上传递，并且都由上层的OnTouch来接收。直到有返回true或者上传到了最上一层View。
        //true:则由该view消费。ACTION_UP ACTION_MOVE则都由它处理
    }

}
