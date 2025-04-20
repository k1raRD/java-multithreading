package com.k1rard.section05;

import com.k1rard.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LocalRaceConditionReentrantLockWithIO {
    private static final Logger log = LoggerFactory.getLogger(LocalRaceConditionReentrantLockWithIO.class);
    private static final Lock lock = new ReentrantLock();
    private static final List<Integer> list = new ArrayList<>();

    static {
        System.setProperty("jdk.tracePinnedThreads", "full");
    }

    public static void main(String[] args) {
        Runnable runnable = () -> log.info("*** Test message ***");

//        demo(Thread.ofPlatform());
//        Thread.ofPlatform().start(runnable);

        demo(Thread.ofVirtual());
        Thread.ofVirtual().start(runnable);

        CommonUtils.sleep(Duration.ofSeconds(15));
    }

    private static void demo(Thread.Builder builder) {
        for (int i = 0; i < 50; i++) {
            builder.start(() -> {
                log.info("Task started. {}", Thread.currentThread());
                ioTask();
                log.info("Task ended. {}", Thread.currentThread());
            });
        }
    }

    private static void ioTask() {
        try {
            lock.lock();
            list.add(1);
            CommonUtils.sleep(Duration.ofSeconds(10));
        } catch (Exception e) {
            log.error("error", e);
        } finally {
            lock.unlock();
        }
    }
}
