package com.wordquest.util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UIUtil {
    // Playful, vibrant colors
    public static final Color COLOR_BACKGROUND = new Color(240, 244, 248); // Soft cool background
    public static final Color COLOR_PRIMARY = new Color(66, 133, 244);      // Vibrant Google Blue
    public static final Color COLOR_PRIMARY_HOVER = new Color(43, 108, 212);
    public static final Color COLOR_TEXT = new Color(32, 33, 36);
    public static final Color COLOR_ACCENT = new Color(234, 67, 53);        // Fun Red for time/alerts
    public static final Color COLOR_SUCCESS = new Color(52, 168, 83);       // Fun Green
    public static final Color COLOR_PANEL_BG = Color.WHITE;

    // Fonts
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 46);
    public static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font FONT_REGULAR = new Font("Segoe UI", Font.PLAIN, 18);
    public static final Font FONT_BOLD = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font FONT_GAME_LARGE = new Font("Segoe UI", Font.BOLD, 54);

    public static ImageIcon loadAndScaleImage(String path, int width, int height) {
        try {
            ImageIcon icon = new ImageIcon(path);
            if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
                System.err.println("Could not load image: " + path);
                return null;
            }
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImg);
        } catch (Exception e) {
            System.err.println("Error processing image: " + path);
            return null;
        }
    }

    public static JButton createPrimaryButton(String text) {
        return createCustomButton(text, COLOR_PRIMARY, COLOR_PRIMARY_HOVER);
    }

    public static JButton createCustomButton(String text, Color bg, Color hoverBg) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isArmed() || getModel().isPressed()) {
                    g2.setColor(hoverBg.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(hoverBg);
                } else {
                    g2.setColor(bg);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                
                // Draw text
                FontMetrics fm = g2.getFontMetrics();
                Rectangle stringBounds = fm.getStringBounds(this.getText(), g2).getBounds();
                int textX = (getWidth() - stringBounds.width) / 2;
                int textY = (getHeight() - stringBounds.height) / 2 + fm.getAscent();
                
                g2.setColor(Color.WHITE);
                g2.setFont(getFont());
                g2.drawString(this.getText(), textX, textY);
                
                g2.dispose();
            }
        };
        btn.setFont(FONT_BOLD);
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setBorder(new EmptyBorder(12, 25, 12, 25));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public static JLabel createTitleLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(FONT_TITLE);
        label.setForeground(COLOR_PRIMARY);
        return label;
    }

    public static JPanel createCardPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Shadow
                g2.setColor(new Color(0, 0, 0, 15));
                g2.fillRoundRect(4, 4, getWidth() - 8, getHeight() - 8, 30, 30);
                
                // Card body
                g2.setColor(COLOR_PANEL_BG);
                g2.fillRoundRect(0, 0, getWidth() - 8, getHeight() - 8, 30, 30);
                g2.dispose();
            }
        };
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        return panel;
    }
}
