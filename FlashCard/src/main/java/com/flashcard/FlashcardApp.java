package com.flashcard;

import java.io.IOException;
import java.util.List;

import com.flashcard.card.Card;
import com.flashcard.command.CommandLineOption;
import com.flashcard.command.HelpFormat;
import com.flashcard.process.CardLoader;
import com.flashcard.process.FlashCardSess;
import com.flashcard.sorter.CardOrganizer;
import com.flashcard.sorter.OrganizerFactory;

public class FlashcardApp {
    public static void main(String[] args) {
        CommandLineOption options = new CommandLineOption(args);

         if (options.isHelp() || options.getCardsFile() == null) {
            HelpFormat.printHelp();
            return;
        }

        try {
            List<Card> cards = CardLoader.loadCardsFromFile(options.getCardsFile());
            CardOrganizer organizer = OrganizerFactory.create(options.getOrder());

            
            FlashCardSess session = new FlashCardSess(cards, organizer, options);
            session.start();

        } catch (IOException e) {
            System.err.println("Error loading cards: " + e.getMessage());
        }
    }
}
