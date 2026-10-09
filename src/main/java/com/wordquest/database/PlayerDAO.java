package com.wordquest.database;

import com.wordquest.model.Player;
import java.sql.*;

public class PlayerDAO {
    
    public Player getOrCreatePlayer(String name) {
        Player player = getPlayerByName(name);
        if (player == null) {
            player = createPlayer(name);
        }
        return player;
    }

    private Player getPlayerByName(String name) {
        String sql = "SELECT * FROM players WHERE player_name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Player(rs.getInt("player_id"), rs.getString("player_name"), rs.getString("created_at"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting player: " + e.getMessage());
        }
        return null;
    }

    private Player createPlayer(String name) {
        String sql = "INSERT INTO players(player_name) VALUES(?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return new Player(rs.getInt(1), name, ""); // Timestamp will be fetched next time
            }
        } catch (SQLException e) {
            System.err.println("Error creating player: " + e.getMessage());
        }
        return null;
    }
}
