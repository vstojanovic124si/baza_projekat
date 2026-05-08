package com.example.baze_podataka.pages;

import com.example.baze_podataka.controllers.RegisterUserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterPage {
    public Scene getScene() {
        TextField userField = new TextField();
        userField.setPromptText("Username");
        PasswordField passField = new PasswordField();
        passField.setPromptText("New Password");
        PasswordField confirmPassField = new PasswordField();
        confirmPassField.setPromptText("Confirm Password");
        Button btnRegisterUser = new Button("Create Account");
        Button backButton = new Button("Back to Login");
        Label messageLabel = new Label();
        btnRegisterUser.setOnAction(e -> {
            RegisterUserController controller = new RegisterUserController();

            boolean success = controller.register(userField.getText(), passField.getText(), confirmPassField.getText());

            if(success) {
                messageLabel.setText("User registered!");
                LoginPage loginPage = new LoginPage();
                Stage stage = (Stage) btnRegisterUser.getScene().getWindow();
                stage.setScene(loginPage.getScene());

            } else {
                messageLabel.setText("Registration failed.");
            }
        });

        backButton.setOnAction(e -> {
            LoginPage loginPage = new LoginPage();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(loginPage.getScene());
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(
                userField,
                passField,
                confirmPassField,
                btnRegisterUser,
                messageLabel,
                backButton
        );

        return new Scene(layout, 500, 500);
    }
}