package com.cj.items.model;

import com.sun.istack.internal.NotNull;
import com.sun.javafx.beans.IDProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

/**
 * Created by Chris.Jones on 1/13/2017.
 */

@Entity(name="items")
public class Items {

    @Id
    @NotNull
    private String location;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private int price;

    @Column
    @NotNull
    private int quantity;

    //private static final int MAX_COUNT = 10;


    //Default constructor for JPA
    public Items() {}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int count) {
        this.quantity = count;
    }

    public static Object getMAX_COUNT() {
        return 10; //MAX_COUNT;
    }

    @Override
    public String toString() {
        return "Items{" +
                "location='" + getLocation() + '\'' +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", count=" + getQuantity() +
                '}';
    }
}
