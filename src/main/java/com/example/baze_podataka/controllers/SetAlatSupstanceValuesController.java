package com.example.baze_podataka.controllers;

import com.example.baze_podataka.Config;
import com.example.baze_podataka.models.Alat;
import com.example.baze_podataka.models.HemijskaSupstancaKolicina;
import com.example.baze_podataka.models.LaboratoryDto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SetAlatSupstanceValuesController implements ChangeListener {

    private TableView<LaboratoryDto> tvLaboratorija;
    private TableView<Alat> tvAlat;
    private TableView<HemijskaSupstancaKolicina> tvHemijskaSupstancaKolicina;

    public SetAlatSupstanceValuesController(TableView<LaboratoryDto> tvLaboratorija, TableView<Alat> tvAlat, TableView<HemijskaSupstancaKolicina> tvhemijskaSupstancaKolicina) {
        this.tvLaboratorija = tvLaboratorija;
        this.tvAlat = tvAlat;
        this.tvHemijskaSupstancaKolicina = tvhemijskaSupstancaKolicina;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        try {
            String query = "SELECT * FROM kolicina_resursa WHERE laboratorija_id = ?";
            PreparedStatement preparedStatement = Config.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, tvLaboratorija.getSelectionModel().getSelectedItem().getLaboratoryId());
            ResultSet rs = preparedStatement.executeQuery();
            ObservableList<HemijskaSupstancaKolicina> hemijskaSupstancaKolicine = FXCollections.observableArrayList();
            while(rs.next()){
                int supstanca_id = rs.getInt("supstanca_id");
                int laboratorija_id = rs.getInt("laboratorija_id");
                double kolicina_resursa = rs.getDouble("kolicina_resursa");
                String status_resursa = rs.getString("status_resursa");
                HemijskaSupstancaKolicina hemijskaSupstancaKolicina = new HemijskaSupstancaKolicina(supstanca_id, kolicina_resursa, status_resursa);
                hemijskaSupstancaKolicine.add(hemijskaSupstancaKolicina);
            }
            tvHemijskaSupstancaKolicina.setItems(hemijskaSupstancaKolicine);

            query = "SELECT * FROM alat WHERE laboratorija_id = ?";
            preparedStatement = Config.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, tvLaboratorija.getSelectionModel().getSelectedItem().getLaboratoryId());
            rs = preparedStatement.executeQuery();
            ObservableList<Alat> alati = FXCollections.observableArrayList();
            while(rs.next()){
                int alat_id = rs.getInt("alat_id");
                Date datum_nabavke = rs.getDate("datum_nabavke");
                Date datum_proizvodnje = rs.getDate("datum_proizvodnje");
                int vrsta_id = rs.getInt("vrsta_id");
                int laboratorija_id = rs.getInt("laboratorija_id");
                Alat alat = new Alat(alat_id, datum_nabavke, datum_proizvodnje, vrsta_id, laboratorija_id);
                alati.add(alat);
            }
            tvAlat.setItems(alati);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
