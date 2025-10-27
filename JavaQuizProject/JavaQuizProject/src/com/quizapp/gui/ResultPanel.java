package com.quizapp.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultPanel extends JPanel {
    private MainFrame mainFrame;
    private JLabel scoreLabel;
    private JButton restartButton;

    public ResultPanel(MainFrame frame) {
        this.mainFrame = frame;
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 150));
        
        scoreLabel = new JLabel("Your score: 0/0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        restartButton = new JButton("Try Again");
        
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the welcome screen
                mainFrame.showPanel("WELCOME");
            }
        });
        
        add(scoreLabel);
        add(restartButton);
    }
    
    // This is called by QuizPanel when the quiz is over
    public void showResult(int score, int totalQuestions) {
        scoreLabel.setText("Your final score: " + score + " / " + totalQuestions);
    }
}