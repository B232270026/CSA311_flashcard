package com.flashcard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.flashcard.card.Card;
import com.flashcard.process.CardLoader;
public class CardLoaderTest {

    @Test
    public void testLoadCardsFromFile() throws IOException {
        // create temp file with test data
        Path tempFile = Files.createTempFile("flashcards", ".txt");
        Files.write(tempFile, List.of(
            "Capital of France|Paris",
            "2 + 2|4"
        ));

        List<Card> cards = CardLoader.loadCardsFromFile(tempFile.toString());

        assertEquals(2, cards.size());

        Card card1 = cards.get(0);
        assertEquals("Capital of France", card1.getQuestion());
        assertEquals("Paris", card1.getAnswer());

        Card card2 = cards.get(1);
        assertEquals("2 + 2", card2.getQuestion());
        assertEquals("4", card2.getAnswer());
    }
}
