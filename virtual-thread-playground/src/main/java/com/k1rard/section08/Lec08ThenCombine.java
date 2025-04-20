package com.k1rard.section08;

import com.k1rard.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Lec08ThenCombine {
    private static final Logger log = LoggerFactory.getLogger(Lec08ThenCombine.class);
    record Airfare(String airfare, int amount) {}

    public static void main(String[] args) {
        try(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var cf1 = getDeltaAirFair(executor);
            var cf2 = getFrontierAirFair(executor);
            var bestDeal = cf1.thenCombine(cf2, (a, b) -> a.amount() <= b.amount() ? a : b)
                    .thenApply(af -> new Airfare(af.airfare, (int) (af.amount() * 0.9)))
                    .join();

            log.info("best deal = {}", bestDeal);
        }
    }

    private static CompletableFuture<Airfare> getDeltaAirFair(ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> {
            var random = ThreadLocalRandom.current().nextInt(100, 1000);
            CommonUtils.sleep(Duration.ofMillis(random));
            log.info("Delta = {}", random);
            return new Airfare("Delta", random);
        }, executorService);
    }

    private static CompletableFuture<Airfare> getFrontierAirFair(ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> {
            var random = ThreadLocalRandom.current().nextInt(100, 1000);
            CommonUtils.sleep(Duration.ofMillis(random));
            log.info("Frontier = {}", random);
            return new Airfare("Frontier", random);
        }, executorService);
    }
}
