package com.example.rupizzeria.pizzaria.src;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BuildYourOwnTest {

    private double roundUpToTwoDecimalPlaces(double value) {
        double scaledValue = value * 100;
        if (scaledValue % 1 == 0.5) {
            return Math.ceil(value * 100) / 100.0;
        }
        else {
            return Math.round(value * 100) / 100.0;
        }
    }

    @Test
    public void testSmallPizza() {
        ChicagoPizza chicagoPizza = new ChicagoPizza();
        Pizza pizza = chicagoPizza.createBuildYourOwn();
        pizza.setSize(Size.SMALL);
        String expectedPrice = String.format("%.2f", roundUpToTwoDecimalPlaces(8.99));
        String actualPrice = String.format("%.2f", pizza.price());
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testThreeToppingsSelectedForMedium() {
        ChicagoPizza chicagoPizza = new ChicagoPizza();
        Pizza pizza = chicagoPizza.createBuildYourOwn();
        pizza.setSize(Size.MEDIUM);
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.MUSHROOM);
        toppings.add(Topping.CHEDDAR);
        pizza.setToppings(toppings);
        String expectedPrice = String.format("%.2f", roundUpToTwoDecimalPlaces(10.99 + (3 * 1.69)));
        String actualPrice = String.format("%.2f", pizza.price());
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testFiveToppingSelectForLarge() {
        NYPizza nyPizza = new NYPizza();
        Pizza pizza = nyPizza.createBuildYourOwn();
        pizza.setSize(Size.LARGE);
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.MUSHROOM);
        toppings.add(Topping.CHEDDAR);
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.GREENPEPPER);
        pizza.setToppings(toppings);
        String expectedPrice = String.format("%.2f", roundUpToTwoDecimalPlaces(12.99 + (5 * 1.69)));
        String actualPrice = String.format("%.2f", pizza.price());
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testPriceNoSizeSelected() {
        NYPizza nyPizza = new NYPizza();
        Pizza pizza = nyPizza.createBuildYourOwn();
        assertThrows(NullPointerException.class, pizza::price);
    }

    @Test
    public void testPriceSmallPizzaWithMaxToppings() {
        NYPizza nyPizza = new NYPizza();
        Pizza pizza = nyPizza.createBuildYourOwn();
        pizza.setSize(Size.SMALL);
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.PROVOLONE);
        toppings.add(Topping.GREENPEPPER);
        toppings.add(Topping.BBQCHICKEN);
        toppings.add(Topping.MUSHROOM);
        toppings.add(Topping.SPINACH);
        toppings.add(Topping.BROCCOLI);
        toppings.add(Topping.JALAPENO);
        pizza.setToppings(toppings);
        String expectedPrice = String.format("%.2f", roundUpToTwoDecimalPlaces(8.99 + (7 * 1.69)));
        String actualPrice = String.format("%.2f", pizza.price());
        assertEquals(expectedPrice, actualPrice);
    }
    @Test
    public void testPriceMediumPizzaWithOneTopping() {
        ChicagoPizza chicagoPizza = new ChicagoPizza();
        Pizza pizza = chicagoPizza.createBuildYourOwn();
        pizza.setSize(Size.MEDIUM);
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.ONION);
        pizza.setToppings(toppings);
        String expectedPrice = String.format("%.2f", roundUpToTwoDecimalPlaces(10.99 + 1.69));
        String actualPrice = String.format("%.2f", pizza.price());
        assertEquals(expectedPrice, actualPrice);
    }

}