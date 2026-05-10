package com.example.baze_podataka.controllers;

import com.example.baze_podataka.Config;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DodajOdlaganjePageController {
    private ComboBox<String> cbSupstance;
    private ComboBox<String> cbSesije;
    private ComboBox<String> cbLokacije;
    private TextField tfKolicina;

    public DodajOdlaganjePageController(ComboBox<String> cbSupstance, ComboBox<String> cbSesije, ComboBox<String> cbLokacije, TextField tfKolicina) {
        this.cbSupstance = cbSupstance;
        this.cbSesije = cbSesije;
        this.cbLokacije = cbLokacije;
        this.tfKolicina = tfKolicina;
    }

    public void popuniSveMenije() {
        try {
            Statement st = Config.getConnection().createStatement();

            ResultSet rsSup = st.executeQuery("SELECT supstanca_id, supstanca_naziv FROM hemijska_supstanca");
            while (rsSup.next()) {
                cbSupstance.getItems().add(rsSup.getInt("supstanca_id") + " - " + rsSup.getString("supstanca_naziv"));
            }

            ResultSet rsSes = st.executeQuery("SELECT sesija_id FROM sesija");
            while (rsSes.next()) {
                cbSesije.getItems().add(String.valueOf(rsSes.getInt("sesija_id")));
            }

            ResultSet rsLok = st.executeQuery("SELECT DISTINCT lokacija FROM odlaganje_otpada");
            while (rsLok.next()) {
                cbLokacije.getItems().add(rsLok.getString("lokacija"));
            }
            cbLokacije.getSelectionModel().select(0);
            cbSesije.getSelectionModel().select(0);
            cbSupstance.getSelectionModel().select(0);
        } catch (Exception e) {
            System.out.println("Greška pri učitavanju podataka: " + e.getMessage());
        }
    }

    public void registrujOdlaganje(Connection connection){
        try {
            int supstanca_id = Integer.parseInt(cbSupstance.getValue().split(" ")[0]);
            int sesija_id = Integer.parseInt(cbSesije.getValue());
            String lokacija =  cbLokacije.getValue();
            double kolicina = Double.parseDouble(tfKolicina.getText());

            PreparedStatement ps = connection.prepareStatement(
                    "CALL Procedura_RegistrujOdlaganje(?, ?, ?, ?)"
            );

            ps.setInt(1, supstanca_id);
            ps.setInt(2, sesija_id);
            ps.setDouble(3, kolicina);
            ps.setString(4, lokacija);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                System.out.println(rs.getString("Status"));
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
