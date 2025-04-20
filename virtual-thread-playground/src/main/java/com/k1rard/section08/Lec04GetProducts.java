package com.k1rard.section08;

import com.k1rard.section07.externalservice.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class Lec04GetProducts {
    private static final Logger log = LoggerFactory.getLogger(Lec04GetProducts.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<String> product1 = CompletableFuture.supplyAsync(() -> Client.getProduct(1), executorService);
            Future<String> product2 = CompletableFuture.supplyAsync(() -> Client.getProduct(2), executorService);
            Future<String> product3 = CompletableFuture.supplyAsync(() -> Client.getProduct(3), executorService);
            Future<String> product4 = CompletableFuture.supplyAsync(() -> Client.getProduct(4), executorService);

            log.info("product-1: {}", product1.get());
            log.info("product-2: {}", product2.get());
            log.info("product-3: {}", product3.get());
            log.info("product-4: {}", product4.get());
        }
    }
}
