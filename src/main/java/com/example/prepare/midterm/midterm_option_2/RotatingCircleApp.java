package com.example.prepare.midterm.midterm_option_2;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Timeline;

public class RotatingCircleApp extends Application {

    private Timeline timeline;
    private double angle = 0;
    private double rotationSpeed = 2; // Начальная скорость

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group rotatingCircle = createRotatingCircle();

        // Анимация с использованием Timeline
        timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            angle += rotationSpeed;
            rotatingCircle.setRotate(angle);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // Кнопки управления
        Button pauseButton = new Button("Pause");
        Button resumeButton = new Button("Resume");
        Button reverseButton = new Button("Reverse");

        pauseButton.setOnAction(e -> timeline.pause());
        resumeButton.setOnAction(e -> timeline.play());
        reverseButton.setOnAction(e -> rotationSpeed = -rotationSpeed);

        // Ползунок скорости
        Slider speedSlider = new Slider(0.1, 10, 2);
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                rotationSpeed = newVal.doubleValue());

        // Размещение элементов
        HBox controls = new HBox(10, pauseButton, resumeButton, reverseButton);
        controls.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(rotatingCircle);
        root.setTop(controls);
        root.setBottom(speedSlider);

        Scene scene = new Scene(root, 400, 450);
        primaryStage.setTitle("Rotating Circle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Group createRotatingCircle() {
        Group group = new Group();
        double radius = 100;

        // Граница круга
        Circle border = new Circle(200, 200, radius);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(3);

        // 4 темных сектора с пробелами между ними
        for (int i = 0; i < 4; i++) {
            Arc arc = new Arc(200, 200, radius, radius, i * 90, 45);
            arc.setFill(Color.BLACK);
            arc.setType(ArcType.ROUND);
            arc.setStroke(Color.BLACK);
            group.getChildren().add(arc);
        }

        group.getChildren().add(border); // Добавляем границу круга

        return group;
    }
}
