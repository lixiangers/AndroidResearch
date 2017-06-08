package com.example.Lock;

public class ReadThread extends Thread {
    private ILockBuffer buff;

    public ReadThread(ILockBuffer buff) {
        this.buff = buff;
    }

    @Override
    public void run() {
        super.run();
        try {
            buff.read();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("我不读了");
        }
        System.out.println("读结束");
    }
}
