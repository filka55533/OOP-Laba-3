package com.example.laba3.Boats;

import java.util.ArrayList;

public class MotorBoat extends Boat {
    public MotorBoat(){
        super();
        engineVolume = 0.0;
    }

    protected double engineVolume;

    @Override
    public int getCountFields() {
        return super.getCountFields() + 1;
    }

    public void setEngineVolume(String engineVolume) throws Exception{
        double num;
        try{
            num = Double.parseDouble(engineVolume);
        }catch (Exception e){
            throw new Exception("Error! Incorrect engine volume value");
        }

        if (num < 0.0)
            throw new Exception("Error! Engine volume should be greater than zero");
        else
            this.engineVolume = num;
    }
    public double getEngineVolume() {
        return engineVolume;
    }

    @Override
    public ArrayList<String[]> getNameItems() {
        ArrayList<String[]> res = super.getNameItems();

        String[] item = new String[2];
        item[0] = "Engine volume";
        item[1] = Double.toString(getEngineVolume());

        res.add(item);
        return res;
    }

    @Override
    public void setItems(String[] items) throws Exception {
        super.setItems(items);

        if (items.length < getCountFields())
            throw new Exception("Error! Incorrect items count");

        setEngineVolume(items[super.getCountFields()]);
    }

    @Override
    public String getType() {
        return "Motor boat";
    }
}
