package com.example.baze_podataka.pages;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DashboardPage {

    public Scene getScene(String username) {
        Label welcomeLabel = new Label("Welcome " + username);
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().add(welcomeLabel);
        return new Scene(layout, 500, 400);
    }
}