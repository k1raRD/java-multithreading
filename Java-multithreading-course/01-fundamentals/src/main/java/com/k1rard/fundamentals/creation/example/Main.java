package com.k1rard.fundamentals.creation.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("We are now in thread " + Thread.currentThread().getName());
                System.out.println("CURRENT THREAD PRIORITY IS: " + Thread.currentThread().getPriority());
                throw new RuntimeException("Intentional Exception");
            }
        });

        thread.setName("Misbehaving Thread");
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread " + t.getName() + " : " + e.getMessage());
            }
        });
        thread.setPriority(Thread.MAX_PRIORITY);
        System.out.println("We're in thread: " + Thread.currentThread().getName() + " before starting a new thread");
        thread.start();
        System.out.println("We're in thread: " + Thread.currentThread().getName() + " after starting a new thread");

        Thread.sleep(10000);
    }
}