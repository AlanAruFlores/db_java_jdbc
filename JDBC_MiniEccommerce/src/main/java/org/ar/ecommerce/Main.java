package org.ar.ecommerce;

import org.ar.ecommerce.dao.IDAOClient;
import org.ar.ecommerce.dao.IDAOManager;
import org.ar.ecommerce.dao.IDAOOrder;
import org.ar.ecommerce.dao.impl.DAOManager;
import org.ar.ecommerce.models.Client;
import org.ar.ecommerce.models.Order;

import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        IDAOManager manager = new DAOManager();
        IDAOClient daoClient = manager.getClientDAO();
        IDAOOrder daoOrder = manager.getOrderDAO();

        //Insert Orders
        /*
        daoOrder.insert(new Order(400f, new Client(1));
        daoOrder.insert(new Order(800f, new Client(1));
        daoOrder.insert(new Order(900f, new Client(1));
        */

        //Insert Client
        /*
        daoClient.insert(new Client("Joshep", "josep@gmail.com"));
        daoClient.insert(new Client("Joshep2", "josep1@gmail.com"));
        daoClient.insert(new Client("Joshep3", "josep2@gmail.com"));
        daoClient.insert(new Client("Joshep4", "josep3@gmail.com"));
        */

        //Delete client and his orders that have a relationship to himself
        daoClient.delete(new Client(1));


        System.out.println("CLIENTS DATABASE: ");
        daoClient.findAll().forEach(System.out::println);

        System.out.println("ORDER DATABASE: ");
        daoOrder.findAll().forEach(System.out::println);

    }
}