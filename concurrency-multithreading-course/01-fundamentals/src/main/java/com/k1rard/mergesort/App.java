package com.k1rard.mergesort;

import java.util.Arrays;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of threads: " + numOfThreads);

        int[] numbers1 = createArray(1000000);
        int[] numbers2 = Arrays.copyOf(numbers1, numbers1.length);

//        for (int i = 0; i < numbers1.length; i++) {
//            numbers2[i] = numbers1[i];
//        }
        // PARALLEL MERGE SORT
        ParallelMergeSort parallelMergeSort = new ParallelMergeSort(numbers1);

        long startTime1 = System.currentTimeMillis();
        parallelMergeSort.parallelMergeSort(0, numbers1.length -1, numOfThreads);
        long endTime1 = System.currentTimeMillis();
        System.out.printf("Time taken with parallel: %6d ms\n", endTime1 - startTime1);

        // SEQUENTIAL MERGE SORT
        long startTime2 = System.currentTimeMillis();
        MergeSort mergeSort = new MergeSort(numbers2);
        mergeSort.mergeSort(0, numbers2.length - 1);
        long endTime2 = System.currentTimeMillis();
        System.out.printf("Time taken with sequential: %6d ms\n", endTime2 - startTime2);
    }

    private static int[] createArray(int n) {

        Random random = new Random();
        int[] a = new int[n];

        for (int i = 0; i < n; ++i) {
            a[i] = random.nextInt(n);
        }

        return a;
    }
}
