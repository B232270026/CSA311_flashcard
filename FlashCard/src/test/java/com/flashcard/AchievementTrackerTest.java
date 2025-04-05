package com.flashcard;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flashcard.achievement.AchievementTracker;
import com.flashcard.card.Card;
public class AchievementTrackerTest {

    private Map<Card, Integer> correctMap;
    private Map<Card, Integer> totalMap;
    private AchievementTracker tracker;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUp() {
        correctMap = new HashMap<>();
        totalMap = new HashMap<>();
        tracker = new AchievementTracker(correctMap, totalMap);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testFastAchievementUnlocked() {
        Card card = new Card("What is 2+2?", "4");
        correctMap.put(card, 1);
        totalMap.put(card, 1);

        tracker.startRound();
        tracker.recordAnswer(card, true, 2000); // 2 seconds
        tracker.endRound();

        tracker.printAchievements();

        String output = outContent.toString();
        assertTrue(output.contains("FAST"));
    }

    @Test
    public void testCorrectAchievementUnlocked() {
        Card card = new Card("What is 2+2?", "4");
        correctMap.put(card, 1);
        totalMap.put(card, 1);

        tracker.startRound();
        tracker.recordAnswer(card, true, 4000);
        tracker.endRound();

        tracker.printAchievements();

        String output = outContent.toString();
        assertTrue(output.contains("CORRECT"));
    }

    @Test
    public void testRepeatAchievementUnlocked() {
        Card card = new Card("What is 2+2?", "4");
        correctMap.put(card, 2);
        totalMap.put(card, 6);  // > 5 удаа орсон

        tracker.startRound();
        tracker.recordAnswer(card, true, 4000);
        tracker.endRound();

        tracker.printAchievements();

        String output = outContent.toString();
        assertTrue(output.contains("REPEAT"));
    }

}
