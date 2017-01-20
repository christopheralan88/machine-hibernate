package com.cj.items;

import com.cj.items.model.Items;
import com.cj.items.model.Sales;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Character.*;

/**
 * Created by Chris.Jones on 1/13/2017.
 */

// holds the methods and fields related to CRUD operations
public class Connection {

    //Hold a reusable reference to a SessionFactory (since we only need one)
    private static final SessionFactory sessionFactory;
    private String username;
    private String password;

    static {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        sessionFactory = config.buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session createSession(SessionFactory sessionFactory) {
        return sessionFactory.openSession();
    }

    public void selectTableRecords(Session session) {
        String hql = "FROM items ORDER BY location";
        Query query = session.createQuery(hql);
        List<Object> results = query.getResultList();
        for (Object result : results) {
            System.out.println(result.toString());
        }
    }

    public void insertTableRecords(Session session) {

        // get table name from user
        //System.out.println("What table would you like to insert new records to?");
        Scanner scanner = new Scanner(System.in);
        //String table = scanner.next();

        // retrieve all table fields
        Field[] fields = Items.class.getDeclaredFields();
        for (Object field : fields) {
            System.out.printf("Enter a value for the %s %n", field.toString());
        }

        // create variable for each item user types in console
        String location = scanner.next();
        String name = scanner.next();
        int price = scanner.nextInt();
        int quantity = scanner.nextInt();

        Transaction transaction = session.beginTransaction();
        Items entry = new Items();
        entry.setLocation(location);
        entry.setName(name);
        entry.setPrice(price);
        entry.setQuantity(quantity);

        session.persist(entry);
        transaction.commit();
    }

    public void deleteTableRecords(Session session) {
        System.out.println("What is the location of the item you would like to delete?");
        Scanner scanner = new Scanner(System.in);
        String location = scanner.next();

        try {
            // check to make sure input starts with letter and has a length of 2
            if (isLetter(location.charAt(0)) && location.length() == 2) {
                // check to make sure input ends with int that is greater than 0 and less than 10
                if (getNumericValue(location.charAt(1)) > 0 && getNumericValue(location.charAt(1)) < 10) {
                    Transaction transaction = session.beginTransaction();
                    String hql = "DELETE FROM items WHERE location = '" + location + "'";
                    Query query = session.createQuery(hql);
                    int result = query.executeUpdate();
                    transaction.commit();
                    System.out.printf("%d records deleted %n", result);
                } else {
                    System.out.println("That is not a valid location.");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void refillAllItemsInMachine(Session session) {
        Transaction transaction = session.beginTransaction();
        String hql = "UPDATE items SET quantity = '" + Items.getMAX_COUNT() + "'";
        Query query = session.createQuery(hql);
        int results = query.executeUpdate();
        transaction.commit();
        System.out.printf("%d records updated.  Each item now has %d items. %n", results, Items.getMAX_COUNT());
    }

    public void printSalesReport(Session session) {
        String hql = "FROM sales ORDER BY time_of_purchase DESC";
        TypedQuery<Sales> query = session.createQuery(hql, Sales.class);
        List<Sales> results = query.getResultList();
        System.out.println("Item Name     Price     Time Of Purchase");
        for (int i = 0; i < results.size(); i++) {
            Sales sale = results.get(i);
            System.out.printf("%s     %s     %s %n", sale.getItemName(), sale.getPrice(),
                    sale.getTimeOfPurchase());
        }
    }

}
