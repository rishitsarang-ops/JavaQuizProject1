package com.quizapp.logic;

public class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String text, String[] opts, int correct) {
        this.questionText = text;
        this.options = opts;
        this.correctAnswerIndex = correct;
    }

    // Getters
    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}