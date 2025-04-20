package com.k1rard;

class Runner1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Runner1: " + i);
        }
    }
}

class Runner2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Runner2: " + i);
        }
    }
}

public class Main {

    public static void main(String[] args)  {
        Thread thread1 = new Runner1();
        Thread thread2 = new Thread(new Runner2());
        thread2.start();
        thread1.start();

        thread2.interrupt();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("FInished with threads....");
    }
}