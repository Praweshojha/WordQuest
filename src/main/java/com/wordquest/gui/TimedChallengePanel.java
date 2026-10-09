package com.wordquest.gui;

import com.wordquest.game.TimedChallengeGame;
import com.wordquest.model.Word;
import com.wordquest.util.Constants;
import com.wordquest.util.UIUtil;
import com.wordquest.util.ValidationUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TimedChallengePanel extends JPanel {
    private MainFrame mainFrame;
    private TimedChallengeGame game;
    private JLabel lblWord;
    private JLabel lblTime;
    private JLabel lblScore;
    private JTextField txtAnswer;
    private Timer timer;

    public TimedChallengePanel(MainFrame mainFrame, String difficulty, List<Word> words) {
        this.mainFrame = mainFrame;
        this.game = new TimedChallengeGame(difficulty, words);
        game.start();
        
        setBackground(UIUtil.COLOR_BACKGROUND);
        setLayout(new BorderLayout());
        
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.add(UIUtil.createTitleLabel("TIMED CHALLENGE"), BorderLayout.CENTER);
        
        lblScore = new JLabel("Score: 0");
        lblScore.setFont(UIUtil.FONT_BOLD);
        header.add(lblScore, BorderLayout.EAST);
        
        add(header, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        
        lblTime = new JLabel("TIME LEFT: 00:30");
        lblTime.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTime.setForeground(UIUtil.COLOR_ACCENT);
        lblTime.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblWord = new JLabel("", SwingConstants.CENTER);
        lblWord.setFont(UIUtil.FONT_GAME_LARGE);
        lblWord.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        txtAnswer = new JTextField(15);
        txtAnswer.setMaximumSize(new Dimension(300, 50));
        txtAnswer.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        txtAnswer.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton btnSubmit = UIUtil.createPrimaryButton("SUBMIT");
        btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnSubmit.addActionListener(e -> submitAnswer());
        txtAnswer.addActionListener(e -> submitAnswer()); // enter key
        
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(lblTime);
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(lblWord);
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(txtAnswer);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(btnSubmit);
        
        add(centerPanel, BorderLayout.CENTER);
        
        JButton btnExit = new JButton("Exit Game");
        btnExit.addActionListener(e -> {
            if(timer != null) timer.stop();
            mainFrame.showPanel("GAME_SELECTION");
        });
        add(btnExit, BorderLayout.SOUTH);
        
        // Setup Swing Timer
        timer = new Timer(1000, e -> {
            game.decrementTime();
            int timeRemaining = game.getTimeRemaining();
            lblTime.setText(String.format("TIME LEFT: 00:%02d", timeRemaining));
            if (game.isGameOver()) {
                timer.stop();
                endGame();
            }
        });
        timer.start();
        
        updateUIState();
    }

    private void submitAnswer() {
        String answer = txtAnswer.getText();
        if (!ValidationUtil.isNotEmpty(answer)) return;
        
        game.checkAnswer(answer);
        updateUIState();
        if (game.isGameOver()) {
            timer.stop();
            endGame();
        }
    }

    private void updateUIState() {
        lblWord.setText(game.getCurrentShuffledWord());
        lblScore.setText("Score: " + game.getScore());
        txtAnswer.setText("");
        txtAnswer.requestFocusInWindow();
    }
    
    private void endGame() {
        GameResultPanel resultPanel = new GameResultPanel(mainFrame, Constants.MODE_TIMED, game);
        mainFrame.addPanel(resultPanel, "RESULT");
        mainFrame.showPanel("RESULT");
    }
}
