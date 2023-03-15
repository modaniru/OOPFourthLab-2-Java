package com.example.ooplab42;

import java.io.*;
import java.nio.file.Path;

public class Model {
    private int A;
    private int B;
    private int C;

    private final int MAX_VALUE = 100;
    private final int MIN_VALUE = 0;
    private Path filePath;

    public Model(String path) {
        filePath = Path.of(path);
    }

    private Functional<Integer> eventHandler;

    public void setA(Integer newA) {
        newA = checkValueBound(newA);
        if (newA > C) {
            C = newA;
        }
        if (newA > B) {
            B = newA;
        }
        A = newA;
        eventHandler.accept(A, B, C);
    }

    public void setC(Integer newC) {
        newC = checkValueBound(newC);
        if (newC < A) {
            A = newC;
        }
        if (newC < B) {
            B = newC;
        }
        C = newC;
        eventHandler.accept(A, B, C);
    }

    public void setB(Integer newB) {
        newB = checkValueBound(newB);
        if (newB < A) {
            B = A;
        } else if (newB > C) {
            B = C;
        } else {
            B = newB;
        }
        eventHandler.accept(A, B, C);
    }

    public void setEventHandler(Functional<Integer> eventHandler) {
        this.eventHandler = eventHandler;
    }

    private int checkValueBound(Integer num){
        if(num > MAX_VALUE) return 100;
        if(num < MIN_VALUE) return 0;
        return num;
    }

    public void save(){
        File file = new File(filePath.toString());
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(serialize());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void load(){
        File file = new File(filePath.toString());
        if(file.exists()){
            try(BufferedReader br = new BufferedReader(new FileReader(file))){
                deserialize(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append(A).append(" ").append(B).append(" ").append(C);
        return sb.toString();
    }

    private void deserialize(String s) {
        String[] s1 = s.split(" ");
        setC(Integer.parseInt(s1[2]));
        setB(Integer.parseInt(s1[1]));
        setA(Integer.parseInt(s1[0]));
    }
}
