package com.k1rard.section07;

import com.k1rard.section07.externalservice.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Lec05ConcurrencyLimit {
    private static final Logger log = LoggerFactory.getLogger(Lec05ConcurrencyLimit.class);

    public static void main(String[] args) {
        ThreadFactory threadFactory = Thread.ofVirtual().name("wings-", 1).factory();
        execute(Executors.newFixedThreadPool(3, threadFactory), 20);
    }

    private static void execute(ExecutorService executorService, int taskCount) {
        try(executorService) {
            for (int i = 0; i <= taskCount; i++) {
                int j = i;
                executorService.submit(() -> printProductionInfo(j));
            }
            log.info("Submitted");
        }
    }

    // 3rd party service
    // contract: 3 concurrent call are allowed
    private static void printProductionInfo(int id) {
        log.info("{} => {}", id, Client.getProduct(id));
    }
}
