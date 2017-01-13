package com.cj.items;

/**
 * Created by Chris.Jones on 1/13/2017.
 */
public enum Menu {
    INSERT,
    DELETE,
    SELECT,
    UPDATE;

    public static void printMenuValues() {
        System.out.println("What would you like to do?  /n");

        //List<String> returnList = new ArrayList<>();
        //for (com.cj.items.Menu value : com.cj.items.Menu.values()) {
            //returnList.add(value.toString());
        //}
        //return returnList;

        for (Menu value : Menu.values()) {
            System.out.println(value.toString());
        }
    }
}
