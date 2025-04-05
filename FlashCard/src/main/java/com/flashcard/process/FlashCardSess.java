package com.flashcard.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
    

    public FlashCardSess(List<Card> cards, CardOrganizer organizer, CommandLineOption options) {
        this.cards = cards;
        this.organizer = organizer;
        this.options = options;
    }

    public void start() {
        List<Card> toAsk = cards;

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
    }

    private void ask(Card card) {
        String front = options.isInvert() ? card.getAnswer() : card.getQuestion();
        String back = options.isInvert() ? card.getQuestion() : card.getAnswer();

        System.out.println("Q: " + front);
        String userAnswer = scanner.nextLine().trim();

        totalMap.put(card, totalMap.getOrDefault(card, 0) + 1);

        if (userAnswer.equalsIgnoreCase("exit")) {
            System.exit(0);
        }

        if (userAnswer.equalsIgnoreCase(back)) {
            System.out.println("Correct!");
            correctMap.put(card, correctMap.getOrDefault(card, 0) + 1);
        } else {
            System.out.println("Incorrect. Correct answer: " + back);
        }
    }
}
