package com.wordquest.game;

import com.wordquest.model.Word;
import com.wordquest.util.Constants;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Collections;

public class WordGuessGame extends GameMode {
    private Word currentWord;
    private Set<Character> guessedLetters;
    private int maxAttempts = 6;
    private int wrongAttempts = 0;
    private boolean won = false;

    public WordGuessGame(String difficulty, List<Word> words) {
        super(difficulty);
        if (!words.isEmpty()) {
            Collections.shuffle(words);
            this.currentWord = words.get(0);
        }
        this.guessedLetters = new HashSet<>();
    }

    @Override
    public void start() {
        guessedLetters.clear();
        wrongAttempts = 0;
        won = false;
    }

    public boolean guessLetter(char c) {
        c = Character.toLowerCase(c);
        if (guessedLetters.contains(c)) {
            return false;
        }
        guessedLetters.add(c);
        
        String wordText = currentWord.getText().toLowerCase();
        if (wordText.indexOf(c) >= 0) {
            checkWinCondition();
            return true;
        } else {
            wrongAttempts++;
            wrongAnswers++;
            return false;
        }
    }

    private void checkWinCondition() {
        String wordText = currentWord.getText().toLowerCase();
        boolean allFound = true;
        for (char c : wordText.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                allFound = false;
                break;
            }
        }
        if (allFound) {
            won = true;
            correctAnswers++;
            score += switch (difficulty) {
                case Constants.DIFF_EASY -> Constants.SCORE_EASY;
                case Constants.DIFF_MEDIUM -> Constants.SCORE_MEDIUM;
                case Constants.DIFF_HARD -> Constants.SCORE_HARD;
                default -> 100;
            };
        }
    }

    public String getDisplayWord() {
        if (currentWord == null) return "";
        StringBuilder sb = new StringBuilder();
        String wordText = currentWord.getText().toLowerCase();
        for (char c : wordText.toCharArray()) {
            if (guessedLetters.contains(c)) {
                sb.append(Character.toUpperCase(c)).append(" ");
            } else {
                sb.append("_ ");
            }
        }
        return sb.toString().trim();
    }

    public int getRemainingAttempts() {
        return maxAttempts - wrongAttempts;
    }

    public Set<Character> getGuessedLetters() {
        return guessedLetters;
    }
    
    public String getActualWord() {
        return currentWord != null ? currentWord.getText().toUpperCase() : "";
    }

    public boolean isWon() { return won; }

    @Override
    public boolean isGameOver() {
        return won || wrongAttempts >= maxAttempts;
    }
}
