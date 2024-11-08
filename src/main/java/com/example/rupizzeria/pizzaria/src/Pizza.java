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

    public void setToppings(ArrayList<Topping> toppings){
        this.toppings = toppings;
    }

    public void setSize(Size size){
        this.size = size;
    }

    public void setCrust(Crust crust){
        this.crust = crust;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Pizza){
            Pizza pizza = (Pizza) obj;
            boolean isEqual = true;
            if(this.toppings.size() != pizza.toppings.size()){
                return false;
            }
            for(int i = 0; i<this.toppings.size(); i++){
                if(this.toppings.get(i).equals(pizza.toppings.get(i))){
                    isEqual = false;
                }
            }
            return isEqual && this.size.equals(pizza.size) && this.crust.equals(pizza.crust);
        }
        return false;
    }

    @Override
    public String toString() {
        String toppingsString = getToppings().isEmpty()
                ? "No toppings"
                : String.join(", ", getToppings().stream()
                .map(Topping::toString)
                .toArray(String[]::new));
        return String.format("[Size=%s, Crust=%s, Toppings=%s]",
                getSize().toString(),
                getCrust(),
                toppingsString);
    }
}