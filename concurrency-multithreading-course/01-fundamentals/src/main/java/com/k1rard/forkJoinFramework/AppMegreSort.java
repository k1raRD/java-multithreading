package com.k1rard.forkJoinFramework;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class AppMegreSort {

    public static void main(String[] args) {
        int[] numbers = createNumbers(100000000);
        SequentialMergeSort sequentialMergeSort = new SequentialMergeSort(numbers);
        long start = System.currentTimeMillis();
        sequentialMergeSort.sort();
        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + " ms");

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        MergeSortTask mergeSortTask = new MergeSortTask(numbers);
        start = System.currentTimeMillis();
        pool.invoke(mergeSortTask);
        System.out.println("Time taken: " + (System.currentTimeMillis() - start) + " ms");
    }

    private static int[] createNumbers(int n) {
        Random random = new Random();
        int[] numbers = new int[n];

        for (int i = 0; i < numbers.length; ++i) {
            numbers[i] = random.nextInt(10000);
        }
        return numbers;
    }

}
