package com.example.Lock;

/**
 * Synchronized lock 不能被中断
 */
public class SynchronizedILockBuffer implements ILockBuffer {
    private Object lock;

    public SynchronizedILockBuffer() {
        lock = this;
    }

    @Override
    public void read() {
        synchronized (lock) {
            System.out.println("从这个buff读数据");
        }
    }

    @Override
    public void write() {
        synchronized (lock) {
            long startTime = System.currentTimeMillis();
            System.out.println("开始往这个buff写入数据…");
            for (; ; )// 模拟要处理很长时间
            {
                if (System.currentTimeMillis()
                        - startTime > Integer.MAX_VALUE)
                    break;
            }
            System.out.println("终于写完了");
        }
    }
}
