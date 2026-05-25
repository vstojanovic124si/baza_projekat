package com.example.baze_podataka.controllers;

import com.example.baze_podataka.Config;
import com.example.baze_podataka.models.LaboratoryDto;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BrisanjeLaboratorijeController {

    private TableView<LaboratoryDto> tvLaboratorije;

    public BrisanjeLaboratorijeController(TableView<LaboratoryDto> tvLaboratorije) {
        this.tvLaboratorije = tvLaboratorije;
    }

    public void runQuery(){
        try {
            if(tvLaboratorije.getSelectionModel().getSelectedItem() == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Brisanje laboratorije");
                alert.setHeaderText(null);
                alert.setContentText("Mora biti izabrana laboratorija!");
                alert.showAndWait();
                return;
            }

            int laboratory_id = tvLaboratorije.getSelectionModel().getSelectedItem().getLaboratoryId();



            String query = "SELECT can_delete_lab(?) AS res";

            PreparedStatement preparedStatement = Config.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, laboratory_id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                int br = rs.getInt("res");
                if(br == 1){
                    String query1 = "DELETE FROM laboratorija WHERE laboratorija_id = ?";
                    PreparedStatement preparedStatement1 = Config.getConnection().prepareStatement(query1);
                    preparedStatement1.setInt(1, laboratory_id);

                    int a = preparedStatement1.executeUpdate();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Brisanje laboratorije");
                    alert.setHeaderText(null);
                    alert.setContentText("Laboratorija je uspešno obrisana!");
                    alert.showAndWait();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Brisanje laboratorije");
                    alert.setHeaderText(null);
                    alert.setContentText("Laboratorija ne moze biti obrisana jer postoji aktivno izvodjenje u njoj!");
                    alert.showAndWait();
                }
            }

            tvLaboratorije.getSelectionModel().clearSelection();

            SetLaboratorijaValuesController setLaboratorijaValuesController = new SetLaboratorijaValuesController(tvLaboratorije);
            setLaboratorijaValuesController.runQuery(Config.getConnection());


        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
