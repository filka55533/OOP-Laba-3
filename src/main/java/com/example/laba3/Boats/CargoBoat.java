package com.example.laba3.Boats;

import java.util.ArrayList;

public class CargoBoat extends MotorBoat {
    public CargoBoat(){
        super();
        tonnage = 0.0;
    }

    protected double tonnage;

    public void setTonnage(String tonnage) throws Exception {
        double number;
        try{
            number = Double.parseDouble(tonnage);
        }catch (Exception e){
            throw new Exception("Error! Incorrect tonnage value");
        }

        if (number < 0.0)
            throw new Exception("Error! Tonnage should be positive");
        else
            this.tonnage = number;
    }
    public double getTonnage() {
        return tonnage;
    }

    @Override
    public int getCountFields() {
        return super.getCountFields() + 1;
    }

    @Override
    public ArrayList<String[]> getNameItems() {
        ArrayList<String[]> res = super.getNameItems();

        String[] item = new String[2];
        item[0] = "Tonnage";
        item[1] = Double.toString(getTonnage());

        res.add(item);
        return res;
    }

    @Override
    public void setItems(String[] items) throws Exception {
        super.setItems(items);

        if (items.length < getCountFields())
            throw new Exception("Error! Incorrect items count");

        setTonnage(items[super.getCountFields()]);
    }
}
