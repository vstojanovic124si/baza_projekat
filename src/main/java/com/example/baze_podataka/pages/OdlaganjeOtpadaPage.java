package com.example.baze_podataka.pages;

import com.example.baze_podataka.Config;
import com.example.baze_podataka.controllers.SetOdlaganjeOtpadaValuesController;
import com.example.baze_podataka.models.OdlaganjeOtpadaDto;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class OdlaganjeOtpadaPage extends Stage {
    private Button btnHomePageBack = new Button("Nazad na home page");
    private BorderPane root = new  BorderPane();
    private TableView<OdlaganjeOtpadaDto> tvOdlaganjeOtpada = new TableView<>();

    public OdlaganjeOtpadaPage() {
        TableColumn<OdlaganjeOtpadaDto, String> tcLokacija =  new TableColumn<>("Lokacija");
        TableColumn<OdlaganjeOtpadaDto, Integer> tcBrojOdlaganja =  new TableColumn<>("Broj Odlaganja");
        TableColumn<OdlaganjeOtpadaDto, Integer> tcUkupnaKolicina =  new TableColumn<>("Ukupna Kolicina (kg)");

        tcLokacija.setCellValueFactory(new PropertyValueFactory<>("lokacija"));
        tcBrojOdlaganja.setCellValueFactory(new PropertyValueFactory<>("broj_odlaganja"));
        tcUkupnaKolicina.setCellValueFactory(new PropertyValueFactory<>("ukupna_kolicina"));

        tvOdlaganjeOtpada.getColumns().addAll(tcLokacija, tcBrojOdlaganja, tcUkupnaKolicina);
        tvOdlaganjeOtpada.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        SetOdlaganjeOtpadaValuesController odlaganjeOtpadaValuesController
                = new SetOdlaganjeOtpadaValuesController(tvOdlaganjeOtpada);

        odlaganjeOtpadaValuesController.runQuery(Config.getConnection());

        root.setCenter(tvOdlaganjeOtpada);
        root.setBottom(btnHomePageBack);
        setScene(new Scene(root, 1500, 800));
    }

    public Button getBtnHomePageBack() {
        return btnHomePageBack;
    }
}
