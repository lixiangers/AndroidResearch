package com.example.Lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Reentrant lock 可以被中断
 */
public class ReentrantILockBuffer implements ILockBuffer {
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void read() throws InterruptedException {
        lock.lockInterruptibly();// 注意这里，可以响应中断
        try {
            System.out.println("从这个buff读数据");
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void write() {
        lock.lock();
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("开始往这个buff写入数据…");
            for (; ; )// 模拟要处理很长时间
            {
                if (System.currentTimeMillis()
                        - startTime > Integer.MAX_VALUE)
                    break;
            }
            System.out.println("终于写完了");
        } finally {
            lock.unlock();
        }
    }
}
