package com.k1rard.apiStream;

import java.util.stream.LongStream;

public class ParallelStream {
    public static void main(String[] args) {
        // parallel() - because we have top make sure that the given stream can be parallelized
        // under the hood the fork-join framework is used
        long value = 10000000000L;
        long start = System.currentTimeMillis();
        System.out.println(sum(value));
        System.out.println("Time taken sequential: " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(parallelSum(value));
        System.out.println("Time taken parallel: " + (System.currentTimeMillis() - start) + "ms");
    }

    private static long sum(long n) {
        return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
    }

    private static long parallelSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
    }
}
