package com.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.flashcard.command.CommandLineOption;

public class CommandLineOptionTest {

    @Test
    public void testDefaultValues() {
        CommandLineOption options = new CommandLineOption(new String[]{});
        
        assertNull(options.getCardsFile());
        assertEquals("random", options.getOrder());
        assertEquals(1, options.getRepetitions());
        assertFalse(options.isInvert());
        assertFalse(options.isHelp());
    }

    @Test
    public void testHelp() {
        CommandLineOption options = new CommandLineOption(new String[]{"--help"});
        
        assertTrue(options.isHelp());
    }

    @Test
    public void testCardsFile() {
        CommandLineOption options = new CommandLineOption(new String[]{"cards.txt"});
        
        assertEquals("cards.txt", options.getCardsFile());
    }

    @Test
    public void testOrder() {
        CommandLineOption options = new CommandLineOption(new String[]{"--order", "worst-first"});
        
        assertEquals("worst-first", options.getOrder());
    }

    @Test
    public void testRepetitions() {
        CommandLineOption options = new CommandLineOption(new String[]{"--repetitions", "3"});
        
        assertEquals(3, options.getRepetitions());
    }

    @Test
    public void testInvertCards() {
        CommandLineOption options = new CommandLineOption(new String[]{"--invertCards"});
        
        assertTrue(options.isInvert());
    }

    @Test
    public void testMultipleOptions() {
        CommandLineOption options = new CommandLineOption(new String[]{
            "cards.txt", 
            "--order", "worst-first",
            "--repetitions", "5",
            "--invertCards"
        });
        
        assertEquals("cards.txt", options.getCardsFile());
        assertEquals("worst-first", options.getOrder());
        assertEquals(5, options.getRepetitions());
        assertTrue(options.isInvert());
    }

    @Test
    public void testIgnoreUnknownOptions() {
        CommandLineOption options = new CommandLineOption(new String[]{
            "--unknown", "value",
            "cards.txt"
        });
        
        assertEquals("cards.txt", options.getCardsFile());
    }
}
