package com.interview.coding.tasks.google.preparation;

import java.util.function.Function;

/**
 * https://leetcode.com/problems/maximum-subarray/
 *
 * Share
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * Example:
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        runAlgorithm(MaximumSubarray::findLargestSumSingleRun, "Single run");
    }

    private static void runAlgorithm(Function<int[], Integer> algorithm, String algorithmName) {
        System.out.println(algorithmName);
        System.out.println(algorithm.apply(null)); // 0
        System.out.println(algorithm.apply(new int[] {-2,1,-3,4,-1,2,1,-5,4})); // [4,-1,2,1] has the largest sum = 6
    }

    /**
     * Time complexity: O(n) - for single loop
     * Space complexity: O(1) - only 2 variables are used, their size is constant
     *
     * @param nums
     * @return
     */
    public static int findLargestSumSingleRun(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxGlobal = Integer.MIN_VALUE;
        int maxLocal = -1000;
        for (int i = 0; i < nums.length; i++) {
            maxLocal = Math.max(nums[i] + maxLocal, nums[i]);
            if (maxLocal > maxGlobal ) maxGlobal = maxLocal;
        }
        return maxGlobal;
    }

}
