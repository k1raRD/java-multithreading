package com.k1rard.section07;

import com.k1rard.section07.aggregator.AggregatorService;
import com.k1rard.section07.aggregator.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Lec04AggregatorDemo {
    private static final Logger log = LoggerFactory.getLogger(Lec04AggregatorDemo.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        var executor = Executors.newThreadPerTaskExecutor(Thread.ofVirtual().name("wings-",1).factory());
        var aggregatorService = new AggregatorService(executor);

        List<Future<ProductDTO>> futureList = IntStream.rangeClosed(1, 50)
                .mapToObj(id -> executor.submit(() -> aggregatorService.getProductDTO(id)))
                .toList();

        List<ProductDTO> productDTOS = futureList.stream()
                .map(Lec04AggregatorDemo::futureToProductDTO)
                .toList();

        log.info("list: {}", productDTOS);
    }

    private static ProductDTO futureToProductDTO(Future<ProductDTO> future) {
        try {
            return future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
