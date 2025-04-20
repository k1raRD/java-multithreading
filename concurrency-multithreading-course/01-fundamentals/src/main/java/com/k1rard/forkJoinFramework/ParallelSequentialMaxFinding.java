package com.k1rard.forkJoinFramework;

import java.util.concurrent.RecursiveTask;

public class ParallelSequentialMaxFinding extends RecursiveTask<Long> {

    private long[] nums;
    private int lowIndex;
    private int highIndex;

    public ParallelSequentialMaxFinding(long[] nums, int lowIndex, int highIndex) {
        this.nums = nums;
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
    }

    @Override
    protected Long compute() {

        // If the array is small - then we use sequential approach
        if(highIndex - lowIndex < 5000) {
            return sequentialMaxFinding();
        } else {
            // We have to use parallelization
            int middleIndex = (highIndex + lowIndex) / 2;
            ParallelSequentialMaxFinding task1 = new ParallelSequentialMaxFinding(nums, lowIndex, middleIndex);
            ParallelSequentialMaxFinding task2 = new ParallelSequentialMaxFinding(nums, middleIndex + 1, highIndex);

            invokeAll(task1, task2);

            return Math.max(task1.join(), task2.join());
        }
    }

    private Long sequentialMaxFinding() {
        long max = nums[lowIndex];

        for (int i = lowIndex + 1; i < highIndex; i++)
            if(nums[i] > max) max = nums[i];

        return max;
    }
}
