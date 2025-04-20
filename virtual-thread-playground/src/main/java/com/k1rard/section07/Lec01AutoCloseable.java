package com.k1rard.section07;

import com.k1rard.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
    ExecutorService now extends the Autocloseable
*/
public class Lec01AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(Lec01AutoCloseable.class);

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.submit(Lec01AutoCloseable::task);
//        log.info("Submitted");
//        executorService.shutdown();

        try(ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            executorService.submit(Lec01AutoCloseable::task);
            executorService.submit(Lec01AutoCloseable::task);
            executorService.submit(Lec01AutoCloseable::task);
            executorService.submit(Lec01AutoCloseable::task);
            executorService.submit(Lec01AutoCloseable::task);
            log.info("Submitted");
        }
    }

    private static void task() {
        CommonUtils.sleep(Duration.ofSeconds(1));
        log.info("Task executed");
    }
}
