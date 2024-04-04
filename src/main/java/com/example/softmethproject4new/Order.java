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

    public double calculateTotal() {
        double total = 0.0;
        for (MenuItem item : this.items) {
            total += item.price();
        }
        return total;
    }

//    @Override
//    public String toString() {
//        StringBuilder s = new StringBuilder("Order #" + orderNumber + ": ");
//        if (items.isEmpty()) {
//            s.append("No items.");
//        } else {
//            for(MenuItem item: items) {
//                s.append(item.toString()).append(", ");
//            }
//            s.setLength(s.length() - 2);
//        }
//        return s.toString();
//    }

}

