package com.example.baze_podataka.controllers;

import com.example.baze_podataka.models.Eksperiment;
import com.example.baze_podataka.models.VrstaEksperimenta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.LightBase;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SetEksperimentValuesController {
    private TableView<Eksperiment> tvEksperiment;

    public SetEksperimentValuesController(TableView<Eksperiment> tvEksperiment) {
        this.tvEksperiment = tvEksperiment;
    }

    public void runQuery(Connection connection){
        try {
            System.out.println("START QUERY");
            String query = "SELECT * from eksperiment";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ObservableList<Eksperiment> eksperimenti = FXCollections.observableArrayList();
            while(rs.next()){
                int id = rs.getInt("eksperiment_id");
                String eksperiment_naziv = rs.getString("eksperiment_naziv");
                String ciljevi_istrazivanja = rs.getString("ciljevi_istrazivanja");
                int vrsta_eksperimenata = rs.getInt("vrsta_eksperimenta_id");
                Eksperiment eksperiment = new Eksperiment(id, eksperiment_naziv, ciljevi_istrazivanja, vrsta_eksperimenata);
                eksperimenti.add(eksperiment);
            }
            System.out.println("ROW FOUND");
            System.out.println(eksperimenti.size());
            tvEksperiment.setItems(eksperimenti);
        }
         catch (Exception e){
            e.printStackTrace();
         }
    }
}
