package com.wordquest.gui;

import com.wordquest.util.UIUtil;
import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JPanel {
    
    public AboutPanel(MainFrame mainFrame) {
        setBackground(UIUtil.COLOR_BACKGROUND);
        setLayout(new BorderLayout());
        
        add(UIUtil.createTitleLabel("ABOUT WORDQUEST"), BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        
        JLabel lblTitle = new JLabel("WordQuest");
        lblTitle.setFont(UIUtil.FONT_TITLE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblSub = new JLabel("Java Word Puzzle Game");
        lblSub.setFont(UIUtil.FONT_SUBTITLE);
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblDesc = new JLabel("Developed as a Java Mini Project");
        lblDesc.setFont(UIUtil.FONT_REGULAR);
        lblDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblTechTitle = new JLabel("Technology Stack:");
        lblTechTitle.setFont(UIUtil.FONT_BOLD);
        lblTechTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblTechs = new JLabel("<html><center>Java 21 LTS<br>Java Swing (GUI)<br>JDBC API<br>SQLite Database<br>Java File I/O</center></html>");
        lblTechs.setFont(UIUtil.FONT_REGULAR);
        lblTechs.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        centerPanel.add(Box.createVerticalStrut(50));
        centerPanel.add(lblTitle);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(lblSub);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(lblDesc);
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(lblTechTitle);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(lblTechs);
        
        add(centerPanel, BorderLayout.CENTER);
        
        JButton btnBack = new JButton("Back to Main Menu");
        btnBack.addActionListener(e -> mainFrame.showPanel("HOME"));
        
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.add(btnBack);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
