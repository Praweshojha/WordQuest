package com.wordquest.gui;

import com.wordquest.database.PlayerDAO;
import com.wordquest.game.GameEngine;
import com.wordquest.model.Player;
import com.wordquest.util.UIUtil;
import com.wordquest.util.ValidationUtil;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {
    
    private MainFrame mainFrame;
    private JTextField nameField;

    public PlayerPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setBackground(UIUtil.COLOR_BACKGROUND);
        setLayout(new GridBagLayout());
        
        JPanel card = UIUtil.createCardPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(400, 250));
        
        JLabel lblWelcome = new JLabel("Welcome to WordQuest!");
        lblWelcome.setFont(UIUtil.FONT_SUBTITLE);
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblPrompt = new JLabel("Enter your player name:");
        lblPrompt.setFont(UIUtil.FONT_REGULAR);
        lblPrompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        nameField = new JTextField(15);
        nameField.setMaximumSize(new Dimension(250, 40));
        nameField.setFont(UIUtil.FONT_REGULAR);
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton btnContinue = UIUtil.createPrimaryButton("CONTINUE");
        btnContinue.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnContinue.addActionListener(e -> {
            String name = nameField.getText();
            if (ValidationUtil.isValidPlayerName(name)) {
                PlayerDAO dao = new PlayerDAO();
                Player player = dao.getOrCreatePlayer(name.trim());
                if(player != null) {
                    GameEngine.getInstance().setCurrentPlayer(player);
                    mainFrame.showPanel("GAME_SELECTION");
                } else {
                    ValidationUtil.showError(this, "Database error: Could not create player.");
                }
            } else {
                ValidationUtil.showError(this, "Please enter a valid name (1-20 characters).");
            }
        });
        
        JButton btnBack = new JButton("Back");
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.addActionListener(e -> mainFrame.showPanel("HOME"));
        
        card.add(Box.createVerticalStrut(20));
        card.add(lblWelcome);
        card.add(Box.createVerticalStrut(20));
        card.add(lblPrompt);
        card.add(Box.createVerticalStrut(10));
        card.add(nameField);
        card.add(Box.createVerticalStrut(20));
        card.add(btnContinue);
        card.add(Box.createVerticalStrut(10));
        card.add(btnBack);
        
        add(card);
    }
}
