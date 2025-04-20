package com.k1rard.forkJoinFramework;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class AppFindingAlgorithm {
    public static void main(String[] args) {
        long[] numbers = createNumbers(900000000);

        int numOfThreads = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(numOfThreads);

        SequentialMaxFinding sequential = new SequentialMaxFinding();
        long start = System.currentTimeMillis();
        System.out.println("Max: " + sequential.max(numbers));
        System.out.println("Time: " + (System.currentTimeMillis() - start));

        ParallelSequentialMaxFinding parallel = new ParallelSequentialMaxFinding(numbers, 0, numbers.length);
        start = System.currentTimeMillis();
        System.out.println("Max: " + pool.invoke(parallel));
        System.out.println("Time: " + (System.currentTimeMillis() - start));
    }

    private static long[] createNumbers(int n) {
        Random random = new Random();
        long[] numbers = new long[n];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(100);
        }

        return numbers;
    }
}
