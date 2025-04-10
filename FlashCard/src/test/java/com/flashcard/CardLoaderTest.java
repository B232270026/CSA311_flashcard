package com.flashcard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.flashcard.card.Card;
import com.flashcard.process.CardLoader;

public class CardLoaderTest {

    @TempDir
    Path tempDir;

    @Test
    public void testLoadCardsFromFile() throws IOException {
        // Create a temporary file with some test data
        Path tempFile = tempDir.resolve("test-cards.txt");
        Files.writeString(tempFile, 
            "Question1|Answer1\n" +
            "Question2|Answer2\n" +
            "Question3|Answer3\n"
        );
        
        List<Card> cards = CardLoader.loadCardsFromFile(tempFile.toString());
        
        assertEquals(3, cards.size());
        assertEquals("Question1", cards.get(0).getQuestion());
        assertEquals("Answer1", cards.get(0).getAnswer());
        assertEquals("Question2", cards.get(1).getQuestion());
        assertEquals("Answer2", cards.get(1).getAnswer());
        assertEquals("Question3", cards.get(2).getQuestion());
        assertEquals("Answer3", cards.get(2).getAnswer());
    }

    @Test
    public void testLoadCardsWithEmptyLines() throws IOException {
        Path tempFile = tempDir.resolve("test-empty-lines.txt");
        Files.writeString(tempFile, 
            "Question1|Answer1\n" +
            "\n" +
            "Question2|Answer2\n" +
            "\n"
        );
        
        List<Card> cards = CardLoader.loadCardsFromFile(tempFile.toString());
        
        assertEquals(2, cards.size());
        assertEquals("Question1", cards.get(0).getQuestion());
        assertEquals("Answer1", cards.get(0).getAnswer());
        assertEquals("Question2", cards.get(1).getQuestion());
        assertEquals("Answer2", cards.get(1).getAnswer());
    }

    @Test
    public void testLoadCardsWithWhitespace() throws IOException {
        Path tempFile = tempDir.resolve("test-whitespace.txt");
        Files.writeString(tempFile, 
            "  Question1  |  Answer1  \n" +
            "Question2|Answer2\n"
        );
        
        List<Card> cards = CardLoader.loadCardsFromFile(tempFile.toString());
        
        assertEquals(2, cards.size());
        assertEquals("Question1", cards.get(0).getQuestion());
        assertEquals("Answer1", cards.get(0).getAnswer());
    }

    @Test
    public void testInvalidLineFormat() throws IOException {
        Path tempFile = tempDir.resolve("test-invalid.txt");
        Files.writeString(tempFile, 
            "Question1|Answer1\n" +
            "InvalidLine\n" +
            "Question2|Answer2\n"
        );
        
        List<Card> cards = CardLoader.loadCardsFromFile(tempFile.toString());
        
        assertEquals(2, cards.size());
        assertEquals("Question1", cards.get(0).getQuestion());
        assertEquals("Answer1", cards.get(0).getAnswer());
        assertEquals("Question2", cards.get(1).getQuestion());
        assertEquals("Answer2", cards.get(1).getAnswer());
    }

    @Test
    public void testFileNotFound() {
        assertThrows(IOException.class, () -> {
            CardLoader.loadCardsFromFile("non-existent-file.txt");
        });
    }
}