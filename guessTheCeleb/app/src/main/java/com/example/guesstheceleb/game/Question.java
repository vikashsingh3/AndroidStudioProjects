package com.example.guesstheceleb.game;

import android.graphics.Bitmap;

public class Question {
    private String celebrityName;
    private Bitmap celebrityImage;
    private String[] possibleNames;

    public Question(String celebrityName, Bitmap celebrityImage, String[] possibleNames) {
        this.celebrityName = celebrityName;
        this.celebrityImage = celebrityImage;
        this.possibleNames = possibleNames;
    }

    public boolean check(String guess) {
        return guess.equals(celebrityName);
    }

    public String[] getPossibleNames() {
        return possibleNames;
    }

    public Bitmap getCelebrityImage() {
        return celebrityImage;
    }
}
