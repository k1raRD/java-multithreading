package com.k1rard.section06;

import com.k1rard.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ThreadFactory;

public class ThreadFactory01 {

    private static final Logger log = LoggerFactory.getLogger(ThreadFactory01.class);

    public static void main(String[] args) {
        demo(Thread.ofVirtual().name("wings-", 1).factory());
        CommonUtils.sleep(Duration.ofSeconds(4));
    }

    /*
        Create a few threads
        Each thread creates 1 child thread
     */
    private static void demo(ThreadFactory factory) {
        for (int i = 0; i < 30; i++) {
            var t = factory.newThread(() -> {
                log.info("Task started. {}", Thread.currentThread());
                var ct =factory.newThread(() -> {
                    log.info("Child task started. {}", Thread.currentThread());
                    CommonUtils.sleep(Duration.ofSeconds(2));
                    log.info("Child task ended. {}", Thread.currentThread());
                });
                ct.start();
                log.info("Task ended. {}", Thread.currentThread());
            });
            t.start();
        }
    }

}
