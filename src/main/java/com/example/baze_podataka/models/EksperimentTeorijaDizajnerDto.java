package com.example.baze_podataka.models;

public class EksperimentTeorijaDizajnerDto {
    private String eksperimentNaziv;
    private String ciljeviIstrazivanja;
    private String teorijaNaziv;
    private String teorijaOpis;

    public EksperimentTeorijaDizajnerDto(String eksperimentNaziv, String ciljeviIstrazivanja, String teorijaNaziv, String teorijaOpis) {
        this.eksperimentNaziv = eksperimentNaziv;
        this.ciljeviIstrazivanja = ciljeviIstrazivanja;
        this.teorijaNaziv = teorijaNaziv;
        this.teorijaOpis = teorijaOpis;
    }

    public String getEksperimentNaziv() {
        return eksperimentNaziv;
    }

    public void setEksperimentNaziv(String eksperimentNaziv) {
        this.eksperimentNaziv = eksperimentNaziv;
    }

    public String getCiljeviIstrazivanja() {
        return ciljeviIstrazivanja;
    }

    public void setCiljeviIstrazivanja(String ciljeviIstrazivanja) {
        this.ciljeviIstrazivanja = ciljeviIstrazivanja;
    }

    public String getTeorijaNaziv() {
        return teorijaNaziv;
    }

    public void setTeorijaNaziv(String teorijaNaziv) {
        this.teorijaNaziv = teorijaNaziv;
    }

    public String getTeorijaOpis() {
        return teorijaOpis;
    }

    public void setTeorijaOpis(String teorijaOpis) {
        this.teorijaOpis = teorijaOpis;
    }
}

