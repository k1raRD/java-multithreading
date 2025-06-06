package com.k1rard.priority;

class Worker implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // A thread with priority 10
        Thread t = new Thread(new Worker());
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
        // main thread with priority 5
        System.out.println("This is in the main thread");
    }
}
