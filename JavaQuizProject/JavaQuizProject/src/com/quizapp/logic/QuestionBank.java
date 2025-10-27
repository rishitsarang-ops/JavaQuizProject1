package com.quizapp.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane; // For showing error popups

public class QuestionBank {

    public static List<Question> loadQuestions(String filePath) {
        List<Question> questions = new ArrayList<>();
        // Use try-with-resources for automatic file closing
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) { // Ensures the line is valid
                    String text = parts[0];
                    String[] options = { parts[1], parts[2], parts[3] };
                    int correct = Integer.parseInt(parts[4]);
                    questions.add(new Question(text, options, correct));
                }
            }
        } catch (IOException e) {
            // Proper exception handling with a GUI popup
            JOptionPane.showMessageDialog(null, 
                "Error: Could not read questions.txt.\n" + e.getMessage(), 
                "File Error", 
                JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            // Handle error in the file format
            JOptionPane.showMessageDialog(null, 
                "Error: Invalid number format in questions.txt.", 
                "File Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        return questions;
    }
}