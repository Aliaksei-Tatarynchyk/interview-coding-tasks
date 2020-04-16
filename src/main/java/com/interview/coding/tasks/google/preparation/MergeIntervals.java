package com.interview.coding.tasks.google.preparation;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Function;

/**
 * https://leetcode.com/problems/merge-intervals/
 *
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 * Example 2:
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class MergeIntervals {
    public static void main(String[] args) {
        runAlgorithm(MergeIntervals::mergeIntervalsWithSorting, "With sorting");
    }

    private static void runAlgorithm(Function<int[][], int[][]> algorithm, String algorithmName) {
        System.out.println(algorithmName);
        System.out.println(algorithm.andThen(MergeIntervals::print2DimArray).apply(null));
        System.out.println(algorithm.andThen(MergeIntervals::print2DimArray).apply(new int[][] {{8,10}})); // [8,10]
        System.out.println(algorithm.andThen(MergeIntervals::print2DimArray).apply(new int[][] {{8,10}, {2,6}, {1,3}, {15,18}})); // [1.6] [8,10] [15,18]
        System.out.println(algorithm.andThen(MergeIntervals::print2DimArray).apply(new int[][] {{8,10}, {1,6}, {1,3}})); // [1,6] [8,10]
        System.out.println(algorithm.andThen(MergeIntervals::print2DimArray).apply(new int[][] {{1,4}, {4,5}})); // [1,5]
        System.out.println(algorithm.andThen(MergeIntervals::print2DimArray).apply(new int[][] {{1,5}, {2,4}})); // [1,5]
    }

    /**
     * Time complexity: O(n log n) because sorting is dominating
     * Space complexity: O(2n) = O(n) for sortedIntervals and mergedIntervals
     *
     * notes:
     * - can be optimized by not cloning input array, but in this case input data will be changed after sorting
     *
     * @param intervals
     * @return
     */
    public static int[][] mergeIntervalsWithSorting(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;

        // time: O(n log n) for sorting
        int[][] sortedIntervals = Arrays.copyOf(intervals, intervals.length);
        Arrays.sort(sortedIntervals, (o1, o2) -> o1[0] - o2[0]);

        // time: O(n) for loop; memory: O(n) for stack
        Deque<int[]> mergedIntervals = new LinkedList<>();
        for (int[] current: sortedIntervals) {
            int[] previous = mergedIntervals.peekLast();

            // if overlap
            if (previous != null && previous[1] >= current[0]) { // >= means to merge {1,4} and {4,5}
                // merge current and next
                previous[1] = Math.max(previous[1], current[1]);
            } else {
                mergedIntervals.addLast(current);
            }
        }

        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }

    private static String print2DimArray(int[][] array) {
        if (array == null) return "null";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) continue;
            sb.append(Arrays.toString(array[i])).append(", ");
        }
        sb.append(']');

        return sb.toString();
    }
}
