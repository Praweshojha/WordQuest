package com.wordquest.database;

import com.wordquest.model.Score;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreDAO {

    public void insertScore(Score score) {
        String sql = "INSERT INTO scores(player_id, game_mode, difficulty, score, correct_answers, wrong_answers, accuracy) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, score.getPlayerId());
            pstmt.setString(2, score.getGameMode());
            pstmt.setString(3, score.getDifficulty());
            pstmt.setInt(4, score.getScore());
            pstmt.setInt(5, score.getCorrectAnswers());
            pstmt.setInt(6, score.getWrongAnswers());
            pstmt.setDouble(7, score.getAccuracy());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting score: " + e.getMessage());
        }
    }

    public List<Score> getLeaderboard() {
        List<Score> leaderboard = new ArrayList<>();
        String sql = "SELECT p.player_name, s.* FROM scores s JOIN players p ON s.player_id = p.player_id ORDER BY s.score DESC LIMIT 50";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Score s = new Score(
                        rs.getInt("player_id"),
                        rs.getString("game_mode"),
                        rs.getString("difficulty"),
                        rs.getInt("score"),
                        rs.getInt("correct_answers"),
                        rs.getInt("wrong_answers"),
                        rs.getDouble("accuracy")
                );
                s.setId(rs.getInt("score_id"));
                s.setPlayedAt(rs.getString("played_at"));
                s.setPlayerName(rs.getString("player_name"));
                leaderboard.add(s);
            }
        } catch (SQLException e) {
            System.err.println("Error getting leaderboard: " + e.getMessage());
        }
        return leaderboard;
    }
}
