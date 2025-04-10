package com.flashcard.achievement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flashcard.card.Card;

public class AchievementTracker {
    private final Map<Card, Integer> correctMap;
    private final Map<Card, Integer> totalMap;
    private final Map<Card, Boolean> lastRoundResults = new HashMap<>();
    private long startTime;
    private long totalTime;
    private int totalAnswers;
    
    // Track individual card attempts within a session
    private final Map<Card, Integer> attemptCount = new HashMap<>();
    private final Map<Card, Integer> correctStreak = new HashMap<>();
    
    // Track timing information
    private final Map<Card, Long> totalTimePerCard = new HashMap<>();
    private final Map<Card, Integer> answersPerCard = new HashMap<>();
    
    // Set to track achievements already earned to avoid duplicates
    private final List<String> achievementsEarned = new ArrayList<>();

    public AchievementTracker(Map<Card, Integer> correctMap, Map<Card, Integer> totalMap) {
        this.correctMap = correctMap;
        this.totalMap = totalMap;
    }

    public void startRound() {
        startTime = System.currentTimeMillis();
        lastRoundResults.clear();
        totalAnswers = 0;
    }

    public void endRound() {
        totalTime = System.currentTimeMillis() - startTime;
        
        // Check for CORRECT achievement
        boolean allCorrect = true;
        for (Map.Entry<Card, Boolean> entry : lastRoundResults.entrySet()) {
            if (!entry.getValue()) {
                allCorrect = false;
                break;
            }
        }
        
        if (allCorrect && !lastRoundResults.isEmpty()) {
            String achievement = "CORRECT: Suuliin toirogt buh kartad zuw hariulsan";
            if (!achievementsEarned.contains(achievement)) {
                achievementsEarned.add(achievement);
            }
        }
        
        // Check for SPEED achievement
        if (totalAnswers > 0 && (totalTime / totalAnswers) < 5000) {
            String achievement = "SPEED: Dundjaar 5 sec-s baga hugatsaand zuw hariulsan";
            if (!achievementsEarned.contains(achievement)) {
                achievementsEarned.add(achievement);
            }
        }
    }

    public void recordAnswer(Card card, boolean isCorrect, long timeMs) {
        totalAnswers++;
        lastRoundResults.put(card, isCorrect);
        
        // REPEAT амжилтын оролдлогыг хянах
        attemptCount.put(card, attemptCount.getOrDefault(card, 0) + 1);
        
        // Track timing information
        totalTimePerCard.put(card, totalTimePerCard.getOrDefault(card, 0L) + timeMs);
        answersPerCard.put(card, answersPerCard.getOrDefault(card, 0) + 1);
        
        // Track correct answers for CONFIDENT achievement
        if (isCorrect) {
            correctStreak.put(card, correctStreak.getOrDefault(card, 0) + 1);
        } else {
            correctStreak.put(card, 0);
        }
        
        // Check for REPEAT achievement (now correctly checking for GREATER THAN 5)
        int attempts = attemptCount.getOrDefault(card, 0);
        if (attempts > 5) {  // Changed from attempts >= 5 to attempts > 5
            String achievement = "REPEAT: Neg kartad 5-aas olon udaa hariulsan";
            if (!achievementsEarned.contains(achievement)) {
                achievementsEarned.add(achievement);
            }
        }
        
        // Check for CONFIDENT achievement
        if (correctStreak.getOrDefault(card, 0) >= 3) {
            String achievement = "CONFIDENT: Neg kartad dor hayj 3 udaa zuw hariulsan";
            if (!achievementsEarned.contains(achievement)) {
                achievementsEarned.add(achievement);
            }
        }
    }

    public void printAchievements() {
        if (achievementsEarned.isEmpty()) {
            System.out.println("Odoogoor amjilt baihgui baina.");
        } else {
            System.out.println("\n--------------- Amjiltuud ---------------");
            for (String achievement : achievementsEarned) {
                System.out.println(achievement);
            }
            
            // Show detailed statistics
            System.out.println("\n---------------Delgerengui medeelel---------------");
            
            // Display cards with more than 5 attempts
            List<Card> repeatedCards = new ArrayList<>();
            for (Map.Entry<Card, Integer> entry : attemptCount.entrySet()) {
                if (entry.getValue() > 5) {
                    repeatedCards.add(entry.getKey());
                }
            }
            
            if (!repeatedCards.isEmpty()) {
                System.out.println("\n5-aas olon udaa hariulsan asuultuud:");
                for (Card card : repeatedCards) {
                    System.out.println("\"" + card.getQuestion() + "\" - " + attemptCount.get(card) + " udaa hariulsan");
                }
            }
            
            // Display average time statistics
            if (totalAnswers > 0) {
                System.out.println("\nDundaj hugatsaanii medeelel:");
                System.out.println("Niit asuultand hatiulsan dundaj hugatsaa: " + (totalTime / totalAnswers) + " ms");
                
                // Display per-card time statistics for cards answered multiple times
                System.out.println("\nKart bur deer zartsuulsan hugatsaa:");
                for (Map.Entry<Card, Integer> entry : answersPerCard.entrySet()) {
                    if (entry.getValue() > 1) {
                        Card card = entry.getKey();
                        long avgTime = totalTimePerCard.get(card) / entry.getValue();
                        System.out.println("\"" + card.getQuestion() + "\" - dundaj hugatsaa " + avgTime + " ms (" + entry.getValue() + " udaa hariulsan)");
                    }
                }
            }
        }
    }
}