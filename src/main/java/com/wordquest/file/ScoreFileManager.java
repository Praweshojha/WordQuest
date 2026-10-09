package com.wordquest.file;

import com.wordquest.model.Score;
import com.wordquest.util.Constants;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ScoreFileManager {

    public static void exportScores(List<Score> scores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_SCORE_HISTORY, true))) {
            for (Score s : scores) {
                writer.write("PlayerID: " + s.getPlayerId() + 
                             ", Mode: " + s.getGameMode() + 
                             ", Diff: " + s.getDifficulty() + 
                             ", Score: " + s.getScore() + 
                             ", Accuracy: " + String.format("%.2f%%", s.getAccuracy()) + 
                             ", Date: " + s.getPlayedAt() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error exporting scores: " + e.getMessage());
        }
    }
}
