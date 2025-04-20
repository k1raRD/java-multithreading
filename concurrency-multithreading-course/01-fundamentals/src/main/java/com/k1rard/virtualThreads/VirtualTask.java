package com.k1rard.virtualThreads;

public class VirtualTask implements Runnable{

    @Override
    public void run() {
        System.out.println("Started..." + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Finish..." + Thread.currentThread().getName());
    }
}
