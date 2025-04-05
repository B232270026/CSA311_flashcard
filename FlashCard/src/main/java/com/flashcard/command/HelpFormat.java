package com.flashcard.command;

public class HelpFormat {
    public static void printHelp() {
        System.out.println(" Flashcard <cards-file> [options] ");
        System.out.println(" Options:");
        System.out.println("  --help               Show this help message");
        System.out.println("  --order <type>       Card order (random, worst-first, recent-mistakes-first)");
        System.out.println("  --repetitions <num>  Required correct answers per card (default: 1)");
        System.out.println("  --invertCards        Show answers instead of questions");
    }
}
