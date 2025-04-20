package com.k1rard.section08;

import com.k1rard.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/*
    Factory method
    Run async
    Executor
 */
public class Lec02RunAsync {
    private static final Logger log = LoggerFactory.getLogger(Lec02RunAsync.class);

    public static void main(String[] args) {
        log.info("Main starts");
        runAsync()
                .thenRun(() -> log.info("It's done"))
                .exceptionally(ex -> {
                    log.info("Error - {}", ex.getMessage());
                    return null;
                });
        log.info("Main ends");
        CommonUtils.sleep(Duration.ofSeconds(2));
    }

    private static CompletableFuture<Void> runAsync() {
        log.info("Method starts");
        var cf = CompletableFuture.runAsync(() -> {
           CommonUtils.sleep(Duration.ofSeconds(1));
           // log.info("task completed");
            throw new RuntimeException();
        }, Executors.newVirtualThreadPerTaskExecutor());
        log.info("Method ends");
        return cf;
    }
}
