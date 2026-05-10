package com.example.baze_podataka.controllers;

import com.example.baze_podataka.models.IstrazivacDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SetIstrazivacValuesController {
    private TableView<IstrazivacDto> tvIstrazivac;

    public SetIstrazivacValuesController(TableView<IstrazivacDto> tvIstrazivac) {
        this.tvIstrazivac = tvIstrazivac;
    }

    public void runQuery(Connection connection){
        try {
            String query = "SELECT * from istrazivac";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ObservableList<IstrazivacDto> istrazivaci = FXCollections.observableArrayList();
            while(rs.next()){
                int id = rs.getInt("istrazivac_id");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String klasifikacija = rs.getString("klasifikacija");
                String sposobnosti = rs.getString("sposobnosti");
                IstrazivacDto istrazivac = new IstrazivacDto(id, ime, prezime, klasifikacija, sposobnosti);
                istrazivaci.add(istrazivac);
            }
            tvIstrazivac.setItems(istrazivaci);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
