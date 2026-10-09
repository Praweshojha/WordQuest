package com.wordquest.util;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class GameUtil {
    
    public static String shuffleWord(String word) {
        List<Character> chars = new ArrayList<>();
        for (char c : word.toCharArray()) {
            chars.add(c);
        }
        Collections.shuffle(chars);
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
        }
        return sb.toString().toUpperCase();
    }

    public static int calculateScore(String difficulty, int timeRemaining) {
        int base = switch (difficulty) {
            case Constants.DIFF_EASY -> Constants.SCORE_EASY;
            case Constants.DIFF_MEDIUM -> Constants.SCORE_MEDIUM;
            case Constants.DIFF_HARD -> Constants.SCORE_HARD;
            default -> 100;
        };
        return base + (timeRemaining * 10);
    }
}
