package org.crud;

import org.crud.dao.IDAOManager;
import org.crud.dao.IUserDAO;
import org.crud.dao.impl.DAOManagerImpl;
import org.crud.modelo.User;

import java.util.Collection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        IDAOManager manager = new DAOManagerImpl();

        IUserDAO userDAO = manager.getUserDAO();
        /*
        User user = userDAO.find(2);
        System.out.println(user);
        */


        //userDAO.insert(new User("Karl", "karlitos"));
        //userDAO.update(new User(3,"Ale", "ale123"));
        //userDAO.delete(new User(2));

        System.out.println("USUARIOS: ");
        Collection<User> users = userDAO.findAll();
        users.stream().forEach(System.out::println);
    }
}