package com.quizapp.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private QuizPanel quizPanel;
    private WelcomePanel welcomePanel;
    private ResultPanel resultPanel;

    public MainFrame() {
        setTitle("JavaTech Quiz");
        setSize(600, 400); // Set a good default size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Create all the panels
        welcomePanel = new WelcomePanel(this);
        quizPanel = new QuizPanel(this);
        resultPanel = new ResultPanel(this);
        
        // Add panels to the CardLayout
        mainPanel.add(welcomePanel, "WELCOME");
        mainPanel.add(quizPanel, "QUIZ");
        mainPanel.add(resultPanel, "RESULT");
        
        add(mainPanel); // Add the main panel to the frame
        setLocationRelativeTo(null); // Center the window on screen
        setVisible(true);
    }
    
    // Public methods to allow panels to switch the view
    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }
    
    // Getters to pass data between panels
    public QuizPanel getQuizPanel() {
        return quizPanel;
    }

    public ResultPanel getResultPanel() {
        return resultPanel;
    }
}