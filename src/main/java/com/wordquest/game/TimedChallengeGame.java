package com.wordquest.game;

import com.wordquest.model.Word;
import java.util.List;

public class TimedChallengeGame extends UnscrambleGame {
    
    private int timeRemaining;
    private boolean timeUp = false;

    public TimedChallengeGame(String difficulty, List<Word> allWords) {
        super(difficulty, allWords);
        this.timeRemaining = 30; // 30 seconds default
    }

    @Override
    public void start() {
        super.start();
        this.timeRemaining = 30;
        this.timeUp = false;
    }

    public void decrementTime() {
        if (timeRemaining > 0) {
            timeRemaining--;
        }
        if (timeRemaining <= 0) {
            timeUp = true;
        }
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    @Override
    public boolean isGameOver() {
        return timeUp || super.isGameOver();
    }
}
