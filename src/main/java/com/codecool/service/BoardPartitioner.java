package com.codecool.service;

import com.codecool.model.Board;
import com.codecool.model.Cell;

import java.util.ArrayList;
import java.util.List;


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

    public static Cell[] getRow(Board board, int index){
        Cell[] row = new Cell[9];
        for (int i = 0; i <WIDTH; i++) {
            row[i] = getCell(board, index, i);
        }
        return row;
    }

    public static Cell[] getBox(Board board, int id){
        List<Cell> box = new ArrayList<Cell>();
        int xOffset = 3 * (id % 3 );
        int yOffset = 3 * (id / 3);
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                box.add(getCell(board,x + xOffset, y + yOffset));
            }
        }
        return (Cell[]) box.toArray();
    }
}
