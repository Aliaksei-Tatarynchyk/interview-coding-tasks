package com.interview.coding.tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Checks whether 2 words are palyndromes. Palyndromes in this particular context means
 * that one word can be made from another by simply changing order of characters. E.g.
 * "abb" and "bab" are palyndromes because one word can be made from another by changing
 * order of chars. "aab" and "abb" have the same character set but they have different
 * count of occurences of characters 'a' and 'b'.
 *
 * Time complexity is O(N);
 * Space complexity is O(N);
 *
 * @author Aliaksei Tatarynchyk
 */
public class ScramblingPalyndromesChecker {

    /**
     * Checks whether 2 words are scrambling palyndromes.
     *
     * @param firstWord
     * @param secondWord
     * @return result of checking palyndromes.
     */
    public Result checkWhetherWordsArePalyndromes(String firstWord, String secondWord) {
        if (firstWord == null && secondWord == null) {
            return new Result(firstWord, secondWord, true, "both words are null");
        }

        if (firstWord == null || secondWord == null) {
            return new Result(firstWord, secondWord, false, "one of words is null and another is not null");
        }

        if (firstWord.equals(secondWord)) {
            return new Result(firstWord, secondWord, true, "words are equal");
        }

        if (firstWord.length() != secondWord.length()) {
            return new Result(firstWord, secondWord, false, "words have different lengths");
        }

        Map<Character, Integer> wordCharOccurrences = new HashMap<>(firstWord.length());

        for (int i = 0; i < firstWord.length(); i++) {
            increaseOccurence(wordCharOccurrences, firstWord.charAt(i));
            decreaseOccurence(wordCharOccurrences, secondWord.charAt(i));
        }

        for (Entry<Character, Integer> wordCharOccurrence : wordCharOccurrences.entrySet()) {
            if (wordCharOccurrence.getValue() != 0) {
                return new Result(firstWord, secondWord, false, "words have different sets of chars");
            }
        }

        return new Result(firstWord, secondWord, true, "words are palyndromes");
    }

    private Integer increaseOccurence(Map<Character, Integer> map, char character) {
        return map.put(character, map.getOrDefault(character, 0) + 1);
    }

    private Integer decreaseOccurence(Map<Character, Integer> map, char character) {
        return map.put(character, map.getOrDefault(character, 0) - 1);
    }

    public static class Result {
        String firstWord;
        String secondWord;

        /** True if words are scrambling palyndromes - one word can be made from another by changing order of characters */
        boolean success;

        /** Explanation of why the words are scrambling palyndromes */
        String explanation;

        public Result(String firstWord, String secondWord, boolean success, String explanation) {
            super();
            this.firstWord = firstWord;
            this.secondWord = secondWord;
            this.success = success;
            this.explanation = explanation;
        }

        public String getFirstWord() {
            return firstWord;
        }

        public String getSecondWord() {
            return secondWord;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getExplanation() {
            return explanation;
        }
    }
}
