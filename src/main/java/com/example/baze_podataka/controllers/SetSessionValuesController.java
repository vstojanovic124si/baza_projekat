package com.example.baze_podataka.controllers;

import com.example.baze_podataka.Config;
import com.example.baze_podataka.models.Eksperiment;
import com.example.baze_podataka.models.SessionDto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.*;

public class SetSessionValuesController implements ChangeListener {
    private TableView<Eksperiment> tvEksperimenti;
    private TableView<SessionDto> tvSesije;

    public SetSessionValuesController(TableView<Eksperiment> tvEksperimenti, TableView<SessionDto> tvSesije) {
        this.tvEksperimenti = tvEksperimenti;
        this.tvSesije = tvSesije;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        runQuery();
    }

    private void runQuery(){
        try {
            Eksperiment eksperiment = tvEksperimenti.getSelectionModel().getSelectedItem();
            if(eksperiment == null)return;

            String query = "select sesija_id, laboratorija_id, s.datum, vreme_pocetka, vreme_zavrsetka from sesija s \n" +
                    "join izvodjenje_eksperimenta ie \n" +
                    "on s.izvodjenje_id = ie.izvodjenje_id " +
                    "where eksperiment_id = ?";

            PreparedStatement preparedStatement = Config.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, tvEksperimenti.getSelectionModel().getSelectedItem().getEksperimentId());
            ResultSet rs = preparedStatement.executeQuery();
            ObservableList<SessionDto> sesije = FXCollections.observableArrayList();
            while(rs.next()){
                int sesija_id = rs.getInt("sesija_id");
                int laboratorija_id = rs.getInt("laboratorija_id");
                Date datum = rs.getDate("datum");
                Time vremePocetka = rs.getTime("vreme_pocetka");
                Time vremeZavrsetka = rs.getTime("vreme_zavrsetka");
                SessionDto sesija = new SessionDto(sesija_id, datum, vremePocetka, vremeZavrsetka, laboratorija_id);
                sesije.add(sesija);
            }
            tvSesije.setItems(sesije);
        } catch (Exception e){
            System.out.println("SET CONTROLLER " + e.getMessage());
        }
    }
}
