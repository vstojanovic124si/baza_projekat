package com.example.baze_podataka.controllers;

import com.example.baze_podataka.pages.LaboratorijaPage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.sql.Statement;
import java.util.EventListener;

public class ButtonLaboratorijaController implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        Stage stage = new LaboratorijaPage();
        stage.show();

    }
}
