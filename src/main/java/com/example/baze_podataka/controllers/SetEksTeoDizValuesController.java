package com.example.baze_podataka.controllers;

import com.example.baze_podataka.Config;
import com.example.baze_podataka.models.EksperimentTeorijaDizajnerDto;
import com.example.baze_podataka.models.IstrazivacDto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SetEksTeoDizValuesController implements ChangeListener {
    private TableView<EksperimentTeorijaDizajnerDto> tvEksperimentEksTeoDiz;
    private TableView<IstrazivacDto> tvIstrazivac;

    public SetEksTeoDizValuesController(TableView<IstrazivacDto> tvIstrazivac, TableView<EksperimentTeorijaDizajnerDto> tvEksperimentEksTeoDiz) {
        this.tvEksperimentEksTeoDiz =  tvEksperimentEksTeoDiz;
        this.tvIstrazivac = tvIstrazivac;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        try{
            tvEksperimentEksTeoDiz.getItems().clear();
            IstrazivacDto selektovaniIstrazivac = tvIstrazivac.getSelectionModel().getSelectedItem();

            if (selektovaniIstrazivac == null) {
                return;
            }

            if (selektovaniIstrazivac.getDaLiJeDizajner().equalsIgnoreCase("Da")) {
                String query = "select eksp.eksperiment_naziv, eksp.ciljevi_istrazivanja, teo.teorija_naziv, teo.teorija_opis from\n" +
                        "eksperiment eksp join teorija teo\n" +
                        "on eksp.teorija_id = teo.teorija_id\n" +
                        "where teo.dizajner_id = ?";

                int indeks = tvIstrazivac.getSelectionModel().getSelectedItem().getIstrazivac_id();
                PreparedStatement preparedStatement = Config.getConnection().prepareStatement(query);
                preparedStatement.setInt(1, indeks);

                ResultSet resultSet = preparedStatement.executeQuery();
                ObservableList<EksperimentTeorijaDizajnerDto> list = FXCollections.observableArrayList();

                while(resultSet.next()){
                    String nazivEksperimenta =  resultSet.getString("eksp.eksperiment_naziv");
                    String ciljeviIstrazivanja = resultSet.getString("eksp.ciljevi_istrazivanja");
                    String teorijaNaziv = resultSet.getString("teorija_naziv");
                    String teorijaOpis = resultSet.getString("teorija_opis");
                    EksperimentTeorijaDizajnerDto eksperimentTeorijaDizajnerDto =
                            new EksperimentTeorijaDizajnerDto(nazivEksperimenta, ciljeviIstrazivanja, teorijaNaziv, teorijaOpis);
                    list.add(eksperimentTeorijaDizajnerDto);
                }
                tvEksperimentEksTeoDiz.setItems(list);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
