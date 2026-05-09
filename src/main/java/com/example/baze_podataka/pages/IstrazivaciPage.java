package com.example.baze_podataka.pages;

import com.example.baze_podataka.Config;
import com.example.baze_podataka.controllers.SetIstrazivacValuesController;
import com.example.baze_podataka.models.IstrazivacDto;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class IstrazivaciPage extends Stage {
    TableView<IstrazivacDto> tvIstrazivac = new TableView<>();
    private BorderPane root = new BorderPane();

    public IstrazivaciPage() {
        TableColumn<IstrazivacDto, Integer> tcId = new TableColumn<>("Istrazivac_id");
        TableColumn<IstrazivacDto, String> tcIme = new TableColumn<>("Ime");
        TableColumn<IstrazivacDto, String> tcPrezime = new TableColumn<>("Prezime");
        TableColumn<IstrazivacDto, String> tcKlasifikacija = new TableColumn<>("Klasifikacija");
        TableColumn<IstrazivacDto, String> tcSpososobnosti = new TableColumn<>("Sposobnosti");
        TableColumn<IstrazivacDto, String> tcIzvodjac = new TableColumn<>("Izvodjac");
        TableColumn<IstrazivacDto, String> tcDizajner = new TableColumn<>("Dizajner");

        tcId.setCellValueFactory(new PropertyValueFactory<>("istrazivac_id"));
        tcIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
        tcPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        tcKlasifikacija.setCellValueFactory(new PropertyValueFactory<>("klasifikacija"));
        tcSpososobnosti.setCellValueFactory(new PropertyValueFactory<>("sposobnosti"));
        tcIzvodjac.setCellValueFactory(new PropertyValueFactory<>("daLiJeIzvodjac"));
        tcDizajner.setCellValueFactory(new PropertyValueFactory<>("daLiJeDizajner"));


        tvIstrazivac.getColumns().addAll(tcId, tcIme, tcPrezime, tcKlasifikacija, tcSpososobnosti, tcIzvodjac, tcDizajner);

        this.root.setCenter(this.tvIstrazivac);
        SetIstrazivacValuesController setIstrazivacValuesController = new SetIstrazivacValuesController(tvIstrazivac);
        setIstrazivacValuesController.runQuery(Config.getConnection());

        this.setScene(new Scene(root, 1000, 800));
    }
}