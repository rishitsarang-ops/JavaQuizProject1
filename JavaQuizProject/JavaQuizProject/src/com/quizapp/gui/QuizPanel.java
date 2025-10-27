package com.quizapp.gui;

import com.quizapp.logic.Question;
import com.quizapp.logic.QuestionBank;
import com.quizapp.logic.QuizTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class QuizPanel extends JPanel {
    private MainFrame mainFrame;
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;

    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;
    private JButton nextButton;
    private JLabel timerLabel;

    private Thread timerThread;

    public QuizPanel(MainFrame frame) {
        this.mainFrame = frame;
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout

        // Top panel for question
        questionLabel = new JLabel("Question will appear here");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(questionLabel, BorderLayout.NORTH);

        // Center panel for options
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        optionButtons = new JRadioButton[4]; // We assume 4 options, but our file has 3
        optionGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        // Bottom panel for timer and next button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        timerLabel = new JLabel("Time: 60");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        bottomPanel.add(timerLabel, BorderLayout.WEST);

        nextButton = new JButton("Next");
        nextButton.addActionListener(e -> handleNextButton());
        bottomPanel.add(nextButton, BorderLayout.EAST);
        
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void startQuiz() {
        // Load questions from the file
        questions = QuestionBank.loadQuestions("questions.txt");
        if (questions.isEmpty()) {
            mainFrame.showPanel("WELCOME"); // Go back if no questions loaded
            return;
        }
        currentQuestionIndex = 0;
        score = 0;
        loadQuestion();
        startTimer(); // Start the timer thread
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question q = questions.get(currentQuestionIndex);
            questionLabel.setText("Q" + (currentQuestionIndex + 1) + ": " + q.getQuestionText());
            
            String[] options = q.getOptions();
            for (int i = 0; i < options.length; i++) {
                optionButtons[i].setText(options[i]);
                optionButtons[i].setVisible(true);
            }
            // Hide unused radio buttons
            for (int i = options.length; i < 4; i++) {
                optionButtons[i].setVisible(false);
            }
            optionGroup.clearSelection(); // Clear previous selection
        } else {
            // No more questions
            submitQuiz();
        }
    }

    private void handleNextButton() {
        // Check if an answer is correct
        int selected = -1;
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                selected = i;
                break;
            }
        }
        
        if (selected == -1) {
            JOptionPane.showMessageDialog(this, "Please select an answer.");
            return;
        }

        if (selected == questions.get(currentQuestionIndex).getCorrectAnswerIndex()) {
            score++;
        }

        // Move to the next question
        currentQuestionIndex++;
        loadQuestion();
    }

    private void startTimer() {
        // Create and start the new thread (Module 4.1)
        QuizTimer quizTimer = new QuizTimer(this, 60); // 60 seconds
        timerThread = new Thread(quizTimer);
        timerThread.start();
    }

    // This method is called by the timer thread
    public void updateTimerDisplay(int seconds) {
        // Must use invokeLater for thread safety with Swing
        SwingUtilities.invokeLater(() -> {
            timerLabel.setText("Time: " + seconds);
        });
    }

    public void submitQuiz() {
        // Stop the timer thread
        if (timerThread != null && timerThread.isAlive()) {
            timerThread.interrupt(); 
        }
        
        // Show the results on the result panel
        mainFrame.getResultPanel().showResult(score, questions.size());
        mainFrame.showPanel("RESULT");
    }
}