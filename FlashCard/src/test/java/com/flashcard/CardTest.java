package com.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.flashcard.card.Card;

public class CardTest {

    @Test
    public void testCardCreation() {
        Card card = new Card("Question", "Answer");
        assertEquals("Question", card.getQuestion());
        assertEquals("Answer", card.getAnswer());
    }

    @Test
    public void testCardEquality() {
        Card card1 = new Card("Question", "Answer");
        Card card2 = new Card("Question", "Answer");
        Card card3 = new Card("Different", "Answer");
        
        assertEquals(card1, card2);
        assertNotEquals(card1, card3);
        assertNotEquals(card1, null);
        assertNotEquals(card1, "Not a card");
    }

    @Test
    public void testCardInvert() {
        Card card = new Card("Question", "Answer");
        card.invert();
        
        assertEquals("Answer", card.getQuestion());
        assertEquals("Question", card.getAnswer());
    }

    @Test
    public void testCheckAnswer() {
        Card card = new Card("Question", "Answer");
        
        assertTrue(card.checkAnswer("Answer"));
        assertTrue(card.checkAnswer("answer"));
        assertTrue(card.checkAnswer("ANSWER"));
        assertFalse(card.checkAnswer("Wrong"));
    }

    @Test
    public void testTrimWhitespace() {
        Card card = new Card("  Question  ", "  Answer  ");
        
        assertEquals("Question", card.getQuestion());
        assertEquals("Answer", card.getAnswer());
    }
}
