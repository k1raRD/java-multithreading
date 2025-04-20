package com.k1rard.section08;

import com.k1rard.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Lec07AnyOf {

    private static final Logger log = LoggerFactory.getLogger(Lec07AnyOf.class);

    public static void main(String[] args) {
        try(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var cf1 = getDeltaAirFair(executor);
            var cf2 = getFrontierAirFair(executor);

            log.info("airfare = {}", CompletableFuture.anyOf(cf1, cf2).join());
        }
    }

    private static CompletableFuture<String> getDeltaAirFair(ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> {
            var random = ThreadLocalRandom.current().nextInt(100, 1000);
            CommonUtils.sleep(Duration.ofMillis(random));
            return STR."Delta-$\{random}";
        }, executorService);
    }

    private static CompletableFuture<String> getFrontierAirFair(ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> {
            var random = ThreadLocalRandom.current().nextInt(100, 1000);
            CommonUtils.sleep(Duration.ofMillis(random));
            return STR."Frontier-$\{random}";
        }, executorService);
    }
}
