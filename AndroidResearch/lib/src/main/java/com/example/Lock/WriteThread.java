package com.example.Lock;

public class WriteThread extends Thread {
    private ILockBuffer buff;

    public WriteThread(ILockBuffer buff) {
        this.buff = buff;
    }

    @Override
    public void run() {
        super.run();
        buff.write();
    }
}
