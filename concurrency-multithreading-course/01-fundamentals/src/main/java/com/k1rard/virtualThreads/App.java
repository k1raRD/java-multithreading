package com.k1rard.virtualThreads;

import java.util.concurrent.ThreadFactory;

public class App {
    public static void main(String[] args) {
        // approach #1
//        var builder = Thread.ofVirtual().name("virtual-", 0);
//        Thread t1 = builder.start(new VirtualTask());
//        Thread t2 = builder.start(new VirtualTask());

        // All virtual threads are daemon threads!!!
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        // approach #2
        ThreadFactory factory = Thread.ofVirtual().name("virtual-", 0).factory();
        Thread t1 = factory.newThread(new VirtualTask());
        Thread t2 = factory.newThread(new VirtualTask());

        t1.start();
        t2.start();

        // All virtual threads are daemon threads!!!
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
