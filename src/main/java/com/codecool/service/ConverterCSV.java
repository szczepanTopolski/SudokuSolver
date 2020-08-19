package com.codecool.service;

import com.codecool.model.Board;
import com.codecool.model.Cell;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.codecool.service.Properties.MAX_HEIGHT;
import static com.codecool.service.Properties.MAX_WIDTH;

public class ConverterCSV implements Converter{

    @Override
    public Board convert(String[][] values) throws IllegalArgumentException{
        Cell[] cells = new Cell[MAX_HEIGHT.getValue() * MAX_WIDTH.getValue()];
        for (int i = 0; i < MAX_HEIGHT.getValue(); i++) {
            for (int j = 0; j < MAX_WIDTH.getValue(); j++) {
                int value = Integer.parseInt(values[i][j]);
                int id = i + j * MAX_WIDTH.getValue();
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
