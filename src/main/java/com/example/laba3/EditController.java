package com.example.laba3;

import com.example.laba3.Boats.Boat;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EditController {
    @FXML
    Button btnEdit;

    @FXML
    Pane pnRoot;


    Label[] labels;
    TextField[] textFields;

    private Boat boat, boatClone;
    public Boat getBoat() {
        return boat;
    }

    public void setBtnEditText(String text){
        btnEdit.setText(text);
    }
    public void initForm(Boat boat) throws CloneNotSupportedException{
        this.boat = boat;
        boatClone = (Boat)boat.clone();

        ArrayList<String[]> items = boat.getNameItems();

        final double vertOffset = 20.0;
        final double horOffset = 20.0;

        double height = Screen.getPrimary().getVisualBounds().getHeight() - 2 * vertOffset;
        double width = Screen.getPrimary().getVisualBounds().getWidth();

        int length = items.size();
        labels = new Label[length];
        textFields = new TextField[length];

        for (int i = 0; i < length; i++){
            double x = horOffset + ((i % 2 == 1) ? width / 2 : 0);
            double y = vertOffset + (i / 2)*height/length;

            String[] item = items.get(i);
            labels[i] = createLabel(item[0], x, y);
            textFields[i] = createTextField(x + item[0].length() * 18 + horOffset, y + vertOffset / 2, item[1]);

            pnRoot.getChildren().add(labels[i]);
            pnRoot.getChildren().add(textFields[i]);
        }

        btnEdit.setLayoutX(horOffset);
        height = (length / 2 + (length % 2 == 1 ? 1 : 0)) * height / length ;
        btnEdit.setLayoutY(height);
        btnEdit.setOnMouseClicked(mouseEvent -> onBtnEditClick());
    }

    private Label createLabel(String text, double x, double y){
        Label res = new Label(text + ":");
        res.setStyle("-fx-font-size: 28;");
        res.setTextFill(Color.LIGHTGRAY);

        res.setLayoutX(x);
        res.setLayoutY(y);
        return res;
    }

    private TextField createTextField(double x, double y, String text){
        TextField res = new TextField();

        res.setStyle("-fx-font-size: 15");
        res.setLayoutY(y);
        res.setLayoutX(x);
        res.setText(text);

        return res;
    }

    public void onBtnEditClick() {
        try{
            String[] items = new String[textFields.length];
            for (int i = 0; i < textFields.length; i ++)
                items[i] = textFields[i].getText();

            boatClone.setItems(items);
            boat = boatClone;
            ((Stage)btnEdit.getScene().getWindow()).close();

        }catch (Exception e){
            System.out.println(e.getMessage());
            showErrorMessage(e.getMessage());
            boat = null;
        }
    }

    public static void showErrorMessage(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(text);
        alert.showAndWait().ifPresent( rs -> {});
    }
}
