package com.flashcard.sorter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.flashcard.card.Card;

public class RandomSorter implements CardOrganizer {
    public List<Card> organize(List<Card> cards, Map<Card, Integer> correct, Map<Card, Integer> total) {
        List<Card> shuffled = new ArrayList<>(cards);
        Collections.shuffle(shuffled);
        return shuffled;
    }
}