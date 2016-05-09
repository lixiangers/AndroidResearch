package com.lixiangers.androidresearch.feature;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.lixiangers.androidresearch.R;

import java.util.ArrayList;
import java.util.List;

/**
 * instanceof 自身实例或子类实例 instanceof 自身类  返回true
 * isInstance  Class类的isInstance(Object obj)方法，obj是被测试的对象，如果obj是调用这个方法的class或接口 的实例，则返回true。这个方法是instanceof运算符的动态等价。
 * Class类的isAssignableFrom(Class cls)方法，如果调用这个方法的class或接口 与 参数cls表示的类或接口相同，或者是参数cls表示的类或接口的父类，则返回true
 */
public class InstanceOfAssignableTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        findViewById(R.id.bt_test).setOnClickListener(v -> {

            String s = new String("Hello");
            System.out.println("s 是 String 的实例:" + s instanceof String);

            System.out.println("s 是 String 的实例:" + String.class.isInstance(s));

            System.out.println("List 是 ArrayList的父类：" + List.class.isAssignableFrom(ArrayList.class));  //true
        });
    }
}
