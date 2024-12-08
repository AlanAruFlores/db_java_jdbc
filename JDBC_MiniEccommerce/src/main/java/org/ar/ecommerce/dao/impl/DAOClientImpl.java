package org.ar.ecommerce.dao.impl;

import org.ar.ecommerce.dao.IDAOClient;
import org.ar.ecommerce.models.Client;

import java.sql.Connection;
import java.util.List;

public class DAOClientImpl implements IDAOClient {
    private Connection connection;

    public DAOClientImpl(Connection connection){
        this.connection = connection;
    }


    @Override
    public void insert(Client object) {

    }

    @Override
    public void update(Client object) {

    }

    @Override
    public void delete(Client object) {

    }

    @Override
    public Client findById(int id) {
        return null;
    }

    @Override
    public List<Client> findAll() {
        return List.of();
    }
}
