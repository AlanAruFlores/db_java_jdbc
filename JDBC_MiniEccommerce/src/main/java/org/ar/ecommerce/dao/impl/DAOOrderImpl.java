package org.ar.ecommerce.dao.impl;

import org.ar.ecommerce.dao.IDAOClient;
import org.ar.ecommerce.dao.IDAOOrder;
import org.ar.ecommerce.models.Client;
import org.ar.ecommerce.models.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOOrderImpl implements IDAOOrder {
    private Connection connection;
    private IDAOClient daoClient;

    private static final String INSERT = "insert into orders (total, id_client) values(?,?)";
    private static final String UPDATE = "update orders set total = ? where id_order = ?";
    private static final String DELETE = "delete from orders where id_order = ?";
    private static final String SELECT = "select * from orders where id_order = ?";
    private static final String SELECT_BY_CLIENT = "select * from orders where id_client = ?";
    private static final String SELECT_ALL = "select * from orders";

    public DAOOrderImpl(Connection connection, IDAOClient daoClient){
        this.connection = connection;
        this.daoClient = daoClient;
    }

    @Override
    public void insert(Order object) {
        try(PreparedStatement ps = connection.prepareStatement(INSERT)){
            ps.setDouble(1, object.getTotal());
            ps.setInt(2,object.getClient().getIdClient());
            int rows = ps.executeUpdate();

            if(rows > 0)
                System.out.println("QUERY: A NEW ROW HAVE JUST INSERTED");


        }catch (SQLException ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void update(Order object) {
        try(PreparedStatement ps = connection.prepareStatement(UPDATE)){
            ps.setDouble(1, object.getTotal());
            ps.setInt(2,object.getIdOrder());
            int rows  = ps.executeUpdate();

            if(rows>0)
                System.out.println("A ROW HAVE JUST UPDATED");

        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Order object) {
        try(PreparedStatement ps  = connection.prepareStatement(DELETE)){
            ps.setInt(1,object.getIdOrder());
            int rows = ps.executeUpdate();

            if(rows>0)
                System.out.println("A ROW HAVE JUST DELETED");

        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Order findById(int id) {
        Order order = null;
        try(PreparedStatement ps  = connection.prepareStatement(SELECT)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                order  = new Order(
                        rs.getInt("id_order"),
                        rs.getDate("order_date"),
                        rs.getFloat("total"),
                        daoClient.findById(rs.getInt("id_client"))
                );
            }

            rs.close();

        }catch (SQLException ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

        return order;
    }

    public List<Order> findAllByClient(Client client){
        List<Order> listOrders  = new ArrayList<>();

        try(PreparedStatement ps  = connection.prepareStatement(SELECT_BY_CLIENT)){
            ps.setInt(1,client.getIdClient());
            ResultSet rs  = ps.executeQuery();
            while(rs.next()){
                listOrders.add(new Order(
                        rs.getInt("id_order"),
                        rs.getDate("order_date"),
                        rs.getFloat("total"),
                        daoClient.findById(rs.getInt("id_client"))
                ));
            }

            rs.close();
        }catch (SQLException ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return listOrders;
    }

    @Override
    public List<Order> findAll() {
        List<Order> listOrders  = new ArrayList<>();

        try(PreparedStatement ps  = connection.prepareStatement(SELECT_ALL)){
            ResultSet rs  = ps.executeQuery();

            while(rs.next()){
                listOrders.add(new Order(
                        rs.getInt("id_order"),
                        rs.getDate("order_date"),
                        rs.getFloat("total"),
                        daoClient.findById(rs.getInt("id_client"))
                ));
            }

            rs.close();
        }catch (SQLException ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

        return listOrders;
    }
}
