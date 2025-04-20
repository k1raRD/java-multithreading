package com.k1rard.section07;

import com.k1rard.section07.concurrencylimit.ConcurrencyLimit;
import com.k1rard.section07.externalservice.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Lec05ConcurrencyLimitWithSemaphore {
    private static final Logger log = LoggerFactory.getLogger(Lec05ConcurrencyLimitWithSemaphore.class);

    public static void main(String[] args) throws Exception {
        ThreadFactory threadFactory = Thread.ofVirtual().name("wings-", 1).factory();
        var limiter = new ConcurrencyLimit(Executors.newThreadPerTaskExecutor(threadFactory), 3);
        execute(limiter, 20);
    }

    private static void execute(ConcurrencyLimit concurrencyLimit, int taskCount) throws Exception {
        try(concurrencyLimit) {
            for (int i = 1; i <= taskCount; i++) {
                int j = i;
                concurrencyLimit.submit(() -> printProductionInfo(j));
            }
            log.info("Submitted");
        }
    }

    // 3rd party service
    // contract: 3 concurrent call are allowed
    private static String printProductionInfo(int id) {
        var product = Client.getProduct(id);
        log.info("{} => {}", id, Client.getProduct(id));
        return product;
    }
}
