package com.codecool.service;

import com.codecool.model.Board;
import com.codecool.model.Cell;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardPartitionerTest {

    TestUtills testUtills;

    @BeforeEach
    private void init(){
        testUtills = new TestUtills();
    }


    @Test
    @DisplayName("get column")
    void getColumn() {
        Board testBoard = createCellBoard();
        int[] expectedCellsValues = {0,9,18,27,36,45,54,63,72};
        Cell[] actualCells = BoardPartitioner.getColumn(testBoard, 0);
        int[] actualCellsValues = new int[9];
        for (int i = 0; i <actualCells.length; i++) {
            actualCellsValues[i] = actualCells[i].getValue();
        }

        assertArrayEquals(expectedCellsValues, actualCellsValues);
    }

    @Test
    @DisplayName("get mid column")
    void getMidColumn() {
        Board testBoard = testUtills.getBoard();
        int[] expectedCellsValues = {0,0,0,1,6,9,0,0,0};
        Cell[] actualCells = BoardPartitioner.getColumn(testBoard, 4);
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
        int[] expectedCellsValues = {0,1,2,3,4,5,6,7,8};
        Cell[] actualCells = BoardPartitioner.getRow(testBoard, 0);
        int[] actualCellsValues = new int[9];
        for (int i = 0; i <actualCells.length; i++) {
            actualCellsValues[i] = actualCells[i].getValue();
        }

        assertArrayEquals(expectedCellsValues, actualCellsValues);
    }

    @Test
    @DisplayName("get mid row")
    void getRowInTheMiddle(){
        Board testBoard = testUtills.getBoard();
        int[] expectedCellsValues = {9,0,0,8,6,3,0,0,5};
        Cell[] actualCells = BoardPartitioner.getRow(testBoard, 4);
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
        int[] expectedCellsValues = {0,1,2,9,10,11,18,19,20};
        Cell[] actualCells = BoardPartitioner.getBox(testBoard, 0);
        int[] actualCellsValues = new int[9];
        for (int i = 0; i <actualCells.length; i++) {
            actualCellsValues[i] = actualCells[i].getValue();
        }

        assertArrayEquals(expectedCellsValues, actualCellsValues);
    }

    @Test
    @DisplayName("get box In the middle")
    void getMidBoxBox(){
        Board testBoard = testUtills.getBoard();
        int[] expectedCellsValues = {5,0,8,0,0,0,0,0,0};
        Cell[] actualCells = BoardPartitioner.getBox(testBoard, 3);
        int[] actualCellsValues = new int[9];
        for (int i = 0; i <actualCells.length; i++) {
            actualCellsValues[i] = actualCells[i].getValue();
        }

        assertArrayEquals(expectedCellsValues, actualCellsValues);
    }


    private Board createCellBoard(){
        Cell[] cells = new Cell[81];

        for (int i = 0; i < 81 ; i++) {
            cells[i] = new Cell(i, i,i, i);
        }
        return new Board(cells) ;
    }
}