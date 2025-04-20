package com.k1rard.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Tasks implements Runnable {
    private int id;

    public Tasks(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task with id: " + id + " is in work - thread id - " + Thread.currentThread().getName());
        long duration = (long) (Math.random() * 5);

        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class FixedThreadExecutorExample {
    public static void main(String[] args) {
        // It is a single thread that willl execute the tasks sequentially so one after another
        try (ExecutorService executorService = Executors.newFixedThreadPool(10)) {

            for (int i = 0; i < 100; i++) {
                executorService.execute(new Tasks(i));
            }

            // We have to shutdown the executor!!!
//            executorService.shutdown();
        }
    }
}
