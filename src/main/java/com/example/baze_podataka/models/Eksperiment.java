package com.example.baze_podataka.models;

import com.example.baze_podataka.Config;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Eksperiment {
    private int eksperimentId;
    private String eksperimentNaziv;
    private String ciljeviIstrazivanja;
    private int vrstaId;

    public Eksperiment(int eksperimentId, String eksperimentNaziv, String ciljeviIstrazivanja, int vrstaId) {
        this.eksperimentId = eksperimentId;
        this.eksperimentNaziv = eksperimentNaziv;
        this.ciljeviIstrazivanja = ciljeviIstrazivanja;
        this.vrstaId = vrstaId;
    }

    public int getEksperimentId() {
        return eksperimentId;
    }

    public void setEksperimentId(int eksperimentId) {
        this.eksperimentId = eksperimentId;
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

    public int getVrstaId() {
        return vrstaId;
    }

    public void setVrstaId(int vrstaId) {
        this.vrstaId = vrstaId;
    }

    public String getNazivVrste(){
        String vrsta = runQuery();
        if(vrsta != null){
            return vrsta;
        }
        else {
            return "Nepoznato";
        }
    }

    private String runQuery(){
        try {
            String query = "SELECT naziv_vrste_eksperimenta FROM vrsta_eksperimenta WHERE vrsta_eksperimenta_id = ?";
            PreparedStatement preparedStatement = Config.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, vrstaId);
            ResultSet rs =preparedStatement.executeQuery();
            while(rs.next()){
                String naziv = rs.getString("naziv_vrste_eksperimenta");
                return naziv;
            }
        } catch (Exception e){
            System.out.println("Greska");
        }
        return null;
    }
}
