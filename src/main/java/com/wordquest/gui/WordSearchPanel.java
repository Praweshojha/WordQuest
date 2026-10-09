package com.wordquest.gui;

import com.wordquest.game.WordSearchGame;
import com.wordquest.model.Word;
import com.wordquest.util.Constants;
import com.wordquest.util.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WordSearchPanel extends JPanel {
    private MainFrame mainFrame;
    private WordSearchGame game;
    private JLabel lblTargetWords;
    private JPanel gridPanel;
    private JButton[][] buttons;
    
    private int firstRow = -1;
    private int firstCol = -1;

    public WordSearchPanel(MainFrame mainFrame, String difficulty, List<Word> words) {
        this.mainFrame = mainFrame;
        this.game = new WordSearchGame(difficulty, words);
        game.start();
        
        setBackground(UIUtil.COLOR_BACKGROUND);
        setLayout(new BorderLayout());
        
        add(UIUtil.createTitleLabel("WORD SEARCH"), BorderLayout.NORTH);
        
        lblTargetWords = new JLabel();
        lblTargetWords.setFont(UIUtil.FONT_BOLD);
        lblTargetWords.setHorizontalAlignment(SwingConstants.CENTER);
        
        char[][] grid = game.getGrid();
        int size = grid.length;
        
        gridPanel = new JPanel(new GridLayout(size, size, 5, 5));
        gridPanel.setOpaque(false);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 150, 20, 150));
        
        buttons = new JButton[size][size];
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                JButton b = new JButton(String.valueOf(grid[i][j]));
                b.setFont(new Font("Monospaced", Font.BOLD, 24));
                b.setBackground(Color.WHITE);
                b.setFocusPainted(false);
                int r = i; int c = j;
                b.addActionListener(e -> cellClicked(r, c));
                buttons[i][j] = b;
                gridPanel.add(b);
            }
        }
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(lblTargetWords, BorderLayout.NORTH);
        centerPanel.add(gridPanel, BorderLayout.CENTER);
        
        add(centerPanel, BorderLayout.CENTER);
        
        JButton btnExit = new JButton("Exit Game / Finish");
        btnExit.addActionListener(e -> endGame());
        add(btnExit, BorderLayout.SOUTH);
        
        updateTargetWordsDisplay();
    }
    
    private void cellClicked(int r, int c) {
        if (firstRow == -1) {
            firstRow = r;
            firstCol = c;
            buttons[r][c].setBackground(Color.YELLOW);
        } else {
            // Second click, form a word
            String selectedWord = getWordBetween(firstRow, firstCol, r, c);
            boolean found = game.markWordFound(selectedWord);
            if (found) {
                highlightWord(firstRow, firstCol, r, c);
            } else {
                // Reset first click if not already part of a found word
                buttons[firstRow][firstCol].setBackground(Color.WHITE); 
            }
            firstRow = -1;
            firstCol = -1;
            updateTargetWordsDisplay();
            
            if (game.isGameOver()) {
                endGame();
            }
        }
    }
    
    private String getWordBetween(int r1, int c1, int r2, int c2) {
        StringBuilder sb = new StringBuilder();
        char[][] grid = game.getGrid();
        if (r1 == r2) {
            int start = Math.min(c1, c2);
            int end = Math.max(c1, c2);
            for (int i = start; i <= end; i++) sb.append(grid[r1][i]);
        } else if (c1 == c2) {
            int start = Math.min(r1, r2);
            int end = Math.max(r1, r2);
            for (int i = start; i <= end; i++) sb.append(grid[i][c1]);
        }
        return sb.toString();
    }
    
    private void highlightWord(int r1, int c1, int r2, int c2) {
        if (r1 == r2) {
            int start = Math.min(c1, c2);
            int end = Math.max(c1, c2);
            for (int i = start; i <= end; i++) buttons[r1][i].setBackground(Color.GREEN);
        } else if (c1 == c2) {
            int start = Math.min(r1, r2);
            int end = Math.max(r1, r2);
            for (int i = start; i <= end; i++) buttons[i][c1].setBackground(Color.GREEN);
        }
    }

    private void updateTargetWordsDisplay() {
        StringBuilder html = new StringBuilder("<html>Find these words: ");
        for (String word : game.getTargetWords()) {
            if (game.getFoundWords().contains(word)) {
                html.append("<s>").append(word).append("</s> ");
            } else {
                html.append(word).append(" ");
            }
        }
        html.append("</html>");
        lblTargetWords.setText(html.toString());
    }

    private void endGame() {
        GameResultPanel resultPanel = new GameResultPanel(mainFrame, Constants.MODE_SEARCH, game);
        mainFrame.addPanel(resultPanel, "RESULT");
        mainFrame.showPanel("RESULT");
    }
}
