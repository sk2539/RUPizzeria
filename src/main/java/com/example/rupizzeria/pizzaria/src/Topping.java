package com.example.rupizzeria.pizzaria.src;

import java.util.ArrayList;
import java.util.Collections;

public enum Topping {
    SAUSAGE,
    PEPPERONI,
    GREENPEPPER,
    ONION,
    MUSHROOM,
    BBQCHICKEN,
    PROVOLONE,
    CHEDDAR,
    BEEF,
    HAM,
    BROCCOLI,
    SPINACH,
    JALAPENO;

    @Override
    public String toString() {
        return super.toString();
    }

    public static Topping stringToTopping(String toppingName) {
        if (toppingName == null) {
            throw new IllegalArgumentException("Topping name cannot be null");
        }
        switch (toppingName.toUpperCase()) {
            case "SAUSAGE":
                return SAUSAGE;
            case "PEPPERONI":
                return PEPPERONI;
            case "GREENPEPPER":
                return GREENPEPPER;
            case "ONION":
                return ONION;
            case "MUSHROOM":
                return MUSHROOM;
            case "BBQCHICKEN":
                return BBQCHICKEN;
            case "PROVOLONE":
                return PROVOLONE;
            case "CHEDDAR":
                return CHEDDAR;
            case "BEEF":
                return BEEF;
            case "HAM":
                return HAM;
            case "BROCCOLI":
                return BROCCOLI;
            case "SPINACH":
                return SPINACH;
            case "JALAPENO":
                return JALAPENO;
            default:
                return null;
        }
    }
}