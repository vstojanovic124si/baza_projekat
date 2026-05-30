package com.example.baze_podataka.controllers;

import com.example.baze_podataka.Config;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUserController {
    public boolean login(String userName, String password) {
        if(userName == null || userName.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            System.out.println("Fields cannot be empty.");
            return false;
        }
        return runQuery(Config.getConnection(), userName, password);
    }

    private boolean runQuery(Connection connection, String userName, String password) {
        try {
            String query = "SELECT sifra FROM administrator WHERE username = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, userName);

            ResultSet resultSet =
                    preparedStatement.executeQuery();
            if(resultSet.next()) {

                String hashedPassword =
                        resultSet.getString("sifra");

                return BCrypt.checkpw(
                        password,
                        hashedPassword
                );
            }
        }
        catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
        return false;
    }
}