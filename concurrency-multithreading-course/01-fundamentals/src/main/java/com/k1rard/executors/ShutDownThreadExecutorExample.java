package com.k1rard.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Work implements Runnable {
    private int id;

    public Work(int id) {
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

public class ShutDownThreadExecutorExample {
    public static void main(String[] args) {
        // It is a single thread that willl execute the tasks sequentially so one after another
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executorService.execute(new Work(i));
        }

        // We prevent the executor to execute any further tasks
        executorService.shutdown();

        // terminate actual (running) tasks
        try {
            if(!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                // executorService.shutdownNow();

            }
        } catch(InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
