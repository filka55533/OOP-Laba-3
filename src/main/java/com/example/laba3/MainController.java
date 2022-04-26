package com.example.laba3;

import com.example.laba3.Boats.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainController {

    //Offsets for gui
    private final double VERTICAL_OFFSET = 50.0;
    private final double HORIZONTAL_OFFSET = 50.0;
    private final int COUNT_COLUMNS = 5;

    BoatCreateDelegate dlCreateBoat;
    Boat boatToProcess;

    @FXML
    MenuButton mbType;

    @FXML
    private TableView<Boat> tvObjectsTable;

    @FXML
    private TableColumn<Boat, String> clmName, clmType, clmLength, clmWeight, clmCrewCount;

    private ObservableList<Boat> olBoatsList;


    //Method for initializing form
    public void initForm(){
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        //Initialization of table
        tvObjectsTable.setLayoutY(VERTICAL_OFFSET);
        tvObjectsTable.setLayoutX(HORIZONTAL_OFFSET);
        tvObjectsTable.setPrefHeight(screenHeight / 2);

        screenWidth = screenWidth - 2 * HORIZONTAL_OFFSET;
        tvObjectsTable.setPrefWidth(screenWidth );

        for (TableColumn i: tvObjectsTable.getColumns()){
            i.setPrefWidth(screenWidth / COUNT_COLUMNS);
        }

        //Initialization choosing boat
        dlCreateBoat = Multihull::new;
        initColumns();

        //Deserialize boats from file
        olBoatsList = new BSONSerializer().toDeserializeBoats();
        tvObjectsTable.setItems(olBoatsList);

    }


    private void initColumns(){

        clmName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        clmCrewCount.setCellValueFactory(c -> new SimpleStringProperty(Integer.toString(c.getValue().getCrewCount())));
        clmLength.setCellValueFactory(c -> new SimpleStringProperty(Double.toString(c.getValue().getLength())));
        clmWeight.setCellValueFactory(c -> new SimpleStringProperty(Double.toString(c.getValue().getWeight())));
        clmType.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getType()));

    }


    //Set delegates on click
    @FXML
    private void onMultihullBtn(){
        dlCreateBoat = Multihull::new;
        mbType.setText("Multihull");
    }


    @FXML
    private void onMonohullBtn(){
        dlCreateBoat = Monohull::new;
        mbType.setText("Monohull");
    }


    @FXML
    private void onMotorboatBtn(){
        dlCreateBoat = MotorBoat::new;
        mbType.setText("Motor boat");
    }


    @FXML
    private void onWarshipBtn(){
        dlCreateBoat = WarShip::new;
        mbType.setText("War ship");
    }


    @FXML
    private void onCargoBoatBtn(){
        dlCreateBoat = CargoBoat::new;
        mbType.setText("Cargo boat");
    }


    @FXML
    private void onAddBtnClick() {
        openForm("Add", dlCreateBoat.initializing());
        if (boatToProcess != null) {
            olBoatsList.add(boatToProcess);
            tvObjectsTable.refresh();
        }
    }


    @FXML
    private void onEditBtnClick(){
        int index = tvObjectsTable.getSelectionModel().getSelectedIndex();
        if (index < 0)
            EditController.showErrorMessage("Index not selected");
        else{
            openForm("Edit", olBoatsList.get(index));
            if (boatToProcess != null){
                olBoatsList.remove(index);
                olBoatsList.add(index, boatToProcess);
                tvObjectsTable.refresh();
            }
        }
    }


    @FXML
    private void onDeleteBtnClick(){
        int index = tvObjectsTable.getSelectionModel().getSelectedIndex();
        if (index >= 0){
            olBoatsList.remove(index);
            tvObjectsTable.refresh();
        }
    }


    @FXML
    private void onSerializeBtnClick(){
        BSONSerializer processor = new BSONSerializer();

        try{
            processor.toSerializeBoats(olBoatsList);
        }catch (Exception e){
            e.printStackTrace();
            EditController.showErrorMessage("Unknown error in serializing");
        }
    }


    //Controller for editor form
    EditController editController;
    private void openForm(String btnText, Boat boat){

        try {

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("edit-form.fxml"));
            Parent root = fxmlLoader.load();

            //Initialization of the form
            editController = fxmlLoader.getController();
            editController.setBtnEditText(btnText);
            editController.initForm(boat);

            stage.setTitle(btnText + " boat");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setOnCloseRequest( windowEvent -> onCloseForm() );
            stage.showAndWait();

            boatToProcess = editController.getBoat();

        }catch (Exception e){

            e.printStackTrace();

        }

    }


    private void onCloseForm(){
        boatToProcess = editController.getBoat();
    }

}