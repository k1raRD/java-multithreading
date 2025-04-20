package com.k1rard.section08;

import com.k1rard.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class Lec01SimpleCompletableFuture {
    private static final Logger log = LoggerFactory.getLogger(Lec01SimpleCompletableFuture.class);

    public static void main(String[] args) {
        log.info("Main starts");
//        var cf = fastTask();
        var cf = slowTask();
        cf.thenAccept(v -> log.info("value = {}", v));
//        log.info("value = {}",cf.join());
        log.info("Main ends");
        CommonUtils.sleep(Duration.ofSeconds(2));
    }

    private static CompletableFuture<String> fastTask() {
        log.info("Method starts");
        var cf = new CompletableFuture<String>();
        cf.complete("hi");
        log.info("Method ends");
        return cf;
    }

    private static CompletableFuture<String> slowTask() {
        log.info("Method starts");
        var cf = new CompletableFuture<String>();
        Thread.ofVirtual().start(() -> {
            CommonUtils.sleep(Duration.ofSeconds(1));
            cf.complete("hi");
        });
        log.info("Method ends");
        return cf;
    }
}
