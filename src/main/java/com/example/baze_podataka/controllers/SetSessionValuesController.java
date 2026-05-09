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
import java.util.EventListener;

public class SetSessionValuesController implements ChangeListener {
    private TableView<Eksperiment> tvEksperimenti;
    private TableView<SessionDto> tvSesije;

    public SetSessionValuesController(TableView<Eksperiment> tvEksperimenti, TableView<SessionDto> tvSesije) {
        this.tvEksperimenti = tvEksperimenti;
        this.tvSesije = tvSesije;
    }

    public void runQuery(Connection connection) {

    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        try {
            String query = "SELECT * FROM sesija WHERE eksperiment_id = ?";
            PreparedStatement preparedStatement = Config.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, tvEksperimenti.getSelectionModel().getSelectedItem().getEksperimentId());
            ResultSet rs = preparedStatement.executeQuery();
            ObservableList<SessionDto> sesije = FXCollections.observableArrayList();
            while(rs.next()){
                int sesija_id = rs.getInt("sesija_id");
                int eksperiment_id = rs.getInt("eksperiment_id");
                int laboratorija_id = rs.getInt("laboratorija_id");
                Date datum = rs.getDate("datum");
                Time vremePocetka = rs.getTime("vreme_pocetka");
                Time vremeZavrsetka = rs.getTime("vreme_zavrsetka");
                SessionDto sesija = new SessionDto(sesija_id, datum, vremePocetka, vremeZavrsetka);
                sesije.add(sesija);
            }
            tvSesije.setItems(sesije);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
