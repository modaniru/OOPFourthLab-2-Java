package com.example.ooplab42;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialize numeric
        initSpinners();
        //init model change events
        model = new Model();
        model.setEventHandlerA(this::changeA);
        model.setEventHandlerB(this::changeB);
        model.setEventHandlerC(this::changeC);
        //
        aSpinner.valueProperty().addListener(((observableValue, integer, t1) -> {
            model.setA(t1);
        }));
        bSpinner.valueProperty().addListener(((observableValue, integer, t1) -> {
            model.setB(t1);
        }));
        cSpinner.valueProperty().addListener(((observableValue, integer, t1) -> {
            model.setC(t1);
        }));
        aSlider.valueProperty().addListener(((observableValue, number, t1) -> {
            model.setA(t1.intValue());
        }));
        bSlider.valueProperty().addListener(((observableValue, number, t1) -> {
            model.setB(t1.intValue());
        }));
        cSlider.valueProperty().addListener(((observableValue, number, t1) -> {
            model.setC(t1.intValue());
        }));
    }


    public void aTextFieldEntered(ActionEvent actionEvent){
        model.setA(Integer.parseInt(aTextBox.getText()));
    }

    public void bTextFieldEntered(ActionEvent actionEvent){
        model.setB(Integer.parseInt(bTextBox.getText()));
    }

    public void cTextFieldEntered(ActionEvent actionEvent){
        model.setC(Integer.parseInt(cTextBox.getText()));
    }
    private void changeA(Integer A){
        aText.setText(A.toString());
        aSpinner.getValueFactory().setValue(A);
        aSlider.setValue(A);
        aTextBox.setText(A.toString());
    }

    private void changeB(Integer B){
        bText.setText(B.toString());
        bSpinner.getValueFactory().setValue(B);
        bSlider.setValue(B);
        bTextBox.setText(B.toString());
    }

    private void changeC(Integer C){
        cText.setText(C.toString());
        cSpinner.getValueFactory().setValue(C);
        cSlider.setValue(C);
        cTextBox.setText(C.toString());
    }

    private void initSpinners(){
        aSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
        bSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
        cSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
    }
}
