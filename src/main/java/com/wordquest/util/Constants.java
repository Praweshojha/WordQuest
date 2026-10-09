package com.wordquest.util;

import java.awt.Color;
import java.awt.Font;

public class Constants {
    public static final String APP_TITLE = "WordQuest — Java Word Puzzle Game";
    
    // Difficulty
    public static final String DIFF_EASY = "Easy";
    public static final String DIFF_MEDIUM = "Medium";
    public static final String DIFF_HARD = "Hard";
    
    // Game Modes
    public static final String MODE_UNSCRAMBLE = "Unscramble";
    public static final String MODE_GUESS = "Word Guess";
    public static final String MODE_SEARCH = "Word Search";
    public static final String MODE_QUIZ = "Word Quiz";
    public static final String MODE_TIMED = "Timed Challenge";

    // Scoring Multipliers
    public static final int SCORE_EASY = 100;
    public static final int SCORE_MEDIUM = 150;
    public static final int SCORE_HARD = 200;

    // Database
    public static final String DB_URL = "jdbc:sqlite:database/wordquest.db";

    // File Paths
    public static final String FILE_WORDS_EASY = "data/words_easy.txt";
    public static final String FILE_WORDS_MEDIUM = "data/words_medium.txt";
    public static final String FILE_WORDS_HARD = "data/words_hard.txt";
    public static final String FILE_QUESTIONS = "data/questions.txt";
    public static final String FILE_SCORE_HISTORY = "scores/score_history.txt";
}
