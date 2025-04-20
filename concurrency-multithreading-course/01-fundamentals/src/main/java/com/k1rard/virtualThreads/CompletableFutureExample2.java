package com.k1rard.virtualThreads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample2 {
    public static void main(String[] args) {

        // try-with-resources closes (shuts down the threads) automatically
        // the threads will be executed (we will wait for them to finish)
        try(var executorService = Executors.newVirtualThreadPerTaskExecutor()) {
          String result =  CompletableFuture.supplyAsync(DBQuery::run, executorService)
                    .thenCombine(
                            CompletableFuture.supplyAsync(RestQuery::run, executorService),
                            (res1, res2) -> {
                                return res2 + res1;
                    })
                    .join();
          System.out.println(result);
        }

//       CompletableFuture.supplyAsync(() -> "Hello ")
//               .thenCombine(CompletableFuture.supplyAsync(() -> "World"), (s1, s2) -> s1 + s2)
//               .thenApplyAsync(String::toUpperCase)
//               .thenAccept(System.out::println);
    }
}
