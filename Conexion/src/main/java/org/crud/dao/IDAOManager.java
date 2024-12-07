package org.crud.dao;

public interface IDAOManager {
    public IUserDAO getUserDAO();
    public boolean closeConnection();
}
