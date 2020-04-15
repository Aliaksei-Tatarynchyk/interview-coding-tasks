package com.interview.coding.tasks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Given an array of positive integers, return all pairs of two numbers sum of which is equal to target.
 * Given numbers = [2, 5, 6, 4, 7, 18, 9, 1, 8, 11, 15], target = 9
 * Result: [[2, 7], [5, 4], [1, 8]]
 *
 * This task came from interview to TicketMaster (April 15, 2020)
 * @author Aliaksei Tatarynchyk
 */
public class FindPairsWhichSumIsNine {
    private static final int TARGET = 9;

    public static void main(String[] args) {
        runAlgorithm(FindPairsWhichSumIsNine::findPairsWithBruteforce, "Bruteforce");
        runAlgorithm(FindPairsWhichSumIsNine::findPairsWithSortingAndBinarySearch, "Sort and binary search");
        runAlgorithm(FindPairsWhichSumIsNine::findPairsWithHashTable, "HashTable");
    }

    public static void runAlgorithm(Consumer<int[]> algorithm, String algorithmName) {
        System.out.println(algorithmName);
        algorithm.accept(null);
        algorithm.accept(new int[0]);
        algorithm.accept(new int[1]);
        algorithm.accept(new int[]{2, 5, 6, 4, 7, 18, 9, 1, 8, 11, 15}); // [2, 7], [5, 4], [1, 8]
        algorithm.accept(new int[]{4, 5, 4, 5}); // [4, 5]
    }

    /**
     * Time complexity: O(n^2) due to inner loop
     * Space complexity: O(1) because no additional data structures are used
     *
     * notes:
     * - it doesn't handle duplicate pairs
     *
     * @param arr
     */
    public static void findPairsWithBruteforce(int[] arr) {
        if (isEdgeCase(arr)) {
            return;
        }

        // Execution time optimization: filter out everything that is greater than TARGET. Introduces extra space complexity.
        // Arrays.stream(arr).filter(i -> i <= TARGET).boxed().collect(Collectors.toList());

        // inner loop is O(n^2)
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == TARGET) printPair(arr[i]);
            }
        }

        System.out.println();
    }

    /**
     * Time complexity: O(2n log n) = O (n log n)
     * Space complexity: O(1) because no additional data structures are used
     *
     * notes:
     * - it doesn't handle duplicated pairs
     * - gives a side-effect of sorting input array, it is generally not a best-practice
     * - it doesn't handle the same pair printed twice in different order
     * - it doesn't handle pairs of the num with itself (not the case for TARGET=9 because 9 % 2 = 1, so it cannot be like 4 + 4 = 8)
     *
     * @param arr
     */
    public static void findPairsWithSortingAndBinarySearch(int[] arr) {
        if (isEdgeCase(arr)) {
            return;
        }

        // time: O(n log n), memory: O(1)
        Arrays.sort(arr); // gives side effect

        // time: total is O(n log n)
        for (int i = 0; i < arr.length; i++) { // time: loop is O(n)
            if (arr[i] > TARGET) continue;
            if (Arrays.binarySearch(arr, TARGET - arr[i]) >= 0) printPair(arr[i]); // time: binary search is O(log n)
        }

        System.out.println();
    }

    /**
     * Time complexity: O(n) because of single loop
     * Space complexity: O(n) due to added set
     *
     * notes:
     * - it doesn't handle duplicated pairs
     * - it doesn't handle pairs of the num with itself (not the case for TARGET=9 because 9 % 2 = 1, so it cannot be like 4 + 4 = 8)
     *
     * @param arr
     */
    public static void findPairsWithHashTable(int[] arr) {
        if (isEdgeCase(arr)) {
            return;
        }

        Set<Integer> set = new HashSet<>(); // memory: O(n)
        for (int i = 0; i < arr.length; i++) { // time: loop is O(n)
            if (set.contains(TARGET - arr[i])) printPair(arr[i]); // time: lookup is O(1)
            else set.add(arr[i]);
        }

        System.out.println();
    }

    private static boolean isEdgeCase(int[] arr) {
        if (arr == null || arr.length <= 1) {
            System.out.println("no pairs found");
            return true;
        }
        return false;
    }

    private static void printPair(int firstNum) {
        System.out.print(String.format("[%d, %d], ", firstNum, TARGET - firstNum));
    }

}
