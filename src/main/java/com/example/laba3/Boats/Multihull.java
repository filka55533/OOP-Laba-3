package com.example.laba3.Boats;

import java.util.ArrayList;

public class Multihull extends SailingYacht {
    public Multihull(){
        super();
        countHull = 2;
    }

    protected int countHull;
    public void setCountHull(String countHull) throws Exception {
        int num;
        try{
            num = Integer.getInteger(countHull);
        }catch (Exception e){
            throw new Exception("Error! Incorrect value about hulls count");
        }
        if (num < 2)
            throw new Exception("Error! Hulls count should be greater than one");
        else
            this.countHull = num;
    }
    public int getCountHull() {
        return countHull;
    }

    @Override
    public int getCountFields() {
        return super.getCountFields() + 1;
    }

    @Override
    public ArrayList<String[]> getNameItems() {
        ArrayList<String[]> res = super.getNameItems();

        String[] field = new String[2];
        field[0] = "Hulls count";
        field[1] = Integer.toString(getCountHull());

        res.add(field);
        return res;
    }

    @Override
    public void setItems(String[] items) throws Exception {
        super.setItems(items);

        if (items.length < getCountFields())
            throw new Exception("Error! Incorrect items count");

        setCountHull(items[super.getCountFields()]);
    }
}