package com.example.baze_podataka.controllers;

import com.example.baze_podataka.models.LaboratoryDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SetLaboratorijaValuesController {
    private TableView<LaboratoryDto> tvLaboratorije;

    public SetLaboratorijaValuesController(TableView<LaboratoryDto> tvLaboratorije) {
        this.tvLaboratorije = tvLaboratorije;
    }

    public void runQuery(Connection connection){
        try {
            String query = "SELECT * FROM laboratorija";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ObservableList<LaboratoryDto> laboratorije = FXCollections.observableArrayList();
            while(rs.next()){
                int laboratorija_id = rs.getInt("laboratorija_id");
                String laboratorija_name = rs.getString("laboratorija_naziv");
                String laboratorija_opis_lokacije = rs.getString("opis_lokacije");
                LaboratoryDto laboratorija = new LaboratoryDto(laboratorija_id, laboratorija_name, laboratorija_opis_lokacije);
                laboratorije.add(laboratorija);
            }
            tvLaboratorije.setItems(laboratorije);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
