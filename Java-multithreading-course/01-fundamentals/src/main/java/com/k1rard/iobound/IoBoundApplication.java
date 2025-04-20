package com.k1rard.iobound;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IoBoundApplication {
    private static final int NUMBER_OF_TASKS = 10000;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Press enter to start");
        s.nextLine();
        System.out.printf("Running %d tasks\n", NUMBER_OF_TASKS);

        long start = System.currentTimeMillis();
        performTasks();
        System.out.printf("Task took %dms to complete\n", System.currentTimeMillis() - start);
    }

    private static void performTasks() {

        ExecutorService executorService = Executors.newFixedThreadPool(10000);
        for (int i = 0; i < NUMBER_OF_TASKS; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    blockingIoOperation();
                }
            });
        }
    }

    private static void blockingIoOperation() {
        System.out.println("Executing a blocking task from thread: " + Thread.currentThread());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
