package com.example.rupizzeria.pizzaria.src;

import java.util.ArrayList;

public class BuildYourOwn extends Pizza{
    public BuildYourOwn(ArrayList<Topping> arr, Crust crust, Size size) {
        super(arr, crust, size);
    }
    public BuildYourOwn(){}

    public double price()
    {
        return 0.0;
    }
}
