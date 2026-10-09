package com.wordquest.game;

import com.wordquest.model.Word;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordSearchGame extends GameMode {
    private char[][] grid;
    private List<String> targetWords;
    private List<String> foundWords;
    private int gridSize;

    public WordSearchGame(String difficulty, List<Word> words) {
        super(difficulty);
        this.gridSize = difficulty.equals("Hard") ? 10 : (difficulty.equals("Medium") ? 8 : 6);
        this.grid = new char[gridSize][gridSize];
        this.targetWords = new ArrayList<>();
        this.foundWords = new ArrayList<>();
        
        Random rand = new Random();
        for (int i = 0; i < 3 && i < words.size(); i++) {
            Word w = words.get(rand.nextInt(words.size()));
            if (w.getText().length() <= gridSize && !targetWords.contains(w.getText().toUpperCase())) {
                targetWords.add(w.getText().toUpperCase());
            }
        }
    }

    @Override
    public void start() {
        generateGrid();
        foundWords.clear();
        score = 0;
        correctAnswers = 0;
    }

    private void generateGrid() {
        // Fill with random
        Random rand = new Random();
        for(int i=0; i<gridSize; i++) {
            for(int j=0; j<gridSize; j++) {
                grid[i][j] = (char)('A' + rand.nextInt(26));
            }
        }
        // Place target words horizontally or vertically
        for (String word : targetWords) {
            boolean placed = false;
            int attempts = 0;
            while(!placed && attempts < 100) {
                boolean horizontal = rand.nextBoolean();
                if (horizontal) {
                    int r = rand.nextInt(gridSize);
                    int c = rand.nextInt(gridSize - word.length() + 1);
                    placed = true;
                    for (int i=0; i<word.length(); i++) {
                        grid[r][c+i] = word.charAt(i);
                    }
                } else {
                    int r = rand.nextInt(gridSize - word.length() + 1);
                    int c = rand.nextInt(gridSize);
                    placed = true;
                    for (int i=0; i<word.length(); i++) {
                        grid[r+i][c] = word.charAt(i);
                    }
                }
                attempts++;
            }
        }
    }

    public char[][] getGrid() { return grid; }
    public List<String> getTargetWords() { return targetWords; }
    public List<String> getFoundWords() { return foundWords; }

    public boolean markWordFound(String word) {
        if (targetWords.contains(word) && !foundWords.contains(word)) {
            foundWords.add(word);
            correctAnswers++;
            score += 150;
            return true;
        }
        return false;
    }

    @Override
    public boolean isGameOver() {
        return foundWords.size() == targetWords.size();
    }
}
