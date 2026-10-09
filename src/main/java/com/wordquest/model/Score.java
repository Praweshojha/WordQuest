package com.wordquest.model;

public class Score {
    private int id;
    private int playerId;
    private String gameMode;
    private String difficulty;
    private int score;
    private int correctAnswers;
    private int wrongAnswers;
    private double accuracy;
    private String playedAt;
    
    // For Leaderboard display
    private String playerName;

    public Score(int playerId, String gameMode, String difficulty, int score, int correctAnswers, int wrongAnswers, double accuracy) {
        this.playerId = playerId;
        this.gameMode = gameMode;
        this.difficulty = difficulty;
        this.score = score;
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
        this.accuracy = accuracy;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getPlayerId() { return playerId; }
    public String getGameMode() { return gameMode; }
    public String getDifficulty() { return difficulty; }
    public int getScore() { return score; }
    public int getCorrectAnswers() { return correctAnswers; }
    public int getWrongAnswers() { return wrongAnswers; }
    public double getAccuracy() { return accuracy; }
    public String getPlayedAt() { return playedAt; }
    public void setPlayedAt(String playedAt) { this.playedAt = playedAt; }
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
}
