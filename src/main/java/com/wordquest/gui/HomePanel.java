package com.wordquest.gui;

import com.wordquest.util.UIUtil;
import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    
    private MainFrame mainFrame;

    public HomePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JPanel gradientPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(240, 244, 248);
                Color color2 = new Color(208, 225, 249);
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2.setPaint(gp);
                g2.fillRect(0, 0, w, h);
                g2.dispose();
            }
        };

        // Center Content
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        
        ImageIcon logoIcon = UIUtil.loadAndScaleImage("assets/logo.jpg", 300, 300);
        JLabel lblLogo;
        if (logoIcon != null) {
            lblLogo = new JLabel(logoIcon);
        } else {
            lblLogo = UIUtil.createTitleLabel("WORDQUEST");
            lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 64)); 
        }
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subTitleLabel = new JLabel("Java Word Puzzle Game");
        subTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        subTitleLabel.setForeground(new Color(100, 100, 100));
        subTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(lblLogo);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(subTitleLabel);
        centerPanel.add(Box.createVerticalStrut(40));
        
        JButton btnStart = UIUtil.createCustomButton("START GAME", UIUtil.COLOR_SUCCESS, UIUtil.COLOR_SUCCESS.brighter());
        btnStart.setFont(new Font("Segoe UI", Font.BOLD, 28));
        btnStart.setPreferredSize(new Dimension(300, 70));
        btnStart.setMaximumSize(new Dimension(300, 70));
        btnStart.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnStart.addActionListener(e -> mainFrame.showPanel("PLAYER"));
        
        centerPanel.add(btnStart);
        
        gradientPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Bottom Navigation
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        bottomPanel.setOpaque(false);
        
        Color navBtnColor = new Color(120, 120, 130);
        JButton btnLeaderboard = UIUtil.createCustomButton("Leaderboard", navBtnColor, navBtnColor.brighter());
        JButton btnHistory = UIUtil.createCustomButton("Game History", navBtnColor, navBtnColor.brighter());
        JButton btnInstructions = UIUtil.createCustomButton("Instructions", navBtnColor, navBtnColor.brighter());
        JButton btnAbout = UIUtil.createCustomButton("About", navBtnColor, navBtnColor.brighter());
        
        btnLeaderboard.addActionListener(e -> mainFrame.showPanel("LEADERBOARD"));
        btnHistory.addActionListener(e -> mainFrame.showPanel("HISTORY"));
        btnInstructions.addActionListener(e -> mainFrame.showPanel("INSTRUCTIONS"));
        btnAbout.addActionListener(e -> mainFrame.showPanel("ABOUT"));
        
        bottomPanel.add(btnLeaderboard);
        bottomPanel.add(btnHistory);
        bottomPanel.add(btnInstructions);
        bottomPanel.add(btnAbout);
        
        gradientPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        add(gradientPanel, BorderLayout.CENTER);
    }
}
