package com.k1rard.section08;

import com.k1rard.section08.aggregator.AggregatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class Lec05AggregatorDemo {

    private static final Logger log = LoggerFactory.getLogger(Lec05AggregatorDemo.class);

    public static void main(String[] arags) throws ExecutionException, InterruptedException {
        var executor = Executors.newThreadPerTaskExecutor(Thread.ofVirtual().name("wings-",1).factory());
        var aggregatorService = new AggregatorService(executor);

        log.info("product: {}", aggregatorService.getProductDTO(51));
    }
}
