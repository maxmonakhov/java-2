package com.example.prepare.final_option_1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.time.LocalTime;

public class ClockDrawingWithSecondRotating extends Application {

    private Line secondHand;
    private double centerX = 150;
    private double centerY = 150;
    private double radius = 100;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        // Основной круг (циферблат)
        Circle clockCircle = new Circle(centerX, centerY, radius);
        clockCircle.setFill(Color.WHITE);
        clockCircle.setStroke(Color.BLACK);

        // Цифры на циферблате
        Text text12 = new Text(centerX - 10, centerY - radius + 15, "12");
        Text text3 = new Text(centerX + radius - 10, centerY + 5, "3");
        Text text6 = new Text(centerX - 5, centerY + radius - 5, "6");
        Text text9 = new Text(centerX - radius + 5, centerY + 5, "9");

        text12.setFont(Font.font(14));
        text3.setFont(Font.font(14));
        text6.setFont(Font.font(14));
        text9.setFont(Font.font(14));

        // Часовая стрелка (примерно на 10)
        Line hourHand = new Line(centerX, centerY, centerX - 30, centerY - 30);
        hourHand.setStroke(Color.BLACK);
        hourHand.setStrokeWidth(3);

        // Минутная стрелка (примерно на 45 минут)
        Line minuteHand = new Line(centerX, centerY, centerX - 50, centerY);
        minuteHand.setStroke(Color.BLACK);
        minuteHand.setStrokeWidth(2);

        // Секундная стрелка (изначально на 12)
        secondHand = new Line(centerX, centerY, centerX, centerY - radius + 10);
        secondHand.setStroke(Color.RED);
        secondHand.setStrokeWidth(1);

        // Анимация секундной стрелки
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateSecondHand()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Добавляем все элементы в root
        root.getChildren().addAll(clockCircle, text12, text3, text6, text9, hourHand, minuteHand, secondHand);

        // Создание сцены и запуск
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Часы с секундной стрелкой");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateSecondHand() {
        int second = LocalTime.now().getSecond();
        double angle = 6 * second - 90; // 6 градусов на каждую секунду, -90 для смещения на 12 часов
        double endX = centerX + (radius - 10) * Math.cos(Math.toRadians(angle));
        double endY = centerY + (radius - 10) * Math.sin(Math.toRadians(angle));
        secondHand.setEndX(endX);
        secondHand.setEndY(endY);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
