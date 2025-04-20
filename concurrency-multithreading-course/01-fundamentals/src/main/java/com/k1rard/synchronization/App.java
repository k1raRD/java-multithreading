package com.k1rard.synchronization;

public class App {
    public static int counter = 0;
    public static int counter2 = 0;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

//    We have to be sure this method is call by one thread
    public static void incrementCounter() {
        synchronized (lock1) {
            counter++;
        }
    }

    public static void incrementCounter2() {
        synchronized (lock2) {
            counter2++;
        }
    }

    public static void process() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                incrementCounter();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                incrementCounter2();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("The counter is: " + counter);
        System.out.println("The counter2 is: " + counter2);
    }
    public static void main(String[] args) {
        process();
    }
}
