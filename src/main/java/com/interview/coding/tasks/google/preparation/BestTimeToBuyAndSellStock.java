package com.interview.coding.tasks.google.preparation;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 * Note that you cannot sell a stock before you buy one.
 *
 * Example 1:
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *              Not 7-1 = 6, as selling price needs to be larger than buying price.
 *
 * Example 2:
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 * Example 3:
 * Input: [7,2,3,4,1,2]
 * Output: 2
 * Explanation: Buy on day 2 (price = 2) and sell on day 4 (price = 4), profit = 4-2 = 2.
 *              Not 2-1 = 1, because there is higher profit before the lowest price.
 */
public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        runAlgorithm(BestTimeToBuyAndSellStock::findMaxProfitSingleRun, "Max profit singe run");
    }

    private static void runAlgorithm(Function<int[], Integer> algorithm, String algorithmName) {
        System.out.println(algorithmName);
        System.out.println(algorithm.apply(null)); // Output: 0
        System.out.println(algorithm.apply(new int[]{5})); // Output: 0
        System.out.println(algorithm.apply(new int[5])); // Output: 0
        System.out.println(algorithm.apply(new int[]{1, 2})); // Output: 1
        System.out.println(algorithm.apply(new int[]{2, 1})); // Output: 0
        System.out.println(algorithm.apply(new int[]{7, 1, 5, 3, 6, 4})); // Output: 5
        System.out.println(algorithm.apply(new int[]{7, 6, 4, 3, 1})); // Output: 0
        System.out.println(algorithm.apply(new int[]{7, 2, 3, 4, 1, 2})); // Output: 2
    }

    /**
     * Time complexity: O(n) - 1 time looping through prices
     * Space complexity: O(1) - constant for 2 int variables
     *
     * @param prices
     * @return
     */
    public static int findMaxProfitSingleRun(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) { // O(n)
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }

        return maxProfit;
    }

}
