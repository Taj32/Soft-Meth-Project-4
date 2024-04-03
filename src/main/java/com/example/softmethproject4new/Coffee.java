package com.example.softmethproject4new;

import java.util.ArrayList;

public class Coffee extends MenuItem{
    private String size;
    private int quantity;
    private ArrayList<String> addIns;


    public Coffee(String size, int quantity, ArrayList<String> addIns) {
        this.size = size;
        this.quantity = quantity;
        this.addIns = addIns;
    }

    @Override
    public double price() {
        double sizePrice = switch(size) {
            case "Short" -> 1.99;
            case "Tall" -> 2.49;
            case "Grande" -> 2.99;
            case "Venti" -> 3.49;
            default -> 0.00;
        };

        double addInPrice = 0.0;
        for (String element : addIns) {
            addInPrice += 0.30;
        }

        double totalPrice = (sizePrice + addInPrice) * quantity;
        return totalPrice;

    }
}
