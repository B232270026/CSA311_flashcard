package com.flashcard.achievement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.flashcard.card.Card;

public class AchievementTracker {
    private final Map<Card, Integer> correctMap;
    private final Map<Card, Integer> totalMap;
    private final Map<Card, List<Long>> timeMap;
    private final Set<Card> wrongInCurrentRound = new HashSet<>();
    private long roundStartTime;
    private long roundEndTime;
    private int repetitionsRequired;

    public AchievementTracker(Map<Card, Integer> correctMap, Map<Card, Integer> totalMap) {
        this.correctMap = correctMap;
        this.totalMap = totalMap;
        this.timeMap = new HashMap<>();
        this.repetitionsRequired = repetitionsRequired;
    }

    public void startRound() {
        wrongInCurrentRound.clear();
        roundStartTime = System.nanoTime();
    }

    public void recordAnswer(Card card, boolean correct, long responseTime) {
        if (!correct) wrongInCurrentRound.add(card);
        timeMap.computeIfAbsent(card, k -> new ArrayList<>()).add(responseTime);
    }

    public void endRound() {
        roundEndTime = System.nanoTime();
    }

    

    public void printAchievements() {
        System.out.println("\n Achievements:");
        // FAST
        long totalTimeMs = (roundEndTime - roundStartTime) / 1_000_000;
        long totalAnswers = timeMap.values().stream().mapToLong(List::size).sum();
        double avgTime = (double) totalTimeMs / totalAnswers;

        if (avgTime < 5000) {
            System.out.println(" FAST: Dundaj hariult < 5 second (" + String.format("%.2f", avgTime / 1000.0) + " sec)");
        }

        // CORRECT
        if (wrongInCurrentRound.isEmpty()) {
            System.out.println(" CORRECT: Buh asuultand zuw hariulsan!");
        }

        // REPEAT
        for (Card c : totalMap.keySet()) {
            if (totalMap.get(c) > 5) {
                System.out.println(" REPEAT: '" + c.getQuestion() + "' davtamjtai asuult (> 5 udaa)");
            }
        }

        // CONFIDENT
        for (Card c : correctMap.keySet()) {
            if (correctMap.get(c) > 3) {
                System.out.println(" CONFIDENT: '" + c.getQuestion() + "' deer itgeltei baisan (>=3 correct)");
            }
        }
    }
}

