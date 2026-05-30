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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class OdlaganjeOtpadaPage extends Stage {
    private Button btnHomePageBack = new Button("Nazad na home page");
    private Button btnDodajOdlaganje = new Button("Dodaj odlaganje");
    private BorderPane root = new  BorderPane();
    private TableView<OdlaganjeOtpadaDto> tvOdlaganjeOtpada = new TableView<>();

    public OdlaganjeOtpadaPage() {
        TableColumn<OdlaganjeOtpadaDto, String> tcLokacija =  new TableColumn<>("Lokacija");
        TableColumn<OdlaganjeOtpadaDto, Integer> tcBrojOdlaganja =  new TableColumn<>("Broj Odlaganja");
        TableColumn<OdlaganjeOtpadaDto, Integer> tcUkupnaKolicina =  new TableColumn<>("Ukupna Kolicina (kg)");
        TableColumn<OdlaganjeOtpadaDto, Integer> tcSupstancaId = new TableColumn<>("Susptanca ID");
        TableColumn<OdlaganjeOtpadaDto, Integer> tcSupstancaNaziv = new TableColumn<>("Naziv supstance");

        tcLokacija.setCellValueFactory(new PropertyValueFactory<>("lokacija"));
        tcBrojOdlaganja.setCellValueFactory(new PropertyValueFactory<>("broj_odlaganja"));
        tcUkupnaKolicina.setCellValueFactory(new PropertyValueFactory<>("ukupna_kolicina"));
        tcSupstancaId.setCellValueFactory(new PropertyValueFactory<>("supstanca_id"));
        tcSupstancaNaziv.setCellValueFactory(new PropertyValueFactory<>("supstanca_naziv"));

        tvOdlaganjeOtpada.getColumns().addAll(tcLokacija, tcBrojOdlaganja, tcUkupnaKolicina, tcSupstancaId, tcSupstancaNaziv);
        tvOdlaganjeOtpada.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        SetOdlaganjeOtpadaValuesController odlaganjeOtpadaValuesController
                = new SetOdlaganjeOtpadaValuesController(tvOdlaganjeOtpada);

        odlaganjeOtpadaValuesController.runQuery(Config.getConnection());

        btnDodajOdlaganje.setOnAction((event) -> {
            DodajOdlaganjePage dodajOdlaganjePage =
                    new DodajOdlaganjePage();

            Stage stage = (Stage) btnHomePageBack.getScene().getWindow();
            Scene odlaganjeOtpadaScene = stage.getScene();
            stage.setScene(dodajOdlaganjePage.getScene());
            stage.setTitle("Dodaj odlaganje");

            dodajOdlaganjePage.getBtnOdlaganjeOtpadaBack().setOnAction(e -> {
                odlaganjeOtpadaValuesController.runQuery(Config.getConnection());
               stage.setScene(odlaganjeOtpadaScene);
               stage.setTitle("Odlaganje hemijskog otpada izvestaj");
            });
        });

        root.setCenter(tvOdlaganjeOtpada);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(btnHomePageBack, btnDodajOdlaganje);
        root.setBottom(hBox);
        setScene(new Scene(root, 1500, 800));
    }

    public Button getBtnHomePageBack() {
        return btnHomePageBack;
    }
}
