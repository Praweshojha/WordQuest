package com.wordquest.database;

import com.wordquest.model.GameHistory;
import java.sql.*;

public class GameHistoryDAO {

    public void insertHistory(GameHistory history) {
        String sql = "INSERT INTO game_history(player_id, game_mode, difficulty, score, duration) VALUES(?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, history.getPlayerId());
            pstmt.setString(2, history.getGameMode());
            pstmt.setString(3, history.getDifficulty());
            pstmt.setInt(4, history.getScore());
            pstmt.setInt(5, history.getDuration());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting history: " + e.getMessage());
        }
    }
}
