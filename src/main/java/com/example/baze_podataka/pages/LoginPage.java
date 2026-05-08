package com.example.baze_podataka.pages;

import com.example.baze_podataka.DatabaseManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPage {
    public Scene getScene() {
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");

        Label registerLabel = new Label("Don't have an account?");
        Button goToRegisterButton = new Button("Register here");

        loginButton.setOnAction(e -> {
            String user = usernameField.getText();
            String pass = passwordField.getText();

            if (user == null || user.trim().isEmpty() || pass == null || pass.trim().isEmpty()) {
                System.out.println("Error: Fields cannot be empty!");
                return;
            }

            boolean isValid = DatabaseManager.validateLogin(user, pass);

            if (isValid) {
                DashboardPage dashboard = new DashboardPage();
                Scene dashboardScene = dashboard.getScene(user);
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(dashboardScene);
            } else {
                System.out.println("Error: Invalid credentials.");
            }
        });

        goToRegisterButton.setOnAction(e -> {
            RegisterPage registerPage = new RegisterPage();
            Scene registerScene = registerPage.getScene();

            Stage stage = (Stage) goToRegisterButton.getScene().getWindow();
            stage.setScene(registerScene);
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(
                usernameField,
                passwordField,
                loginButton,
                new Separator(),
                registerLabel,
                goToRegisterButton
        );

        return new Scene(layout, 300, 300);
    }
}