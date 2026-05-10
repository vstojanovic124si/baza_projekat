package com.example.baze_podataka.pages;

import com.example.baze_podataka.Config;
import com.example.baze_podataka.controllers.SetEksperimentValuesController;
import com.example.baze_podataka.controllers.SetSessionValuesController;
import com.example.baze_podataka.models.Eksperiment;
import com.example.baze_podataka.models.SessionDto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Time;
import java.util.Date;

public class HomePage extends Stage {
    private Button btnPrikazLaboratorija = new Button("Prikaz Laboratorija");
    private Button btnPrikazIstrazivaca = new Button("Prikaz Istrazivaca");
    private Button btnIzmenaSesije = new Button("Izmena sesije");
    private TableView<Eksperiment> tvEksperiment = new TableView<>();
    private TableView<SessionDto> tvSesija = new TableView<>();


    private BorderPane root = new BorderPane();


    public HomePage() {
        TableColumn<Eksperiment, Integer> tcId = new TableColumn<>("Id");
        TableColumn<Eksperiment, String> tcNaziv = new TableColumn<>("Naziv eksperimenta");
        TableColumn<Eksperiment, String> tcCiljeviIstrazivanja = new TableColumn<>("Ciljevi istrazivanja");
        TableColumn<Eksperiment, String> tcVrstaEksperimenta = new TableColumn<>("Vrsta eksperimenta");

        tcId.setCellValueFactory(new PropertyValueFactory<>("eksperimentId"));
        tcNaziv.setCellValueFactory(new PropertyValueFactory<>("eksperimentNaziv"));
        tcCiljeviIstrazivanja.setCellValueFactory(new PropertyValueFactory<>("ciljeviIstrazivanja"));
        tcVrstaEksperimenta.setCellValueFactory(new PropertyValueFactory<>("nazivVrste"));

        tvEksperiment.getColumns().add(tcId);
        tvEksperiment.getColumns().add(tcNaziv);
        tvEksperiment.getColumns().add(tcCiljeviIstrazivanja);
        tvEksperiment.getColumns().add(tcVrstaEksperimenta);

        TableColumn<SessionDto, Integer> tcSessionId = new TableColumn<>("Sesija ID");
        TableColumn<SessionDto, Date> tcSesijaDatum = new TableColumn<>("Datum");
        TableColumn<SessionDto, Time> tcSessionVremePocetka = new TableColumn<>("Vreme pocetka");
        TableColumn<SessionDto, Time> tcSessionVremeZavrsetka = new TableColumn<>("Vreme zavrsetka");

        tcSessionId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        tcSesijaDatum.setCellValueFactory(new PropertyValueFactory<>("date"));
        tcSessionVremePocetka.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        tcSessionVremeZavrsetka.setCellValueFactory(new PropertyValueFactory<>("endTime"));

        btnPrikazIstrazivaca.setOnAction(e -> {
            IstrazivaciPage istrazivaciPage = new IstrazivaciPage();
            Stage stage = (Stage) btnPrikazIstrazivaca.getScene().getWindow();
            Scene homeScene = stage.getScene();

            stage.setScene(istrazivaciPage.getScene());
            stage.setTitle("Prikaz Istrazivaca");

            istrazivaciPage.getBtnHomePageBack().setOnAction(event ->{
                stage.setScene(homeScene);
                stage.setTitle("Home Page");
            });
        });

        btnIzmenaSesije.setOnAction(e -> {

        });

        tvSesija.getColumns().add(tcSessionId);
        tvSesija.getColumns().add(tcSesijaDatum);
        tvSesija.getColumns().add(tcSessionVremePocetka);
        tvSesija.getColumns().add(tcSessionVremeZavrsetka);

        this.root.setCenter(this.tvEksperiment);

        this.root.setRight(this.tvSesija);

        VBox vb1 = new VBox(10, this.btnIzmenaSesije, this.btnPrikazLaboratorija, btnPrikazIstrazivaca);
        vb1.setAlignment(Pos.CENTER);
        vb1.setPadding(new Insets(10));

        this.root.setLeft(vb1);
        SetEksperimentValuesController setEksperimentValuesController = new SetEksperimentValuesController(tvEksperiment);
        setEksperimentValuesController.runQuery(Config.getConnection());
        tvEksperiment.getSelectionModel().selectedItemProperty().addListener(new SetSessionValuesController(tvEksperiment, tvSesija));

        this.setTitle("Home page");
        this.setScene(new Scene(root, 1000, 800));
    }
}