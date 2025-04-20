package com.k1rard.virtualThreads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureWithVirtualThreads2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        Future<String> result = executorService.submit(vehicleService());
//        String value1 = result.get();
//
//        Future<String> result2 = executorService.submit(checkDB());
//        String value2 = result2.get();
//
//        Future<String> result3 = executorService.submit(userService());
//        String value3 = result3.get();
    }
}
