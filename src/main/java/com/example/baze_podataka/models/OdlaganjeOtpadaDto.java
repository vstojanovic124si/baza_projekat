package com.example.baze_podataka.models;

public class OdlaganjeOtpadaDto {
    private String lokacija;
    private int broj_odlaganja;
    private double ukupna_kolicina;
    private int supstanca_id;
    private String supstanca_naziv;

    public OdlaganjeOtpadaDto(String lokacija, int broj_odlaganja, double ukupna_kolicina, int supstanca_id, String supstanca_naziv) {
        this.lokacija = lokacija;
        this.broj_odlaganja = broj_odlaganja;
        this.ukupna_kolicina = ukupna_kolicina;
        this.supstanca_id = supstanca_id;
        this.supstanca_naziv = supstanca_naziv;
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

    public void setUkupna_kolicina(double ukupna_kolicina) {
        this.ukupna_kolicina = ukupna_kolicina;
    }

    public int getSupstanca_id() {
        return supstanca_id;
    }

    public void setSupstanca_id(int supstanca_id) {
        this.supstanca_id = supstanca_id;
    }

    public String getSupstanca_naziv() {
        return supstanca_naziv;
    }

    public void setSupstanca_naziv(String supstanca_naziv) {
        this.supstanca_naziv = supstanca_naziv;
    }
}
