package com.example.guesstheceleb.game;

import java.util.Locale;

public class Game {

    private int currentIdx = 0;
    private Question[] questions;
    private int score = 0;

    public Game(Question[] questions) {
        this.questions = questions;
    }

    public boolean isGameOver() {
        return (currentIdx >= questions.length);
    }

    public Question next() {

        Question q = questions[currentIdx];
        currentIdx++;
        return q;
    }

    public void updateScore(Boolean isOK) {
        if (isOK) {
            score++;
        }
    }

    public String getScore() {
        return String.format(Locale.getDefault(),
                "Score: %d/%d", score, questions.length);
    }

    public int count() {
        return questions.length;
    }
}
