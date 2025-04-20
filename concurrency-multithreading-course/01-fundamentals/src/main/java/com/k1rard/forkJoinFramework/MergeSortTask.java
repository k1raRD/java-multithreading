package com.k1rard.forkJoinFramework;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class MergeSortTask extends RecursiveAction {

    private int[] nums;

    public MergeSortTask(int[] nums) {
        this.nums = nums;
    }

    @Override
    protected void compute() {
        if(nums.length <= 1) {
            // Sequential merge sort
            mergeSort(nums);
            return;
        }

        int middleIndex = nums.length / 2;

        int[] left = Arrays.copyOfRange(nums, 0, middleIndex);
        int[] right = Arrays.copyOfRange(nums, middleIndex, nums.length);

        MergeSortTask task1 = new MergeSortTask(left);
        MergeSortTask task2 = new MergeSortTask(right);

        invokeAll(task1, task2);


    }

    private void mergeSort(int[] nums) {
        // base-case
        if(nums.length <= 1) {
            return;
        }

        int middleIndex = nums.length / 2;

        int[] left = Arrays.copyOfRange(nums, 0, middleIndex);
        int[] right = Arrays.copyOfRange(nums, middleIndex, nums.length);

        mergeSort(left);
        mergeSort(right);

        // We have to combine the sub-solutions
        merge(left, right, nums);
    }

    private void merge(int[] leftSubArray, int[] rightSubArray, int[] originalArray) {

        int i = 0;
        int j = 0;
        int k = 0;

        // We consider the temp array and copy items into the nums
        while (i < leftSubArray.length && j <= rightSubArray.length) {
            if(leftSubArray[i] < rightSubArray[j]) {
                originalArray[k++] = leftSubArray[i++];
            } else {
                nums[k++] = rightSubArray[j++];
            }
        }

        // We have to copy the items the left sub-array (if there are any)
        while (i < leftSubArray.length) {
            originalArray[k++] = leftSubArray[i++];
        }

        // We have to copy the items the left sub-array (if there are any)
        while (j < rightSubArray.length) {
            originalArray[k++] = rightSubArray[j++];
        }
    }
}
