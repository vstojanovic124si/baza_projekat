package com.example.baze_podataka.controllers;

import javafx.scene.control.TextField;
import java.sql.*;

public class UpdateSessionController {
    private int sessionId;
    private TextField tfLaboratoryId;
    private TextField tfDatum;
    private TextField tfStart;
    private TextField tfEnd;

    public UpdateSessionController(int sessionId, TextField tfDatum, TextField tfStart, TextField tfEnd, TextField tfLaboratoryId) {
        this.sessionId = sessionId;
        this.tfDatum = tfDatum;
        this.tfStart = tfStart;
        this.tfEnd = tfEnd;
        this.tfLaboratoryId = tfLaboratoryId;
    }

    public boolean updateSession(Connection connection) {
        String labIdText = tfLaboratoryId.getText();
        String datum = tfDatum.getText();
        String start = tfStart.getText();
        String end = tfEnd.getText();

        try{
            if (!proveriLaboratoriju(connection, labIdText)) {
                System.out.println("Greška: Laboratorija sa ID " + labIdText + " ne postoji.");
                return false;
            }

            Time startTime = Time.valueOf(start);
            Time endTime = Time.valueOf(end);
            if (!endTime.after(startTime)) {
                System.out.println("Greška: Vreme završetka mora biti posle vremena početka.");
                return false;
            }

            if (postojiPreklapanje(connection, Integer.parseInt(labIdText), datum, startTime, endTime)) {
                System.out.println("Greška: Termini se preklapaju sa postojećom sesijom u toj laboratoriji.");
                return false;
            }

            return izvrsiUpdate(connection, Integer.parseInt(labIdText), datum, startTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean proveriLaboratoriju(Connection conn, String labId) throws SQLException {
        String query = "SELECT COUNT(*) FROM laboratorija WHERE laboratorija_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, Integer.parseInt(labId));
        ResultSet rs = ps.executeQuery();
        return rs.next() && rs.getInt(1) > 0;
    }

    private boolean postojiPreklapanje(Connection conn, int labId, String datum, Time start, Time end) throws SQLException {

        String query = "SELECT COUNT(*) FROM sesija WHERE laboratorija_id = ? AND datum = ? AND sesija_id != ? " +
                "AND NOT (vreme_pocetka >= ? OR vreme_zavrsetka <= ?)";
        PreparedStatement ps2 = conn.prepareStatement(query);
        ps2.setInt(1, labId);
        ps2.setString(2, datum);
        ps2.setInt(3, sessionId);
        ps2.setTime(4, end);
        ps2.setTime(5, start);

        ResultSet rs = ps2.executeQuery();
        return rs.next() && rs.getInt(1) > 0;
    }

    private boolean izvrsiUpdate(Connection conn, int labId, String datum, Time start, Time end) throws SQLException {
        String query = "UPDATE sesija SET laboratorija_id = ?, datum = ?, vreme_pocetka = ?, vreme_zavrsetka = ? WHERE sesija_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, labId);
        ps.setString(2, datum);
        ps.setTime(3, start);
        ps.setTime(4, end);
        ps.setInt(5, sessionId);
        return ps.executeUpdate() > 0;
    }
}