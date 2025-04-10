// RecentMistakesFirstSorter implementation
package com.flashcard.sorter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.flashcard.card.Card;

public class RecentMistakesFirstSorter implements CardOrganizer {
    private List<Card> recentMistakes = new ArrayList<>();
    
    @Override
    public List<Card> organize(List<Card> cards, Map<Card, Integer> correctMap, Map<Card, Integer> totalMap) {
        List<Card> result = new ArrayList<>();
        
        // First add cards with recent mistakes (maintaining their original order)
        for (Card card : recentMistakes) {
            if (cards.contains(card)) {
                result.add(card);
            }
        }
        
        // Then add remaining cards in their original order
        for (Card card : cards) {
            if (!result.contains(card)) {
                result.add(card);
            }
        }
        
        // Clear the recent mistakes list
        recentMistakes.clear();
        
        // Record cards that are not correctly answered for the next round
        for (Card card : cards) {
            int correct = correctMap.getOrDefault(card, 0);
            int total = totalMap.getOrDefault(card, 0);
            
            // If the card has been attempted but not always correct
            if (total > 0 && correct < total) {
                recentMistakes.add(card);
            }
        }
        
        return result;
    }
}
