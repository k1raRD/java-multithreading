package com.k1rard.section09;

import com.k1rard.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.ThreadLocalRandom;

public class Lec07ScopedValueWithStructureTaskScope {
    private static final Logger log = LoggerFactory.getLogger(Lec07ScopedValueWithStructureTaskScope.class);
    private static final ScopedValue<String> SESSION_TOKEN = ScopedValue.newInstance();

    public static void main(String[] args) {

        ScopedValue.runWhere(SESSION_TOKEN, "token-123", Lec07ScopedValueWithStructureTaskScope::task);

    }

    private static void task() {
        try(var taskScope = new StructuredTaskScope<>()){
            log.info("token: {}", SESSION_TOKEN.get());
            var subtask1 = taskScope.fork(Lec07ScopedValueWithStructureTaskScope::getDeltaAirfare);
            var subtask2 = taskScope.fork(Lec07ScopedValueWithStructureTaskScope::getFrontierAirfare);

            taskScope.join();

            log.info("subtask1 state: {}", subtask1.state());
            log.info("subtask2 state: {}", subtask2.state());

            log.info("subtask1 result: {}", subtask1.get());
            log.info("subtask2 result: {}", subtask2.get());

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static String getDeltaAirfare(){
        var random = ThreadLocalRandom.current().nextInt(100, 1000);
        log.info("delta: {}", random);
        log.info("token: {}", SESSION_TOKEN.get());
        CommonUtils.sleep("delta", Duration.ofSeconds(1));
        return "Delta-$" + random;
    }

    private static String getFrontierAirfare(){
        var random = ThreadLocalRandom.current().nextInt(100, 1000);
        log.info("frontier: {}", random);
        log.info("token: {}", SESSION_TOKEN.get());
        CommonUtils.sleep("frontier", Duration.ofSeconds(2));
        return "Frontier-$" + random;
    }

    private static String failingTask(){
        throw new RuntimeException("oops");
    }
}
