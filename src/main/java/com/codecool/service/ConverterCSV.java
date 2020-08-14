package com.codecool.service;

import com.codecool.model.Board;
import com.codecool.model.Cell;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class ConverterCSV implements Converter{

    @Override
    public Board convert(String[][] values) throws IllegalArgumentException{
        final int MAX_HEIGHT = 9;
        final int MAX_WIDTH = 9;
        Cell[] cells = new Cell[MAX_HEIGHT * MAX_WIDTH];
        for (int i = 0; i < MAX_HEIGHT; i++) {
            for (int j = 0; j < MAX_WIDTH; j++) {
                int value = Integer.parseInt(values[i][j]);
                int id = i + j * MAX_WIDTH;
                cells[id] = new Cell(id, i, j, value);
                Cell cell = cells[id];
                fillPossibilitiesWhenHasNoValue(value, cell);
            }
        }
        return new Board(cells);
    }

    private void fillPossibilitiesWhenHasNoValue(int value, Cell cell) {
        List<Integer> possibilities = Arrays.asList(1,2,3,4,5,6,7,8,9);
        if(value != 0) {
            cell.setPossibilities(new HashSet<>(possibilities));
        }
    }
}
