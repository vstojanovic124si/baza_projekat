package com.example.baze_podataka.controllers;

import com.example.baze_podataka.Config;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

public class DodajOdlaganjePageController {
    private ComboBox<String> cbHemijskiOtpad;
    private ComboBox<String> cbLokacije;
    private ComboBox<String> cbBezbednosniNivo;
    private TextField tfVrstaOdlaganja;

    public DodajOdlaganjePageController(ComboBox<String> cbHemijskiOtpad,
                                        ComboBox<String> cbLokacije,
                                        ComboBox<String> cbBezbednosniNivo,
                                        TextField tfVrstaOdlaganja) {
        this.cbHemijskiOtpad = cbHemijskiOtpad;
        this.cbLokacije = cbLokacije;
        this.cbBezbednosniNivo = cbBezbednosniNivo;
        this.tfVrstaOdlaganja = tfVrstaOdlaganja;
    }

    public void popuniSveMenije() {
        try {
            cbHemijskiOtpad.getItems().clear();
            cbLokacije.getItems().clear();
            cbBezbednosniNivo.getItems().clear();

            cbBezbednosniNivo.getItems().addAll("Visok", "Srednji", "Nizak");
            Connection conn = Config.getConnection();

            String query = "SELECT ho.otpad_id, s.supstanca_naziv " +
                    "FROM hemijski_otpad ho " +
                    "JOIN hemijska_supstanca s ON ho.supstanca_id = s.supstanca_id " +
                    "WHERE ho.otpad_id NOT IN (SELECT otpad_id FROM odlaganje_otpada)";

            Statement st = conn.createStatement();
            ResultSet rsHemijskiOtpad = st.executeQuery(query);

            while (rsHemijskiOtpad.next()) {
                int id = rsHemijskiOtpad.getInt("otpad_id");
                String naziv = rsHemijskiOtpad.getString("supstanca_naziv");
                cbHemijskiOtpad.getItems().add(id + " - " + naziv);
            }

            ResultSet rsLok = st.executeQuery("SELECT DISTINCT lokacija FROM odlaganje_otpada");

            while (rsLok.next()) {
                cbLokacije.getItems().add(rsLok.getString("lokacija"));
            }

            if (!cbLokacije.getItems().isEmpty()) {
                cbLokacije.getSelectionModel().select(0);
            }
            if(!cbHemijskiOtpad.getItems().isEmpty()) {
                cbHemijskiOtpad.getSelectionModel().select(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Greška pri učitavanju podataka: " + e.getMessage());
        }
    }


    public boolean registrujOdlaganje(Connection connection){
        try {
            int otpad_id = Integer.parseInt(cbHemijskiOtpad.getValue().split(" - ")[0]);
            String lokacija =  cbLokacije.getValue();
            String bezbednost = cbBezbednosniNivo.getValue();
            String vrstaOdlaganja = tfVrstaOdlaganja.getText();

            String query = "INSERT INTO odlaganje_otpada (otpad_id, lokacija, datum_odlaganja, vreme_odlaganja, bezbednosni_nivo, vrsta_odlaganja)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, otpad_id);
            st.setString(2, lokacija);
            st.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            st.setTime(4, java.sql.Time.valueOf(LocalTime.now()));
            st.setString(5, bezbednost);
            st.setString(6, vrstaOdlaganja);
            int status = st.executeUpdate();
            return status > 0;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
