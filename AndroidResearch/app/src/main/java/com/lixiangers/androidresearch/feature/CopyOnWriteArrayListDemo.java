package com.lixiangers.androidresearch.feature;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ArrayList 当多个线程同时对同一个ArrayList 读写的时候，会抛出java.util.ConcurrentModificationException
 * CopyOnWriteArrayList 在进行写操作的时候add,remove,set等操作的时候采用lock,并且是重新copy了array，在新的array基础上进行修改，
 * CopyOnWriteArrayList 然后把引用重新执行新的array，读操作还是在以前的array上进行。保证不会抛出ConcurrentModificationException
 * 写操作是加锁，并且对整个list的copy操作时相当耗时的，过多的写操作不推荐使用该存储结构，适合使用在读操作远远大于写操作的场景里，比如缓存。
 */
public class CopyOnWriteArrayListDemo {
    private boolean isCopyOnWriteArrayList = false;


    public CopyOnWriteArrayListDemo(boolean isCopyOnWriteArrayList) {
        this.isCopyOnWriteArrayList = isCopyOnWriteArrayList;
    }

    /**
     * 读线程
     *
     * @author wangjie
     */
    private static class ReadTask implements Runnable {
        List<String> list;

        public ReadTask(List<String> list) {
            this.list = list;
        }

        public void run() {
            for (String str : list) {
                System.out.println(str);
            }
        }
    }

    /**
     * 写线程
     *
     * @author wangjie
     */
    private static class WriteTask implements Runnable {
        List<String> list;
        int index;

        public WriteTask(List<String> list, int index) {
            this.list = list;
            this.index = index;
        }

        public void run() {
            list.remove(index);
            list.add(index, "write_" + index);
        }
    }

    public void run() {
        List<String> list;
        if (isCopyOnWriteArrayList)
            list = new CopyOnWriteArrayList<String>();
        else
            list = new ArrayList<String>();
        final int NUM = 10;
        for (int i = 0; i < NUM; i++) {
            list.add("main_" + i);
        }
        ExecutorService executorService = Executors.newFixedThreadPool(NUM);
        for (int i = 0; i < NUM; i++) {
            executorService.execute(new ReadTask(list));
            executorService.execute(new WriteTask(list, i));
        }
        executorService.shutdown();
    }
}
