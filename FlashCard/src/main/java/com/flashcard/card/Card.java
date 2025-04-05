package com.flashcard.card;

public class Card {
    private String question;
    private String answer;

    public Card(String question, String answer) {
        this.question = question.trim();
        this.answer = answer.trim();
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object Ob) {
        if (this == Ob) 
            return true;
        if(!(Ob instanceof Card))
            return false;
        Card card = (Card) Ob;
         return question.equals(card.question) && answer.equals(card.answer);
    }

   
    public void invert() {
        String temp = question;
        question = answer;
        answer = temp;
    }

    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(answer);
    }
}
