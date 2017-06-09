package com.example;

/**
 * 当一个线程正在处于阻塞状态下，执行interrupt 会中断线程，
 * 当一个线程正在运行下，执行interrupt 不会中断线程
 */
public class InterruptMain {
    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new CanInterruptThread();//被阻塞的线程可以响应中断
//        Thread thread = new CannotInterruptThread();//一直运行的线程无法响应中断
        Thread thread = new OptimizeInterruptThread();//一个优化的可以处理中断的一直运行的线程
        thread.start();

        Thread.sleep(200);

        thread.interrupt();//中断
        System.out.println("执行中断");
    }

    public static class CanInterruptThread extends Thread {
        //被阻塞的线程可以响应中断
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException");
                    boolean interrupt = this.isInterrupted();
                    System.out.println("interrupt:" + interrupt);
                }
            }
        }
    }

    public static class CannotInterruptThread extends Thread {
        //一直运行的线程无法响应中断
        @Override
        public void run() {
            while (true) {
                System.out.println("我在运行");
            }
        }
    }


    public static class OptimizeInterruptThread extends Thread {
        //一个优化的可以处理中断的一直运行的线程
        @Override
        public void run() {
            while (true) {
                if (this.isInterrupted()) {
                    System.out.println("我被中断了");
                    break;
                }
                System.out.println("我在运行");
            }
        }
    }

}
