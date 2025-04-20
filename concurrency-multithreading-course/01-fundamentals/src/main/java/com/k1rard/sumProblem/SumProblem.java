package com.k1rard.sumProblem;

public class SumProblem {

    // Linear running time algorithm O(N)
    public int sum(int[] nums) {
        int sum = 0;

        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
        }

        return sum;
    }
}
