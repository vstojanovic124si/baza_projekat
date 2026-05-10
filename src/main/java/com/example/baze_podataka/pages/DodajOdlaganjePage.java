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
    private ComboBox<String> cbSupstance = new ComboBox<>();
    private ComboBox<String> cbSesije = new ComboBox<>();
    private ComboBox<String> cbLokacije = new ComboBox<>();
    private TextField tfKolicina = new TextField();
    private DodajOdlaganjePageController controller;

    public DodajOdlaganjePage() {
        controller = new DodajOdlaganjePageController(cbSupstance, cbSesije, cbLokacije, tfKolicina);
        controller.popuniSveMenije();
    }

    public Scene getScene() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label lblTitle = new Label("Registracija Odlaganja Otpada");

        Label lblSup = new Label("Izaberite Supstancu:");
        cbSupstance.setMaxWidth(Double.MAX_VALUE);

        Label lblSes = new Label("Izaberite Sesiju:");
        cbSesije.setMaxWidth(Double.MAX_VALUE);

        Label lblKol = new Label("Količina (kg):");
        tfKolicina.setPromptText("Unesite težinu...");

        Label lblLok = new Label("Izaberite Lokaciju:");
        cbLokacije.setMaxWidth(Double.MAX_VALUE);

        Button btnRegistruj = new Button("Registruj odlaganje");
        btnRegistruj.setMinWidth(100);
        Label lblStatus = new Label("");

        btnRegistruj.setOnAction(e -> {
            controller.registrujOdlaganje(Config.getConnection());
        });

        layout.getChildren().addAll(
                lblTitle,
                lblSup, cbSupstance,
                lblSes, cbSesije,
                lblKol, tfKolicina,
                lblLok, cbLokacije,
                btnRegistruj, lblStatus, btnOdlaganjeOtpadaBack
        );

        return new Scene(layout, 400, 550);
    }

    public Button getBtnOdlaganjeOtpadaBack() {
        return btnOdlaganjeOtpadaBack;
    }
}