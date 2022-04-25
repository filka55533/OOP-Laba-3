package com.example.laba3.Boats;

import java.util.ArrayList;

public class WarShip extends MotorBoat {
    public WarShip(){
        super();
        countGuns = 0;
        typeShip = ClassWarship.BATTLESHIP;
    }
    protected int countGuns;
    public void setCountGuns(String countGuns)throws Exception {
        int num;
        try{
            num = Integer.getInteger(countGuns);
        }catch (Exception e){
            throw new Exception("Error! Incorrect value of count guns");
        }

        if (num < 0)
            throw new Exception("Error! Count guns should be a positive number");
        else
            this.countGuns = num;
    }
    public int getCountGuns() {
        return countGuns;
    }

    public String ClassWarshipsToString(ClassWarship type){
        String res = "";

        switch (type){
            case AIRCRAFT_CARRIER -> res = "Aircraft carrier";
            case CRUISER -> res = "Cruiser";
            case DESTROYER -> res = "Destroyer";
            case BATTLESHIP -> res = "Battleship";
        }

        return res;
    }

    protected ClassWarship typeShip;
    public ClassWarship getTypeShip() {
        return typeShip;
    }
    public void setClassShip(String typeShip) throws Exception{
        ClassWarship type;
        typeShip = typeShip.toUpperCase();

        switch (typeShip){
            case "AIRCRAFT", "AIRCRAFT CARRIER" -> type = ClassWarship.AIRCRAFT_CARRIER;
            case "CRUISER" -> type = ClassWarship.CRUISER;
            case "DESTROYER" -> type = ClassWarship.DESTROYER;
            case "BATTLESHIP" -> type = ClassWarship.BATTLESHIP;

            default -> throw new Exception("Error! Incorrect value of the type ship");
        }

        this.typeShip = type;
    }

    @Override
    public int getCountFields() {
        return super.getCountFields() + 2;
    }

    @Override
    public void setItems(String[] items) throws Exception {
        super.setItems(items);

        if (items.length < getCountFields())
            throw new Exception("Error! Incorrect items count");

        int offset = super.getCountFields();
        setCountGuns(items[offset]);
        setClassShip(items[offset + 1]);
    }

    @Override
    public ArrayList<String[]> getNameItems() {
        ArrayList<String[]> res = super.getNameItems();

        for (int i = 0; i < getCountFields() - super.getCountFields(); i++){
            String[] item = new String[2];
            switch (i){
                case 0 -> {
                    item[0] = "Count guns";
                    item[1] = Integer.toString(getCountGuns());
                }
                case 1 -> {
                    item[0] = "Warship class";
                    item[1] = ClassWarshipsToString(getTypeShip());
                }
            }
            res.add(item);
        }

        return res;
    }

    @Override
    public String getType() {
        return "War ship";
    }
}
