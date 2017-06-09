package com.example;

import com.example.Lock.MyCount;
import com.example.Lock.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * write lock 和 read lock互斥;
 * write lock 和 write lock互斥
 * read lock 和 read lock不互斥(提高了读取的性能)
 */
public class ReaderWriterLockMain {
    public static void main(String[] args) {
        //创建并发访问的账户
        MyCount myCount = new MyCount("95599200901215522", 10000);
        //创建一个锁对象
        ReadWriteLock lock = new ReentrantReadWriteLock(true);
        //创建一个线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        //创建一些并发访问用户，一个信用卡，存的存，取的取，好热闹啊
        User u0 = new User("lixiang里想", myCount, 1000, lock, false);
        User u5 = new User("张三他爹", myCount, 0, lock, true);
        User u1 = new User("张三", myCount, -4000, lock, false);
        User u2 = new User("张三他爹", myCount, 6000, lock, false);
        User u3 = new User("张三他弟", myCount, -8000, lock, false);
        User u4 = new User("张三", myCount, 800, lock, false);
        //在线程池中执行各个用户的操作
        pool.execute(u0);
        pool.execute(u1);
        pool.execute(u2);
        pool.execute(u3);
        pool.execute(u4);
        pool.execute(u5);

        pool.shutdown();
    }
}
