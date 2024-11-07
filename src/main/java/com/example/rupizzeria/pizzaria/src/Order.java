package com.example.rupizzeria.pizzaria.src;

import java.util.ArrayList;

public class Order {
    private int number; //order number
    private ArrayList<Pizza> pizzas; //can use List<E> instead of ArrayList<E>

    public Order(int number, Pizza pizza){
        this.number = number;
        pizzas.add(pizza);
    }

    public void addPizza(Pizza pizza){
        pizzas.add(pizza);
    }

    public void removePizza(int i){
        pizzas.remove(i);
    }
}
