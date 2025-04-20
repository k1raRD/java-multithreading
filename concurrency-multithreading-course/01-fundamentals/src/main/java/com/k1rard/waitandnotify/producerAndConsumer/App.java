package com.k1rard.waitandnotify.producerAndConsumer;

import java.util.ArrayList;
import java.util.List;

class Processor {
    private List<Integer> list = new ArrayList<>();
    private static final int UPPER_LIMIT = 5;
    private static final int LOWER_LIMIT = 0;
    private final Object lock = new Object();
    private int value = 0;

    public void producer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                Thread.sleep(2000);
                if(list.size() == UPPER_LIMIT) {
                    System.out.println("Waiting for removing items...");
                    lock.wait();
                } else {
                    System.out.println("Adding: " + value);
                    list.add(value);
                    value++;
                    // We can call the notify - because the other thread will be notified
                    // only when it is in a waiting state
                    lock.notify();
                }
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                Thread.sleep(2000);
                if(list.isEmpty()) {
                    System.out.println("Waiting for adding items...");
                    lock.wait();
                } else {
                    System.out.println("Removing: " + list.removeLast());
                    // We can call the notify - because the other thread will be notified
                    // only when it is in a waiting state
                    lock.notify();
                }
            }
        }
    }
}

public class App {
    public static void main(String[] args) {
        Processor processor = new Processor();
        Thread t1 = new Thread(() -> {
            try {
                processor.producer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                processor.consumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
    }
}
