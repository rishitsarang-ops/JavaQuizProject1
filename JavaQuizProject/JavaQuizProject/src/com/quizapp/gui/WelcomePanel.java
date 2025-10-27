package com.quizapp.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel {
    private MainFrame mainFrame;

    public WelcomePanel(MainFrame frame) {
        this.mainFrame = frame;
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 150)); // Center layout
        
        JLabel welcomeLabel = new JLabel("Welcome to the JavaTech Quiz!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        JButton startButton = new JButton("Start Quiz");
        
        // This is Event Handling (Module 5.2)
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call methods on other panels to start the game
                mainFrame.getQuizPanel().startQuiz();
                mainFrame.showPanel("QUIZ");
            }
        });
        
        add(welcomeLabel);
        add(startButton);
    }
}