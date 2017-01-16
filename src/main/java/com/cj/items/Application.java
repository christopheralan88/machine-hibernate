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
        //printManagerMenu();
        ManagerMenu managerMenu = new ManagerMenu();
        managerMenu.printManagerMenu();

        //Configuration config = new Configuration();
        //config.configure("hibernate.cfg.xml");
        //SessionFactory sessionFactory = config.buildSessionFactory();

        //Transaction transaction = session.beginTransaction();

        //Items entry1 = new Items();
        //entry1.setLocation("A1");
        //entry1.setName("Doritos");
        //entry1.setPrice(3);
        //entry1.setCount(10);

        //System.out.println(entry1);

        //Items entry2 = new Items();
        //entry2.setLocation("B1");
        //entry2.setName("Fritos");
        //entry2.setPrice(5);
        //entry2.setCount(10);

        //System.out.println(entry2);

        //session.persist(entry1);
        //session.persist(entry2);
        //transaction.commit();

        session.close(); //close session
        System.exit(0); //close application
    }

    private static void  printManagerMenu() {
        System.out.println("What would you like to do?");
        for (Menu info : EnumSet.allOf(Menu.class)) {
            System.out.printf("%s /n", info);
        }
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (! Menu.matchesEnum(choice)) {
            System.out.println("That is not a valid choice.%n");
            printManagerMenu(); //starts method over again.
        }

        if (choice.equalsIgnoreCase("INSERT")) {
            connection.insertTableRecords(session);
        }

        if (choice.equalsIgnoreCase("DELETE")) {
            connection.deleteTableRecords(session);
        }

        if (choice.equalsIgnoreCase("SELECT")) {
            connection.selectTableRecords(session);
        }

        //if (choice.equalsIgnoreCase("UPDATE")) {
            //TODO:  cj make updateTableRecords method in ManagerMenu class.  Make it take a location since it's the primary key.
            //TODO:  cj do we need an update option?  If something needs to be updated, delete it and then insert it OR use refill method.
            //connection.updateTableRecords();
        //}

        if (choice.equalsIgnoreCase("REFILL")) {
            connection.refillAllItemsInMachine(session);
        }

        if (choice.equalsIgnoreCase("QUIT")) {
            System.out.println("Have a good day %s!  /n");
            //printCustomerMenu();
        }

        printManagerMenu();
    }

}
