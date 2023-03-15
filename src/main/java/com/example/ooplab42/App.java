package com.example.ooplab42;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ООП Лабораторная 4.2");
        stage.setScene(scene);
        stage.show();
        Controller controller = fxmlLoader.getController();
        stage.setOnCloseRequest(controller::save);
    }

    public static void main(String[] args) {
        launch();
    }
}