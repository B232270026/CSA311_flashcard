package com.flashcard;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.flashcard.achievement.AchievementTracker;
import com.flashcard.card.Card;

public class AchievementTrackerTest {

    @Test
    public void testAchievements() {
        Card card = new Card("Q", "A");

        Map<Card, Integer> correctMap = new HashMap<>();
        Map<Card, Integer> totalMap = new HashMap<>();
        AchievementTracker tracker = new AchievementTracker(correctMap, totalMap);

        tracker.startRound();
        for (int i = 0; i < 6; i++) {
            tracker.recordAnswer(card, i >= 3, 4000); // эхний 3 буруу, дараагийн 3 зөв
        }
        tracker.endRound();
        tracker.printAchievements(); // гарч байгаа output-г гараар шалгах
    }
}
