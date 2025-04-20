package com.k1rard.mergesort;

public class ParallelMergeSort {
    private int[] nums;
    // merge sort is not an in-place algorithm
    private int[] tempArray;

    public ParallelMergeSort(int[] nums) {
        this.nums = nums;
        this.tempArray = new int[nums.length];
    }

    private Thread creatThread(int low, int high, int numOfThreads) {
        return new Thread() {
            @Override
            public void run() {
                parallelMergeSort(low, high, numOfThreads/2);
            }
        };
    }

    public void parallelMergeSort(int low, int high, int numOfThreads) {
        if(numOfThreads <= 1) {
            mergeSort(low, high);
            return;
        }

        int middleIndex = (low + high) / 2;

        Thread leftSorted = creatThread(low, middleIndex, numOfThreads);
        Thread righttSorted = creatThread(middleIndex + 1, high, numOfThreads);

        leftSorted.start();
        righttSorted.start();

        try {
            leftSorted.join();
            righttSorted.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        merge(low, middleIndex, high);
    }

    public void showArray() {
        for (int i = 0; i < nums.length; ++i) {
            System.out.print(nums[i] + " ");
        }
    }

    public void sort() {
        mergeSort(0, nums.length-1);
    }

    private void mergeSort(int low, int high) {
        // base-case
        if(low >= high) {
            return;
        }

        int middleIndex = (low + high) / 2;

        // We keep splitting the problem into smaller and smaller sub-problems
        // until a given array contains just one item
        mergeSort(low, middleIndex);
        mergeSort(middleIndex + 1, high);

        // We have to combine the sub-solutions
        merge(low, middleIndex, high);
    }

    private void merge(int low, int middle, int high) {

        // copy the items into temporary array
        for (int i = 0; i <= high ; i++) {
            tempArray[i] = nums[i];
        }

        int i = low;
        int j = middle +1;
        int k = low;

        // We consider the temp array and copy items into the nums
        while (i <= middle && j <= high) {
            if(tempArray[i] < tempArray[j]) {
                nums[k] = tempArray[i];
                ++i;
            } else {
                nums[k] = tempArray[j];
                ++j;
            }

            ++k;
        }

        // We have to copy the items the left sub-array (if there are any)
        while (i <= middle) {
            nums[k] = tempArray[i];
            ++k;
            ++i;
        }

        // We have to copy the items the left sub-array (if there are any)
        while (j <= high) {
            nums[k] = tempArray[j];
            ++k;
            ++j;
        }
    }

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
