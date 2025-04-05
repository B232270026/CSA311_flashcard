package com.flashcard.process;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.flashcard.card.Card;

public class CardLoader {
    public static List<Card> loadCardsFromFile(String filename) throws IOException {
        List<Card> cards = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filename));
        
        for (String line : lines) {
            if (line.trim().isEmpty()) continue; // хоосон мөр алгасах
            String[] parts = line.split("\\|");
            if (parts.length == 2) {
                cards.add(new Card(parts[0].trim(), parts[1].trim()));
            } else {
                System.err.println("Invalid line (skip): " + line);
            }
        }

        return cards;
    }
}
