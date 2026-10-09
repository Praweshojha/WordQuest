package com.wordquest.gui;

import com.wordquest.game.WordGuessGame;
import com.wordquest.model.Word;
import com.wordquest.util.Constants;
import com.wordquest.util.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WordGuessPanel extends JPanel {
    private MainFrame mainFrame;
    private WordGuessGame game;
    private JLabel lblWord;
    private JLabel lblAttempts;
    private JLabel lblGuessed;
    private JTextField txtGuess;

    public WordGuessPanel(MainFrame mainFrame, String difficulty, List<Word> words) {
        this.mainFrame = mainFrame;
        this.game = new WordGuessGame(difficulty, words);
        game.start();
        
        setBackground(UIUtil.COLOR_BACKGROUND);
        setLayout(new BorderLayout());
        
        add(UIUtil.createTitleLabel("WORD GUESS (Hangman)"), BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        
        lblAttempts = new JLabel("Attempts remaining: 6");
        lblAttempts.setFont(UIUtil.FONT_BOLD);
        lblAttempts.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblGuessed = new JLabel("Used letters: ");
        lblGuessed.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblWord = new JLabel("", SwingConstants.CENTER);
        lblWord.setFont(UIUtil.FONT_GAME_LARGE);
        lblWord.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        txtGuess = new JTextField(3);
        txtGuess.setMaximumSize(new Dimension(80, 50));
        txtGuess.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        txtGuess.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtGuess.setHorizontalAlignment(JTextField.CENTER);
        
        JButton btnGuess = UIUtil.createPrimaryButton("GUESS");
        btnGuess.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnGuess.addActionListener(e -> submitGuess());
        txtGuess.addActionListener(e -> submitGuess());
        
        centerPanel.add(Box.createVerticalStrut(50));
        centerPanel.add(lblAttempts);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(lblGuessed);
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(lblWord);
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(txtGuess);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(btnGuess);
        
        add(centerPanel, BorderLayout.CENTER);
        
        JButton btnExit = new JButton("Exit Game");
        btnExit.addActionListener(e -> mainFrame.showPanel("GAME_SELECTION"));
        add(btnExit, BorderLayout.SOUTH);
        
        updateUIState();
    }

    private void submitGuess() {
        String input = txtGuess.getText();
        if (input.length() > 0) {
            char c = input.charAt(0);
            if (Character.isLetter(c)) {
                game.guessLetter(c);
            }
        }
        updateUIState();
    }

    private void updateUIState() {
        if (game.isGameOver()) {
            if (game.isWon()) {
                JOptionPane.showMessageDialog(this, "You guessed it! The word was " + game.getActualWord(), "Victory", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Game Over! The word was " + game.getActualWord(), "Defeat", JOptionPane.ERROR_MESSAGE);
            }
            GameResultPanel resultPanel = new GameResultPanel(mainFrame, Constants.MODE_GUESS, game);
            mainFrame.addPanel(resultPanel, "RESULT");
            mainFrame.showPanel("RESULT");
        } else {
            lblWord.setText(game.getDisplayWord());
            lblAttempts.setText("Attempts remaining: " + game.getRemainingAttempts());
            lblGuessed.setText("Used letters: " + game.getGuessedLetters().toString());
            txtGuess.setText("");
            txtGuess.requestFocusInWindow();
        }
    }
}
