package com.lixiangers.androidresearch;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class ReflectionTestMain {
    public static void main(String[] args) {
//        double lat = 39.928774689792;
//        double lng = 116.46697203776;
//        LatLng latLng = MapUtil.bdToGD(lat, lng);
//        Log.d("MapTest", "lat:" + latLng.latitude + " lng:" + latLng.longitude);


        ReflectionTest r = new ReflectionTest();
        Class temp = r.getClass();

        String className = temp.getName();        // 获取指定类的类名

        //        ------------------------------------------构造方法------------------------------------------------------------------------

        try {
            Constructor[] theConstructors = temp.getDeclaredConstructors();        // 获取指定类的公有构造方法

            for (Constructor theConstructor : theConstructors) {
                int mod = theConstructor.getModifiers();    // 输出修饰域和方法名称
                System.out.print(Modifier.toString(mod) + " " + className + "(");
                Class[] parameterTypes = theConstructor.getParameterTypes();    // 获取指定构造方法的参数的集合
                for (int j = 0; j < parameterTypes.length; j++) {    // 输出打印参数列表
                    System.out.print(parameterTypes[j].getName());
                    if (parameterTypes.length > j + 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println(")");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //        ------------------------------------------方法------------------------------------------------------------------------
 /*
         * Note: 方法getDeclaredMethods()只能获取到由当前类定义的所有方法，不能获取从父类继承的方法 ,但是可以获取到静态的方法
         *       方法getMethods() 不仅能获取到当前类定义的public方法，也能得到从父类继承和已经实现接口的public方法，不能获取到静态的方法
         * 请查阅开发文档对这两个方法的详细描述。
         */
        Method[] methods = temp.getDeclaredMethods();
//        Method[] methods = temp.getMethods();

        for (Method method : methods) {

            // 打印输出方法的修饰域
            int mod = method.getModifiers();
            System.out.print(Modifier.toString(mod) + " ");

            // 输出方法的返回类型
            System.out.print(method.getReturnType().getName());

            // 获取输出的方法名
            System.out.print(" " + method.getName() + "(");

            // 打印输出方法的参数列表
            Class[] parameterTypes = method.getParameterTypes();
            for (int j = 0; j < parameterTypes.length; j++) {
                System.out.print(parameterTypes[j].getName());
                if (parameterTypes.length > j + 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(")");
        }


        //        ------------------------------------------属性------------------------------------------------------------------------

        try {
            System.out.println("public 属性");
            Field[] fb = temp.getFields();
            for (Field aFb : fb) {

                Class cl = aFb.getType();    // 属性的类型

                int md = aFb.getModifiers();    // 属性的修饰域

                Field f = temp.getField(aFb.getName());    // 属性的值
                f.setAccessible(true);
                Object value = (Object) f.get(r);

                // 判断属性是否被初始化
                if (value == null) {
                    System.out.println(Modifier.toString(md) + " " + cl + " : " + aFb.getName());
                } else {
                    System.out.println(Modifier.toString(md) + " " + cl + " : " + aFb.getName() + " = " + value.toString());
                }
            }

            System.out.println("public & 非public 属性");
            Field[] fa = temp.getDeclaredFields();
            for (Field aFa : fa) {

                Class cl = aFa.getType();    // 属性的类型

                int md = aFa.getModifiers();    // 属性的修饰域

                Field f = temp.getDeclaredField(aFa.getName());    // 属性的值
                f.setAccessible(true);    // Very Important
                Object value = (Object) f.get(r);

                if (value == null) {
                    System.out.println(Modifier.toString(md) + " " + cl + " : " + aFa.getName());
                } else {
                    System.out.println(Modifier.toString(md) + " " + cl + " : " + aFa.getName() + " = " + value.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
