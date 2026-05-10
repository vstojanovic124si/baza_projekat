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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Time;
import java.util.Date;

public class HomePage extends Stage {
    private Button btnPrikazLaboratorija = new Button("Prikaz Laboratorija");
    private Button btnPrikazIstrazivaca = new Button("Prikaz Istrazivaca");
    private Button btnIzmenaSesije = new Button("Izmena sesije");
    private Button btnOdlaganjeOtpada = new Button("Izvestaj odlaganja");
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

        TableColumn<SessionDto, Integer> tcSessionId = new TableColumn<>("Sesija ID");
        TableColumn<SessionDto, Integer> tcLaboratoryId= new TableColumn<>("Laboratorija ID");
        TableColumn<SessionDto, Date> tcSesijaDatum = new TableColumn<>("Datum");
        TableColumn<SessionDto, Time> tcSessionVremePocetka = new TableColumn<>("Vreme pocetka");
        TableColumn<SessionDto, Time> tcSessionVremeZavrsetka = new TableColumn<>("Vreme zavrsetka");

        tcSessionId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        tcLaboratoryId.setCellValueFactory(new PropertyValueFactory<>("laboratoryId"));
        tcSesijaDatum.setCellValueFactory(new PropertyValueFactory<>("date"));
        tcSessionVremePocetka.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        tcSessionVremeZavrsetka.setCellValueFactory(new PropertyValueFactory<>("endTime"));

        tvEksperiment.getColumns().addAll(tcId, tcNaziv, tcCiljeviIstrazivanja, tcVrstaEksperimenta);
        tvSesija.getColumns().addAll(tcSessionId, tcLaboratoryId, tcSesijaDatum, tcSessionVremePocetka, tcSessionVremeZavrsetka);


        VBox vb1 = new VBox(10, this.btnIzmenaSesije, this.btnPrikazLaboratorija, btnPrikazIstrazivaca, btnOdlaganjeOtpada);
        vb1.setAlignment(Pos.CENTER);
        vb1.setPadding(new Insets(10));
        SetEksperimentValuesController setEksperimentValuesController = new SetEksperimentValuesController(tvEksperiment);
        setEksperimentValuesController.runQuery(Config.getConnection());
        tvEksperiment.getSelectionModel().selectedItemProperty().addListener(new SetSessionValuesController(tvEksperiment, tvSesija));



        /// butoni
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
            if(tvSesija.getItems().isEmpty() || tvSesija.getSelectionModel().getSelectedItem() == null) {
                return ;
            }
            SessionDto selectedSesija =  tvSesija.getItems().get(tvSesija.getSelectionModel().getSelectedIndex());

            Stage stage = (Stage) btnIzmenaSesije.getScene().getWindow();
            Scene homeScene = stage.getScene();

            IzmenaSesijePage izmenaSesijePage = new IzmenaSesijePage(selectedSesija);

            stage.setScene(izmenaSesijePage.getScene());
            stage.setTitle("Izmena Sesije");

            izmenaSesijePage.getBtnHomePageBack().setOnAction(event -> {
                Eksperiment prethodnoSelektovan = tvEksperiment.getSelectionModel().getSelectedItem();

                setEksperimentValuesController.runQuery(Config.getConnection());

                if (prethodnoSelektovan != null) {
                    for (Eksperiment eks : tvEksperiment.getItems()) {
                        if (eks.getEksperimentId() == prethodnoSelektovan.getEksperimentId()) {
                            tvEksperiment.getSelectionModel().select(eks);
                            break;
                        }
                    }
                }

                stage.setScene(homeScene);
                stage.setTitle("Home page");
            });
        });


        btnOdlaganjeOtpada.setOnAction(e -> {
            OdlaganjeOtpadaPage odlaganjeOtpadaPage = new OdlaganjeOtpadaPage();

            Stage stage = (Stage) btnIzmenaSesije.getScene().getWindow();
            Scene homeScene = stage.getScene();
            stage.setScene(odlaganjeOtpadaPage.getScene());

            stage.setTitle("Odlaganje hemijskog otpada izvestaj");

            odlaganjeOtpadaPage.getBtnHomePageBack().setOnAction(event ->{
                stage.setScene(homeScene);
                stage.setTitle("Home page");
            });
        });


        root.setCenter(this.tvEksperiment);
        root.setRight(this.tvSesija);
        root.setLeft(vb1);
        this.setTitle("Home page");
        this.setScene(new Scene(root, 1000, 800));
    }
}