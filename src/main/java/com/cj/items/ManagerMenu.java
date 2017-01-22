package com.cj.items;

import com.cj.items.model.Users;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

/**
 * Created by chris&amy on 1/15/2017.
 */
public class ManagerMenu {

    private static Menu menu;
    private Session session;
    private Connection connection;
    private String username;
    private String password;
    private String firstName;

    public static ManagerMenu ManagerMenuFactory() {

        ManagerMenu managerMenu;

        // set fields to the Connection and Session fields that have already been instantiated in Application class with Main method
        Connection connection = Application.getConnection();
        Session session = Application.getSession();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your username: ");
        String username = scanner.next();
        System.out.println("Please enter your password: ");
        String password = scanner.next();

        TypedQuery<Users> query = session.createQuery("FROM users WHERE username = '" + username + "' AND password = '" +
                password + "' AND active = TRUE");
        List<Users> results = query.getResultList();

        // if the query returns records then instantiate ManagerMenu object because username and password were correct and user is active.
        // else query did not return records and username and password were not correct.
        if (results.size() > 0) {
            managerMenu = new ManagerMenu();
            managerMenu.connection = connection;
            managerMenu.session = session;
            managerMenu.username = results.get(0).getUsername();
            managerMenu.password = results.get(0).getPassword();
            managerMenu.firstName = results.get(0).getFirstName();
        } else {
            System.out.println("The username or password you entered is incorrect");
            managerMenu = null;
        }
        return managerMenu;
    }

    public static Menu getMenu() {
        return menu;
    }

    public static void setMenu(Menu menu) {
        ManagerMenu.menu = menu;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void  printManagerMenu() {
        System.out.println("What would you like to do?");
        for (Menu info : EnumSet.allOf(Menu.class)) {
            System.out.printf("%s %n", info);
        }
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (! Menu.matchesEnum(choice)) {
            System.out.println("That is not a valid choice.");
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

        if (choice.equalsIgnoreCase("REPORT")) {
            connection.printSalesReport(session);
        }

        if (choice.equalsIgnoreCase("QUIT")) {
            //TODO:  cj make the line below a printf function with the user's first name filling the placeholder.
            System.out.println("Have a good day %s!");
            CustomerMenu.printCustomerMenu();
        }

        printManagerMenu();
    }
}
