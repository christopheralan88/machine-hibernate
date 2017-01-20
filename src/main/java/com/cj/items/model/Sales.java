package com.cj.items.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;


@Entity(name = "sales")
public class Sales {

    @Id
    @Column(name = "time_of_purchase")
    private Timestamp timeOfPurchase;

    @Column(name = "item_name")
    private String itemName;

    @Column
    private int price;


    public Sales() {}

    public Timestamp getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public void setTimeOfPurchase(Timestamp timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "timeOfPurchase=" + timeOfPurchase +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                '}';
    }
}
