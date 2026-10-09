package com.wordquest.gui;

import com.wordquest.util.Constants;
import com.wordquest.util.UIUtil;
import com.wordquest.file.WordFileManager;

import javax.swing.*;
import java.awt.*;

public class GameSelectionPanel extends JPanel {
    private MainFrame mainFrame;
    private JComboBox<String> diffCombo;

    public GameSelectionPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setBackground(UIUtil.COLOR_BACKGROUND);
        setLayout(new BorderLayout());
        
        JLabel titleLabel = UIUtil.createTitleLabel("Select Game Mode");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        add(titleLabel, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel(new GridLayout(2, 3, 15, 15));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        
        centerPanel.add(createGameCard("UNSCRAMBLE", Constants.MODE_UNSCRAMBLE, new Color(251, 188, 5), "assets/unscramble.jpg"));
        centerPanel.add(createGameCard("WORD GUESS", Constants.MODE_GUESS, new Color(52, 168, 83), "assets/guess.jpg"));
        centerPanel.add(createGameCard("WORD SEARCH", Constants.MODE_SEARCH, new Color(156, 39, 176), "assets/search.jpg"));
        centerPanel.add(createGameCard("WORD QUIZ", Constants.MODE_QUIZ, new Color(255, 111, 0), "assets/quiz.jpg"));
        centerPanel.add(createGameCard("TIMED", Constants.MODE_TIMED, new Color(234, 67, 53), "assets/timed.jpg"));
        
        add(centerPanel, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setOpaque(false);
        
        diffCombo = new JComboBox<>(new String[]{Constants.DIFF_EASY, Constants.DIFF_MEDIUM, Constants.DIFF_HARD});
        diffCombo.setFont(UIUtil.FONT_REGULAR);
        diffCombo.setBackground(Color.WHITE);
        
        JButton btnBack = UIUtil.createPrimaryButton("Back to Menu");
        btnBack.addActionListener(e -> mainFrame.showPanel("HOME"));
        
        JLabel diffLabel = new JLabel("Difficulty: ");
        diffLabel.setFont(UIUtil.FONT_BOLD);
        
        bottomPanel.add(diffLabel);
        bottomPanel.add(diffCombo);
        bottomPanel.add(btnBack);
        
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createGameCard(String title, String mode, Color color, String imagePath) {
        JPanel card = UIUtil.createCardPanel();
        card.setLayout(new BorderLayout());
        
        ImageIcon icon = UIUtil.loadAndScaleImage(imagePath, 120, 120);
        if (icon != null) {
            JLabel lblImage = new JLabel(icon, SwingConstants.CENTER);
            lblImage.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
            card.add(lblImage, BorderLayout.NORTH);
        }
        
        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setFont(UIUtil.FONT_BOLD);
        lblTitle.setForeground(UIUtil.COLOR_TEXT);
        card.add(lblTitle, BorderLayout.CENTER);
        
        JButton btnPlay = UIUtil.createCustomButton("PLAY", color, color.brighter());
        btnPlay.addActionListener(e -> startGame(mode));
        card.add(btnPlay, BorderLayout.SOUTH);
        
        return card;
    }
    
    private void startGame(String mode) {
        String difficulty = (String) diffCombo.getSelectedItem();
        String filepath = switch(difficulty) {
            case Constants.DIFF_EASY -> Constants.FILE_WORDS_EASY;
            case Constants.DIFF_MEDIUM -> Constants.FILE_WORDS_MEDIUM;
            case Constants.DIFF_HARD -> Constants.FILE_WORDS_HARD;
            default -> Constants.FILE_WORDS_EASY;
        };
        
        JPanel gamePanel = null;
        
        if (mode.equals(Constants.MODE_UNSCRAMBLE)) {
            gamePanel = new UnscramblePanel(mainFrame, difficulty, WordFileManager.loadWords(filepath));
        } else if (mode.equals(Constants.MODE_GUESS)) {
            gamePanel = new WordGuessPanel(mainFrame, difficulty, WordFileManager.loadWords(filepath));
        } else if (mode.equals(Constants.MODE_QUIZ)) {
            gamePanel = new WordQuizPanel(mainFrame, difficulty, WordFileManager.loadQuestions(Constants.FILE_QUESTIONS));
        } else if (mode.equals(Constants.MODE_SEARCH)) {
            gamePanel = new WordSearchPanel(mainFrame, difficulty, WordFileManager.loadWords(filepath));
        } else if (mode.equals(Constants.MODE_TIMED)) {
            gamePanel = new TimedChallengePanel(mainFrame, difficulty, WordFileManager.loadWords(filepath));
        }

        if (gamePanel != null) {
            mainFrame.addPanel(gamePanel, mode);
            mainFrame.showPanel(mode);
        }
    }
}
