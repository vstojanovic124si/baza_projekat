package com.example.baze_podataka.pages;

import com.example.baze_podataka.Config;
import com.example.baze_podataka.controllers.UpdateSessionController;
import com.example.baze_podataka.models.SessionDto;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class IzmenaSesijePage {

    private SessionDto selectedSesija;
    private Button btnHomePageBack = new Button("Nazad");

    public IzmenaSesijePage(SessionDto sesija) {
        this.selectedSesija = sesija;
    }

    public Scene getScene() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label lblId = new Label("ID Sesije: " + selectedSesija.getSessionId());

        Label lblLabId = new Label("ID Laboratorije:");
        TextField tfLabId = new TextField(String.valueOf(selectedSesija.getLaboratoryId()));

        Label lblDatum = new Label("Datum (yyyy-mm-dd):");
        TextField tfDatum = new TextField(selectedSesija.getDate().toString());

        Label lblStart = new Label("Početak (hh:mm:ss):");
        TextField tfStart = new TextField(selectedSesija.getStartTime().toString());

        Label lblEnd = new Label("Kraj (hh:mm:ss):");
        TextField tfEnd = new TextField(selectedSesija.getEndTime().toString());

        Button btnSacuvaj = new Button("Sačuvaj izmene");

        btnSacuvaj.setOnAction(e -> {
            UpdateSessionController updateSessionController
                    = new UpdateSessionController(selectedSesija.getSessionId(), tfDatum, tfStart, tfEnd, tfLabId);

            boolean success = updateSessionController.updateSession(Config.getConnection());

            if(success) {
                System.out.println("IZMENA SESIJE: Uspelo");
                btnHomePageBack.fire();
            }
            else{
                System.out.println("IZMENA SESIJE: GRESKA");
            }
        });

        layout.getChildren().addAll(lblId, lblLabId, tfLabId, lblDatum, tfDatum, lblStart, tfStart, lblEnd, tfEnd, btnSacuvaj, btnHomePageBack);

        return new Scene(layout, 300, 450);
    }

    public Button getBtnHomePageBack() {
        return btnHomePageBack;
    }
}