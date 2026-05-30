package com.example.baze_podataka.models;

import com.example.baze_podataka.Config;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HemijskaSupstancaKolicina {
    private int supstancaId;
    private String supstancaNaziv;
    private double kolicinaResursa;
    private String statusResursa;

    public HemijskaSupstancaKolicina(int supstancaId, double kolicinaResursa, String statusResursa) {
        this.supstancaId = supstancaId;
        this.kolicinaResursa = kolicinaResursa;
        this.statusResursa = statusResursa;
    }

    public int getSupstancaId() {
        return supstancaId;
    }

    public void setSupstancaId(int supstancaId) {
        this.supstancaId = supstancaId;
    }

    public String getSupstancaNaziv() {
        String naziv = this.runQuery();
        if(naziv == null){
            return "NEPOZNAT";
        }
        return naziv;
    }

    public void setSupstancaNaziv(String supstancaNaziv) {
        this.supstancaNaziv = supstancaNaziv;
    }

    public double getKolicinaResursa() {
        return kolicinaResursa;
    }

    public void setKolicinaResursa(double kolicinaResursa) {
        this.kolicinaResursa = kolicinaResursa;
    }

    public String getStatusResursa() {
        return statusResursa;
    }

    public void setStatusResursa(String statusResursa) {
        this.statusResursa = statusResursa;
    }
    private String runQuery(){
        try {
            String query = "SELECT * FROM hemijska_supstanca WHERE supstanca_id = ?";
            PreparedStatement preparedStatement = Config.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, supstancaId);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                String naziv = rs.getString("supstanca_naziv");
                return naziv;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}