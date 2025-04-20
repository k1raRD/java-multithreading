package com.k1rard.section08;

import com.k1rard.section08.aggregator.AggregatorService;
import com.k1rard.section08.aggregator.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Lec06AllOf {

    private static final Logger log = LoggerFactory.getLogger(Lec06AllOf.class);

    public static void main(String[] args) {
        var executor = Executors.newThreadPerTaskExecutor(Thread.ofVirtual().name("wings-",1).factory());
        var aggregatorService = new AggregatorService(executor);

        List<CompletableFuture<ProductDTO>> futureList = IntStream
                .rangeClosed(1, 100)
                .mapToObj(id -> CompletableFuture.supplyAsync(() -> aggregatorService.getProductDTO(id), executor))
                .toList();

        // wait for all the completable futures to complete.
        CompletableFuture.allOf(futureList.toArray(CompletableFuture[]::new)).join();

        List<ProductDTO> productDTOS = futureList.stream()
                                                 .map(CompletableFuture::join)
                                                 .toList();
        log.info("list: {}", productDTOS);
    }
}
