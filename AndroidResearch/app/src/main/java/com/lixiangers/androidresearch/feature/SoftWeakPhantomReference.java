package com.lixiangers.androidresearch.feature;

import android.app.Activity;
import android.os.Bundle;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

public class SoftWeakPhantomReference extends Activity {
    private ReferenceQueue softQueue = new ReferenceQueue();
    private ReferenceQueue weakQueue = new ReferenceQueue();
    private ReferenceQueue phantomQueue = new ReferenceQueue();

    public void checkSoftQueue() {
        Reference inq;
        while ((inq = softQueue.poll()) != null) {
            System.out.println("In queue: " + inq + " : " + inq.get());
        }
    }

    public void checkWeakQueue() {
        Reference inq;
        while ((inq = weakQueue.poll()) != null) {
            System.out.println("In queue: " + inq + " : " + inq.get());
        }
    }

    Grocery grocery1;
    Grocery grocery2;
    Grocery grocery3;
    Grocery grocery4;
    Grocery grocery5;
    Grocery grocery6;
    Grocery grocery7;
    Grocery grocery8;
    Grocery grocery9;
    Grocery grocery10;


    public void checkPhantomQueue() {
        Reference inq;
        while ((inq = phantomQueue.poll()) != null)
            System.out.println("In queue: " + inq + " : " + inq.get());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int size = 10;
        // 创建10个Grocery对象以及10个软引用
//        Set sa = new HashSet();
//        for (int i = 0; i < size; i++) {
//            SoftReference ref = new SoftReference(new Grocery("soft" + i), softQueue);
//            System.out.println("Just created soft: " + ref.get());
//            sa.add(ref);
//        }
//        System.gc();

        System.out.println("---------------------------------------------------");
        // 创建10个Grocery对象以及10个弱引用
        Set wa = new HashSet();
        addLocalWeakReference(size, wa);//添加局部变量的WeakReference
//        addOverallWeakReference(wa);//添加全局变量的WeakReference 已区别WeakReference 回收的机制
        System.gc();

        System.out.println("---------------------------------------------------");
        // 创建10个Grocery对象以及10个虚引用
        Set pa = new HashSet();
        for (int j = 0; j < size; j++) {
            PhantomReference ref = new PhantomReference(new Grocery("Phantom " + j), phantomQueue);
            System.out.println("Just created Phantom: " + ref.get());
            pa.add(ref);
        }
        System.gc();

        checkSoftQueue();
        checkWeakQueue();
        checkPhantomQueue();
    }

    private void addLocalWeakReference(int size, Set wa) {
        for (int i = 0; i < size; i++) {
            WeakReference ref = new WeakReference(new Grocery("weak " + i), weakQueue);
            System.out.println("Just created weak: " + ref.get());
            wa.add(ref);
        }
    }

    private void addOverallWeakReference(Set wa) {
        int i = 0;
        grocery1 = new Grocery("weak " + i++);
        grocery2 = new Grocery("weak " + i++);
        grocery3 = new Grocery("weak " + i++);
        grocery4 = new Grocery("weak " + i++);
        grocery5 = new Grocery("weak " + i++);
        grocery6 = new Grocery("weak " + i++);
        grocery7 = new Grocery("weak " + i++);
        grocery8 = new Grocery("weak " + i++);
        grocery9 = new Grocery("weak " + i++);
        grocery10 = new Grocery("weak " + i++);
        WeakReference ref1 = new WeakReference(grocery1, weakQueue);
        System.out.println("Just created weak: " + ref1.get());

        WeakReference ref2 = new WeakReference(grocery2, weakQueue);
        System.out.println("Just created weak: " + ref2.get());

        WeakReference ref3 = new WeakReference(grocery3, weakQueue);
        System.out.println("Just created weak: " + ref3.get());

        WeakReference ref4 = new WeakReference(grocery4, weakQueue);
        System.out.println("Just created weak: " + ref4.get());

        WeakReference ref5 = new WeakReference(grocery5, weakQueue);
        System.out.println("Just created weak: " + ref5.get());

        WeakReference ref6 = new WeakReference(grocery6, weakQueue);
        System.out.println("Just created weak: " + ref6.get());

        WeakReference ref7 = new WeakReference(grocery7, weakQueue);
        System.out.println("Just created weak: " + ref7.get());

        WeakReference ref8 = new WeakReference(grocery8, weakQueue);
        System.out.println("Just created weak: " + ref8.get());

        WeakReference ref9 = new WeakReference(grocery9, weakQueue);
        System.out.println("Just created weak: " + ref9.get());

        WeakReference ref10 = new WeakReference(grocery10, weakQueue);
        System.out.println("Just created weak: " + ref10.get());
        wa.add(ref1);
        wa.add(ref2);
        wa.add(ref3);
        wa.add(ref4);
        wa.add(ref5);
        wa.add(ref6);
        wa.add(ref7);
        wa.add(ref8);
        wa.add(ref9);
        wa.add(ref10);
    }
}
