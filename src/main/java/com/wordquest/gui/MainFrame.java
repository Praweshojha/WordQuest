package com.wordquest.gui;

import com.wordquest.util.Constants;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle(Constants.APP_TITLE);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setResizable(false);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Add panels to CardLayout
        mainPanel.add(new HomePanel(this), "HOME");
        mainPanel.add(new PlayerPanel(this), "PLAYER");
        mainPanel.add(new GameSelectionPanel(this), "GAME_SELECTION");
        mainPanel.add(new LeaderboardPanel(this), "LEADERBOARD");
        mainPanel.add(new HistoryPanel(this), "HISTORY");
        mainPanel.add(new InstructionsPanel(this), "INSTRUCTIONS");
        mainPanel.add(new AboutPanel(this), "ABOUT");
        
        // Game panels will be added dynamically or initialized later
        // mainPanel.add(new UnscramblePanel(this), "UNSCRAMBLE");
        // mainPanel.add(new WordGuessPanel(this), "WORD_GUESS");
        // mainPanel.add(new WordSearchPanel(this), "WORD_SEARCH");
        // mainPanel.add(new WordQuizPanel(this), "WORD_QUIZ");
        // mainPanel.add(new TimedChallengePanel(this), "TIMED_CHALLENGE");

        add(mainPanel);
        cardLayout.show(mainPanel, "HOME");
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }
    
    public void addPanel(JPanel panel, String name) {
        mainPanel.add(panel, name);
    }
}
