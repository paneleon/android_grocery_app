package com.example.grocery_bud;

import java.util.HashMap;

public class FoodCategoryItems {

    // class that stores HashMaps of all items by category
    // key is the item, value is the price of the item
    public static HashMap<String, Number> fruitsVegetables = new HashMap<String, Number>(){{
        put("apple",1.10);
        put("pineapple", 2.20);
        put("orange",1.50);
        put("peach", 1.30);
        put("banana",1.20);
        put("grapes",2.10);
        put("carrot",1.00);
        put("tomato",1.10);
        put("cucumber", 1.20);
        put("onion",0.60);
    }};

    public static HashMap<String, Number> grainsCereals = new HashMap<String, Number>(){{
        put("rice",1.10);
        put("oats", 1.20);
        put("barley",1.10);
        put("corn", 1.30);
        put("chia",2.20);
        put("quinoa",2.00);
        put("rye",1.10);
    }};

    public static HashMap<String, Number> meatSeaPoultry = new HashMap<String, Number>(){{
        put("beef",5.10);
        put("lamb", 6.20);
        put("pork", 5.10);
        put("sausage", 3.30);
        put("bacon", 2.20);
        put("chicken", 3.00);
        put("turkey", 3.10);
        put("duck", 4.20);
        put("fish", 6.00);
        put("crab", 10.10);
        put("oysters", 12.00);
        put("eggs", 3.10);
    }};


    public static HashMap<String, Number> dairy = new HashMap<String, Number>(){{
        put("milk", 1.20);
        put("yogurt", 0.80);
        put("cheese", 2.70);
        put("sour cream", 1.80);
        put("cream", 2.90);
        put("butter", 3.10);
        put("kefir", 1.10);
    }};

    public static HashMap<String, Number> baked = new HashMap<String, Number>(){{
        put("bread", 1.00);
        put("croissant", 2.00);
        put("bagel", 1.10);
        put("bun", 0.70);
        put("buiscuit", 1.20);
        put("cheesecake", 7.00);
        put("brownie", 6.10);
        put("muffin", 2.00);
        put("doughnut", 1.60);
    }};
}
