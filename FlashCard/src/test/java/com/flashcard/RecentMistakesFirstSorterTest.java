package com.flashcard;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.flashcard.card.Card;
import com.flashcard.sorter.RecentMistakesFirstSorter;
public class RecentMistakesFirstSorterTest {
    @Test
    public void testSortingPrioritizesRecentMistakes() {
        Card c1 = new Card("Q1", "A1");
        Card c2 = new Card("Q2", "A2");
        Card c3 = new Card("Q3", "A3");

        List<Card> cards = List.of(c1, c2, c3);

        Map<Card, Integer> correct = Map.of(c1, 1, c2, 0, c3, 1);
        Map<Card, Integer> attempts = Map.of(c1, 1, c2, 1, c3, 1);

        RecentMistakesFirstSorter sorter = new RecentMistakesFirstSorter();
        List<Card> result = sorter.organize(cards, correct, attempts);

        assertEquals(c2, result.get(0)); // Q2 had a mistake
    }
}
