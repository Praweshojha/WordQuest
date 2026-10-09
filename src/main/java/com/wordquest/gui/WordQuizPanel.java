package com.wordquest.gui;

import com.wordquest.game.WordQuizGame;
import com.wordquest.model.Question;
import com.wordquest.util.Constants;
import com.wordquest.util.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WordQuizPanel extends JPanel {
    private MainFrame mainFrame;
    private WordQuizGame game;
    private JLabel lblQuestion;
    private JLabel lblProgress;
    private JLabel lblScore;
    private JRadioButton rbtnA, rbtnB, rbtnC, rbtnD;
    private ButtonGroup group;

    public WordQuizPanel(MainFrame mainFrame, String difficulty, List<Question> questions) {
        this.mainFrame = mainFrame;
        this.game = new WordQuizGame(difficulty, questions);
        game.start();
        
        setBackground(UIUtil.COLOR_BACKGROUND);
        setLayout(new BorderLayout());
        
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.add(UIUtil.createTitleLabel("WORD QUIZ"), BorderLayout.CENTER);
        lblScore = new JLabel("Score: 0");
        lblScore.setFont(UIUtil.FONT_BOLD);
        header.add(lblScore, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        
        lblProgress = new JLabel();
        lblProgress.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblQuestion = new JLabel("Question text...");
        lblQuestion.setFont(UIUtil.FONT_SUBTITLE);
        lblQuestion.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setOpaque(false);
        optionsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        rbtnA = new JRadioButton("A");
        rbtnB = new JRadioButton("B");
        rbtnC = new JRadioButton("C");
        rbtnD = new JRadioButton("D");
        
        Font optFont = new Font("Segoe UI", Font.PLAIN, 18);
        rbtnA.setFont(optFont); rbtnB.setFont(optFont);
        rbtnC.setFont(optFont); rbtnD.setFont(optFont);
        
        group = new ButtonGroup();
        group.add(rbtnA); group.add(rbtnB);
        group.add(rbtnC); group.add(rbtnD);
        
        optionsPanel.add(rbtnA); optionsPanel.add(Box.createVerticalStrut(10));
        optionsPanel.add(rbtnB); optionsPanel.add(Box.createVerticalStrut(10));
        optionsPanel.add(rbtnC); optionsPanel.add(Box.createVerticalStrut(10));
        optionsPanel.add(rbtnD);
        
        JButton btnSubmit = UIUtil.createPrimaryButton("SUBMIT");
        btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSubmit.addActionListener(e -> submitAnswer());
        
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(lblProgress);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(lblQuestion);
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(optionsPanel);
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(btnSubmit);
        
        add(centerPanel, BorderLayout.CENTER);
        
        JButton btnExit = new JButton("Exit Game");
        btnExit.addActionListener(e -> mainFrame.showPanel("GAME_SELECTION"));
        add(btnExit, BorderLayout.SOUTH);
        
        updateUIState();
    }

    private void submitAnswer() {
        String answer = "";
        if (rbtnA.isSelected()) answer = "A";
        else if (rbtnB.isSelected()) answer = "B";
        else if (rbtnC.isSelected()) answer = "C";
        else if (rbtnD.isSelected()) answer = "D";
        
        if (answer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select an option.");
            return;
        }

        Question q = game.getCurrentQuestion();
        boolean correct = game.checkAnswer(answer);
        if (correct) {
            JOptionPane.showMessageDialog(this, "✓ Correct!", "Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "✗ Incorrect! Correct answer was: " + q.getCorrectAnswer(), "Result", JOptionPane.ERROR_MESSAGE);
        }
        
        group.clearSelection();
        updateUIState();
    }

    private void updateUIState() {
        if (game.isGameOver()) {
            GameResultPanel resultPanel = new GameResultPanel(mainFrame, Constants.MODE_QUIZ, game);
            mainFrame.addPanel(resultPanel, "RESULT");
            mainFrame.showPanel("RESULT");
        } else {
            Question q = game.getCurrentQuestion();
            lblProgress.setText("Question " + game.getCurrentQuestionNumber() + " / " + game.getTotalQuestions());
            lblQuestion.setText(q.getQuestionText());
            rbtnA.setText("A. " + q.getOptionA());
            rbtnB.setText("B. " + q.getOptionB());
            rbtnC.setText("C. " + q.getOptionC());
            rbtnD.setText("D. " + q.getOptionD());
            lblScore.setText("Score: " + game.getScore());
        }
    }
}
