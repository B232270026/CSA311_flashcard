package com.flashcard.sorter;

public class OrganizerFactory {
    public static CardOrganizer create(String type) {
        return switch (type) {
            case "worst-first" -> new WorstFirstSorter();
            case "recent-mistakes-first" -> new RecentMistakesFirstSorter();
            default -> new RandomSorter();
        };
    }
}
