package com.example.rupizzeria.pizzaria.src;


import java.util.ArrayList;


public class Deluxe extends Pizza{
    public Deluxe(ArrayList<Topping> arr, Crust crust, Size size) {
        super(arr, crust, size);
    }


    public Deluxe(){}


    @Override
    public double price() {
        if(this.getSize().equals(Size.SMALL)){
            return 16.99;
        }
        else if(this.getSize().equals(Size.MEDIUM)){
            return 18.99;
        }
        else if(this.getSize().equals(Size.LARGE)){
            return 20.99;
        }
        return 0.0;
    }
}