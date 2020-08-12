package com.codecool.service;

import com.codecool.model.Board;
import com.codecool.model.Cell;

import java.util.Arrays;
import java.util.stream.Stream;

public class BoardPartitioner {
    private final int width = 9;
    Cell[] cells;

    public BoardPartitioner(Board board) {
        this.cells = board.getCells();
    }

    public Cell getCell(int x, int y) {
        return cells[y * width + x];
    }

    public Cell[] getColumn(int index){
        Cell[] column = new Cell[8];
        for (int i = 0; i <cells.length ; i++) {
            column[i] = getCell(index, i);;
        }
        return column;
    }
}
