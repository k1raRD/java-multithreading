package com.k1rard.section07;

import com.k1rard.section07.externalservice.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Lec03AccessResponseUsingFuture {
    private static final Logger log = LoggerFactory.getLogger(Lec03AccessResponseUsingFuture.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<String> product1 = executorService.submit(() -> Client.getProduct(1));
            Future<String> product2 = executorService.submit(() -> Client.getProduct(1));
            Future<String> product3 = executorService.submit(() -> Client.getProduct(1));
            Future<String> product4 = executorService.submit(() -> Client.getProduct(1));

            log.info("product-1: {}", product1.get());
            log.info("product-2: {}", product2.get());
            log.info("product-3: {}", product3.get());
            log.info("product-4: {}", product4.get());
        }
    }
}
