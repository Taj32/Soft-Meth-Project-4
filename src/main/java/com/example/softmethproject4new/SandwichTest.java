package com.example.softmethproject4new;

import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SandwichTest {

    @Test
    public void TestChickenSandwich(){ //no addOns
        ArrayList<String> addOns = new ArrayList<>();
        Sandwich chickenSandwich = new Sandwich("wheat", "chicken", addOns);
        double expectedPrice = 8.99;
        assertEquals(expectedPrice, chickenSandwich.price(), 0.01);

        System.out.println(String.format("%.2f",chickenSandwich.price()));


    }

    @Test
    public void TestBeefSandwich_withCheese(){ //no addOns
        ArrayList<String> addOns = new ArrayList<>();
        addOns.add("cheese");
        Sandwich beefSandwich = new Sandwich("bagel", "beef", addOns);
        double expectedPrice = 11.99; // 10.99 + 1 for cheese
        assertEquals(expectedPrice, beefSandwich.price(), 0.01);

        System.out.println(String.format("%.2f",beefSandwich.price()));


    }
    @Test
    public void TestFishSandwich_withAddOns() { // Test case with fish and multiple add-ons
        ArrayList<String> addOns = new ArrayList<>();
        addOns.add("cheese");
        addOns.add("lettuce");
        Sandwich fishSandwich = new Sandwich("wheat", "fish", addOns);
        double expectedPrice = 11.29; // 9.99 + 1 + 0.30
        assertEquals(expectedPrice, fishSandwich.price(), 0.01);

        System.out.println(String.format("%.2f",fishSandwich.price()));
    }

    @Test
    public void TestChickenSandwich_withVeggies() { // Test case with chicken and non-cheese add-ons
        ArrayList<String> addOns = new ArrayList<>();
        addOns.add("lettuce");
        addOns.add("tomato");
        Sandwich chickenSandwich = new Sandwich("sour dough", "chicken", addOns);
        double expectedPrice = 9.59; // 8.99 + 0.30 + 0.30
        assertEquals(expectedPrice, chickenSandwich.price(), 0.01);


        System.out.println(String.format("%.2f",chickenSandwich.price()));
    }

    @Test
    public void TestBeefSandwich_withAllAddOns() { // Test case with beef, cheese, and other add-ons
        ArrayList<String> addOns = new ArrayList<>();
        addOns.add("cheese");
        addOns.add("lettuce");
        addOns.add("tomato");
        Sandwich beefSandwich = new Sandwich("sour dough", "beef", addOns);
        double expectedPrice = 12.59; // 10.99 + 1 + 0.30 + 0.30
        assertEquals(expectedPrice, beefSandwich.price(), 0.01);

        System.out.println(String.format("%.2f",beefSandwich.price()));
    }

    @Test
    public void TestFishSandwich_withTomato() { // Test case with beef, cheese, and other add-ons
        ArrayList<String> addOns = new ArrayList<>();
        addOns.add("tomato");
        Sandwich fishSandwich = new Sandwich("bagel", "fish", addOns);
        double expectedPrice = 10.29; // 9.99 + 0.30
        assertEquals(expectedPrice, fishSandwich.price(), 0.01);

        System.out.println(String.format("%.2f",fishSandwich.price()));
    }

}
