package com.example.rupizzeria.pizzaria.src;

import java.util.ArrayList;

public class BuildYourOwn extends Pizza{
    public BuildYourOwn(ArrayList<Topping> arr, Crust crust, Size size) {
        super(arr, crust, size);
    }

    public BuildYourOwn(){}

    @Override
    public double price() {
        double price = 0.0;
        if(this.getSize().equals(Size.SMALL)){
            price += 8.99;
        }
        else if(this.getSize().equals(Size.MEDIUM)){
            price += 10.99;
        }
        else if(this.getSize().equals(Size.LARGE)){
            price += 12.99;
        }
        if(this.getToppings()!=null && this.getToppings().size() <= 7){
            price += (1.69 * this.getToppings().size());
        }
        double scaledValue = price * 100;
        if (scaledValue % 1 == 0.5) {
            price = Math.ceil(price * 100) / 100.0;
        }
        else {
            price = Math.round(price * 100) / 100.0;
        }
        return price;
    }
}