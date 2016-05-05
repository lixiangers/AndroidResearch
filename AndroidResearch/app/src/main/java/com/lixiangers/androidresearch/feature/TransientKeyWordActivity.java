package com.lixiangers.androidresearch.feature;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Transient 关键字的用法
 * 1.被Transient修饰的变量是不能被序列化的（针对继承Serializable有效,其他parcelable Externalizable 是不起作用的（后面两个都需要自己重新方法实现）)）
 * 2.静态的变量是不能被序列化的
 * 3.Transient 不能修饰方法和类
 */
public class TransientKeyWordActivity extends Activity {

    public static final String APATCH_PATH = "/user.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        User user = new User();
        user.setUsername("Alexia");
        user.setPasswd("123456");
        User.serialVersionUID = 123;

        System.out.println("read before Serializable: ");
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPasswd());
        System.out.println("serialVersionUID: " + User.serialVersionUID);

        try {
            ObjectOutputStream os = new ObjectOutputStream(
                    new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath().concat(APATCH_PATH)));
            os.writeObject(user); // 将User对象写进文件
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // 在反序列化之前改变username的值
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(
                    Environment.getExternalStorageDirectory().getAbsolutePath().concat(APATCH_PATH)));
            user = (User) is.readObject(); // 从流中读取User的数据
            is.close();
            User.serialVersionUID = 456;

            System.out.println("\nread after Serializable: ");
            System.out.println("username: " + user.getUsername());
            System.out.println("password: " + user.getPasswd());
            System.out.println("serialVersionUID: " + User.serialVersionUID);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static class User implements Serializable {
        private static long serialVersionUID = 123;

        public String username;
        private transient String passwd;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPasswd() {
            return passwd;
        }

        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }

    }
}
