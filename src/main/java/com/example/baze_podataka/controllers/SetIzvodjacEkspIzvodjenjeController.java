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

public class SetIzvodjacEkspIzvodjenjeController implements ChangeListener {
    private TableView<EksperimentIzvodjacIzvDto> tvEksperimentIzvodjacIzvodjenje;
    private TableView<IstrazivacDto> tvIstrazivac;

    public SetIzvodjacEkspIzvodjenjeController(TableView<IstrazivacDto> tvIstrazivac, TableView<EksperimentIzvodjacIzvDto> tvEksperimentIzvodjacIzvodjenje) {
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
                String query = "select eksp.eksperiment_naziv, uloga, izv_eksp.datum, izv_eksp.status\n" +
                        "from eksperiment eksp\n" +
                        "         join izvodjenje_eksperimenta izv_eksp on eksp.eksperiment_id = izv_eksp.eksperiment_id\n" +
                        "         join izvodjac_izvodjenje izv_izv on izv_eksp.eksperiment_id = izv_izv.eksperiment_id\n" +
                        "where izv_izv.istrazivac_id = ?";

                int indeks = tvIstrazivac.getSelectionModel().getSelectedItem().getIstrazivac_id();
                PreparedStatement preparedStatement = Config.getConnection().prepareStatement(query);
                preparedStatement.setInt(1, indeks);

                ResultSet resultSet = preparedStatement.executeQuery();

                ObservableList<EksperimentIzvodjacIzvDto> list = FXCollections.observableArrayList();

                while(resultSet.next()){
                    String nazivEksperimenta =  resultSet.getString("eksp.eksperiment_naziv");
                    String uloga = resultSet.getString("uloga");
                    Date datum = resultSet.getDate("izv_eksp.datum");
                    String status = resultSet.getString("izv_eksp.status");

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
