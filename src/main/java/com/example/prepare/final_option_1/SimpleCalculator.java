package com.example.prepare.final_option_1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SimpleCalculator extends Application {

    private TextField xField = new TextField();
    private TextField yField = new TextField();
    private Label resultLabel = new Label("Результат: ");

    @Override
    public void start(Stage primaryStage) {
        // Верхняя часть с вводом x и y
        GridPane inputPane = new GridPane();
        inputPane.setHgap(10);
        inputPane.setVgap(5);
        inputPane.setAlignment(Pos.CENTER);

        Label xLabel = new Label("x = ");
        Label yLabel = new Label("y = ");

        xField.setPrefWidth(50);
        yField.setPrefWidth(50);

        inputPane.add(xLabel, 0, 0);
        inputPane.add(xField, 1, 0);
        inputPane.add(yLabel, 0, 1);
        inputPane.add(yField, 1, 1);

        // Кнопки операций
        HBox buttonPane = new HBox(10);
        buttonPane.setAlignment(Pos.CENTER);

        Button addButton = new Button("+");
        Button subButton = new Button("-");
        Button mulButton = new Button("*");
        Button divButton = new Button("/");

        addButton.setOnAction(e -> calculate("+"));
        subButton.setOnAction(e -> calculate("-"));
        mulButton.setOnAction(e -> calculate("*"));
        divButton.setOnAction(e -> calculate("/"));

        buttonPane.getChildren().addAll(addButton, subButton, mulButton, divButton);

        // Результат
        resultLabel.setFont(Font.font(18));
        resultLabel.setTextFill(Color.RED);
        HBox resultPane = new HBox(resultLabel);
        resultPane.setAlignment(Pos.CENTER);

        // Основной layout
        VBox root = new VBox(15, inputPane, buttonPane, resultPane);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Simple Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculate(String operation) {
        try {
            double x = Double.parseDouble(xField.getText());
            double y = Double.parseDouble(yField.getText());
            double result = 0;

            switch (operation) {
                case "+": result = x + y; break;
                case "-": result = x - y; break;
                case "*": result = x * y; break;
                case "/":
                    if (y == 0) {
                        resultLabel.setText("Ошибка: Деление на 0!");
                        return;
                    }
                    result = x / y;
                    break;
            }
            resultLabel.setText("Результат: " + x + " " + operation + " " + y + " = " + result);
        } catch (NumberFormatException e) {
            resultLabel.setText("Ошибка: Введите числа!");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
