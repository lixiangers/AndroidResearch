package com.lixiangers.androidresearch.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Exector {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        //1.第一种获取线程结果,直接submit callable,利用返回的Future 获取结果
//        Future<Integer> submit = executor.submit(task);

        //2.第二种获取线程结果的方式(submit 提交是Runnable)
//        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
//        executor.submit(futureTask);

        //3.第三种获取线程结果的方式(利用submit Runnable 后的future则get一直null)
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Future<?> submit = executor.submit(futureTask);

        executor.shutdown();
        System.out.println("主线程在执行任务");

        try {
            System.out.println("获取子线程结果");
            //1.第一种获取结果方式
//            System.out.println("task运行结果" + submit.get());

            //2.第二种获取结果方式
//            System.out.println("task运行结果" + futureTask.get());

            //3.第三种获取结果方式
            System.out.println("task运行结果" + submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }

    static class MyFuture extends FutureTask<Integer> {
        public MyFuture(Callable<Integer> callable) {
            super(callable);
        }

        //执行完成后会回调结果
        @Override
        protected void done() {
            super.done();
            try {
                System.out.println("任务执行完毕:" + get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程在进行计算");
            Thread.sleep(3000);
            int sum = 0;
            for (int i = 0; i < 100; i++)
                sum += i;

            System.out.println("Callable子线程计算结束！" + sum);
            return sum;
        }
    }
}
