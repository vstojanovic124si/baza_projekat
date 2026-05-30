package com.example.baze_podataka.controllers;

import com.example.baze_podataka.Config;
import com.example.baze_podataka.models.EksperimentIzvodjacIzvDto;
import com.example.baze_podataka.models.IstrazivacDto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SetDaLiJeIzvodjacController implements ChangeListener {
    private TableView<EksperimentIzvodjacIzvDto> tvEksperimentIzvodjacIzvodjenje;
    private TableView<IstrazivacDto> tvIstrazivac;

    public SetDaLiJeIzvodjacController(TableView<IstrazivacDto> tvIstrazivac, TableView<EksperimentIzvodjacIzvDto> tvEksperimentIzvodjacIzvodjenje) {
        this.tvEksperimentIzvodjacIzvodjenje =  tvEksperimentIzvodjacIzvodjenje;
        this.tvIstrazivac = tvIstrazivac;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        try{
            tvEksperimentIzvodjacIzvodjenje.getItems().clear();
            IstrazivacDto selektovaniIstrazivac = tvIstrazivac.getSelectionModel().getSelectedItem();

            if (selektovaniIstrazivac == null) {
                return;
            }

            if (selektovaniIstrazivac.getDaLiJeIzvodjac().equalsIgnoreCase("Da")) {
                String query = "select eksperiment.eksperiment_naziv, uloga, izvodjenje_eksperimenta.datum, izvodjenje_eksperimenta.status\n" +
                        "from eksperiment join izvodjenje_eksperimenta\n" +
                        "on eksperiment.eksperiment_id = izvodjenje_eksperimenta.eksperiment_id\n" +
                        "join izvodjac_izvodjenje\n" +
                        "on izvodjenje_eksperimenta.izvodjenje_id = izvodjac_izvodjenje.izvodjenje_id\n" +
                        "where izvodjac_izvodjenje.istrazivac_id = ?";

                int indeks = tvIstrazivac.getSelectionModel().getSelectedItem().getIstrazivac_id();
                PreparedStatement preparedStatement = Config.getConnection().prepareStatement(query);
                preparedStatement.setInt(1, indeks);

                ResultSet resultSet = preparedStatement.executeQuery();

                ObservableList<EksperimentIzvodjacIzvDto> list = FXCollections.observableArrayList();

                while(resultSet.next()){
                    String nazivEksperimenta =  resultSet.getString("eksperiment.eksperiment_naziv");
                    String uloga = resultSet.getString("uloga");
                    Date datum = resultSet.getDate("izvodjenje_eksperimenta.datum");
                    String status = resultSet.getString("izvodjenje_eksperimenta.status");

                    EksperimentIzvodjacIzvDto eksperimentIzvodjacIzvDto =
                            new EksperimentIzvodjacIzvDto(nazivEksperimenta, uloga, datum, status);

                    list.add(eksperimentIzvodjacIzvDto);
                }
                tvEksperimentIzvodjacIzvodjenje.setItems(list);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
