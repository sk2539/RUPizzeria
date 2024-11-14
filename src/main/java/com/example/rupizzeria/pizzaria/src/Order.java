package com.example.rupizzeria.pizzaria.src;

import java.util.ArrayList;

public class Order {
    private int number; //order number
    private ArrayList<Pizza> pizzas; //can use List<E> instead of ArrayList<E>

    public Order(int number, Pizza pizza) {
        this.number = number;
        this.pizzas = new ArrayList<>();  // Initialize pizzas as a new ArrayList
        if (pizza != null) {
            this.pizzas.add(pizza);
        }
    }

    public void setOrderNum(int num){
        this.number = num;
    }
    public int getOrderNum(){
        return number;
    }

    public ArrayList<Pizza> getOrder(){
        return pizzas;
    }

    public void addPizza(Pizza pizza){
        pizzas.add(pizza);
    }

    public void removeithPizza(int i){
        pizzas.remove(i);
    }

    public void removePizza(Pizza pizza){
        for(int i = 0; i<pizzas.size(); i++){
            if(pizzas.get(i).equals(pizza)){
                pizzas.remove(i);
            }
        }
    }
}
