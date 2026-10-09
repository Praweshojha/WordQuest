package com.wordquest.gui;

import com.wordquest.game.UnscrambleGame;
import com.wordquest.model.Word;
import com.wordquest.util.Constants;
import com.wordquest.util.UIUtil;
import com.wordquest.util.ValidationUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UnscramblePanel extends JPanel {
    private MainFrame mainFrame;
    private UnscrambleGame game;
    private JLabel lblWord;
    private JLabel lblHint;
    private JLabel lblProgress;
    private JLabel lblScore;
    private JTextField txtAnswer;

    public UnscramblePanel(MainFrame mainFrame, String difficulty, List<Word> words) {
        this.mainFrame = mainFrame;
        this.game = new UnscrambleGame(difficulty, words);
        game.start();
        
        setBackground(UIUtil.COLOR_BACKGROUND);
        setLayout(new BorderLayout());
        
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel title = UIUtil.createTitleLabel("UNSCRAMBLE THE WORD");
        lblScore = new JLabel("Score: 0");
        lblScore.setFont(UIUtil.FONT_BOLD);
        header.add(title, BorderLayout.CENTER);
        header.add(lblScore, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        
        lblProgress = new JLabel("");
        lblProgress.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblHint = new JLabel("");
        lblHint.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblWord = new JLabel("", SwingConstants.CENTER);
        lblWord.setFont(UIUtil.FONT_GAME_LARGE);
        lblWord.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        txtAnswer = new JTextField(15);
        txtAnswer.setMaximumSize(new Dimension(300, 50));
        txtAnswer.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        txtAnswer.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setOpaque(false);
        JButton btnSubmit = UIUtil.createPrimaryButton("SUBMIT");
        JButton btnSkip = new JButton("SKIP");
        btnPanel.add(btnSubmit);
        btnPanel.add(btnSkip);
        
        btnSubmit.addActionListener(e -> submitAnswer());
        txtAnswer.addActionListener(e -> submitAnswer()); // enter key
        btnSkip.addActionListener(e -> {
            game.skip();
            updateUIState();
        });
        
        centerPanel.add(Box.createVerticalStrut(50));
        centerPanel.add(lblProgress);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(lblHint);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(lblWord);
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(txtAnswer);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(btnPanel);
        
        add(centerPanel, BorderLayout.CENTER);
        
        JButton btnExit = new JButton("Exit Game");
        btnExit.addActionListener(e -> mainFrame.showPanel("GAME_SELECTION"));
        add(btnExit, BorderLayout.SOUTH);
        
        updateUIState();
    }

    private void submitAnswer() {
        String answer = txtAnswer.getText();
        if (!ValidationUtil.isNotEmpty(answer)) return;
        
        boolean correct = game.checkAnswer(answer);
        if (correct) {
            JOptionPane.showMessageDialog(this, "✓ Correct! +" + Constants.SCORE_MEDIUM + " points", "Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "✗ Incorrect! The word was: " + game.getCurrentWord().getText(), "Result", JOptionPane.ERROR_MESSAGE);
        }
        updateUIState();
    }

    private void updateUIState() {
        if (game.isGameOver()) {
            GameResultPanel resultPanel = new GameResultPanel(mainFrame, Constants.MODE_UNSCRAMBLE, game);
            mainFrame.addPanel(resultPanel, "RESULT");
            mainFrame.showPanel("RESULT");
        } else {
            lblProgress.setText("Question " + game.getCurrentQuestionNumber() + " / " + game.getTotalQuestions());
            lblHint.setText("Hint: " + game.getHint());
            lblWord.setText(game.getCurrentShuffledWord());
            lblScore.setText("Score: " + game.getScore());
            txtAnswer.setText("");
            txtAnswer.requestFocusInWindow();
        }
    }
}
