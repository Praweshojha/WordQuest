package com.wordquest.model;

public class GameHistory {
    private int historyId;
    private int playerId;
    private String gameMode;
    private String difficulty;
    private int score;
    private int duration;
    private String playedAt;

    public GameHistory(int playerId, String gameMode, String difficulty, int score, int duration) {
        this.playerId = playerId;
        this.gameMode = gameMode;
        this.difficulty = difficulty;
        this.score = score;
        this.duration = duration;
    }

    public int getHistoryId() { return historyId; }
    public void setHistoryId(int historyId) { this.historyId = historyId; }
    public int getPlayerId() { return playerId; }
    public String getGameMode() { return gameMode; }
    public String getDifficulty() { return difficulty; }
    public int getScore() { return score; }
    public int getDuration() { return duration; }
    public String getPlayedAt() { return playedAt; }
    public void setPlayedAt(String playedAt) { this.playedAt = playedAt; }
}
