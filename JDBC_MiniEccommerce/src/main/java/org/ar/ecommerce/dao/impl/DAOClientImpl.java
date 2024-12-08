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

public class DAOClientImpl implements IDAOClient {
    private Connection connection;

    private final String INSERT = "insert into clients (name,email) values(?, ?)";
    private final String UPDATE = "update clients set name=?, email=? where id_client=?";
    private final String DELETE = "delete clients where id_client=?";
    private final String SELECT = "select * from clients where id_client=?";
    private final String SELECT_ALL = "select * from clients";

    public DAOClientImpl(Connection connection){
        this.connection = connection;
    }


    @Override
    public void insert(Client object) {
        try(PreparedStatement ps = connection.prepareStatement(INSERT))
        {
            ps.setString(1, object.getName());
            ps.setString(2, object.getEmail());
            int rows = ps.executeUpdate();

            if(rows > 0)
                System.out.println("QUERY: A NEW ROW HAS JUST INSERTED");
        }
        catch(SQLException ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Client object) {
        try(PreparedStatement ps =  connection.prepareStatement(UPDATE)){
            ps.setString(1, object.getName());
            ps.setString(2, object.getEmail());
            ps.setInt(3,object.getIdClient());

            int rows = ps.executeUpdate();
            if(rows > 0)
                System.out.println("QUERY: A ROW HAVE JUST UPDATED");

        }catch (SQLException ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Client object) {
        IDAOOrder orderDAO = new DAOOrderImpl(this.connection, this);

        try(PreparedStatement ps = connection.prepareStatement(DELETE)){
            connection.setAutoCommit(false);

            List<Order> orders = orderDAO.findAllByClient(object);

            orders.forEach(ord ->{
                orderDAO.delete(ord);
            });

            ps.setInt(1, object.getIdClient());
            int rows = ps.executeUpdate();

            if(rows > 0)
                System.out.println("QUERY: A ROW HAS JUST DELETED");


            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException ex){
            try{
                connection.rollback();
                connection.setAutoCommit(true);
            }catch (SQLException ex2){
                ex2.printStackTrace();
            }
            ex.printStackTrace();
            System.out.print(ex.getMessage());
        }

    }

    @Override
    public Client findById(int id) {
        Client client = null;

        try(PreparedStatement ps = connection.prepareStatement(SELECT)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
               client = new Client(rs.getInt("id_client"), rs.getString("name"),
                       rs.getString("email"), rs.getDate("register_date"));
            }

            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.print(ex.getMessage());
        }
        return client;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clientsList  = new ArrayList<Client>();

        try(PreparedStatement ps = connection.prepareStatement(SELECT_ALL)){
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                clientsList.add(
                        new Client(
                                rs.getInt("id_client"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getDate("register_date")
                        )
                );
            }

            rs.close();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return clientsList;
    }
}
