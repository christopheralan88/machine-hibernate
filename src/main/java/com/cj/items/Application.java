package com.cj.items;

import com.cj.items.model.Items;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.imageio.spi.ServiceRegistry;
import java.sql.*;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Chris.Jones on 1/13/2017.
 */

// holds recursive code from other classes
public class Application {

    //Hold a reusable reference to a SessionFactory (since we only need one)
    //private static final SessionFactory sessionFactory;

    //static {
        //Configuration config = new Configuration();
        //config.configure("hibernate.cfg.xml");
        //sessionFactory = config.buildSessionFactory();
    //}

    private static Connection connection;
    private static Session session;


    public static Connection getConnection() {
        return connection;
    }

    public static Session getSession() {
        return session;
    }

    public static void main(String[] args) {
        // instantiating the Connection object also instantiates a SessionFactory object - see Connection class for details
        connection = new Connection();
        session = connection.createSession(connection.getSessionFactory());
        CustomerMenu.printCustomerMenu();
        //printManagerMenu();
        //ManagerMenu managerMenu = new ManagerMenu();
        //managerMenu.printManagerMenu();
    }
}
