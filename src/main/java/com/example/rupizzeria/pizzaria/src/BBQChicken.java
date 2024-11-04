package com.example.rupizzeria.pizzaria.src;

import java.util.ArrayList;

public class BBQChicken extends Pizza{
    public BBQChicken(ArrayList<Topping> toppings, Crust crust, Size size) {
        super(toppings, crust, size);
    }

    public BBQChicken(){}

    public double price()
    {
        return 0.0;
    }
}
