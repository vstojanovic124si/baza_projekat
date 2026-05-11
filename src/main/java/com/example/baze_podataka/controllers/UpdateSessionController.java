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

        try {
            Time startTime = Time.valueOf(start);
            Time endTime = Time.valueOf(end);

            if (!endTime.after(startTime)) {
                System.out.println("Greška: Vreme završetka mora biti posle vremena početka.");
                return false;
            }

            return izvrsiUpdateProcedurom(connection, sessionId, Integer.parseInt(labIdText), datum, startTime, endTime);
        }
        catch (Exception e){
            System.out.println("UPDATE CONTROLLER " +  e.getMessage());
        }
        return false;
    }

    private boolean izvrsiUpdateProcedurom(Connection conn, int sesijaId, int labId, String datum, Time start, Time end) throws SQLException {
        String sql = "{CALL proc_update_session(?, ?, ?, ?, ?)}";
        CallableStatement statement = conn.prepareCall(sql);
        statement.setInt(1, sesijaId);
        statement.setInt(2, labId);
        statement.setString(3, datum);
        statement.setTime(4, start);
        statement.setTime(5, end);
        boolean hasResults = statement.execute();

        if (hasResults) {
            try (ResultSet rs = statement.getResultSet()) {
                if (rs.next()) {
                    String status = rs.getString("status");
                    System.out.println("Status iz baze: " + status);
                    return "USPESNO".equals(status);
                }
            }
        }
        return false;
    }
}