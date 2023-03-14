package com.example.ooplab42;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

import java.io.*;
import java.lang.ref.PhantomReference;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class App extends Application {
    private Controller controller;
    private final String savePath = "save.txt";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
        Controller controller = fxmlLoader.getController();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ООП Лабораторная 4.2");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e ->{

            Platform.exit();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}