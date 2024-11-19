package com.example.rupizzeria.pizzaria.src;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class BuildYourOwnTest {

    @Test
    public void testSmallPizza() {
        PizzaFactory chicagoPizza = new ChicagoPizza();
        Pizza pizza = chicagoPizza.createBuildYourOwn();
        pizza.setSize(Size.SMALL);
        assertEquals(8.99, pizza.price(), 0.001);
    }

    @Test
    public void testThreeToppingsSelectedForMedium() {
        PizzaFactory chicagoPizza = new ChicagoPizza();
        Pizza pizza = chicagoPizza.createBuildYourOwn();
        pizza.setSize(Size.MEDIUM);
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.MUSHROOM);
        toppings.add(Topping.CHEDDAR);
        pizza.setToppings(toppings);
        assertEquals(10.99 + (3 * 1.69), pizza.price(), 0.001);
    }

    @Test
    public void testFiveToppingSelectForLarge() {
        PizzaFactory nyPizza = new NYPizza();
        Pizza pizza = nyPizza.createBuildYourOwn();
        pizza.setSize(Size.LARGE);
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.MUSHROOM);
        toppings.add(Topping.CHEDDAR);
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.GREENPEPPER);
        pizza.setToppings(toppings);
        assertEquals(12.99 + (5 * 1.69), pizza.price(), 0.001);
    }

    @Test
    public void testPriceNoSizeSelected() {
        PizzaFactory nyPizza = new NYPizza();
        Pizza pizza = nyPizza.createBuildYourOwn();
        assertThrows(NullPointerException.class, pizza::price);
    }

    @Test
    public void testPriceSmallPizzaWithMaxToppings() {
        PizzaFactory nyPizza = new NYPizza();
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
        assertEquals(8.99 + (7 * 1.69), pizza.price(), 0.001);
    }
    @Test
    public void testPriceMediumPizzaWithOneTopping() {
        PizzaFactory chicagoPizza = new ChicagoPizza();
        Pizza pizza = chicagoPizza.createBuildYourOwn();
        pizza.setSize(Size.MEDIUM);
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.ONION);
        pizza.setToppings(toppings);
        assertEquals(10.99 + 1.69, pizza.price(), 0.001);
    }
}