package com.example.baze_podataka.models;

public class VrstaEksperimenta {
    private int vrsta_eksperimenta_id;
    private String naziv_vrste_eksperimenta;


    public VrstaEksperimenta(int vrsta_eksperimenta_id, String naziv_vrste_eksperimenta) {
        this.vrsta_eksperimenta_id = vrsta_eksperimenta_id;
        this.naziv_vrste_eksperimenta = naziv_vrste_eksperimenta;
    }

    public int getVrsta_eksperimenta_id() {
        return vrsta_eksperimenta_id;
    }

    public void setVrsta_eksperimenta_id(int vrsta_eksperimenta_id) {
        this.vrsta_eksperimenta_id = vrsta_eksperimenta_id;
    }

    public String getNaziv_vrste_eksperimenta() {
        return naziv_vrste_eksperimenta;
    }

    public void setNaziv_vrste_eksperimenta(String naziv_vrste_eksperimenta) {
        this.naziv_vrste_eksperimenta = naziv_vrste_eksperimenta;
    }
}
