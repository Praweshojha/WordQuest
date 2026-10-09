package com.wordquest.game;

import com.wordquest.model.Word;
import com.wordquest.util.Constants;
import com.wordquest.util.GameUtil;
import java.util.List;
import java.util.Collections;

public class UnscrambleGame extends GameMode {
    private List<Word> words;
    private int currentWordIndex = 0;
    private String currentShuffledWord;
    private static final int MAX_QUESTIONS = 5;

    public UnscrambleGame(String difficulty, List<Word> allWords) {
        super(difficulty);
        this.words = allWords;
        Collections.shuffle(this.words);
    }

    @Override
    public void start() {
        currentWordIndex = 0;
        score = 0;
        correctAnswers = 0;
        wrongAnswers = 0;
        prepareNextWord();
    }

    public void prepareNextWord() {
        if (!isGameOver()) {
            currentShuffledWord = GameUtil.shuffleWord(getCurrentWord().getText());
            // Ensure it's actually shuffled
            while(currentShuffledWord.equalsIgnoreCase(getCurrentWord().getText()) && currentShuffledWord.length() > 1) {
                currentShuffledWord = GameUtil.shuffleWord(getCurrentWord().getText());
            }
        }
    }

    public boolean checkAnswer(String answer) {
        if (isGameOver()) return false;
        
        boolean correct = answer.trim().equalsIgnoreCase(getCurrentWord().getText());
        if (correct) {
            correctAnswers++;
            score += switch (difficulty) {
                case Constants.DIFF_EASY -> Constants.SCORE_EASY;
                case Constants.DIFF_MEDIUM -> Constants.SCORE_MEDIUM;
                case Constants.DIFF_HARD -> Constants.SCORE_HARD;
                default -> 100;
            };
        } else {
            wrongAnswers++;
        }
        currentWordIndex++;
        prepareNextWord();
        return correct;
    }
    
    public void skip() {
        wrongAnswers++;
        currentWordIndex++;
        prepareNextWord();
    }

    public String getCurrentShuffledWord() {
        return currentShuffledWord;
    }
    
    public Word getCurrentWord() {
        if (currentWordIndex < words.size()) {
            return words.get(currentWordIndex);
        }
        return null;
    }

    public String getHint() {
        Word w = getCurrentWord();
        if (w == null) return "";
        String text = w.getText();
        return "Starts with '" + text.charAt(0) + "' and ends with '" + text.charAt(text.length() - 1) + "'";
    }

    @Override
    public boolean isGameOver() {
        return currentWordIndex >= MAX_QUESTIONS || currentWordIndex >= words.size();
    }
    
    public int getCurrentQuestionNumber() {
        return currentWordIndex + 1;
    }
    
    public int getTotalQuestions() {
        return Math.min(MAX_QUESTIONS, words.size());
    }
}
