package com.example.Lock;
//synchronized reentrant

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁(fair):安装请求锁的线程顺序 依次获取锁（开销大）
 * 非公平锁(unfair):不是保证按照请求锁的线程的顺序获取锁
 * <p>
 * <p>
 * synchronized reentrant 区别
 * 1.synchronized 的性能比reentrant低
 * 2.synchronized 不需要手工释放锁，reentrant需要在手工释放
 * 3.reentrant的用法更丰富，wait，notify等线程控制特性
 * 4.线程在等待一个锁的控制权时候，reentrant lock 可以中断，synchronized 不能中断（即使你去主动中断，也不会中断,容易造成死锁）
 */
public class LockMain {
    static ReentrantLock lock = new ReentrantLock();//默认非公平锁

    public static void main(String[] args) {
//        new Thread(new MyRunner()).start();
//        new Thread(new MyRunner()).start();
//        ILockBuffer ILockBuffer = new SynchronizedILockBuffer();
        ILockBuffer ILockBuffer = new ReentrantILockBuffer();
        WriteThread writeThread = new WriteThread(ILockBuffer);
        writeThread.start();
        ReadThread readThread = new ReadThread(ILockBuffer);
        readThread.start();
        new Thread() {
            @Override
            public void run() {
                super.run();
                long start = System.currentTimeMillis();
                for (; ; ) {
                    if (System.currentTimeMillis()
                            - start > 5000) {
                        System.out.println("不等了，尝试中断");
                        readThread.interrupt();
                        break;
                    }
                }
            }
        }.start();
    }

    public static class MyRunner implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "------->" + i);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
