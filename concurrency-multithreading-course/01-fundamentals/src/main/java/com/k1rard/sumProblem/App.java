package com.k1rard.sumProblem;

import java.util.Random;

public class App {
    public static void main(String[] args) {

        Random random = new Random();
        int[] nums = new int[10000000];
        for (int i = 0; i < 10000000; i++) {
            nums[i] = random.nextInt(100);
        }

        // Sequential sum algorithm
        SumProblem sumProblem = new SumProblem();
        long start = System.currentTimeMillis();
        System.out.println("Sequential sum: " + sumProblem.sum(nums));
        System.out.println("Sequential time: " + (System.currentTimeMillis() - start));


        // Parallel algorithm
        int n = Runtime.getRuntime().availableProcessors();
        ParallelSumProblem parallelSumProblem = new ParallelSumProblem(n);
        start = System.currentTimeMillis();
        System.out.println("Parallel sum: " +  parallelSumProblem.sum(nums));
        System.out.println("Parallel Time: " + (System.currentTimeMillis() - start));
    }
}
