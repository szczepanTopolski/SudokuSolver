package com.codecool.service;

import com.codecool.model.Board;
import com.codecool.model.Cell;


public  class BoardPartitioner {
    private static final int width = 9;



    public static Cell getCell(Board board, int x, int y) {
        return board.getCells()[y * width + x];
    }

    public static Cell[] getColumn(Board board, int index){
        Cell[] column = new Cell[9];
        for (int i = 0; i <board.getCells().length ; i++) {
            column[i] = getCell(board, index, i);;
        }
        return column;
    }
}
