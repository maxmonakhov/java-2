package com.example.prepare.final_option_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ClockDrawing extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        double radius = 100;
        double centerX = 150;
        double centerY = 150;

        Circle clockCircle = new Circle(centerX, centerY, radius);
        clockCircle.setFill(Color.WHITE);
        clockCircle.setStroke(Color.BLACK);

        Text text12 = new Text(centerX - 10, centerY - radius + 15, "12");
        Text text3 = new Text(centerX + radius - 10, centerY + 5, "3");
        Text text6 = new Text(centerX - 5, centerY + radius - 5, "6");
        Text text9 = new Text(centerX - radius + 5, centerY + 5, "9");

        text12.setFont(Font.font(14));
        text3.setFont(Font.font(14));
        text6.setFont(Font.font(14));
        text9.setFont(Font.font(14));

        Line hourHand = new Line(centerX, centerY, centerX - 30, centerY - 30);
        hourHand.setStroke(Color.BLACK);
        hourHand.setStrokeWidth(3);

        Line minuteHand = new Line(centerX, centerY, centerX - 50, centerY);
        minuteHand.setStroke(Color.BLACK);
        minuteHand.setStrokeWidth(2);

        root.getChildren().addAll(clockCircle, text12, text3, text6, text9, hourHand, minuteHand);

        Scene scene = new Scene(root, 300, 300);

        primaryStage.setTitle("Часы на JavaFX");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
