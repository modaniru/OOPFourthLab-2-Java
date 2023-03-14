package com.example.ooplab42;

import java.io.File;
import java.util.Arrays;
import java.util.function.Consumer;

public class Model {
    private int A;
    private int B;
    private int C;

    public Model(int a, int b, int c) {
        A = a;
        B = b;
        C = c;
    }

    public Model() {
    }

    private Consumer<Integer> eventHandlerA;
    private Consumer<Integer> eventHandlerB;
    private Consumer<Integer> eventHandlerC;

    public void setA(Integer newA){
        if(newA > C){
            setC(newA);
        }
        if(newA > B){
            setB(newA);
        }
        A = newA;
        eventHandlerA.accept(A);
    }

    public void setC(Integer newC){
        if(newC < A){
            setA(newC);
        }
        if(newC < B){
            setB(newC);
        }
        C = newC;
        eventHandlerC.accept(C);
    }

    public void setB(Integer newB){
        if(newB < A){
            B = A;
        }
        else if(newB > C){
            B = C;
        }
        else{
            B = newB;
        }
        eventHandlerB.accept(B);
    }

    public void setEventHandlerA(Consumer<Integer> eventHandlerA) {
        this.eventHandlerA = eventHandlerA;
    }

    public void setEventHandlerB(Consumer<Integer> eventHandlerB) {
        this.eventHandlerB = eventHandlerB;
    }

    public void setEventHandlerC(Consumer<Integer> eventHandlerC) {
        this.eventHandlerC = eventHandlerC;
    }

    public String serialize(){
        StringBuilder sb = new StringBuilder();
        sb.append(A).append(" ").append(B).append(" ").append(C);
        return sb.toString();
    }

    public void deserialize(String s){
        String[] s1 = s.split(" ");
        setC(Integer.parseInt(s1[2]));
        setB(Integer.parseInt(s1[1]));
        setA(Integer.parseInt(s1[0]));
    }
}
