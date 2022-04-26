package com.example.laba3.Boats;

import java.util.ArrayList;

public abstract class SailingYacht extends Boat {
    public SailingYacht(){
        super();
        sailCount = 1;
        sailsSquare = 0.0;

    }

    protected int sailCount;
    protected double sailsSquare;

    @Override
    public int getCountFields() {
        return super.getCountFields() + 2;
    }

    public void setSailCount(String sailCount) throws Exception{
        int number;
        try{
            number = Integer.parseInt(sailCount);
        }catch (Exception e){
            throw new Exception("Error! Incorrect value of sails count");
        }

        if (number <= 0)
            throw new Exception("Error! Sails count should be greater 0");
        else
            this.sailCount = number;
    }
    public int getSailCount() {
        return sailCount;
    }

    public void setSailsSquare(String sailsSquare) throws Exception{
        double number;
        try{
            number = Double.parseDouble(sailsSquare);
        }catch (Exception e){
            throw new Exception("Error! Incorrect value of sails square");
        }

        if (number <= 0)
            throw new Exception("Error! Sails square should be greater 0");
        else
            this.sailsSquare = number;
    }
    public double getSailsSquare() {
        return sailsSquare;
    }

    @Override
    public ArrayList<String[]> getNameItems() {
        ArrayList<String[]> res = super.getNameItems();

        for (int i = 0; i < 2; i++){
            String[] field = new String[2];
            switch (i){
                case 0 -> {
                    field[0] = "Sails count";
                    field[1] = Integer.toString(getSailCount());
                }
                case 1 -> {
                    field[0] = "Sails square";
                    field[1] = Double.toString(getSailsSquare());
                }
            }
            res.add(field);
        }
        return res;
    }

    @Override
    public void setItems(String[] items) throws Exception {
        super.setItems(items);

        if (items.length < getCountFields())
            throw new Exception("Error! Incorrect fields count");

        int offset = super.getCountFields();
        setSailCount(items[offset]);
        setSailsSquare(items[offset + 1]);
    }

    @Override
    public String getType(){
        return "Sailing yacht";
    }
}
