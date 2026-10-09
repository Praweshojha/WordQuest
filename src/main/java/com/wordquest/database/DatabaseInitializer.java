package com.wordquest.database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseInitializer {
    public static void initialize() {
        String playersTable = "CREATE TABLE IF NOT EXISTS players ("
                + "player_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "player_name TEXT NOT NULL,"
                + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP"
                + ");";

        String scoresTable = "CREATE TABLE IF NOT EXISTS scores ("
                + "score_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "player_id INTEGER,"
                + "game_mode TEXT,"
                + "difficulty TEXT,"
                + "score INTEGER,"
                + "correct_answers INTEGER,"
                + "wrong_answers INTEGER,"
                + "accuracy REAL,"
                + "played_at DATETIME DEFAULT CURRENT_TIMESTAMP,"
                + "FOREIGN KEY(player_id) REFERENCES players(player_id)"
                + ");";

        String historyTable = "CREATE TABLE IF NOT EXISTS game_history ("
                + "history_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "player_id INTEGER,"
                + "game_mode TEXT,"
                + "difficulty TEXT,"
                + "score INTEGER,"
                + "duration INTEGER,"
                + "played_at DATETIME DEFAULT CURRENT_TIMESTAMP,"
                + "FOREIGN KEY(player_id) REFERENCES players(player_id)"
                + ");";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(playersTable);
            stmt.execute(scoresTable);
            stmt.execute(historyTable);
        } catch (SQLException e) {
            System.err.println("Database initialization error: " + e.getMessage());
        }
    }
}
