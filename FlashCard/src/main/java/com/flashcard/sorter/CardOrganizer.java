package com.flashcard.sorter;
import java.util.List;
import java.util.Map;

import com.flashcard.card.Card;

public interface CardOrganizer {
    List<Card> organize(List<Card> cards, Map<Card, Integer> correct, Map<Card, Integer> total);
}
