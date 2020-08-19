package com.codecool.service;

import com.codecool.model.Board;
import com.codecool.model.Cell;

import java.util.ArrayList;
import java.util.List;

import static com.codecool.service.Properties.*;


public  class BoardPartitioner {

    public static Cell getCell(Board board, int x, int y) {
        return board.getCells()[y * MAX_WIDTH.getValue() + x];
    }

    public static Cell[] getColumn(Board board, int x){
        Cell[] column = new Cell[9];
        for (int y = 0; y < MAX_HEIGHT.getValue(); y++) {
            column[y] = getCell(board, x, y);;
        }
        return column;
    }

    public static Cell[] getRow(Board board, int y){
        Cell[] row = new Cell[9];
        for (int x = 0; x < MAX_WIDTH.getValue(); x++) {
            row[x] = getCell(board, x, y);
        }
        return row;
    }

    public static Cell[] getBoxByCellId(Board board, int id){
        int idOfSquare = changeCellIdToSquareId(board.getCells()[id]);
        return getBox(board, idOfSquare);
    }


    public static Cell[] getBox(Board board, int idOfSquare) {
        List<Cell> box = new ArrayList<Cell>();
        int xOffset = 3 * (idOfSquare % 3);
        int yOffset = 3 * (idOfSquare / 3);
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                box.add(getCell(board, x + xOffset, y + yOffset));
            }
        }
        return box.toArray(new Cell[0]);
    }

    private static int changeCellIdToSquareId(Cell cell){
        int x = cell.getX();
        int y = cell.getY();
              if (x >= 0 && x <=2 && y >= 0 && y <=2){
            return 0;
        }else if (x >= 3 && x <=5 && y >= 0 && y <=2){
            return 1;
        }else if (x >= 6 && x <=8 && y >= 0 && y <=2){
            return 2;
        }else if (x >= 0 && x <=2 && y >= 3 && y <=5){
            return 3;
        }else if (x >= 3 && x <=5 && y >= 3 && y <=5){
            return 4;
        }else if (x >= 6 && x <=8 && y >= 3 && y <=5){
            return 5;
        }else if (x >= 0 && x <=2 && y >= 6 && y <=8){
            return 6;
        }else if (x >= 3 && x <=5 && y >= 6 && y <=8){
            return 7;
        }else if (x >= 6 && x <=8 && y >= 6 && y <=8){
            return 8;
        }
        //null
        return -1;
    }
}
