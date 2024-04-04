package com.example.softmethproject4new;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int nextOrderNumber = 1;
    private int orderNumber;
    private List<MenuItem> items;

    public Order() {
        this.orderNumber = nextOrderNumber++;
        this.items = new ArrayList<>();
    }

    public void addItems(List<MenuItem> itemsToAdd) {
        this.items.addAll(itemsToAdd);
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Order #" + orderNumber + ": ");
        if (items.isEmpty()) {
            s.append("No items.");
        } else {
            for(MenuItem item: items) {
                s.append(item.toString()).append(", ");
            }
            // Remove the last comma and space
            s.setLength(s.length() - 2);
        }
        return s.toString();
    }

}
