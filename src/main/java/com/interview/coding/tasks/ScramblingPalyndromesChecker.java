package com.interview.coding.tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
     * @return true if words are palyndromes.
     */
    public boolean checkWhetherWordsArePalyndromes(String firstWord, String secondWord) {
        if (firstWord == null && secondWord == null) {
            return true;
        }

        if (firstWord == null || secondWord == null) {
            return false;
        }

        if (firstWord.equals(secondWord)) {
            return true;
        }

        if (firstWord.length() != secondWord.length()) {
            return false;
        }

        Map<Character, Integer> wordCharOccurrences = new HashMap<>(firstWord.length());

        // I'm not sure it's better than the option below, it is neither shorter nor clearer. But it uses Java 8.
        IntStream.range(0, firstWord.length()).forEach(i -> {
            wordCharOccurrences.merge(firstWord.charAt(i), 1, (current, update) -> current + update);
            wordCharOccurrences.merge(secondWord.charAt(i), -1, (current, update) -> current + update);
        });

//        for (int i = 0; i < firstWord.length(); i++) {
//            wordCharOccurrences.merge(firstWord.charAt(i), 1, (current, update) -> current + update);
//            wordCharOccurrences.merge(secondWord.charAt(i), -1, (current, update) -> current + update);
//        }

//        for (int i = 0; i < firstWord.length(); i++) {
//            wordCharOccurrences.put(firstWord.charAt(i), wordCharOccurrences.getOrDefault(character, 0) + 1);
//            wordCharOccurrences.put(secondWord.charAt(i), wordCharOccurrences.getOrDefault(character, 0) - 1);
//        }

//        for (int i = 0; i < firstWord.length(); i++) {
//            increaseOccurence(wordCharOccurrences, firstWord.charAt(i));
//            decreaseOccurence(wordCharOccurrences, secondWord.charAt(i));
//        }

        for (Entry<Character, Integer> wordCharOccurrence : wordCharOccurrences.entrySet()) {
            if (wordCharOccurrence.getValue() != 0) {
                return false;
            }
        }

        return true;
    }

}
