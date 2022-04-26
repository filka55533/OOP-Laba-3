package com.example.laba3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override


    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-window.fxml"));
        //Form initialization
        Parent root = fxmlLoader.load();

        MainController controller = fxmlLoader.getController();
        controller.initForm();

        Scene scene = new Scene(root, 320, 240);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();


    }
}