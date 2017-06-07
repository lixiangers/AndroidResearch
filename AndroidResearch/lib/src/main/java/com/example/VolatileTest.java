package com.example;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 参考资料：http://www.importnew.com/18126.html
 * <p>
 * 可见性：多个线程访问同一个变量的时候，一个线程修改了变量的值，会立即保存到主内存中，其他线程能够立即
 * 看到修改的值
 * 原子性：一个操作或者多个操作，要么全部执行并且不会被中断，要么都不执行。
 * 有序性：程序的执行时按照代码的顺序执行（java会对代码进行优化，可能会改变代码的执行顺序）
 * <p>
 * <p>
 * <p>
 * <p>
 * Volatile:
 * 1.Volatile关键字会强制将修改的值立即写入主存
 * 2.当线程2修改Volatile修饰的变量的时候，会是线程1的工作内存中缓存变量的失效（
 * 反应到硬件层的话，即使CPU的L1或者L2缓存中对应的缓存行无效）
 * 3.线程1的工作内存缓存的变量无效时候，就需要到主内存中读取最新的值
 * 4.保证一定的顺序行（对volatile变量进行读写的时候时，它之前的代码肯定已经执行，它之后的代码肯定还没有执行）
 * //x、y为非volatile变量
 * //flag为volatile变量
 * <p>
 * x = 2;        //语句1
 * y = 0;        //语句2
 * flag = true;  //语句3
 * x = 4;         //语句4
 * y = -1;       //语句5
 * 语句1,2肯定会在语句3之前执行，但是语句1,语句2的顺序无法保证。
 * 语句4,5肯定会在语句3之后执行，但是语句4,语句5的顺序无法保证
 */
public class VolatileTest {

    public static void main(String[] args) {
//        IncreaseWithoutVolatile volatileTest = new IncreaseWithoutVolatile();
//        IncreaseWithVolatile volatileTest = new IncreaseWithVolatile();
//        IncreaseWithSynchronized volatileTest = new IncreaseWithSynchronized();
//        IncreaseWithLock volatileTest = new IncreaseWithLock();
        IncreaseWithAtomic volatileTest = new IncreaseWithAtomic();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        volatileTest.increase();
                    }
                }
            }.start();
        }

        while (Thread.activeCount() > 2) {
            System.out.println("Tread Count:" + Thread.activeCount());
            Thread.yield();
        }

        System.out.println("Volatile test count:" + volatileTest.count + "  cost time:" + (System.currentTimeMillis() - startTime));
    }

    public static class IncreaseWithoutVolatile {
        /**
         * 多个线程对count进行修改，不能保证可见性，原子性，所以计算结果肯定是错的
         */
        public int count = 0;

        public void increase() {
            count++;
        }
    }

    public static class IncreaseWithVolatile {
        /**
         * 虽然count 有volatile修饰，可以保证可见性
         * 但是++操作不是原子性，可以分为读取主内存中count值，修改count值，最新值保存到主内存中三个步骤
         * 例如：线程1读取到了count的最新值，此时线程1阻塞。线程2开始执行，读取最新的count值，进行++，然后
         * 写入11到主内存中。此时线程1进行执行，因为已经读取到了count值，接着执行++，然后把11写到主内存中。
         * 两个线程执行完成，值都是11，造成了脏数据
         */

        public volatile int count = 0;

        public void increase() {
            count++;
        }
    }

    public static class IncreaseWithSynchronized {
        /**
         * synchronized 保存每次只有一个线程执行increase 方法，并且执行完后会把最新的值
         * 写入到主内存中
         */

        public int count = 0;

        public synchronized void increase() {
            count++;
        }
    }

    public static class IncreaseWithLock {
        /**
         * ReentrantLock 保存每次只有一个线程执行increase 方法，并且执行完后会把最新的值
         * 写入到主内存中
         */

        public int count = 0;
        private ReentrantLock lock = new ReentrantLock();

        public synchronized void increase() {
            try {
                lock.lock();
                count++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static class IncreaseWithAtomic {
        /**
         * java.util.concurrent.atomic提供了一些原子的操作类，即对基本数据类型的自增，
         * 自减，加法，减法操作进行了封装，保证了这些操作的原子性。atomic利用CAS(Compare And Swap)来实现
         * 原子性操作
         * <p>
         * count的读取和修改是一起执行，不会被中断
         * <p>
         * CAS 是利用CPU 的硬件命令(CMPXCHG命令)保证原子性
         */

        public AtomicInteger count = new AtomicInteger();

        public synchronized void increase() {
            count.getAndIncrement();
        }
    }
}
