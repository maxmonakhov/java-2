package com.example.prepare.midterm.midterm_option_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class TaskOrganizerApp extends Application {
    private ListView<String> taskListView;
    private TextField titleField;
    private TextArea descriptionArea;
    private DatePicker deadlinePicker;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Органайзер задач");

        taskListView = new ListView<>();

        titleField = new TextField();
        titleField.setPromptText("Название задачи");

        descriptionArea = new TextArea();
        descriptionArea.setPromptText("Описание задачи");

        deadlinePicker = new DatePicker();
        deadlinePicker.setPromptText("Срок выполнения");

        Button addButton = new Button("Добавить задачу");
        addButton.setOnAction(e -> addTask());

        Button removeButton = new Button("Удалить выбранную задачу");
        removeButton.setOnAction(e -> removeTask());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(titleField, descriptionArea, deadlinePicker, addButton, taskListView, removeButton);

        primaryStage.setScene(new Scene(layout, 400, 500));
        primaryStage.show();
    }

    private void addTask() {
        String title = titleField.getText();
        String description = descriptionArea.getText();
        String deadline = (deadlinePicker.getValue() != null) ? deadlinePicker.getValue().toString() : "Без срока";

        if (title.isEmpty() || description.isEmpty()) {
            showAlert("Ошибка", "Введите название и описание задачи!");
            return;
        }

        String task = title + " (Срок: " + deadline + ")\n" + description;
        taskListView.getItems().add(task);

        titleField.clear();
        descriptionArea.clear();
        deadlinePicker.setValue(null);
    }

    private void removeTask() {
        int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            taskListView.getItems().remove(selectedIndex);
        } else {
            showAlert("Ошибка", "Выберите задачу для удаления!");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
