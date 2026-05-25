package com.example.baze_podataka.models;

import com.example.baze_podataka.Config;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class Alat {
    private int alatId;
    private Date datumNabavke;
    private Date datumProizvodnje;
    private int vrstaAlataId;
    private int laboratorijaId;

    public Alat(int alatId, Date datumNabavke, Date datumProizvodnje, int vrstaAlataId, int laboratorijaId) {
        this.alatId = alatId;
        this.datumNabavke = datumNabavke;
        this.datumProizvodnje = datumProizvodnje;
        this.vrstaAlataId = vrstaAlataId;
        this.laboratorijaId = laboratorijaId;
    }

    public int getAlatId() {
        return alatId;
    }

    public void setAlatId(int alatId) {
        this.alatId = alatId;
    }

    public Date getDatumNabavke() {
        return datumNabavke;
    }

    public void setDatumNabavke(Date datumNabavke) {
        this.datumNabavke = datumNabavke;
    }

    public Date getDatumProizvodnje() {
        return datumProizvodnje;
    }

    public void setDatumProizvodnje(Date datumProizvodnje) {
        this.datumProizvodnje = datumProizvodnje;
    }

    public int getVrstaAlataId() {
        return vrstaAlataId;
    }

    public void setVrstaAlataId(int vrstaAlataId) {
        this.vrstaAlataId = vrstaAlataId;
    }

    public int getLaboratorijaId() {
        return laboratorijaId;
    }

    public void setLaboratorijaId(int laboratorijaId) {
        this.laboratorijaId = laboratorijaId;
    }

    public String getNazivVrste(){
        String naziv = this.runQuery();
        if(naziv == null){
            return "Nepoznato";
        }
        return naziv;
    }

    private String runQuery(){
        try {
            String query = "SELECT * FROM vrsta_alata WHERE vrsta_id = ?";
            PreparedStatement preparedStatement = Config.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, vrstaAlataId);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                String naziv = rs.getString("opis");
                return naziv;
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}