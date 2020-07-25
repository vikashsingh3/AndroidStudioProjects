package com.example.guesstheceleb.game;

import android.graphics.Bitmap;

public class GameBuilder {
    private CelebrityManager celebrityManager;
    private static final int QUESTION_NUMBER_EASY = 3;
    private static final int QUESTION_NUMBER_MEDIUM = 6;
    private static final int QUESTION_NUMBER_HARD = 9;
    private static final int QUESTION_NUMBER_EXPERT = 12;

    public GameBuilder(CelebrityManager celebrityManager) {
        this.celebrityManager = celebrityManager;
    }

    public Game create(Difficulty level) {
        switch (level) {
            case MEDIUM:
                return new Game(makeQuestions(QUESTION_NUMBER_MEDIUM));
            case HARD:
                return new Game(makeQuestions(QUESTION_NUMBER_HARD));
            case EXPERT:
                return new Game(makeQuestions(QUESTION_NUMBER_EXPERT));
            default:
                return new Game(makeQuestions(QUESTION_NUMBER_EASY));
        }
    }

    private Question[] makeQuestions(int count) {
        String[] possibleAns = new String[count];

        for (int i = 0; i < possibleAns.length; i++) {
            possibleAns[i] = celebrityManager.getName(i);
        }

        Question[] questions = new Question[count];
        for (int i = 0; i < count; i++) {
            String celebrityName = celebrityManager.getName(i);
            Bitmap celebImage = celebrityManager.getBitmap(i);
            Question q = new Question(celebrityName, celebImage, possibleAns);
            questions[i] = q;
        }
        return questions;
    }
}
