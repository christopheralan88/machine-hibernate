package com.cj.items;

import com.cj.items.model.Items;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

/**
 * Created by chris&amy on 1/17/2017.
 */
public enum CustomerMenu {
    BUY,
    EXIT,
    MANAGER;

    public static boolean matchesEnum(String input) {
        for (CustomerMenu menuItem : CustomerMenu.values()) {
            if (menuItem.toString().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static void printCustomerMenu() {
        System.out.println("Please choose one of the following: ");
        for (CustomerMenu choice : EnumSet.allOf(CustomerMenu.class)) {
            System.out.printf("%s%n", choice);
        }
        Scanner scanner = new Scanner(System.in);
        String customerChoice = scanner.next();
        if (!matchesEnum(customerChoice)) {
            System.out.println("That is not a valid choice.");
            printCustomerMenu(); //starts method over again.
        }

        if (customerChoice.equalsIgnoreCase("EXIT")) {
            System.out.println("Have a good day!");
            Application.getSession().close(); //close session
            System.exit(0); //close application
        }

        if (customerChoice.equalsIgnoreCase("BUY")) {
            System.out.println("Here are the available vending machine items: ");

            String hql = "FROM items WHERE quantity > 0 ORDER BY location";
            TypedQuery<Items> query = Application.getSession().createQuery(hql, Items.class);
            List<Items> results = query.getResultList();

            System.out.println("Location     Name     Price");
            for (int i = 0; i < results.size(); i++) {
                Items item = results.get(i);
                System.out.printf("%s     %s     %d %n", item.getLocation(), item.getName(),
                       item.getPrice());
            }

            Scanner sc = new Scanner(System.in);
            String location = sc.next();
            String updateItemsHql = "UPDATE items SET quantity = (SELECT quantity FROM items WHERE location =" +
                    " '" + location + "') - 1 WHERE location = '" + location + "'";
            String insertSaleHql = String.format("INSERT INTO sales VALUES (CURRENT_TIMESTAMP(),(SELECT name FROM items WHERE location = '%s'),(SELECT price FROM items WHERE location = '%s'))", location, location);

            try {
                Transaction transaction = Application.getSession().beginTransaction();
                Query updateItemQuery = Application.getSession().createQuery(updateItemsHql);
                updateItemQuery.executeUpdate();
                Query insertSaleQuery = Application.getSession().createSQLQuery(insertSaleHql);
                //TODO:  cj change method name above to a non-depracted method.  We need to send a regular SQL string instead of ORM.
                insertSaleQuery.executeUpdate();
                Application.getSession().flush();
                transaction.commit();
                System.out.printf("Enjoy!");
            } catch (Exception ex) {
                System.out.println("I'm sorry there was a problem vending your product.");
                //TODO:  cj subtract money from funds to refund customer.
            }
        }

        if (customerChoice.equalsIgnoreCase("MANAGER")) {
            //TODO:  cj build sign in method to start printManagerMenu method.
            ManagerMenu managerMenu = new ManagerMenu();
            managerMenu.printManagerMenu();
            //manager = new Manager("chris.jones", "chris1234", machine);
            //System.out.printf("Welcome back %s!  %n", manager.getUsernameFirstName());
            //printManagerMenu();
        }

        printCustomerMenu();
    }
}
