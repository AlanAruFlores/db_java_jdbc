package org.ar.ecommerce.dao.impl;

import org.ar.ecommerce.dao.IDAOClient;
import org.ar.ecommerce.dao.IDAOManager;
import org.ar.ecommerce.dao.IDAOOrder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOManager implements IDAOManager {

    private static final String URL = "jdbc:sqlserver://localhost:1434;databaseName=ecommerce_db;encrypt=false;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "pachan242";

    private Connection connection;

    private IDAOOrder orderDAO;
    private IDAOClient clientDAO;

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
    public IDAOClient getClientDAO() {
        if(clientDAO == null){
            this.clientDAO = new DAOClientImpl(this.connection);
        }
        return clientDAO;
    }

    @Override
    public IDAOOrder getOrderDAO() {
        if(orderDAO == null){
            this.orderDAO = new DAOOrderImpl(this.connection, getClientDAO());
        }
        return orderDAO;
    }

    @Override
    public boolean closeConnection() throws SQLException {
        if(connection != null && !connection.isClosed()){
            this.connection.close();
            return true;
        }

        return false;
    }
}
