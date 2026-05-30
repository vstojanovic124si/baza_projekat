package com.example.baze_podataka.controllers;

import com.example.baze_podataka.models.OdlaganjeOtpadaDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SetOdlaganjeOtpadaValuesController {

    private TableView<OdlaganjeOtpadaDto> tvOdlaganjeOtpada;

    public SetOdlaganjeOtpadaValuesController(TableView<OdlaganjeOtpadaDto> tvOdlaganjeOtpada) {
        this.tvOdlaganjeOtpada = tvOdlaganjeOtpada;
    }

    public void runQuery(Connection connection) {
        try {
            String query = "SELECT * FROM View_KriticanOtpadPoLokacijama ORDER BY Ukupna_Kolicina_Kg DESC;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ObservableList<OdlaganjeOtpadaDto> result = FXCollections.observableArrayList();
            while (resultSet.next()) {
                String lokacija = resultSet.getString("Lokacija_Odlaganja");
                int brojOdlaganja = resultSet.getInt("Broj_Odlaganja");
                double ukupnaKolicina = resultSet.getDouble("Ukupna_Kolicina_Kg");
                int supstanca_id1 = resultSet.getInt("Supstanca_id");
                String supstanca_naziv = resultSet.getString("Supstanca_naziv");

                OdlaganjeOtpadaDto odlaganjeOtpadaDto = new OdlaganjeOtpadaDto(lokacija, brojOdlaganja, ukupnaKolicina, supstanca_id1, supstanca_naziv);
                result.add(odlaganjeOtpadaDto);
            }
            tvOdlaganjeOtpada.setItems(result);
        } catch (Exception e) {
        }
    }
}
