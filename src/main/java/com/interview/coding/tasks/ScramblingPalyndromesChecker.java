package com.interview.coding.tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

        Map<Integer, Integer> charOccurrences = new HashMap<>(firstWord.length());

        firstWord.chars().forEach(c -> increaseCharOccurrences(charOccurrences, c, 1));
        secondWord.chars().forEach(c -> increaseCharOccurrences(charOccurrences, c, -1));

        Optional<Integer> nonZeroCharOccurrence = charOccurrences.values().stream()
                .filter(occurrencesCount -> occurrencesCount != 0 )
                .findAny();

        return !nonZeroCharOccurrence.isPresent();
    }

    private Integer increaseCharOccurrences(Map<Integer, Integer> charOccurrences, int character, int increaseBy) {
        return charOccurrences.merge(character, increaseBy, (oldValue, newValue) -> oldValue + newValue);
    }

}
