package org.crud.dao.impl;

import org.crud.dao.IDAOManager;
import org.crud.dao.IUserDAO;
import org.crud.modelo.User;

import java.sql.Connection;
import  java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOManagerImpl implements IDAOManager {

    private Connection connection;


    //Conexion SQL
    private static final String CONNECTION = "jdbc:sqlserver://localhost:1434;databaseName=jdbc_crud_users;encrypt=false;trustServerCertificate=true";
    private static final String USERNAME = "sa";
     private static final String PASSWORD = "pachan242";

    /*onexion MYSQL
    private static final String CONNECTION = "jdbc:mysql://localhost:3307/jdbc_crud_users";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    */

    public IUserDAO userDaoImpl;

    public DAOManagerImpl() {
        Properties prop = new Properties();

        prop.setProperty("user", USERNAME);
        prop.setProperty("password", PASSWORD);
        try {
            this.connection = DriverManager.getConnection(CONNECTION, prop);
        }catch( SQLException ex){
            ex.printStackTrace();
            System.out.println("Error to connect to database. Please check the properties defined in the application");
        }
    }


    @Override
    public IUserDAO getUserDAO() {
        if(this.userDaoImpl == null )
            this.userDaoImpl = new UserDAOImpl(connection);

        return this.userDaoImpl;
    }

    @Override
    public boolean closeConnection() {
            try {
                if(connection != null && !connection.isClosed()){
                    this.connection.close();
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Error to close the connection of the database");
            }
        return false;
    }
}
