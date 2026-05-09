package com.example.baze_podataka;

import com.example.baze_podataka.pages.LoginPage;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginPage loginPage = new LoginPage();

        primaryStage.setScene(loginPage.getScene());

        primaryStage.setTitle("Hemijski eksperimenti");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
