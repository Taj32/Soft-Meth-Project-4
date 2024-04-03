package com.example.softmethproject4new;

import javafx.scene.control.Menu;

import java.util.ArrayList;

public class Sandwich extends MenuItem {

    private String bread;
    private String protein;
    private ArrayList<String> addOns;

    public Sandwich(String bread, String protein, ArrayList<String> addOns) {
        if(bread == null) {
            this.bread = "";
        }
        else {
            this.bread = bread;
        }
        this.protein = protein;
        this.addOns = addOns;

        System.out.println(bread);
        System.out.println(protein);
    }

    @Override
    public double price() {
        double sandwich_price = switch(protein){
            case "beef" -> 10.99;
            case "chicken" -> 8.99;
            case "fish" -> 9.99;
            default -> 0.00;
        };

        for (String element : addOns) {
            if(element.equals("cheese")) {
                System.out.println("adding cheese");
                sandwich_price += 1;
            }
            else {
                sandwich_price += 0.30;
            }

        }

        return sandwich_price;

    }

    public String getProtein() {
        return protein;
    }

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }


}
