package com.codecool.service;

import com.codecool.model.Board;
import com.codecool.model.Cell;

import java.util.Arrays;
import java.util.stream.Stream;

public class ConverterCSV implements Converter{

    @Override
    public Board convert(String[][] values) throws IllegalArgumentException{
        final int MAX_HEIGHT = 9;
        final int MAX_WIDTH = 9;
        Cell[] cells = new Cell[MAX_HEIGHT * MAX_WIDTH];
        for (int i = 0; i < MAX_HEIGHT; i++) {
            for (int j = 0; j < MAX_WIDTH; j++) {
                cells[i+j* MAX_WIDTH] = new Cell(i,j, Integer.parseInt(values[i][j]));
            }
        }
        return new Board(cells);
    }

}
