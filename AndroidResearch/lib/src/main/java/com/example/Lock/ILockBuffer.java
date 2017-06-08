package com.example.Lock;

public interface ILockBuffer {
    void read() throws InterruptedException;

    void write();
}
