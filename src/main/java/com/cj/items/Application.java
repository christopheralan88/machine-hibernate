package com.cj.items;

import com.cj.items.model.Items;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.imageio.spi.ServiceRegistry;

/**
 * Created by Chris.Jones on 1/13/2017.
 */
public class Application {

    //Hold a reusable reference to a SessionFactory (since we only need one)
    private static final SessionFactory sessionFactory;

    static {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        sessionFactory = config.buildSessionFactory();
    }

    public static void main(String[] args) {
        //Configuration config = new Configuration();
        //config.configure("hibernate.cfg.xml");
        //SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Items entry1 = new Items();
        entry1.setLocation("A1");
        entry1.setName("Doritos");
        entry1.setPrice(3);
        entry1.setCount(10);

        System.out.println(entry1);

        session.persist(entry1);
        transaction.commit();


    }

}
