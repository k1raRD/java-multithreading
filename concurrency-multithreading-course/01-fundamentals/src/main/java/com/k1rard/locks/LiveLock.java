package com.k1rard.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLock {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        LiveLock liveLock = new LiveLock();

        new Thread(liveLock::worker1, "worker1").start();
        new Thread(liveLock::worker2, "worker2").start();
    }

    private void worker1() {
        while (true) {
            try {
                lock1.tryLock(50, TimeUnit.MILLISECONDS);
                System.out.println("Worker1 aquires the lock1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Worker1 tries to get lock2...");

            if(lock2.tryLock()) {
                System.out.println("Worker1 aquires the lock2");
                lock2.unlock();
            } else {
                System.out.println("Worker1 cannot acquire lock2");
                continue;
            }

            break;
        }

        lock1.unlock();
        lock2.unlock();
    }

    private void worker2() {
        while (true) {
            try {
                lock2.tryLock(50, TimeUnit.MILLISECONDS);
                System.out.println("Worker2 aquires the lock2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Worker2 tries to get lock1...");

            if(lock1.tryLock()) {
                System.out.println("Worker2 aquires the lock1");
                lock1.unlock();
            } else {
                System.out.println("Worker2 cannot acquire lock1");
                continue;
            }

            break;
        }

        lock1.unlock();
        lock2.unlock();
    }
}
