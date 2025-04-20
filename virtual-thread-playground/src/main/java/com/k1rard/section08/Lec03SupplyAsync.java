package com.k1rard.section08;

import com.k1rard.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * We can supply values asynchronously
 * Factory method
 * Executor
 */
public class Lec03SupplyAsync {
    private static final Logger log = LoggerFactory.getLogger(Lec03SupplyAsync.class);

    public static void main(String[] args) {
        log.info("Main starts");
//        var cf = fastTask();
        var cf = slowTask();
        cf.thenAccept(v -> log.info("value = {}", v));
//        log.info("value = {}",cf.join());
        log.info("Main ends");
        CommonUtils.sleep(Duration.ofSeconds(2));
    }

    private static CompletableFuture<String> slowTask() {
        log.info("Method starts");
        var cf = CompletableFuture.supplyAsync(() -> {
            CommonUtils.sleep(Duration.ofSeconds(1));;
            return "hi";
        }, Executors.newVirtualThreadPerTaskExecutor());
        log.info("Method ends");
        return cf;
    }
}
