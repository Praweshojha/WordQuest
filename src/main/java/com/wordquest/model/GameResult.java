package com.wordquest.model;

public class GameResult {
    private String gameMode;
    private String difficulty;
    private int score;
    private int correctAnswers;
    private int wrongAnswers;
    private double accuracy;

    public GameResult(String gameMode, String difficulty, int score, int correctAnswers, int wrongAnswers) {
        this.gameMode = gameMode;
        this.difficulty = difficulty;
        this.score = score;
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
        int total = correctAnswers + wrongAnswers;
        this.accuracy = total > 0 ? ((double) correctAnswers / total) * 100 : 0.0;
    }

    public String getGameMode() { return gameMode; }
    public String getDifficulty() { return difficulty; }
    public int getScore() { return score; }
    public int getCorrectAnswers() { return correctAnswers; }
    public int getWrongAnswers() { return wrongAnswers; }
    public double getAccuracy() { return accuracy; }
}
