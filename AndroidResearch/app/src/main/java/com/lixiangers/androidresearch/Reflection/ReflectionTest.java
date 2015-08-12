package com.lixiangers.androidresearch.Reflection;

import android.view.View;

import java.io.Serializable;

public class ReflectionTest extends Object implements View.OnClickListener, Serializable {

    // 成员变量
    private int bInt;
    public Integer bInteger = new Integer(4);
    public String strB = "crazypebble";
    private String strA;

    // 构造函数
    public ReflectionTest() {

    }

    protected ReflectionTest(int id, String name) {

    }

    // 成员方法
    private int abc(int id, String name) {
        System.out.println("crazypebble ---> " + id + "-" + name);
        return 123;
    }

    protected static void edf() {

    }

    @Override
    public void onClick(View view) {

    }
}