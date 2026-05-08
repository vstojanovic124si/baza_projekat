package com.example.baze_podataka.pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

        Button registerButton = new Button("Create Account");
        Button backButton = new Button("Back to Login");

        registerButton.setOnAction(e -> {
            String user =  userField.getText().trim();
            String pass = passField.getText().trim();
            String confirmPass = confirmPassField.getText().trim();

            if(user == null || user.isEmpty() || pass == null || pass.isEmpty() || confirmPass == null || confirmPass.isEmpty()) {
                System.out.println("Error: Fields cannot be empty");
                return;
            }

            if(!pass.equalsIgnoreCase(confirmPass)) {
                System.out.println("Error: Passwords do not match");
                return;
            }

            /// query za dodavanje lika u bazu

            System.out.println("User registered!");
        });

        backButton.setOnAction(e -> {
            LoginPage loginPage = new LoginPage();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(loginPage.getScene());
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(userField, passField, confirmPassField, registerButton, backButton);

        return new Scene(layout, 300, 250);
    }
}