package com.k1rard.section09;

import com.k1rard.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.UUID;

public class Lec02ThreadLocal {
    private static final Logger log = LoggerFactory.getLogger(Lec02ThreadLocal.class);
    private static final ThreadLocal<String> SESSION_TOKEN = new InheritableThreadLocal<>();

    public static void main(String[] args) {

        Thread.ofVirtual().name("1").start( () -> processIncomingRequest());
        Thread.ofVirtual().name("2").start( () -> processIncomingRequest());

        CommonUtils.sleep(Duration.ofSeconds(1));

    }

    // ** ---- below code is just to demonstrate the workflow --- **

    private static void processIncomingRequest(){
        authenticate();
        controller();
    }

    private static void authenticate(){
        var token = UUID.randomUUID().toString();
        log.info("token={}", token);
        SESSION_TOKEN.set(token);
    }

    // @Principal
    private static void controller(){
        log.info("controller: {}", SESSION_TOKEN.get());
        service();
    }

    private static void service(){
        log.info("service: {}", SESSION_TOKEN.get());
        var threadName = "child-of-" + Thread.currentThread().getName();
        Thread.ofVirtual().name(threadName).start(() -> callExternalService());
    }

    // This is a client to call external service
    private static void callExternalService(){
        log.info("preparing HTTP request with token: {}", SESSION_TOKEN.get());
        SESSION_TOKEN.set("dasdas");
        log.info("preparing HTTP request with token: {}", SESSION_TOKEN.get());
    }
}
