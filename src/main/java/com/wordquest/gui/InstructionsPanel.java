package com.wordquest.gui;

import com.wordquest.util.UIUtil;
import javax.swing.*;
import java.awt.*;

public class InstructionsPanel extends JPanel {
    
    public InstructionsPanel(MainFrame mainFrame) {
        setBackground(UIUtil.COLOR_BACKGROUND);
        setLayout(new BorderLayout());
        
        add(UIUtil.createTitleLabel("INSTRUCTIONS"), BorderLayout.NORTH);
        
        JTextArea txtInstructions = new JTextArea();
        txtInstructions.setText(
            "How to play WordQuest:\n\n" +
            "1. Enter your player name.\n" +
            "2. Select a Game Mode and Difficulty (Easy, Medium, Hard).\n\n" +
            "Game Modes:\n" +
            "- UNSCRAMBLE: Re-arrange the shuffled letters to form the correct word.\n" +
            "- WORD GUESS: Hangman-style game. Guess the word letter by letter. You have 6 attempts.\n" +
            "- WORD SEARCH: Find the target words hidden in the grid.\n" +
            "- WORD QUIZ: Answer multiple-choice questions about vocabulary.\n" +
            "- TIMED CHALLENGE: Unscramble words before the 30-second timer runs out!\n\n" +
            "Scoring:\n" +
            "- Easy: 100 pts per correct answer\n" +
            "- Medium: 150 pts per correct answer\n" +
            "- Hard: 200 pts per correct answer\n" +
            "- Timed: Bonus points for remaining seconds\n\n" +
            "Check out the Leaderboard to see top scores!"
        );
        txtInstructions.setFont(UIUtil.FONT_REGULAR);
        txtInstructions.setEditable(false);
        txtInstructions.setLineWrap(true);
        txtInstructions.setWrapStyleWord(true);
        txtInstructions.setMargin(new Insets(20, 20, 20, 20));
        txtInstructions.setBackground(UIUtil.COLOR_PANEL_BG);
        
        JScrollPane scrollPane = new JScrollPane(txtInstructions);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        
        add(scrollPane, BorderLayout.CENTER);
        
        JButton btnBack = new JButton("Back to Main Menu");
        btnBack.addActionListener(e -> mainFrame.showPanel("HOME"));
        
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.add(btnBack);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
