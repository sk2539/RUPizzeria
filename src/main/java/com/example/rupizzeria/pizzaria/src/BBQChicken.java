package com.example.rupizzeria.pizzaria.src;

import java.util.ArrayList;

public class BBQChicken extends Pizza{
    public BBQChicken(ArrayList<Topping> toppings, Crust crust, Size size) {
        super(toppings, crust, size);
    }

    public BBQChicken(){}

    @Override
    public double price() {
        if(this.getSize().equals(Size.SMALL)){
            return 14.99;
        }
        else if(this.getSize().equals(Size.MEDIUM)){
            return 16.99;
        }
        else if(this.getSize().equals(Size.LARGE)){
            return 19.99;
        }
        return 0.00;
    }
}