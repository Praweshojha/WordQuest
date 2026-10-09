package com.wordquest.game;

public abstract class GameMode {
    protected int score = 0;
    protected int correctAnswers = 0;
    protected int wrongAnswers = 0;
    protected String difficulty;

    public GameMode(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getScore() { return score; }
    public int getCorrectAnswers() { return correctAnswers; }
    public int getWrongAnswers() { return wrongAnswers; }
    public String getDifficulty() { return difficulty; }

    public abstract void start();
    public abstract boolean isGameOver();
}
