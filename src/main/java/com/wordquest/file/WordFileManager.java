package com.wordquest.file;

import com.wordquest.model.Word;
import com.wordquest.model.Question;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordFileManager {

    public static List<Word> loadWords(String filepath) {
        List<Word> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    words.add(new Word(line.trim()));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading words from " + filepath + ": " + e.getMessage());
        }
        return words;
    }

    public static List<Question> loadQuestions(String filepath) {
        List<Question> questions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 6) {
                        questions.add(new Question(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading questions from " + filepath + ": " + e.getMessage());
        }
        return questions;
    }
}
