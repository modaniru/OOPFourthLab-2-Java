package com.example.ooplab42;

import com.example.ooplab42.models.ValueModel;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Spinner<Integer> spinner;
    @FXML
    private TextField textBox1;
    @FXML
    private Slider slider;
    private ValueModel model;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialize numeric
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
        spinner.valueProperty().addListener(this::numericValueChanged);
        //initialize model
        model = new ValueModel();
        model.setEvent(this::changeModel);
        //init text box
        textBox1.textProperty().addListener(this::textBoxChangeEvent);
        //init slider
        slider.valueProperty().addListener(this::sliderChangeEvent);
    }

    private void sliderChangeEvent(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
        model.setValue(newValue.intValue());
    }

    private void textBoxChangeEvent(ObservableValue<? extends String> observableValue, String oldValue, String newValue){
        model.setValue(Integer.parseInt(newValue));
    }

    private void numericValueChanged(ObservableValue<? extends Integer> observableValue, Integer oldValue, Integer newValue) {
        model.setValue(newValue);
    }

    //model change event
    private void changeModel(Integer value){
        spinner.getValueFactory().setValue(value);
        textBox1.setText(value.toString());
        slider.setValue(value);
    }
}
