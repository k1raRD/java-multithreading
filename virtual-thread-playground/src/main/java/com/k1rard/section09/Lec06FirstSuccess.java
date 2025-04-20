package com.k1rard.section09;

import com.k1rard.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A simple demo of Structured Concurrency where we want to cancel all the running
 * subtasks when we get the first success response
 */
public class Lec06FirstSuccess {
    private static final Logger log = LoggerFactory.getLogger(Lec06FirstSuccess.class);

    public static void main(String[] args) {

        try(var taskScope = new StructuredTaskScope.ShutdownOnSuccess<>()){
            var subtask1 = taskScope.fork(Lec06FirstSuccess::failingTask);
            var subtask2 = taskScope.fork(Lec06FirstSuccess::failingTask);

            taskScope.join();

            log.info("subtask1 state: {}", subtask1.state());
            log.info("subtask2 state: {}", subtask2.state());

            log.info("subtask result: {}", taskScope.result(ex -> new RuntimeException("All failed")));

        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    private static String getDeltaAirfare(){
        var random = ThreadLocalRandom.current().nextInt(100, 1000);
        log.info("delta: {}", random);
        CommonUtils.sleep("delta", Duration.ofSeconds(3));
        return "Delta-$" + random;
    }

    private static String getFrontierAirfare(){
        var random = ThreadLocalRandom.current().nextInt(100, 1000);
        log.info("frontier: {}", random);
        CommonUtils.sleep("frontier", Duration.ofSeconds(2));
        return "Frontier-$" + random;
    }

    private static String failingTask(){
        throw new RuntimeException("oops");
    }
}
