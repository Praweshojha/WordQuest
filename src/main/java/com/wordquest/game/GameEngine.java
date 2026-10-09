package com.wordquest.game;

import com.wordquest.model.Player;

public class GameEngine {
    private static GameEngine instance;
    private Player currentPlayer;

    private GameEngine() {}

    public static GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
