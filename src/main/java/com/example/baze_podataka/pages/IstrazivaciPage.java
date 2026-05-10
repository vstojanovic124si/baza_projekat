package com.example.baze_podataka.pages;

import com.example.baze_podataka.Config;
import com.example.baze_podataka.controllers.SetEksTeoDizValuesController;
import com.example.baze_podataka.controllers.SetIstrazivacValuesController;
import com.example.baze_podataka.controllers.SetIzvodjacEkspIzvodjenjeController;
import com.example.baze_podataka.models.EksperimentIzvodjacIzvDto;
import com.example.baze_podataka.models.EksperimentTeorijaDizajnerDto;
import com.example.baze_podataka.models.IstrazivacDto;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.Date;

public class IstrazivaciPage extends Stage {
    private TableView<IstrazivacDto> tvIstrazivac = new TableView<>();
    private TableView<EksperimentIzvodjacIzvDto> tvEksperimentIzvodjacIzv = new TableView<>();
    private TableView<EksperimentTeorijaDizajnerDto> tvEksperimentEksTeoDiz = new TableView<>();
    private BorderPane root = new BorderPane();
    private Button btnHomePageBack = new Button("Nazad na home page");

    public IstrazivaciPage() {

        // Table View Istrazivac
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

        // Table view Eksperiment Izvodjac izvodjenje
        TableColumn<EksperimentIzvodjacIzvDto, String> tcEkspNazivIzvodjac = new  TableColumn<>("Eksperiment naziv");
        TableColumn<EksperimentIzvodjacIzvDto, String> tcUloga = new  TableColumn<>("Uloga");
        TableColumn<EksperimentIzvodjacIzvDto, Date> tcDatum = new  TableColumn<>("Datum");
        TableColumn<EksperimentIzvodjacIzvDto, String> tcStatus = new  TableColumn<>("Status");

        tcEkspNazivIzvodjac.setCellValueFactory(new PropertyValueFactory<>("eksperimentNaziv"));
        tcUloga.setCellValueFactory(new PropertyValueFactory<>("uloga"));
        tcDatum.setCellValueFactory(new PropertyValueFactory<>("datum"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Table view Eskperiment Dizajner Teorija
        TableColumn<EksperimentTeorijaDizajnerDto, String> tcEkspNaziv = new  TableColumn<>("Eksperiment naziv");
        TableColumn<EksperimentTeorijaDizajnerDto, String> tcCiljeviEksp = new  TableColumn<>("Ciljevi Eksperimenta");
        TableColumn<EksperimentTeorijaDizajnerDto, String> tcTeorijaNaziv = new  TableColumn<>("Teorija Naziv");
        TableColumn<EksperimentTeorijaDizajnerDto, String> tcTeorijaOpis = new  TableColumn<>("Teorija Opis");

        tcEkspNaziv.setCellValueFactory(new PropertyValueFactory<>("eksperimentNaziv"));
        tcCiljeviEksp.setCellValueFactory(new PropertyValueFactory<>("ciljeviIstrazivanja"));
        tcTeorijaNaziv.setCellValueFactory(new PropertyValueFactory<>("teorijaNaziv"));
        tcTeorijaOpis.setCellValueFactory(new PropertyValueFactory<>("teorijaOpis"));

        tvIstrazivac.getSelectionModel().selectedItemProperty()
                .addListener(new SetEksTeoDizValuesController(tvIstrazivac, tvEksperimentEksTeoDiz));

        tvIstrazivac.getSelectionModel().selectedItemProperty()
                        .addListener(new SetIzvodjacEkspIzvodjenjeController(tvIstrazivac, tvEksperimentIzvodjacIzv));


        tvIstrazivac.getColumns().addAll(tcId, tcIme, tcPrezime, tcKlasifikacija, tcSpososobnosti, tcIzvodjac, tcDizajner);
        tvEksperimentEksTeoDiz.getColumns().addAll(tcEkspNaziv, tcCiljeviEksp, tcTeorijaNaziv, tcTeorijaOpis);
        tvEksperimentIzvodjacIzv.getColumns().addAll(tcEkspNazivIzvodjac, tcUloga, tcDatum, tcStatus);

        root.setLeft(tvIstrazivac);
        root.setCenter(tvEksperimentEksTeoDiz);
        root.setRight(tvEksperimentIzvodjacIzv);
        root.setBottom(btnHomePageBack);
        SetIstrazivacValuesController setIstrazivacValuesController = new SetIstrazivacValuesController(tvIstrazivac);
        setIstrazivacValuesController.runQuery(Config.getConnection());

        setScene(new Scene(root, 1500, 800));
    }

    public Button getBtnHomePageBack() {
        return btnHomePageBack;
    }
}