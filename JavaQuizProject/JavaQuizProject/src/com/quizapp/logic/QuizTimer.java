package com.quizapp.logic;

import com.quizapp.gui.QuizPanel;

// This task will run on a separate thread
public class QuizTimer implements Runnable {

    private QuizPanel quizPanel;
    private int secondsRemaining;

    public QuizTimer(QuizPanel panel, int totalSeconds) {
        this.quizPanel = panel;
        this.secondsRemaining = totalSeconds;
    }

    @Override
    public void run() {
        try {
            while (secondsRemaining >= 0) {
                // Inter-thread communication:
                // Ask the GUI thread to update the label
                quizPanel.updateTimerDisplay(secondsRemaining);
                
                secondsRemaining--;
                
                // This makes the thread sleep for 1 second
                Thread.sleep(1000); 
            }
            // Time's up!
            quizPanel.submitQuiz(); // Auto-submit when time hits 0

        } catch (InterruptedException e) {
            // This happens if the user finishes early and we stop the thread.
            // It's normal, so we just print a message.
            System.out.println("Timer was interrupted (user finished early).");
        }
    }
}