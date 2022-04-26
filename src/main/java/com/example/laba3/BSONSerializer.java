package com.example.laba3;

import com.example.laba3.Boats.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.*;
import org.bson.io.BasicOutputBuffer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BSONSerializer {

    private final String DOCUMENT_NAME = "boats.bson";

    private final Map<String, BoatCreateDelegate> objectsConstructors;
    public BSONSerializer(){
        objectsConstructors = new HashMap<>();

        objectsConstructors.put(new CargoBoat().getType(), CargoBoat::new);
        objectsConstructors.put(new Monohull().getType(), Monohull::new);
        objectsConstructors.put(new MotorBoat().getType(), MotorBoat::new);
        objectsConstructors.put(new Multihull().getType(), Multihull::new);
        objectsConstructors.put(new WarShip().getType(), WarShip::new);

    }

    public void toSerializeBoats(ObservableList<Boat> boatsList) throws IOException {

        final BasicOutputBuffer buffer = new BasicOutputBuffer();
        final BsonWriter writer = new BsonBinaryWriter(buffer);

        writer.writeStartDocument();
        for (Boat i : boatsList) {

            //Write type of the ship
            writer.writeName("Type:");
            writer.writeString(i.getType());

            for (String[] item : i.getNameItems()) {
                writer.writeName(item[0]);
                writer.writeString(item[1]);
            }

        }
        writer.writeEndDocument();

        System.out.println("Ok");

        File file = new File(DOCUMENT_NAME);
        FileOutputStream stream = new FileOutputStream(file);
        stream.write(buffer.getInternalBuffer());
        stream.close();

    }

    public ObservableList<Boat> toDeserializeBoats(){
        ObservableList<Boat> res = FXCollections.observableArrayList();

        if (Files.notExists(Paths.get(DOCUMENT_NAME)))
            return res;

        BasicOutputBuffer buffer = new BasicOutputBuffer();

        try{

            buffer.writeBytes(Files.readAllBytes(Paths.get(DOCUMENT_NAME)));
            BsonBinaryReader reader = new BsonBinaryReader(ByteBuffer.wrap(buffer.toByteArray()));


            boolean isNotEnd = true;
            reader.readStartDocument();
            while (reader.readBsonType() != BsonType.END_OF_DOCUMENT){


                String type = reader.readString();
                Boat boat = objectsConstructors.get(type).initializing();

                ArrayList<String> items = new ArrayList<>();
                for (int i = 0; i < boat.getCountFields() && reader.readBsonType() != BsonType.END_OF_DOCUMENT; i++){
                    items.add(reader.readString());
                }


                try{
                    boat.setItems(items.toArray(new String[items.size()]));
                    res.add(boat);
                }catch (Exception e) {
                    System.out.println("Error in getting array");
                }

            }
            reader.readEndDocument();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Oops");
        }

        return res;
    }
}
