package com.cj.items;

import org.hibernate.Session;

import java.util.EnumSet;
import java.util.Scanner;

/**
 * Created by chris&amy on 1/15/2017.
 */
public class ManagerMenu {

    private static Menu menu;
    private Session session;
    private Connection connection;

    public ManagerMenu() {
        // set fields to the Connection and Session fields that have already been instantiated in Application class with Main method
        this.connection = Application.getConnection();
        this.session = Application.getSession();
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

        if (choice.equalsIgnoreCase("QUIT")) {
            //TODO:  cj make the line below a printf function with the user's first name filling the placeholder.
            System.out.println("Have a good day %s!");
            CustomerMenu.printCustomerMenu();
        }

        printManagerMenu();
    }
}
