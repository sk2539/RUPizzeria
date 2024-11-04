package com.example.rupizzeria.pizzaria.src;

import java.util.ArrayList;

public class Deluxe extends Pizza{
    public Deluxe(ArrayList<Topping> arr, Crust crust, Size size) {
        super(arr, crust, size);
    }

    public Deluxe(){}

    public double price()
    {
        return 0.0;
    }
}
