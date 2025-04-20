package com.k1rard.sumProblem;

public class ParallelSumWorker extends Thread{

    private int[] numbers;
    private int low;
    private int high;
    private int partialSum;

    public ParallelSumWorker(int[] numbers, int low, int high) {
        this.numbers = numbers;
        this.low = low;
        this.high = Math.min(numbers.length, high);
    }

    @Override
    public void run() {

        partialSum = 0;

        for (int i = 0; i < high; i++) {
            partialSum += numbers[i];
        }
    }

    public int getPartialSum() {
        return this.partialSum;
    }
}
