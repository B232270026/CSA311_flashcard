package com.flashcard.sorter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.flashcard.card.Card;

public class RecentMistakesFirstSorter implements CardOrganizer {
    private final Set<Card> recentMistakes = new LinkedHashSet<>();

    public List<Card> organize(List<Card> cards, Map<Card, Integer> correct, Map<Card, Integer> total) {
        List<Card> result = new ArrayList<>();

        for (Card c : cards) {
            int t = total.getOrDefault(c, 0);
            int r = correct.getOrDefault(c, 0);
            if (t > 0 && r < t) {
                recentMistakes.add(c);
            }
        }

        for (Card c : recentMistakes) {
            if (cards.contains(c)) result.add(c);
        }

        for (Card c : cards) {
            if (!result.contains(c)) result.add(c);
        }

        return result;
    }
}
