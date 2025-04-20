package com.k1rard.virtualThreads;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComparisonPlatforAndVirtualThreads {
    public static void main(String[] args) {
        // Creating platform threads
//        for (int i = 0; i < 100000000; i++) {
//            Thread.ofPlatform().start(() -> {
//                try {
//                    System.out.println("Started " + Thread.currentThread());
//                    Thread.sleep(Duration.ofSeconds(5));
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//        }

        // Creating virtual Threads
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < 1000000; i++) {
            executorService.submit(() -> {
                try {
                    System.out.println("Started " + Thread.currentThread());
                    Thread.sleep(Duration.ofSeconds(1));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
