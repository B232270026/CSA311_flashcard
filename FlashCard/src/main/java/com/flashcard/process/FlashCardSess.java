package com.flashcard.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.flashcard.achievement.AchievementTracker;
import com.flashcard.card.Card;
import com.flashcard.command.CommandLineOption;
import com.flashcard.sorter.CardOrganizer;

public class FlashCardSess {
    private final List<Card> cards;
    private final CardOrganizer organizer;
    private final CommandLineOption options;
    private final Map<Card, Integer> correctMap = new HashMap<>();
    private final Map<Card, Integer> totalMap = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);
    private final AchievementTracker tracker;


    public FlashCardSess(List<Card> cards, CardOrganizer organizer, CommandLineOption options) {
        this.cards = cards;
        this.organizer = organizer;
        this.options = options;
        this.tracker = new AchievementTracker(correctMap, totalMap);
    }

    public void start() {
        List<Card> toAsk = cards;
        tracker.startRound();

        boolean done;
        do {
            done = true;
            toAsk = organizer.organize(toAsk, correctMap, totalMap);
            for (Card card : toAsk) {
                int correct = correctMap.getOrDefault(card, 0);
                if (correct >= options.getRepetitions()) continue;

                ask(card);
                if (correctMap.getOrDefault(card, 0) < options.getRepetitions()) {
                    done = false;
                }
            }
        } while (!done);

        System.out.println("All cards completed!");
        tracker.endRound();
        tracker.printAchievements();
    }

    private void ask(Card card) {
        String front = options.isInvert() ? card.getAnswer() : card.getQuestion();
        String back = options.isInvert() ? card.getQuestion() : card.getAnswer();
        long start = System.nanoTime();
        System.out.println("Question: " + front);
        String userAnswer = scanner.nextLine().trim();
        long end = System.nanoTime();
        totalMap.put(card, totalMap.getOrDefault(card, 0) + 1);
        boolean isCorrect = userAnswer.equalsIgnoreCase(back);
        totalMap.put(card, totalMap.getOrDefault(card, 0) + 1);
        
        if (isCorrect) {
            System.out.println("Correct!");
            correctMap.put(card, correctMap.getOrDefault(card, 0) + 1);
        } else {
            System.out.println(" Incorrect. Correct Answer: " + back);
        }
        
        tracker.recordAnswer(card, isCorrect, (end - start) / 1_000_000);

       

        
    }
}
