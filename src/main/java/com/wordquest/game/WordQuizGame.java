package com.wordquest.game;

import com.wordquest.model.Question;
import com.wordquest.util.Constants;
import java.util.List;
import java.util.Collections;

public class WordQuizGame extends GameMode {
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private static final int MAX_QUESTIONS = 5;

    public WordQuizGame(String difficulty, List<Question> allQuestions) {
        super(difficulty);
        this.questions = allQuestions;
        Collections.shuffle(this.questions);
    }

    @Override
    public void start() {
        currentQuestionIndex = 0;
        score = 0;
        correctAnswers = 0;
        wrongAnswers = 0;
    }

    public boolean checkAnswer(String selectedOption) {
        if (isGameOver()) return false;
        
        Question q = getCurrentQuestion();
        boolean correct = q.getCorrectAnswer().equalsIgnoreCase(selectedOption);
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
        currentQuestionIndex++;
        return correct;
    }

    public Question getCurrentQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex);
        }
        return null;
    }

    @Override
    public boolean isGameOver() {
        return currentQuestionIndex >= MAX_QUESTIONS || currentQuestionIndex >= questions.size();
    }
    
    public int getCurrentQuestionNumber() {
        return currentQuestionIndex + 1;
    }
    
    public int getTotalQuestions() {
        return Math.min(MAX_QUESTIONS, questions.size());
    }
}
