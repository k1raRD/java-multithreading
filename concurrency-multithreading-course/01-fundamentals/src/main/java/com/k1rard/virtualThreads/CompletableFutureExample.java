package com.k1rard.virtualThreads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample {
    public static void main(String[] args) {
        ExecutorService cpuExecutor = Executors.newFixedThreadPool(5);
        ExecutorService ioExecutor = Executors.newCachedThreadPool();

        CompletableFuture.supplyAsync(() -> "Hello world!", cpuExecutor)
                .thenApplyAsync(String::toUpperCase, ioExecutor)
                .thenApply(s -> s + " something")
                .thenAccept(System.out::println);
    }
}
