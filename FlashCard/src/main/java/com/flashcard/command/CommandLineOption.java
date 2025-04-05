package com.flashcard.command;

public class CommandLineOption {
    private boolean help = false;
    private String cardsFile;
    private String order = "random";
    private int repetitions = 1;
    private boolean invert = false;

    public CommandLineOption(String[] args){
        parse(args);
    }

    private void parse(String[] args) {
        for (int i=0; i<args.length; i++) {
            switch (args[i]) {
                case "--help": 
                    help = true; 
                    break;
                case "--order": 
                    order = args[++i]; 
                    break;
                case "--repetitions": 
                    repetitions = Integer.parseInt(args[++i]);
                    break;
                case "--invertCards": 
                    invert = true;
                    break;
                default:
                    if(!args[i].startsWith ("--")) {
                        cardsFile = args[i];
                    }
            }
        }
    }

    public boolean isHelp() {
        return help;
    }
    public String getCardsFile() {
        return cardsFile;
    }
    public String getOrder() {
        return order;
    }
    public int getRepetitions() {
        return repetitions;
    }
    public boolean isInvert() {
        return invert;
    }
}