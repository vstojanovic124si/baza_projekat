package com.example.baze_podataka.pages;

import com.example.baze_podataka.Config;
import com.example.baze_podataka.controllers.BrisanjeLaboratorijeController;
import com.example.baze_podataka.controllers.SetAlatSupstanceValuesController;
import com.example.baze_podataka.controllers.SetLaboratorijaValuesController;
import com.example.baze_podataka.models.Alat;
import com.example.baze_podataka.models.HemijskaSupstancaKolicina;
import com.example.baze_podataka.models.LaboratoryDto;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LaboratorijaPage extends Stage {
    Label lbLaboratorije = new Label("Lista svih laboratorija");
    Button btBrisanjeLaboratorije = new Button("Obrisi selektovanu laboratoriju");
    TableView<LaboratoryDto> tvLaboratorija = new TableView<>();
    TableView<HemijskaSupstancaKolicina> tvHemijskaSupstanca = new TableView<>();
    TableView<Alat> tvAlati = new TableView<>();

    private BorderPane root = new BorderPane();

    public LaboratorijaPage() {
        TableColumn<LaboratoryDto, Integer> tcLaboratorijaId = new TableColumn<>("Id");
        TableColumn<LaboratoryDto, String> tcLaboratoryName = new TableColumn<>("Ime laboratorije");
        TableColumn<LaboratoryDto, String> tcLaboratoryLocation = new TableColumn<>("Lokacija laboratorije");

        tcLaboratorijaId.setCellValueFactory(new PropertyValueFactory<>("laboratoryId"));
        tcLaboratoryName.setCellValueFactory(new PropertyValueFactory<>("laboratoryName"));
        tcLaboratoryLocation.setCellValueFactory(new PropertyValueFactory<>("location"));

        tvLaboratorija.getColumns().add(tcLaboratorijaId);
        tvLaboratorija.getColumns().add(tcLaboratoryName);
        tvLaboratorija.getColumns().add(tcLaboratoryLocation);
        tvLaboratorija.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<HemijskaSupstancaKolicina, Integer> tcSupstancaId = new TableColumn<>("ID supstance");
        TableColumn<HemijskaSupstancaKolicina, String> tcSupstancaNaziv = new TableColumn<>("Naziv supstance");
        TableColumn<HemijskaSupstancaKolicina, String> tcSupstancaKolicina = new TableColumn<>("Kolicina supstance");
        TableColumn<HemijskaSupstancaKolicina, String> tcStatusSupstance = new TableColumn<>("Status supstance");

        tcSupstancaId.setCellValueFactory(new PropertyValueFactory<>("supstancaId"));
        tcSupstancaNaziv.setCellValueFactory(new PropertyValueFactory<>("supstancaNaziv"));
        tcSupstancaKolicina.setCellValueFactory(new PropertyValueFactory<>("kolicinaResursa"));
        tcStatusSupstance.setCellValueFactory(new PropertyValueFactory<>("statusResursa"));

        tvHemijskaSupstanca.getColumns().add(tcSupstancaId);
        tvHemijskaSupstanca.getColumns().add(tcSupstancaNaziv);
        tvHemijskaSupstanca.getColumns().add(tcSupstancaKolicina);
        tvHemijskaSupstanca.getColumns().add(tcStatusSupstance);
        tvHemijskaSupstanca.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Alat, Integer> tcAlatId = new TableColumn<>("ID alata");
        TableColumn<Alat, String> tcAlatVrsta = new TableColumn<>("Vrsta alata");

        tcAlatId.setCellValueFactory(new PropertyValueFactory<>("alatId"));
        tcAlatVrsta.setCellValueFactory(new PropertyValueFactory<>("nazivVrste"));

        tvAlati.getColumns().add(tcAlatId);
        tvAlati.getColumns().add(tcAlatVrsta);
        tvAlati.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        SetLaboratorijaValuesController setLaboratorijaValuesController = new SetLaboratorijaValuesController(tvLaboratorija);

        tvLaboratorija.getSelectionModel().selectedItemProperty().addListener(new SetAlatSupstanceValuesController(tvLaboratorija, tvAlati, tvHemijskaSupstanca));

        setLaboratorijaValuesController.runQuery(Config.getConnection());
        VBox vb1 = new VBox(10, lbLaboratorije, btBrisanjeLaboratorije);
        vb1.setAlignment(Pos.CENTER);
        vb1.setPadding(new Insets(10));


        btBrisanjeLaboratorije.setOnAction(event -> {
            BrisanjeLaboratorijeController brisanjeLaboratorijeController = new BrisanjeLaboratorijeController(tvLaboratorija);
            brisanjeLaboratorijeController.runQuery();
        });


        root.setTop(vb1);
        root.setLeft(tvLaboratorija);
        root.setCenter(tvHemijskaSupstanca);
        root.setRight(tvAlati);
        this.setTitle("Laboratorije");

        this.setScene(new Scene(root, 1000, 600));
    }


}