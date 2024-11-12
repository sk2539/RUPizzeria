package com.example.rupizzeria.pizzaria.src;

import java.util.ArrayList;

public class Meatzza extends Pizza{
    public Meatzza(ArrayList<Topping> arr, Crust crust, Size size) {
        super(arr, crust, size);
    }

    public Meatzza(){}

    @Override
    public double price() {
        if(this.getSize().equals(Size.SMALL)){
            return 17.99;
        }
        else if(this.getSize().equals(Size.MEDIUM)){
            return 19.99;
        }
        else if(this.getSize().equals(Size.LARGE)){
            return 21.99;
        }
        return 0.00;
    }
}