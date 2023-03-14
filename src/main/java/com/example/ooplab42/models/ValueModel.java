package com.example.ooplab42.models;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ValueModel{
    private Consumer<Integer> eventHandler;
    private int value;
    public void setValue(int value){
        this.value = value;
        eventHandler.accept(value);
    }

    public int getValue(){
        return value;
    }

    public void setEvent(Consumer<Integer> runnable){
        eventHandler = runnable;
    }
}
