package com.k1rard.daemon;

class DaemonWorker implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("DaemonThread is working...");
        }
    }
}

class NormalWorker implements Runnable {

    @Override
    public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Finishing the execution...");
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        Thread t1 = new Thread(new DaemonWorker());
        Thread t2 = new Thread(new NormalWorker());
        t1.setDaemon(true);
        System.out.println(t1.isDaemon());
        t1.start();
        t2.start();
    }
}
