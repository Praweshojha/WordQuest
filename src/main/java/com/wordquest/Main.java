package com.wordquest;

import com.wordquest.database.DatabaseInitializer;
import com.wordquest.gui.MainFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Initialize Database
        DatabaseInitializer.initialize();

        // Start GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
