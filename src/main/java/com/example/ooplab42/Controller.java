package com.example.ooplab42;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Spinner<Integer> aSpinner;
    @FXML
    private TextField aTextBox;
    @FXML
    private Slider aSlider;
    @FXML
    private Spinner<Integer> bSpinner;
    @FXML
    private TextField bTextBox;
    @FXML
    private Slider bSlider;
    @FXML
    private Spinner<Integer> cSpinner;
    @FXML
    private TextField cTextBox;
    @FXML
    private Slider cSlider;
    @FXML
    private Text aText;
    @FXML
    private Text bText;
    @FXML
    private Text cText;

    private Model model;

    //initialize methods
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initSpinners();
        initModel();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("saves.txt"))) {
            model.deserialize(bufferedReader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        initChangeValueListeners();
    }

    //init spinners
    private void initSpinners() {
        aSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
        bSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
        cSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
    }

    //init model
    public void initModel() {
        model = new Model();
        model.setEventHandlerA(this::changeA);
        model.setEventHandlerB(this::changeB);
        model.setEventHandlerC(this::changeC);
    }

    //init change value listeners
    public void initChangeValueListeners() {
        aSpinner.valueProperty().addListener(this::aViewChange);
        bSpinner.valueProperty().addListener(this::bViewChange);
        cSpinner.valueProperty().addListener(this::cViewChange);
        aSlider.valueProperty().addListener(this::aViewChange);
        bSlider.valueProperty().addListener(this::bViewChange);
        cSlider.valueProperty().addListener(this::cViewChange);
    }

    //Если изменяется какая-нибудь вьюха числа A
    private void aViewChange(ObservableValue<? extends Number> o, Number oldValue, Number newValue) {
        model.setA(newValue.intValue());
    }

    //Если изменяется какая-нибудь вьюха числа B
    private void bViewChange(ObservableValue<? extends Number> o, Number oldValue, Number newValue) {
        model.setB(newValue.intValue());
    }

    //Если изменяется какая-нибудь вьюха числа C
    private void cViewChange(ObservableValue<? extends Number> o, Number oldValue, Number newValue) {
        model.setC(newValue.intValue());
    }

    public void aTextFieldEntered(ActionEvent actionEvent) {
        model.setA(Integer.parseInt(aTextBox.getText()));
    }

    public void bTextFieldEntered(ActionEvent actionEvent) {
        model.setB(Integer.parseInt(bTextBox.getText()));
    }

    public void cTextFieldEntered(ActionEvent actionEvent) {
        model.setC(Integer.parseInt(cTextBox.getText()));
    }

    //model change events init
    private void changeA(Integer A) {
        aText.setText(A.toString());
        aSpinner.getValueFactory().setValue(A);
        aSlider.setValue(A);
        aTextBox.setText(A.toString());
    }

    private void changeB(Integer B) {
        bText.setText(B.toString());
        bSpinner.getValueFactory().setValue(B);
        bSlider.setValue(B);
        bTextBox.setText(B.toString());
    }

    private void changeC(Integer C) {
        cText.setText(C.toString());
        cSpinner.getValueFactory().setValue(C);
        cSlider.setValue(C);
        cTextBox.setText(C.toString());
    }
    public void save(ActionEvent actionEvent) throws IOException {
        File file = new File("saves.txt");
        if(file.exists()) file.delete();
        file.createNewFile();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(model.serialize());
        }
    }

}
