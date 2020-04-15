package com.interview.coding.tasks.google.preparation;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Function;

/**
 * https://leetcode.com/problems/valid-parentheses/
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 *
 * Input: "()"
 * Output: true
 * Example 2:
 *
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: "(]"
 * Output: false
 * Example 4:
 *
 * Input: "([)]"
 * Output: false
 * Example 5:
 *
 * Input: "{[]}"
 * Output: true
 */
public class ValidParentheses {

    public static void main(String[] args) {
        runAlgorithm(ValidParentheses::checkValidParenthesesStack, "Stack");
        runAlgorithm(ValidParentheses::checkValidParenthesesStackOptimized, "Stack optimized");
    }

    private static void runAlgorithm(Function<String, Boolean> algorithm, String algorithmName) {
        System.out.println(algorithmName);
        System.out.println(algorithm.apply(null)); // Output: true
        System.out.println(algorithm.apply("(){")); // Output: false
        System.out.println(algorithm.apply("()}")); // Output: false
        System.out.println(algorithm.apply("((")); // Output: false
        System.out.println(algorithm.apply("))")); // Output: false
        System.out.println(algorithm.apply("()}}")); // Output: false
        System.out.println(algorithm.apply("()")); // Output: true
        System.out.println(algorithm.apply("()[]{}")); // Output: true
        System.out.println(algorithm.apply("(]")); // Output: false
        System.out.println(algorithm.apply("([)]")); // Output: false
        System.out.println(algorithm.apply("{[]}")); // Output: true
    }

    /**
     * Time complexity: O(n) - for single loop
     * Space complexity: O(n) - for maintaining stack, in the worst case it will contain all the input chars, e.g. for "(((("
     *
     * @param input
     * @return
     */
    public static boolean checkValidParenthesesStack(String input) {
        if (input == null || input.length() == 0) return true;
        if (input.length() % 2 != 0) return false;

        Deque<Character> stack = new LinkedList<>();

        for (int i = 0; i < input.length(); i++) {
            Character currentBracket = input.charAt(i);
            if (isOpenBracket(currentBracket)) stack.addFirst(currentBracket);
            else {
                if (stack.peekFirst() == null) return false; // there is no open bracket;
                if (!isValidBracketPair(stack.peekFirst(), currentBracket)) return false; // closed bracket doesn't correspond to open bracket
                stack.pollFirst();
            }
        }

        return stack.isEmpty();
    }

    private static boolean isOpenBracket(Character currentBracket) {
        return "{[(".indexOf(currentBracket) != -1;
    }

    private static boolean isValidBracketPair(Character openBracket, Character closedBracket) {
        if ('{' == openBracket && '}' == closedBracket) return true;
        if ('(' == openBracket && ')' == closedBracket) return true;
        if ('[' == openBracket && ']' == closedBracket) return true;
        return false;
    }

    /**
     * Time complexity: O(n) - for single loop
     * Space complexity: O(n) - for stack
     *
     * Note:
     * it should be (?) a bit quicker because of using map for checking open and closed brackets mappings
     *
     * @param input
     * @return
     */
    public static boolean checkValidParenthesesStackOptimized(String input) {
        if (input == null || input.length() == 0) return true;
        if (input.length() % 2 != 0) return false;

        if (input == null || input.length() == 0) return true;
        if (input.length() % 2 != 0) return false;

        Map<Character, Character> bracketsMapping = new HashMap<>();
        bracketsMapping.put('{', '}');
        bracketsMapping.put('[', ']');
        bracketsMapping.put('(', ')');

        Deque<Character> stack = new LinkedList<>();

        for (int i = 0; i < input.length(); i++) {
            Character currentBracket = input.charAt(i);
            if (bracketsMapping.containsKey(currentBracket)) stack.addFirst(currentBracket);
            else {
                Character openBracket = stack.pollFirst();
                if (openBracket == null) return false;
                if (!currentBracket.equals(bracketsMapping.get(openBracket))) return false; // closed bracket doesn't correspond to open bracket
            }
        }

        return stack.isEmpty();
    }

}
