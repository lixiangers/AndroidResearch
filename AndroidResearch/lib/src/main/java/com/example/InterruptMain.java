package com.example;

/**
 * 当一个线程正在处于阻塞状态下，执行interrupt 会中断线程，
 * 当一个线程正在运行下，执行interrupt 不会中断线程
 */
public class InterruptMain {
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
//        testInterrupt();
//        testSynchronizedThread();
        testSynchronizedWaitNofityThread();
    }

    private static void testInterrupt() throws InterruptedException {
        //        Thread thread = new CanInterruptThread();//被阻塞的线程可以响应中断(但是因为(synchronized)造成的无法中断)
//        Thread thread = new CannotInterruptThread();//一直运行的线程无法响应中断
//        Thread thread = new OptimizeInterruptThread();//一个优化的可以处理中断的一直运行的线程
        Thread thread = new SynchronizedInterruptThread();//一个优化的可以处理中断的一直运行的线程
        thread.start();

        Thread.sleep(200);

        thread.interrupt();//中断
        System.out.println("执行中断");
    }

    /**
     * 验证synchronized  wait,notify 的使用
     * 在synchronized wait的时候是可以中断的
     *
     * @throws InterruptedException
     */
    private static void testSynchronizedWaitNofityThread() throws InterruptedException {
        Thread thread = new SynchronizedInterruptThread();
        thread.start();

        Thread.sleep(1000);

        synchronized (lock) {
            lock.notify();
        }

        Thread.sleep(200);

        thread.interrupt();//中断
        System.out.println("执行中断");
    }

    /**
     * 验证 在等待获取锁的时候是不能中断
     *
     * @throws InterruptedException
     */
    private static void testSynchronizedThread() throws InterruptedException {
        //先占用lock
        new SynchronizedAssistThread().start();

        Thread thread = new SynchronizedInterruptThread();
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

    public static class SynchronizedInterruptThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {//一直停止这里无法中断
                    try {
                        System.out.println("我开始执行");
                        lock.wait();//释放锁，此时是可以响应interrupt
                        System.out.println("我继续执行");
                    } catch (InterruptedException e) {
                        System.out.println("SynchronizedInterruptThread  interrupt");
                        break;
                    }
                }
                if (this.isInterrupted()) {
                    System.out.println("我被中断了");
                    break;
                }
            }
        }
    }


    public static class SynchronizedAssistThread extends Thread {
        @Override
        public void run() {
            super.run();
            synchronized (lock) {
                while (true) {
                    System.out.println("我一直占用lock");
                }
            }
        }
    }

}
