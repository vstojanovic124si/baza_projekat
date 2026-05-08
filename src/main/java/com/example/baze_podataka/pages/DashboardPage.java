package com.example.baze_podataka.pages;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class DashboardPage {
    public Scene getScene(String username) {
        Label welcomeLabel = new Label("Welcome, " + username + "!");
        StackPane root = new StackPane(welcomeLabel);
        return new Scene(root, 400, 300);
    }
}