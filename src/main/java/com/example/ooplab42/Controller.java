package com.example.ooplab42;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;

import java.net.URL;
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
    private final String path = "saves.txt";

    //initialize methods
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initSpinners();
        initModel();
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
        model = new Model("saves.txt");
        model.setEventHandler(this::changeModelEvent);
        model.load();
    }

    //init change value listeners
    public void initChangeValueListeners() {
        aTextBox.focusedProperty().addListener(this::aTextFieldFocusChange);
        bTextBox.focusedProperty().addListener(this::bTextFieldFocusChange);
        cTextBox.focusedProperty().addListener(this::cTextFieldFocusChange);
        aSpinner.valueProperty().addListener(this::aViewChange);
        bSpinner.valueProperty().addListener(this::bViewChange);
        cSpinner.valueProperty().addListener(this::cViewChange);
        aSlider.valueProperty().addListener(this::aViewChange);
        bSlider.valueProperty().addListener(this::bViewChange);
        cSlider.valueProperty().addListener(this::cViewChange);
    }

    private void aViewChange(ObservableValue<? extends Number> o, Number oldValue, Number newValue) {
        model.setA(newValue.intValue());
    }

    private void bViewChange(ObservableValue<? extends Number> o, Number oldValue, Number newValue) {
        model.setB(newValue.intValue());
    }

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

    private void aTextFieldFocusChange(ObservableValue<? extends Boolean> o, Boolean oldValue, Boolean newValue){
        if(!newValue) model.setA(Integer.parseInt(aTextBox.getText()));
    }

    private void bTextFieldFocusChange(ObservableValue<? extends Boolean> o, Boolean oldValue, Boolean newValue){
        if(!newValue) model.setB(Integer.parseInt(bTextBox.getText()));
    }

    private void cTextFieldFocusChange(ObservableValue<? extends Boolean> o, Boolean oldValue, Boolean newValue){
        if(!newValue) model.setC(Integer.parseInt(cTextBox.getText()));
    }

    public void changeModelEvent(Integer A, Integer B, Integer C){
        aText.setText(A.toString());
        aSpinner.getValueFactory().setValue(A);
        aSlider.setValue(A);
        aTextBox.setText(A.toString());
        bText.setText(B.toString());
        bSpinner.getValueFactory().setValue(B);
        bSlider.setValue(B);
        bTextBox.setText(B.toString());
        cText.setText(C.toString());
        cSpinner.getValueFactory().setValue(C);
        cSlider.setValue(C);
        cTextBox.setText(C.toString());
    }

    public void save(WindowEvent windowEvent){
        model.save();
    }
}
