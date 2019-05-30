package com.connorchurch.james.churchapp.activities;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable {
    private static final long serialVersionUID = -6099312954099962806L;
    private String title;
    private String body;

    public Item(String title) {
        this.title = title;

    }

    public String getTitle() {
        return title;
    }


    public static ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<Item>();
       items.add(new Item("Facebook"));
        //items.add(new Item("Item 2"));
        //items.add(new Item("Item 3"));
        return items;
    }


    @Override
    public String toString() {
        return getTitle();
    }

}
