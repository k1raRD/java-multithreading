package com.k1rard.section07;

import com.k1rard.section07.externalservice.Client;
import com.k1rard.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Lec06ScheduleExecutorWithVirtualThread {
    private static final Logger log = LoggerFactory.getLogger(Lec06ScheduleExecutorWithVirtualThread.class);

    public static void main(String[] args) {
        schedule();
    }

    private static void schedule() {
        var scheduler = Executors.newSingleThreadScheduledExecutor();
        var executorService = Executors.newVirtualThreadPerTaskExecutor();
        try(scheduler; executorService) {
            scheduler.scheduleAtFixedRate(() -> {
                executorService.submit(() -> printProductionInfo(1));
                log.info("Executing task");
            }, 0, 3, TimeUnit.SECONDS);
            CommonUtils.sleep(Duration.ofSeconds(15));
        }
    }

    private static void printProductionInfo(int id) {
        log.info("{} => {}", id, Client.getProduct(id));
    }
}
