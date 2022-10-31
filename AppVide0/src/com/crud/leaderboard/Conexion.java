package com.crud.leaderboard;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class Conexion {

    private static Connection connect;

    public static Connection ConectarnosBD() throws SQLException {
        try{
            String dbUrl = "jdbc:mysql://localhost:3306/aplicativoweb";
            String dbUser = "root";
            String dbPassword = "";

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connect = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "No se conecto", "error", JOptionPane.INFORMATION_MESSAGE);
            System.err.println(ex.getMessage());
            System.exit(0);
        }
        return connect;
    }
}
