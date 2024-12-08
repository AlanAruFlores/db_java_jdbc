package org.ar.ecommerce.dao.impl;

import org.ar.ecommerce.dao.IDAOManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOManager implements IDAOManager {

    private static final String URL = "jdbc:sqlserver://localhost:1434;databaseName=ecommerce_db;encrypt=false;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "pachan242";

    private Connection connection;

    public DAOManager() {
        try{
            Properties props = new Properties();
            props.setProperty("user", USER);
            props.setProperty("password", PASSWORD);
            this.connection = DriverManager.getConnection(URL, props);
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }


    @Override
    public boolean closeConnection() {
        return false;
    }
}
