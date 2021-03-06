package com.lixiangers.androidresearch.Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class LoadMethodEx {
    public Object Load(String cName, String MethodName, Object[] params) {

        Object retObject = null;

        try {
            // 加载指定的类
            Class cls = Class.forName(cName);    // 获取Class类的对象的方法之二

            // 利用newInstance()方法，获取构造方法的实例
            // Class的newInstance方法只提供默认无参构造实例
            // Constructor的newInstance方法提供带参的构造实例
            Constructor ct = cls.getConstructor();
            Object obj = ct.newInstance();
            //Object obj = cls.newInstance();

            // 根据方法名获取指定方法的参数类型列表
            Class paramTypes[] = this.getParamTypes(cls, MethodName);

            // 获取指定方法
            Method meth = cls.getDeclaredMethod(MethodName, paramTypes);
            meth.setAccessible(true);

            // 调用指定的方法并获取返回值为Object类型
            retObject = meth.invoke(obj, params);

        } catch (Exception e) {
            System.err.println(e);
        }

        return retObject;
    }

    /**
     * 获取参数类型，返回值保存在Class[]中
     */
    private Class[] getParamTypes(Class cls, String mName) {
        Class[] cs = null;

        /*
         * Note: 由于我们一般通过反射机制调用的方法，是非public方法
         * 所以在此处使用了getDeclaredMethods()方法
         */
        Method[] mtd = cls.getDeclaredMethods();
        for (int i = 0; i < mtd.length; i++) {
            if (!mtd[i].getName().equals(mName)) {    // 不是我们需要的参数，则进入下一次循环
                continue;
            }

            cs = mtd[i].getParameterTypes();
        }
        return cs;
    }
}
