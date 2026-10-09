package com.wordquest.gui;

import com.wordquest.database.GameHistoryDAO;
import com.wordquest.database.ScoreDAO;
import com.wordquest.game.GameEngine;
import com.wordquest.game.GameMode;
import com.wordquest.model.GameHistory;
import com.wordquest.model.Player;
import com.wordquest.model.Score;
import com.wordquest.util.UIUtil;

import javax.swing.*;
import java.awt.*;

public class GameResultPanel extends JPanel {
    private MainFrame mainFrame;

    public GameResultPanel(MainFrame mainFrame, String modeName, GameMode gameMode) {
        this.mainFrame = mainFrame;
        setBackground(UIUtil.COLOR_BACKGROUND);
        setLayout(new BorderLayout());

        saveResult(modeName, gameMode);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        
        JLabel lblTitle = UIUtil.createTitleLabel("GAME COMPLETE!");
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Player p = GameEngine.getInstance().getCurrentPlayer();
        String playerName = p != null ? p.getName() : "Unknown";
        
        JLabel lblDetails = new JLabel(String.format("<html><div style='text-align: center;'>"
                + "Player: %s<br>"
                + "Mode: %s<br>"
                + "Difficulty: %s<br>"
                + "Correct: %d<br>"
                + "Wrong: %d<br>"
                + "Score: <span style='color: #4CAF50; font-size: 24px;'><b>%d</b></span></div></html>",
                playerName, modeName, gameMode.getDifficulty(), 
                gameMode.getCorrectAnswers(), gameMode.getWrongAnswers(), gameMode.getScore()));
                
        lblDetails.setFont(UIUtil.FONT_REGULAR);
        lblDetails.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setOpaque(false);
        
        JButton btnPlayAgain = UIUtil.createPrimaryButton("PLAY AGAIN");
        JButton btnMenu = UIUtil.createPrimaryButton("MAIN MENU");
        JButton btnLeaderboard = new JButton("LEADERBOARD");
        JButton btnHistory = new JButton("GAME HISTORY");
        
        btnPlayAgain.addActionListener(e -> mainFrame.showPanel("GAME_SELECTION"));
        btnMenu.addActionListener(e -> mainFrame.showPanel("HOME"));
        btnLeaderboard.addActionListener(e -> mainFrame.showPanel("LEADERBOARD"));
        btnHistory.addActionListener(e -> mainFrame.showPanel("HISTORY"));
        
        buttonPanel.add(btnPlayAgain);
        buttonPanel.add(btnMenu);
        buttonPanel.add(btnLeaderboard);
        buttonPanel.add(btnHistory);
        
        centerPanel.add(Box.createVerticalStrut(50));
        centerPanel.add(lblTitle);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(lblDetails);
        centerPanel.add(Box.createVerticalStrut(50));
        centerPanel.add(buttonPanel);
        
        add(centerPanel, BorderLayout.CENTER);
    }

    private void saveResult(String modeName, GameMode gameMode) {
        Player p = GameEngine.getInstance().getCurrentPlayer();
        if (p == null) return;
        
        int total = gameMode.getCorrectAnswers() + gameMode.getWrongAnswers();
        double accuracy = total > 0 ? ((double)gameMode.getCorrectAnswers() / total) * 100 : 0.0;
        
        Score score = new Score(p.getId(), modeName, gameMode.getDifficulty(), gameMode.getScore(),
                gameMode.getCorrectAnswers(), gameMode.getWrongAnswers(), accuracy);
        new ScoreDAO().insertScore(score);
        
        GameHistory history = new GameHistory(p.getId(), modeName, gameMode.getDifficulty(), gameMode.getScore(), 0);
        new GameHistoryDAO().insertHistory(history);
    }
}
