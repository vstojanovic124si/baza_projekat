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
        try{
            createViewQuery(connection);

            String query = "SELECT * FROM View_KriticanOtpadPoLokacijama ORDER BY Ukupna_Kolicina_Kg DESC;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ObservableList<OdlaganjeOtpadaDto> result =  FXCollections.observableArrayList();
            while(resultSet.next()){
                String lokacija =  resultSet.getString("Lokacija_Odlaganja");
                int brojOdlaganja = resultSet.getInt("Broj_Odlaganja");
                double ukupnaKolicina =  resultSet.getDouble("Ukupna_Kolicina_Kg");
                int supstanca_id1 = resultSet.getInt("Supstanca_id");
                String supstanca_naziv = resultSet.getString("Supstanca_naziv");

                OdlaganjeOtpadaDto odlaganjeOtpadaDto = new OdlaganjeOtpadaDto(lokacija, brojOdlaganja, ukupnaKolicina, supstanca_id1, supstanca_naziv);
                result.add(odlaganjeOtpadaDto);
            }
            tvOdlaganjeOtpada.setItems(result);
        }
        catch (Exception e){

        }
    }

    private void createViewQuery(Connection connection) {
        try{
            String query = "CREATE OR REPLACE VIEW View_KriticanOtpadPoLokacijama AS\n" +
                    "SELECT\n" +
                    "oo.lokacija AS Lokacija_Odlaganja,\n" +
                    "COUNT(oo.otpad_id) AS Broj_Odlaganja,\n" +
                    "CAST(SUM(ho.kolicina) AS DECIMAL(10,2)) AS Ukupna_Kolicina_Kg,\n" +
                    "s.supstanca_id as Supstanca_id, s.supstanca_naziv as Supstanca_naziv\n" +
                    "FROM odlaganje_otpada oo\n" +
                    "\t\t\t\tJOIN hemijski_otpad ho ON oo.otpad_id = ho.otpad_id\n" +
                    "\t\t\t\tJOIN hemijska_supstanca s ON ho.supstanca_id = s.supstanca_id\n" +
                    "\t\t\t\tWHERE oo.bezbednosni_nivo <> 'Nizak'\n" +
                    "\t\t\t\tGROUP BY oo.lokacija, s.supstanca_id, s.supstanca_naziv\n" +
                    "\t\t\t\tHAVING SUM(ho.kolicina) > 1.00;";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
