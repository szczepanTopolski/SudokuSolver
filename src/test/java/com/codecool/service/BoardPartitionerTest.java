package com.codecool.service;

import com.codecool.model.Board;
import com.codecool.model.Cell;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardPartitionerTest {
    @Test
    @DisplayName("get column")
    void getColumn() {
        Board testBoard = createCellBoard();
        int[] expectedCellsValues = {10,19,28,37,46,55,64,73,82};
        Cell[] actualCells = BoardPartitioner.getColumn(testBoard, 0);
        int[] actualCellsValues = new int[9];
        for (int i = 0; i <actualCells.length; i++) {
            actualCellsValues[i] = actualCells[i].getValue();
        }

        assertArrayEquals(expectedCellsValues, actualCellsValues);
    }
    @Test
    @DisplayName("get row")
    void getRow(){
        Board testBoard = createCellBoard();
        int[] expectedCellsValues = {10,11,12,13,14,15,16,17,18};
        Cell[] actualCells = BoardPartitioner.getRow(testBoard, 0);
        int[] actualCellsValues = new int[9];
        for (int i = 0; i <actualCells.length; i++) {
            actualCellsValues[i] = actualCells[i].getValue();
        }

        assertArrayEquals(expectedCellsValues, actualCellsValues);
    }
    @Test
    @DisplayName("get box")
    void getBox(){
        Board testBoard = createCellBoard();
        int[] expectedCellsValues = {10,11,12,19,20,21,28,29,30};
        Cell[] actualCells = BoardPartitioner.getBox(testBoard, 0);
        int[] actualCellsValues = new int[9];
        for (int i = 0; i <actualCells.length; i++) {
            actualCellsValues[i] = actualCells[i].getValue();
        }

        assertArrayEquals(expectedCellsValues, actualCellsValues);
    }


    private Board createCellBoard(){
        Cell[] cells = new Cell[81];

        for (int i = 0; i < 81 ; i++) {
            cells[i] = new Cell(i, i,i, i+10);
        }
        return new Board(cells) ;
    }
}