package com.k1rard.sumProblem;

public class ParallelSumProblem {

    private ParallelSumWorker[] workers;
    private int numOfThreads;

    public ParallelSumProblem(int numOfThreads) {
        this.numOfThreads = numOfThreads;
        this.workers = new ParallelSumWorker[numOfThreads];
    }

    public int sum(int[] numbers) {

        int size = (int) Math.ceil(numbers.length *1.0 / numOfThreads);

        for (int i = 0; i < numOfThreads; i++) {
            workers[i] = new ParallelSumWorker(numbers, i * size, (i + 1) * size);
            workers[i].start();
        }

        try {
            for(ParallelSumWorker worker: workers) {
                worker.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // We have to sum up the subResults
        int total = 0;

        for(ParallelSumWorker worker: workers) {
            total += worker.getPartialSum();
        }

        return total;
    }
}
