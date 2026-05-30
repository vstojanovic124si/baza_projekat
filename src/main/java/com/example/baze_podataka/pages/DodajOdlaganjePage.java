package com.example.baze_podataka.pages;

import com.example.baze_podataka.Config;
import com.example.baze_podataka.controllers.DodajOdlaganjePageController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.*;

public class DodajOdlaganjePage {
    private Button btnOdlaganjeOtpadaBack = new Button("Nazad");
    private ComboBox<String> cbHemijskiOtpad = new  ComboBox<>();
    private ComboBox<String> cbLokacije = new ComboBox<>();
    private ComboBox<String> cbBezbednosniNivo = new ComboBox<>();
    private TextField tfVrstaOdlaganja = new  TextField();
    private DodajOdlaganjePageController controller;

    public DodajOdlaganjePage() {
        controller = new DodajOdlaganjePageController(cbHemijskiOtpad, cbLokacije, cbBezbednosniNivo, tfVrstaOdlaganja);
        controller.popuniSveMenije();
    }

    public Scene getScene() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label lblTitle = new Label("Registracija Odlaganja Otpada");

        Label lblHemijskiOtpad = new Label("Hemijski Otpad");
        cbHemijskiOtpad.setMaxWidth(Double.MAX_VALUE);

        Label lblLok = new Label("Izaberite Lokaciju:");
        cbLokacije.setMaxWidth(Double.MAX_VALUE);

        Label lblBezbednost = new  Label("Bezednost:");
        cbBezbednosniNivo.setMaxWidth(Double.MAX_VALUE);

        Label lblVrsta = new Label("Vrsta:");
        tfVrstaOdlaganja.setMaxWidth(Double.MAX_VALUE);

        Button btnRegistruj = new Button("Registruj odlaganje");
        btnRegistruj.setMinWidth(100);
        Label lblStatus = new Label("");

        btnRegistruj.setOnAction(e -> {
            boolean status = controller.registrujOdlaganje(Config.getConnection());

            if(status){
                System.out.println("Uspelo");
            }
        });

        layout.getChildren().addAll(
                lblTitle,
                lblHemijskiOtpad, cbHemijskiOtpad,
                lblLok, cbLokacije,
                lblBezbednost, cbBezbednosniNivo,
                lblVrsta, tfVrstaOdlaganja,
                btnRegistruj, lblStatus, btnOdlaganjeOtpadaBack
        );

        return new Scene(layout, 400, 550);
    }

    public Button getBtnOdlaganjeOtpadaBack() {
        return btnOdlaganjeOtpadaBack;
    }
}