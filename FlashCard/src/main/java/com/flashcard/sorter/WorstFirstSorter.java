package com.flashcard.sorter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.flashcard.card.Card;

public class WorstFirstSorter implements CardOrganizer {
    public List<Card> organize(List<Card> cards, Map<Card, Integer> correct, Map<Card, Integer> total) {
        List<Card> sorted = new ArrayList<>(cards);
        sorted.sort((a, b) -> {
            int aWrong = total.getOrDefault(a, 0) - correct.getOrDefault(a, 0);
            int bWrong = total.getOrDefault(b, 0) - correct.getOrDefault(b, 0);
            return Integer.compare(bWrong, aWrong);
        });
        return sorted;
    } 
}
