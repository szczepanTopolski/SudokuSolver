package com.codecool.service;

import com.codecool.model.Board;
import com.codecool.model.Cell;


public  class BoardPartitioner {
    private static final int WIDTH = 9;
    private static final int HEIGHT = 9;



    public static Cell getCell(Board board, int x, int y) {
        return board.getCells()[y * WIDTH + x];
    }

    public static Cell[] getColumn(Board board, int index){
        Cell[] column = new Cell[9];
        for (int i = 0; i <HEIGHT; i++) {
            column[i] = getCell(board, index, i);;
        }
        return column;
    }
}
