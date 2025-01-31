package com.example.prepare.midterm.midterm_option_2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class StudentsApp extends Application {

    private static final String FILE_NAME = "students.txt";

    @Override
    public void start(Stage primaryStage) {
        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        List<String> studentData = readStudentsFromFile(FILE_NAME);
        StringBuilder output = new StringBuilder();

        int totalAge = 0;
        int studentCount = 0;

        for (String line : studentData) {
            output.append(line).append("\n");
            String[] parts = line.split(" ");
            if (parts.length == 4) {
                try {
                    totalAge += Integer.parseInt(parts[2]); // Возраст
                    studentCount++;
                } catch (NumberFormatException e) {
                    output.append("Ошибка в данных: ").append(line).append("\n");
                }
            }
        }

        if (studentCount > 0) {
            double averageAge = (double) totalAge / studentCount;
            output.append("\nСредний возраст: ").append(String.format("%.2f", averageAge));
        } else {
            output.append("\nНет корректных данных для расчета.");
        }

        textArea.setText(output.toString());

        Scene scene = new Scene(textArea, 400, 300);
        primaryStage.setTitle("Список студентов");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private List<String> readStudentsFromFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            return List.of("Ошибка чтения файла: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
