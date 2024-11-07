package com.example.rupizzeria.pizzaria.src;

import java.util.ArrayList;

public class ChicagoPizza implements PizzaFactory{
    @Override
    public Pizza createDeluxe() {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.GREENPEPPER);
        toppings.add(Topping.ONION);
        toppings.add(Topping.MUSHROOM);
        Pizza deluxe = new Deluxe(toppings, Crust.DEEPDISH, null);
        return deluxe;
    }

    @Override
    public Pizza createMeatzza() {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.BEEF);
        toppings.add(Topping.HAM);
        Pizza meatzza = new Meatzza(toppings, Crust.STUFFED, null);
        return meatzza;
    }

    @Override
    public Pizza createBBQChicken() {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.BBQCHICKEN);
        toppings.add(Topping.GREENPEPPER);
        toppings.add(Topping.PROVOLONE);
        toppings.add(Topping.CHEDDAR);
        Pizza bbqChicken = new BBQChicken(toppings, Crust.PAN, null);
        return bbqChicken;
    }

    @Override
    public Pizza createBuildYourOwn() {
        Pizza buildYourOwn = new BuildYourOwn(null, Crust.PAN, null);
        return buildYourOwn;
    }

}
