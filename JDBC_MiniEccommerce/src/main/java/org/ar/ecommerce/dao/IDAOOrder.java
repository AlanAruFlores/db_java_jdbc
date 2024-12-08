package org.ar.ecommerce.dao;

import org.ar.ecommerce.models.Client;
import org.ar.ecommerce.models.Order;

import java.util.List;

public interface IDAOOrder extends IDAO<Order> {

    public List<Order> findAllByClient(Client client);

}
