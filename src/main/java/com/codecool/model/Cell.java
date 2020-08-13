package com.codecool.model;

import java.util.HashSet;
import java.util.Set;

public class Cell {
    private final int id;
    private final int x;
    private final int y;
    private int value;
    private Set<Integer> possibilities;

    public Cell(int id, int x, int y, int value) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getId() {
        return id;
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

    public Set<Integer> getPossibilities() {
        return possibilities;
    }

    public void setPossibilities(Set<Integer> possibilities) {
        this.possibilities = possibilities;
    }

    public void addPossibility(int value){
        this.possibilities.add(value);
    }

    public void removePossibility(int value){
        this.possibilities.remove(value);
    }
}
