package com.cj.items;

/**
 * Created by Chris.Jones on 1/13/2017.
 */

// holds methods and fields relating to user interface menu
public enum Menu {
    INSERT,
    DELETE,
    SELECT,
    UPDATE,
    REFILL,
    REPORT,
    QUIT;

    public static boolean matchesEnum(String input) {
        for (Menu item : Menu.values()) {
            if (item.toString().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}
