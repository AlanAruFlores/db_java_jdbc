package org.ar.ecommerce.dao.impl;

import org.ar.ecommerce.dao.IDAOOrder;
import org.ar.ecommerce.models.Order;

import java.sql.Connection;
import java.util.List;

public class DAOOrderImpl implements IDAOOrder {
    private Connection connection;

    public DAOOrderImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Order object) {

    }

    @Override
    public void update(Order object) {

    }

    @Override
    public void delete(Order object) {

    }

    @Override
    public Order findById(int id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }
}
