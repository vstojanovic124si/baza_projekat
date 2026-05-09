package com.example.baze_podataka.pages;

import com.example.baze_podataka.controllers.LoginUserController;
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
        Button btnGoToRegisterPage = new Button("Register here");

        Label messageLabel = new Label();

        loginButton.setOnAction(e -> {
            LoginUserController controller = new LoginUserController();
            boolean success = controller.login(usernameField.getText(), passwordField.getText());
            if(success) {
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new HomePage().getScene());
            } else {
                messageLabel.setText("Invalid credentials.");
            }
        });

        btnGoToRegisterPage.setOnAction(e -> {
            RegisterPage registerPage = new RegisterPage();
            Stage stage = (Stage) btnGoToRegisterPage.getScene().getWindow();
            stage.setScene(registerPage.getScene());
            stage.setTitle("Home page");
        });

        VBox layout = new VBox(10);

        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(
                usernameField,
                passwordField,
                loginButton,
                messageLabel,
                new Separator(),
                registerLabel,
                btnGoToRegisterPage
        );

        return new Scene(layout, 500, 500);
    }
}