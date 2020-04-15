package com.interview.coding.tasks.google.preparation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * https://leetcode.com/problems/contains-duplicate/
 *
 * Given an array of integers, find if the array contains any duplicates.
 * Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
 *
 * Example 1:
 * Input: [1,2,3,1]
 * Output: true
 *
 * Example 2:
 * Input: [1,2,3,4]
 * Output: false
 *
 * Example 3:
 * Input: [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 */
public class ContainsDuplicate {

    public static void main(String[] args) {
        runAlgorithm(ContainsDuplicate::containsDuplicatesNaive, "Naive");
        runAlgorithm(ContainsDuplicate::containsDuplicatesWithSorting, "With Sorting");
        runAlgorithm(ContainsDuplicate::containsDuplicatesWithHashSet, "With HashSet");
    }

    private static void runAlgorithm(Function<int[], Boolean> algorithm, String algorithmName) {
        System.out.println(algorithmName);
        System.out.println(algorithm.apply(null)); // Output: false
        System.out.println(algorithm.apply(new int[]{1})); // Output: false
        System.out.println(algorithm.apply(new int[]{1, 2, 3, 1})); // Output: true
        System.out.println(algorithm.apply(new int[]{1, 2, 3, 4})); // Output: false
        System.out.println(algorithm.apply(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2})); // Output: true
    }

    /**
     * Time complexity: O(n^2) because of inner loop
     * Space complexity: O(1) because no additional data structures are used
     *
     * Notes:
     * not efficient for large input because of O(n^2) time complexity
     * it is more efficient if first elements are duplicates, in this particular cases complexity will be ~ O(n)
     *
     * @param nums - array of integers
     * @return true if there are some duplicates in <code>nums</code>
     */
    public static boolean containsDuplicatesNaive(int[] nums) {
        if (isNullOrEmptyOrContainsSingleElement(nums)) return false;

        for (int i = 0; i < nums.length; i++) { // O(n)
            for (int j = i+1; j < nums.length; j++) { //together with the loop above it will give O(n^2)
                if (nums[i] == nums[j]) return true;
            }
        }

        return false;
    }

    /**
     * Time complexity: O(n log n) because sort complexity is O(n log n) and it's dominating
     * Space complexity: O(1) because no additional data structures are used
     *
     * Notes:
     * changes the input - not a good practice
     *
     * @param nums - array of integers
     * @return true if there are some duplicates in <code>nums</code>
     */
    public static boolean containsDuplicatesWithSorting(int[] nums) {
        if (isNullOrEmptyOrContainsSingleElement(nums)) return false;

        Arrays.sort(nums); // O(n log n)
        for (int i = 1; i < nums.length; i++) { // O(n)
            if (nums[i] == nums[i-1]) return true;
        }

        return false;
    }

    /**
     * Time complexity: O(n) because of single loop
     * Space complexity: O(n) because of introducing a HashSet
     *
     * Notes:
     * may not be efficient for small input
     * using of treeset instead of hashset will not be efficient because treeset has O(log n) complexity for insert and search.
     *
     * @param nums - array of integers
     * @return true if there are some duplicates in <code>nums</code>
     */
    public static boolean containsDuplicatesWithHashSet(int[] nums) {
        if (isNullOrEmptyOrContainsSingleElement(nums)) return false;

        Set<Integer> variants = new HashSet<>(nums.length);
        for (int x: nums) {
            if (variants.contains(x)) return true;
            variants.add(x);
        }

        return false;
    }

    private static boolean isNullOrEmptyOrContainsSingleElement(int[] nums) {
        return nums == null || nums.length <= 1;
    }
}
