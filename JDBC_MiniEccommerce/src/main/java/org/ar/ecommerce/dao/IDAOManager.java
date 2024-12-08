package org.ar.ecommerce.dao;

import java.sql.SQLException;

public interface IDAOManager {

    public IDAOClient getClientDAO();
    public IDAOOrder getOrderDAO();
    public boolean closeConnection() throws SQLException;
}
