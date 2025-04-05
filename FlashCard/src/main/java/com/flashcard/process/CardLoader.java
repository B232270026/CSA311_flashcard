package com.flashcard.process;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.flashcard.card.Card;

public class CardLoader {
    public static List<Card> loadCards(String filename) throws IOException {
        List<Card> cards = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filename));

        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length == 2) {
                cards.add(new Card(parts[0], parts[1]));
            }
        }
        return cards;
    }
}
