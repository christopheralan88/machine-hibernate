package com.cj.items;

import org.hibernate.Session;


/**
 * Created by Chris.Jones on 1/13/2017.
 */

// holds recursive code from other classes
public class Application {

    private static Connection connection;
    private static Session session;
    private static ManagerMenu managerMenu;


    public static Connection getConnection() {
        return connection;
    }

    public static Session getSession() {
        return session;
    }

    public static ManagerMenu getManagerMenu() {
        return managerMenu;
    }

    public static void setManagerMenu(ManagerMenu managerMenu) {
        Application.managerMenu = managerMenu;
    }

    public static void main(String[] args) {
        // instantiating the Connection object also instantiates a SessionFactory object - see Connection class for details
        connection = new Connection();
        session = connection.createSession(connection.getSessionFactory());
        CustomerMenu.printCustomerMenu();
    }
}
