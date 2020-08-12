package com.codecool.model;

import java.util.HashSet;
import java.util.Set;

public class Cell {
    private final int x;
    private final int y;
    private int value;
    private final Set<Integer> possibilities;

    public Cell(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.possibilities = new HashSet<>();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addPossibility(int value){
        this.possibilities.add(value);
    }

    public void removePossibility(int value){
        this.possibilities.remove(value);
    }
}
