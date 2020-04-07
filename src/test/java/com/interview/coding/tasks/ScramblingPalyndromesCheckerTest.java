package com.interview.coding.tasks;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScramblingPalyndromesCheckerTest {

    ScramblingPalyndromesChecker palyndromeChecker = new ScramblingPalyndromesChecker();

    @Test
    public void shouldCheckThatWords_areScramblingPalyndromes_ifBothWordsAreEmpty() throws Exception {
        Assertions.assertThat(palyndromeChecker.checkWhetherWordsArePalyndromes("", ""))
            .as("Success field should be true, that means that words ARE palyndromes, because both words are equally empty")
            .hasFieldOrPropertyWithValue("success", true);
    }

    @Test
    public void shouldCheckThatWords_areScramblingPalyndromes_ifBothWordsAreNull() throws Exception {
        Assertions.assertThat(palyndromeChecker.checkWhetherWordsArePalyndromes(null, null))
            .as("Success field should be true, that means that words ARE palyndromes, because both words are equally null")
            .hasFieldOrPropertyWithValue("success", true);
    }

    @Test
    public void shouldCheckThatWords_areScramblingPalyndromes_ifWordsAreEqual() throws Exception {
        Assertions.assertThat(palyndromeChecker.checkWhetherWordsArePalyndromes("aac", "aac"))
            .as("Success field should be true, that means that words ARE palyndromes, because both words are equal")
            .hasFieldOrPropertyWithValue("success", true);
    }

    @Test
    public void shouldCheckThatWords_areScramblingPalyndromes_ifOneWordCanBeMadeFromAnotherByChangingOrderOfItsChars() throws Exception {
        Assertions.assertThat(palyndromeChecker.checkWhetherWordsArePalyndromes("aac", "aca"))
            .as("Success field should be true, that means that words ARE palyndromes, because one word can be made from another by changing order of chars")
            .hasFieldOrPropertyWithValue("success", true);
    }

    @Test
    public void shouldCheckThatWords_areNot_ScramblingPalyndromes_ifTheyHaveDifferentLengths() throws Exception {
        Assertions.assertThat(palyndromeChecker.checkWhetherWordsArePalyndromes("aac", "aa"))
            .as("Success field should be false, that means that words are NOT palyndromes, because words have different length")
            .hasFieldOrPropertyWithValue("success", false);
    }

    @Test
    public void shouldCheckThatWords_areNot_ScramblingPalyndromes_ifTheyHaveDifferentCharactersSets() throws Exception {
        Assertions.assertThat(palyndromeChecker.checkWhetherWordsArePalyndromes("aac", "abc"))
            .as("Success field should be false, that means that words are NOT palyndromes, because there is no char 'b' in the first word")
            .hasFieldOrPropertyWithValue("success", false);
    }

    @Test
    public void shouldCheckThatWords_areNot_ScramblingPalyndromes_ifTheyHaveSameCharacters_butDifferentCountOfCharOccurences() throws Exception {
        Assertions.assertThat(palyndromeChecker.checkWhetherWordsArePalyndromes("aac", "acc"))
            .as("Success field should be false, that means that words are NOT palyndromes, because words have different count of chars 'a' and 'c'")
            .hasFieldOrPropertyWithValue("success", false);
    }
}
