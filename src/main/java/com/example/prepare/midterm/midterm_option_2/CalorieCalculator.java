package com.example.prepare.midterm.midterm_option_2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CalorieCalculator extends Application {

    private final List<Product> products = new ArrayList<>();
    private final Label totalCaloriesLabel = new Label("Общее количество калорий: 0");
    private final Label totalStatsLabel = new Label("Белки: 0, Жиры: 0, Углеводы: 0");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Калькулятор калорий");

        // Поля ввода
        TextField nameField = new TextField();
        nameField.setPromptText("Название продукта");

        TextField amountField = new TextField();
        amountField.setPromptText("Количество (г)");

        TextField caloriesField = new TextField();
        caloriesField.setPromptText("Калорийность (ккал/100г)");

        TextField proteinField = new TextField();
        proteinField.setPromptText("Белки (г/100г)");

        TextField fatField = new TextField();
        fatField.setPromptText("Жиры (г/100г)");

        TextField carbsField = new TextField();
        carbsField.setPromptText("Углеводы (г/100г)");

        Button addButton = new Button("Добавить продукт");

        addButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                double amount = Double.parseDouble(amountField.getText());
                double calories = Double.parseDouble(caloriesField.getText());
                double protein = Double.parseDouble(proteinField.getText());
                double fat = Double.parseDouble(fatField.getText());
                double carbs = Double.parseDouble(carbsField.getText());

                Product product = new Product(name, amount, calories, protein, fat, carbs);
                products.add(product);
                updateStats();

                // Очистка полей после добавления
                nameField.clear();
                amountField.clear();
                caloriesField.clear();
                proteinField.clear();
                fatField.clear();
                carbsField.clear();

            } catch (NumberFormatException ex) {
                showAlert("Ошибка ввода", "Пожалуйста, введите корректные числовые значения.");
            }
        });

        VBox root = new VBox(10, nameField, amountField, caloriesField, proteinField, fatField, carbsField, addButton, totalCaloriesLabel, totalStatsLabel);
        root.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(root, 350, 400));
        primaryStage.show();
    }

    private void updateStats() {
        double totalCalories = 0, totalProtein = 0, totalFat = 0, totalCarbs = 0;

        for (Product product : products) {
            totalCalories += product.getTotalCalories();
            totalProtein += product.getTotalProtein();
            totalFat += product.getTotalFat();
            totalCarbs += product.getTotalCarbs();
        }

        totalCaloriesLabel.setText("Общее количество калорий: " + String.format("%.2f", totalCalories));
        totalStatsLabel.setText(String.format("Белки: %.2f, Жиры: %.2f, Углеводы: %.2f", totalProtein, totalFat, totalCarbs));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Класс продукта
    static class Product {
        private final String name;
        private final double amount;
        private final double calories;
        private final double protein;
        private final double fat;
        private final double carbs;

        public Product(String name, double amount, double calories, double protein, double fat, double carbs) {
            this.name = name;
            this.amount = amount;
            this.calories = calories;
            this.protein = protein;
            this.fat = fat;
            this.carbs = carbs;
        }

        public double getTotalCalories() {
            return (calories * amount) / 100;
        }

        public double getTotalProtein() {
            return (protein * amount) / 100;
        }

        public double getTotalFat() {
            return (fat * amount) / 100;
        }

        public double getTotalCarbs() {
            return (carbs * amount) / 100;
        }
    }
}
