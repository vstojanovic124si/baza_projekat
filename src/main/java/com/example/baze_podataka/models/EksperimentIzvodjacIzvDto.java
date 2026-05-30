package com.example.baze_podataka.models;

import java.sql.Date;

public class EksperimentIzvodjacIzvDto {
    private String eksperimentNaziv;
    private String uloga;
    private Date datum;
    private String status;

    public EksperimentIzvodjacIzvDto(String eksperimentNaziv, String uloga, Date datum, String status) {
        this.eksperimentNaziv = eksperimentNaziv;
        this.uloga = uloga;
        this.datum = datum;
        this.status = status;
    }

    public String getEksperimentNaziv() {
        return eksperimentNaziv;
    }

    public void setEksperimentNaziv(String eksperimentNaziv) {
        this.eksperimentNaziv = eksperimentNaziv;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

