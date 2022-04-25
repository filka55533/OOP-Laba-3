package com.example.laba3.Boats;

import java.util.ArrayList;

public class Monohull extends SailingYacht {
    public Monohull(){
        super();
        draft = 0.0;
    }

    @Override
    public int getCountFields() {
        return super.getCountFields() + 1;
    }


    protected double draft;

    public double getDraft() { return draft;}
    public void setDraft(String draft) throws Exception{
        double number;
        try{
            number = Double.parseDouble(draft);
        }catch (Exception e){
            throw new Exception("Error! Incorrect draft value");
        }

        if (number < 0.0)
            throw new Exception("Error! Draft value should be greater 0");
        else
            this.draft = number;
    }

    @Override
    public ArrayList<String[]> getNameItems() {
        ArrayList<String[]> res = super.getNameItems();
        String[] item = new String[2];
        item[0] = "Draft";
        item[1] = Double.toString(getDraft());
        res.add(item);

        return res;
    }

    @Override
    public void setItems(String[] items) throws Exception {
        super.setItems(items);

        if (items.length < getCountFields())
            throw new Exception("Error! Incorrect count items");

        setDraft(items[super.getCountFields()]);
    }
}
