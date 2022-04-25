package com.example.laba3.Boats;


import java.util.ArrayList;

public abstract class Boat {
    private static final int COUNT_FIELDS = 4;
    public int getCountFields(){
        return COUNT_FIELDS;
    }

    protected double length, weight;
    protected int crewCount;
    protected String name;

    public Boat(){
        length = 0;
        weight = 0;
        crewCount = 1;
        name = "";
    }

    public double getLength() {
        return length;
    }

    public double getWeight() {
        return weight;
    }

    public int getCrewCount() {
        return crewCount;
    }

    public void setCrewCount(String crewCount) throws Exception{
        int numberCrew;
        try{
            numberCrew = Integer.getInteger(crewCount);

        }catch (Exception e){
            throw new Exception("Error! Incorrect value in crew count field");
        }

        if (numberCrew > 0)
            this.crewCount = numberCrew;
        else
            throw new Exception("Error! Value in crew count field should be greater than zero");
    }

    public void setLength(String length) throws Exception {
        double number;
        try{
            number = Double.parseDouble(length);
        }catch (Exception e){
            throw new Exception("Error! Incorrect value in length field");
        }

        if (number < 0.0)
            throw new Exception("Error! Value of length should be greater 0");
        else
            this.length = number;
    }

    public void setWeight(String weight) throws Exception{
        double number;
        try{
            number = Double.parseDouble(weight);
        }catch (Exception e){
            throw new Exception("Error! Incorrect value in weight field");
        }

        if (number < 0.0)
            throw new Exception("Error! Value of weight should be greater 0");
        else
            this.weight = number;
    }

    public void setName(String name) throws Exception {
        if (name.equals(""))
            throw new Exception("Error! Name can not be empty");
        else
            this.name = name;
    }

    public String getName() {
        return name;
    }

    //[0] - field's name
    //[1] - field value
    public ArrayList<String[]> getNameItems(){
        ArrayList<String[]> res = new ArrayList<>();
        for (int i = 0; i < getCountFields(); i++){
            String[] field = new String[2];
            switch (i){
                case 0 -> {
                    field[0] = "Length";
                    field[1] = Double.toString(getLength());
                }
                case 1 -> {
                    field[0] = "Weight";
                    field[1] = Double.toString(getWeight());
                }
                case 2 -> {
                    field[0] = "Crew count";
                    field[1] = Integer.toString(getCrewCount());
                }
                case 3 -> {
                    field[0] = "Boat name";
                    field[1] = getName();
                }
            }
            res.add(field);
        }

        return res;
    }

    public void setItems(String[] items) throws Exception{
        if (items.length < COUNT_FIELDS)
            throw new Exception("Error! Incorrect count of items");

        setLength(items[0]);
        setWeight(items[1]);
        setCrewCount(items[2]);
        setName(items[3]);

    }

    public String getType(){
        return "Boat";
    }
}
