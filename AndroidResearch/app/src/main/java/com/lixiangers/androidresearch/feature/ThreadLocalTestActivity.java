package com.lixiangers.androidresearch.feature;

import android.app.Activity;
import android.os.Bundle;

import com.lixiangers.androidresearch.R;

/**
 * ThreadLocal 变量 为每个拥有它的线程 都单独有变量副本，各个线程操作的都是变量的副本，不回存在线程冲突的问题
 * 相比使用lock进行线程并发同步，ThreadLocal使用的空间解决并发的问题。
 * 每个线程的副本都是放在Thread的threadLocals中，当线程完成后，副本的也会进行回收，不用担心内存泄漏问题
 */
public class ThreadLocalTestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_common);
        findViewById(R.id.bt_test).setOnClickListener(v -> {
            SequenceNumber sequenceNumber = new SequenceNumber();
            new TestThread(sequenceNumber).start();
            new TestThread(sequenceNumber).start();
            new TestThread(sequenceNumber).start();
            new TestThread(sequenceNumber).start();
            new TestThread(sequenceNumber).start();
        });
    }

    private static class SequenceNumber {
        private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return 0;
            }
        };

        public int getNextNum() {
            threadLocal.set(threadLocal.get() + 1);
            return threadLocal.get();
        }
    }

    private static class TestThread extends Thread {
        private SequenceNumber sequenceNumber;

        public TestThread(SequenceNumber sequenceNumber) {
            this.sequenceNumber = sequenceNumber;
        }

        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 5; i++) {
                System.out.println("thread[" + Thread.currentThread().getName() +
                        "] sn[" + sequenceNumber.getNextNum() + "]");
            }
        }
    }
}
