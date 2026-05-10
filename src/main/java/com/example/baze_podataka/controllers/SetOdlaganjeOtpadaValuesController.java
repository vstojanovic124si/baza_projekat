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
                int ukupnaKolicina =  resultSet.getInt("Ukupna_Kolicina_Kg");

                OdlaganjeOtpadaDto odlaganjeOtpadaDto = new OdlaganjeOtpadaDto(lokacija, brojOdlaganja, ukupnaKolicina);
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
                    "    oo.lokacija AS Lokacija_Odlaganja,\n" +
                    "    COUNT(oo.otpad_id) AS Broj_Odlaganja,\n" +
                    "    SUM(ho.kolicina) AS Ukupna_Kolicina_Kg\n" +
                    "FROM odlaganje_otpada oo\n" +
                    "         JOIN hemijski_otpad ho ON oo.otpad_id = ho.otpad_id\n" +
                    "         JOIN hemijska_supstanca s ON ho.supstanca_id = s.supstanca_id\n" +
                    "WHERE oo.bezbednosni_nivo != 'Nizak'\n" +
                    "GROUP BY oo.lokacija\n" +
                    "HAVING SUM(ho.kolicina) > 5.0;";
            Statement statement = connection.createStatement();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
