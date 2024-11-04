package com.example.rupizzeria.pizzaria.src;

import java.util.ArrayList;

public abstract class Pizza {
    private ArrayList<Topping> toppings; //Topping is a Enum class
    private Crust crust; //Crust is a Enum class
    private Size size; //Size is a Enum class
    public abstract double price();

    public Pizza(){}

    public Pizza(ArrayList<Topping> toppings, Crust crust, Size size){
        this.toppings = toppings;
        this.crust = crust;
        this.size = size;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public Crust getCrust(){
        return crust;
    }

    public Size getSize(){
        return size;
    }
}