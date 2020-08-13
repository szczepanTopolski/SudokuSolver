package com.codecool.service;

import com.codecool.model.Board;
import com.codecool.model.Cell;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class ConverterCSV implements Converter{

    private final int MAX_HEIGTH = 9;
    private final int MAX_WIDTH = 9;

    @Override
    public Board convert(String input) throws IllegalArgumentException{
        if(input == null) throw new IllegalArgumentException();
        String[] rows = input.split("\n");
        checkBoardFullFilledWithRows(rows);
        Stream<String[]> stream = getRows(rows);
        checkRowsFullFilledWIthDigits(stream);
        String[][] values = getRows(rows).toArray(String[][]::new);
        return new Board(fullFillCellsWithValues(values));
    }

    private Cell[] fullFillCellsWithValues(String[][] values) {
        Cell[] cells = new Cell[MAX_HEIGTH * MAX_WIDTH];
        for (int i = 0; i < MAX_HEIGTH; i++) {
            for (int j = 0; j < MAX_WIDTH; j++) {
                int value = Integer.parseInt(values[i][j]);
                int id = i + j * MAX_WIDTH;
                cells[id] = new Cell(id, i, j, value);
                Cell cell = cells[id];
                fillPossibilitiesWhenHasNoValue(value, cell);
            }
        }
        return cells;
    }

    private void fillPossibilitiesWhenHasNoValue(int value, Cell cell) {
        List<Integer> possibilities = Arrays.asList(1,2,3,4,5,6,7,8,9);
        if(value != 0) {
            cell.setPossibilities(new HashSet<>(possibilities));
        }
    }

    private Stream<String[]> getRows(String[] rows) {
        return Arrays.stream(rows)
                .map(row -> row.split(";"));
    }

    private void checkBoardFullFilledWithRows(String[] rows) {
        if(rows.length != 9) throw new IllegalArgumentException();
    }

    private void checkRowsFullFilledWIthDigits(Stream<String[]> stream) {
        if(!stream.allMatch(row-> row.length == 9 &&
            Arrays.stream(row).allMatch(cell-> Character.isDigit(cell.charAt(0))))) throw new IllegalArgumentException();
    }
}
