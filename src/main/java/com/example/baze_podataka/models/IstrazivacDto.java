package com.example.baze_podataka.models;

import com.example.baze_podataka.Config;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IstrazivacDto {
    private int istrazivac_id;
    private String ime;
    private String prezime;
    private String klasifikacija;
    private String sposobnosti;

    public IstrazivacDto(int istrazivac_id, String ime, String prezime, String klasifikacija, String sposobnosti) {
        this.istrazivac_id = istrazivac_id;
        this.ime = ime;
        this.prezime = prezime;
        this.klasifikacija = klasifikacija;
        this.sposobnosti = sposobnosti;
    }

    private String queryDaLijeDizajner(Connection connection, int istrazivac_id){
        try{
            String query = "SELECT * FROM dizajner_eksperimenata WHERE istrazivac_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, istrazivac_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            return (resultSet.next()) ? "Da" : "Ne";
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private String queryDaLijeIzvodjac(Connection connection, int istrazivac_id){
        try{
            String query = "SELECT * FROM izvodjac_eksperimenata WHERE istrazivac_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, istrazivac_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            return (resultSet.next()) ? "Da" : "Ne";
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int getIstrazivac_id() {
        return istrazivac_id;
    }

    public void setIstrazivac_id(int istrazivac_id) {
        this.istrazivac_id = istrazivac_id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKlasifikacija() {
        return klasifikacija;
    }

    public void setKlasifikacija(String klasifikacija) {
        this.klasifikacija = klasifikacija;
    }

    public String getSposobnosti() {
        return sposobnosti;
    }

    public void setSposobnosti(String sposobnosti) {
        this.sposobnosti = sposobnosti;
    }

    public String getDaLiJeIzvodjac(){
        return queryDaLijeIzvodjac(Config.getConnection(), istrazivac_id);
    }

    public String getDaLiJeDizajner(){
        return queryDaLijeDizajner(Config.getConnection(), istrazivac_id);
    }
}
