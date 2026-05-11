package com.example.baze_podataka.models;

public class OdlaganjeOtpadaDto {
    private String lokacija;
    private int broj_odlaganja;
    private double ukupna_kolicina;

    public OdlaganjeOtpadaDto(String lokacija, int broj_odlaganja, double ukupna_kolicina) {
        this.lokacija = lokacija;
        this.broj_odlaganja = broj_odlaganja;
        this.ukupna_kolicina = ukupna_kolicina;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public int getBroj_odlaganja() {
        return broj_odlaganja;
    }

    public void setBroj_odlaganja(int broj_odlaganja) {
        this.broj_odlaganja = broj_odlaganja;
    }

    public double getUkupna_kolicina() {
        return ukupna_kolicina;
    }

    public void setUkupna_kolicina(int ukupna_kolicina) {
        this.ukupna_kolicina = ukupna_kolicina;
    }
}
