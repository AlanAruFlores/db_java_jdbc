package org.crud.dao.impl;

import org.crud.dao.IUserDAO;
import org.crud.modelo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDAOImpl implements IUserDAO {

    private Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    private final String INSERT = "insert into [user] (username, password) values (?,?);";
    private final String UPDATE = "update [user] set username = ?, password = ? where id = ?";
    private final String DELETE = "delete [user] where id = ?";
    private final String SELECT_ID = "select * from [user] where id = ?";
    private final String SELECT_ALL = "select * from [user]";


    @Override
    public void insert(User row) {
        try(PreparedStatement ps = connection.prepareStatement(INSERT)){
            ps.setString(1, row.getUsername());
            ps.setString(2, row.getPassword());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(User row) {
        try(PreparedStatement ps = connection.prepareStatement(UPDATE)){
            ps.setString(1, row.getUsername());
            ps.setString(2, row.getPassword());
            ps.setInt(3, row.getId());
            ps.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(User row) {
        try(PreparedStatement ps = connection.prepareStatement(DELETE)){
            ps.setInt(1, row.getId());
            ps.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public User find(int id) {
        User userRetrieved = null;
        try(PreparedStatement ps = connection.prepareStatement(SELECT_ID)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                userRetrieved =  new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }

            rs.close();

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return userRetrieved;
    }

    @Override
    public Collection<User> findAll() {
        Collection<User> listUsersRetrieved = new ArrayList<User>();

        try(PreparedStatement ps = this.connection.prepareStatement(SELECT_ALL)){
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                listUsersRetrieved.add(
                        new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"))
                );
            }

            rs.close();

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }


        return listUsersRetrieved;
    }
}
