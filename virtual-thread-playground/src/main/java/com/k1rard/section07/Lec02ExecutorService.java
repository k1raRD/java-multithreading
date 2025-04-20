package com.k1rard.section07;

import com.k1rard.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Lec02ExecutorService {

    private static final Logger log = LoggerFactory.getLogger(Lec02ExecutorService.class);

    public static void main(String[] args) {
//        execute(Executors.newSingleThreadExecutor(), 3);
//        execute(Executors.newFixedThreadPool(5), 20);
//        execute(Executors.newCachedThreadPool(), 200);
//        execute(Executors.newVirtualThreadPerTaskExecutor(), 10_000);
        schedule();
    }

    // single thread executor - to execute tasks sequentially
    private static void single(){
        execute(Executors.newSingleThreadExecutor(), 3);
    }

    // fixed thread pool
    private static void fixed(){
        execute(Executors.newFixedThreadPool(5), 20);
    }

    // elastic thread pool
    private static void cached(){
        execute(Executors.newCachedThreadPool(), 200);
    }

    // ExecutorService which creates VirtualThread per task
    private static void virtual(){
        execute(Executors.newVirtualThreadPerTaskExecutor(), 10_000);
    }

    private static void schedule() {
        try(var executorService = Executors.newSingleThreadScheduledExecutor()) {
            executorService.scheduleAtFixedRate(() -> {
                log.info("Executing task");
            }, 0, 1, TimeUnit.SECONDS);
            CommonUtils.sleep(Duration.ofSeconds(10));
        }
    }

    private static void execute(ExecutorService executorService, int taskCount) {
        try(executorService) {
            for (int i = 0; i < taskCount; i++) {
                int j = i;
                executorService.submit(() -> ioTask(j));
            }
            log.info("Submitted");
        }
    }

    private static void ioTask(int i){
        log.info("Task started: {}. Thread Info {}", i, Thread.currentThread());
        CommonUtils.sleep(Duration.ofSeconds(5));
        log.info("Task ended: {}. Thread Info {}", i, Thread.currentThread());
    }
}
