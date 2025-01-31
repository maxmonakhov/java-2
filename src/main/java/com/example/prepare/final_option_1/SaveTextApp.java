package com.example.prepare.final_option_1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;

public class SaveTextApp extends Application {

    private TextArea textArea = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        // Поле для ввода текста
        textArea.setPrefSize(300, 100);

        // Кнопка сохранения
        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(e -> saveTextToFile());

        // Layout
        VBox root = new VBox(10, textArea, saveButton);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20px;");

        // Создание сцены
        Scene scene = new Scene(root, 350, 200);
        primaryStage.setTitle("Сохранение текста");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void saveTextToFile() {
        String text = textArea.getText();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writer.write(text);
            System.out.println("Текст успешно сохранён в output.txt");
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
