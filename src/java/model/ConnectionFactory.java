package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionFactory {
    private static final String DATABASE_URL = "jdbc:mysql://localhost/quintapet";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "root";
    
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
}
