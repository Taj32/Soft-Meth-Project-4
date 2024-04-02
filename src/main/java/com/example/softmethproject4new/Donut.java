package com.example.softmethproject4new;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Donut extends MenuItem{

    private String type;
    private String flavor;
    private int quantity;

    public Donut(String type, String flavor, int quantity){
        this.type = type;
        this.flavor = flavor;
        this.quantity = quantity;
    }

    public static List<String> typeOfFlavors(String type){
        switch(type){
            case "Yeast":
                return Arrays.asList("Glazed", "Pistachio", "Blueberry-Lemon", "Double-Chocolate", "Strawberry-Sprinkles", "Coconut");
            case "Cake":
                return Arrays.asList("Chai", "Passionfruit", "Blood-Orange");
            case "Donut Holes":
                return Arrays.asList("Jelly", "Glazed", "Powder");
            default:
                return new ArrayList<>();
        }
    }
    @Override
    public double price() {
        double donut_price = switch(type){
            case "Yeast" -> 1.79;
            case "Cake" -> 1.89;
            case "Donut Hole" -> 0.39;
            default -> 0.00;
        };

        return donut_price * quantity;

    }

    @Override
    public String toString(){
        String amount = String.valueOf(quantity);

        return "("+amount+")";
    }
}