package com.codecool.service;

import com.codecool.model.Board;
import com.codecool.model.Cell;

import java.util.Arrays;
import java.util.HashSet;

public class TestUtills {

    public Board getBoard(boolean solved) {
        int[][] values = solved ? getSolvedValues() : getValues();
        Cell[] cells = new Cell[81];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int id = i + j * 9;
                cells[id] = new Cell(id, i, j , values[j][i]);
                fillWithPossibilitiesWhenHasNoValue(cells[id]);
            }
        }
        return new Board(cells);
    }

    private int[][] getValues() {
        return new int[][]{ {3, 0, 6, 5, 0, 8, 4, 0, 0},
                            {5, 2, 0, 0, 0, 0, 0, 0, 0},
                            {0, 8, 7, 0, 0, 0, 0, 3, 1},
                            {0, 0, 3, 0, 1, 0, 0, 8, 0},
                            {9, 0, 0, 8, 6, 3, 0, 0, 5},
                            {0, 5, 0, 0, 9, 0, 6, 0, 0},
                            {1, 3, 0, 0, 0, 0, 2, 5, 0},
                            {0, 0, 0, 0, 0, 0, 0, 7, 4},
                            {0, 0, 5, 2, 0, 6, 3, 0, 0}};
    }

private int[][] getSolvedValues() {
    return new int[][]{ {3, 1, 6, 5, 7, 8, 4, 9, 2},
            {5, 2, 9, 1, 3, 4, 7, 6, 8},
            {4, 8, 7, 6, 2, 9, 5, 3, 1},
            {2, 6, 3, 4, 1, 5, 9, 8, 7},
            {9, 7, 4, 8, 6, 3, 1, 2, 5},
            {8, 5, 1, 7, 9, 2, 6, 4, 3},
            {1, 3, 8, 9, 4, 7, 2, 5, 6},
            {6, 9, 2, 3, 5, 1, 8, 7, 4},
            {7, 4, 5, 2, 8, 6, 3, 1, 9}};
}
    private void fillWithPossibilitiesWhenHasNoValue(Cell cell){
        if(cell.getValue() == 0)
            cell.setPossibilities(new HashSet<>(Arrays.asList(1,2,3,4,5,6,7,8,9)));
    }
}
