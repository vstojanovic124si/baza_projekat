package com.example.baze_podataka.controllers;

import com.example.baze_podataka.Config;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterUserController {

    public boolean register(String userName, String password, String confirmedPassword) {
        if(userName == null || userName.trim().isEmpty()
                || password == null || password.trim().isEmpty()
                || confirmedPassword == null || confirmedPassword.trim().isEmpty()) {

            System.out.println("Fields cannot be empty.");
            return false;
        }

        if(!password.equals(confirmedPassword)) {
            System.out.println("Passwords do not match.");
            return false;
        }

        return runQuery(Config.getConnection(), userName, password);
    }

    private boolean runQuery(Connection connection, String userName, String password) {
        try {
            String checkQuery =
                    "SELECT * FROM administrator WHERE username = ?";
            PreparedStatement checkStatement =
                    connection.prepareStatement(checkQuery);
            checkStatement.setString(1, userName);
            ResultSet resultSet =
                    checkStatement.executeQuery();
            if(resultSet.next()) {
                System.out.println("User already exists.");
                return false;
            }

            String insertQuery =
                    "INSERT INTO administrator(username, sifra) VALUES(?, ?)";

            PreparedStatement insertStatement =
                    connection.prepareStatement(insertQuery);

            String hashedPassword =
                    BCrypt.hashpw(password, BCrypt.gensalt(10));

            insertStatement.setString(1, userName);
            insertStatement.setString(2, hashedPassword);

            insertStatement.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
        return false;
    }
}