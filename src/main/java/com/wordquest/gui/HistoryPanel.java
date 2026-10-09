package com.wordquest.gui;

import com.wordquest.database.ScoreDAO;
import com.wordquest.model.Score;
import com.wordquest.file.ScoreFileManager;
import com.wordquest.util.UIUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoryPanel extends JPanel {
    private MainFrame mainFrame;
    private DefaultTableModel tableModel;
    private List<Score> currentScores;

    public HistoryPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setBackground(UIUtil.COLOR_BACKGROUND);
        setLayout(new BorderLayout());
        
        add(UIUtil.createTitleLabel("GAME HISTORY"), BorderLayout.NORTH);
        
        String[] columns = {"Player", "Game Mode", "Difficulty", "Score", "Correct", "Wrong", "Accuracy", "Played At"};
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
        
        JButton btnExport = UIUtil.createPrimaryButton("Export History to File");
        btnExport.addActionListener(e -> {
            if (currentScores != null && !currentScores.isEmpty()) {
                ScoreFileManager.exportScores(currentScores);
                JOptionPane.showMessageDialog(this, "History exported to scores/score_history.txt");
            } else {
                JOptionPane.showMessageDialog(this, "No history to export.");
            }
        });
        
        JButton btnBack = new JButton("Back to Main Menu");
        btnBack.addActionListener(e -> mainFrame.showPanel("HOME"));
        
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.add(btnExport);
        bottomPanel.add(btnBack);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        loadHistoryData();
    }
    
    private void loadHistoryData() {
        tableModel.setRowCount(0); // clear existing
        currentScores = new ScoreDAO().getLeaderboard(); // In a real app we might fetch by player ID, but we just show all history here.
        
        if (currentScores.isEmpty()) {
            tableModel.addRow(new Object[]{"No game history available.", "-", "-", "-", "-", "-", "-", "-"});
        } else {
            for (Score s : currentScores) {
                tableModel.addRow(new Object[]{
                    s.getPlayerName(),
                    s.getGameMode(),
                    s.getDifficulty(),
                    s.getScore(),
                    s.getCorrectAnswers(),
                    s.getWrongAnswers(),
                    String.format("%.2f%%", s.getAccuracy()),
                    s.getPlayedAt()
                });
            }
        }
    }
}
