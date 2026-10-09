package com.wordquest.util;

import javax.swing.JOptionPane;
import java.awt.Component;

public class ValidationUtil {
    
    public static boolean isValidPlayerName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() <= 20;
    }
    
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }
    
    public static void showError(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void showInfo(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
