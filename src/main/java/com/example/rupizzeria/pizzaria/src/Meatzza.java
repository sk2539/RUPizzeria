package com.example.rupizzeria.pizzaria.src;

import java.util.ArrayList;

public class Meatzza extends Pizza{
    public Meatzza(ArrayList<Topping> arr, Crust crust, Size size) {
        super(arr, crust, size);
    }

    public Meatzza(){}

    public double price()
    {
        return 0.0;
    }
}
