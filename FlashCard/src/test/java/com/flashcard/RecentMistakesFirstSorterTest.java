package com.flashcard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.flashcard.card.Card;
import com.flashcard.sorter.RecentMistakesFirstSorter;

public class RecentMistakesFirstSorterTest {

    @Test
    public void testSort_withRecentMistakes() {
        Card card1 = new Card("Q1", "A1");
        Card card2 = new Card("Q2", "A2");
        Card card3 = new Card("Q3", "A3");

        List<Card> cards = Arrays.asList(card1, card2, card3);

        Map<Card, Integer> correct = new HashMap<>();
        Map<Card, Integer> total = new HashMap<>();

        correct.put(card1, 2);
        total.put(card1, 2);

        correct.put(card2, 1);
        total.put(card2, 3);

        correct.put(card3, 3);
        total.put(card3, 3);

        RecentMistakesFirstSorter sorter = new RecentMistakesFirstSorter();
        List<Card> sorted = sorter.organize(cards, correct, total);

        assertEquals(card2, sorted.get(0), "Card with recent mistake should be first");
        
      
        assertTrue(sorted.containsAll(cards), "All cards should be present in the result");
        assertEquals(3, sorted.size(), "There should be 3 cards total");
    }

    @Test
    public void testSort_noMistakes_allCorrect() {
        Card card1 = new Card("Q1", "A1");
        Card card2 = new Card("Q2", "A2");

        List<Card> cards = Arrays.asList(card1, card2);

        Map<Card, Integer> correct = new HashMap<>();
        Map<Card, Integer> total = new HashMap<>();

        correct.put(card1, 3);
        total.put(card1, 3);

        correct.put(card2, 2);
        total.put(card2, 2);

        RecentMistakesFirstSorter sorter = new RecentMistakesFirstSorter();
        List<Card> sorted = sorter.organize(cards, correct, total);

        // No mistakes, so order should be unchanged
        assertEquals(cards, sorted, "When no mistakes, order should be unchanged");
    }

    @Test
    public void testSort_allMistakes() {
        Card card1 = new Card("Q1", "A1");
        Card card2 = new Card("Q2", "A2");

        List<Card> cards = Arrays.asList(card1, card2);

        Map<Card, Integer> correct = new HashMap<>();
        Map<Card, Integer> total = new HashMap<>();

        correct.put(card1, 0);
        total.put(card1, 2);

        correct.put(card2, 1);
        total.put(card2, 3);

        RecentMistakesFirstSorter sorter = new RecentMistakesFirstSorter();
        List<Card> sorted = sorter.organize(cards, correct, total);

        assertEquals(card1, sorted.get(0));
        assertEquals(card2, sorted.get(1));
    }
}
