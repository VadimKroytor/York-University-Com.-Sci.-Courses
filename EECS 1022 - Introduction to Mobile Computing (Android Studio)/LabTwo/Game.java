package com.example.vadim.caps;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


import ca.roumani.i2c.Country;
import ca.roumani.i2c.CountryDB;

public class Game {

    private CountryDB db;

    public Game(){

        this.db = new CountryDB();

    }

    public String qa(){

        List<String> capitals = db.getCapitals();
        int n = capitals.size();
        double index =  Math.random() * (double) n;
        String c = capitals.get((int) index);
        Map<String, Country> data = db.getData();
        Country ref = data.get(c);

        String country = ref.getName();
        String capital = ref.getCapital();
        String output;

        if(Math.random() < 0.5){
            output = "What is the capital of " + country + "?\n";
        }else{
            output = capital + " is the capital of?\n";
        }
        return output;
    }
    public static void main(String[] args) {
        Game wow = new Game();
        String biscuits = wow.qa();
        //System.out.println(biscuits);
        int index = 0;
    }
    }
