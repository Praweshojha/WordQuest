package com.wordquest.gui;

import com.wordquest.database.ScoreDAO;
import com.wordquest.model.Score;
import com.wordquest.util.UIUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LeaderboardPanel extends JPanel {
    private MainFrame mainFrame;
    private DefaultTableModel tableModel;

    public LeaderboardPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setBackground(UIUtil.COLOR_BACKGROUND);
        setLayout(new BorderLayout());
        
        add(UIUtil.createTitleLabel("LEADERBOARD"), BorderLayout.NORTH);
        
        String[] columns = {"Rank", "Player", "Game Mode", "Difficulty", "Score", "Accuracy", "Date"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        JTable table = new JTable(tableModel);
        table.setFont(UIUtil.FONT_REGULAR);
        table.setRowHeight(30);
        table.getTableHeader().setFont(UIUtil.FONT_BOLD);
        table.getTableHeader().setBackground(UIUtil.COLOR_PRIMARY);
        table.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        add(scrollPane, BorderLayout.CENTER);
        
        JButton btnBack = new JButton("Back to Main Menu");
        btnBack.addActionListener(e -> mainFrame.showPanel("HOME"));
        
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.add(btnBack);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    // Override addNotify to refresh data when panel is shown
    @Override
    public void addNotify() {
        super.addNotify();
        loadLeaderboardData();
    }
    
    private void loadLeaderboardData() {
        tableModel.setRowCount(0); // clear existing
        List<Score> scores = new ScoreDAO().getLeaderboard();
        
        if (scores.isEmpty()) {
            tableModel.addRow(new Object[]{"-", "No games have been played yet.", "-", "-", "-", "-", "-"});
        } else {
            int rank = 1;
            for (Score s : scores) {
                tableModel.addRow(new Object[]{
                    rank++,
                    s.getPlayerName(),
                    s.getGameMode(),
                    s.getDifficulty(),
                    s.getScore(),
                    String.format("%.2f%%", s.getAccuracy()),
                    s.getPlayedAt()
                });
            }
        }
    }
}
