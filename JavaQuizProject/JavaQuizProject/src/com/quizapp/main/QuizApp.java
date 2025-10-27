package com.quizapp.main;

import com.quizapp.gui.MainFrame;
import javax.swing.SwingUtilities;

public class QuizApp {
    public static void main(String[] args) {
        // All Swing applications should start this way
        // This makes sure the GUI runs on a dedicated thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}