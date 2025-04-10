// WorstFirstSorter implementation
package com.flashcard.sorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.flashcard.card.Card;

public class WorstFirstSorter implements CardOrganizer {
    @Override
    public List<Card> organize(List<Card> cards, Map<Card, Integer> correctMap, Map<Card, Integer> totalMap) {
        List<Card> result = new ArrayList<>(cards);
        
        result.sort(new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                double score1 = getSuccessRate(c1, correctMap, totalMap);
                double score2 = getSuccessRate(c2, correctMap, totalMap);
                return Double.compare(score1, score2);
            }
        });
        
        return result;
    }
    
    private double getSuccessRate(Card card, Map<Card, Integer> correctMap, Map<Card, Integer> totalMap) {
        int correct = correctMap.getOrDefault(card, 0);
        int total = totalMap.getOrDefault(card, 0);
        return total == 0 ? 0 : (double) correct / total;
    }
}
