package com.codecool.service;

import com.codecool.exception.InvalidSudokuException;
import com.codecool.model.Board;
import com.codecool.model.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CorrectnessValidatorTest {
    private Board boardStub;
    private Cell[] cellsStub;
    private final int DUPLICATE_TEST_VALUE = 82;

    @BeforeEach
    void init(){
        cellsStub = new Cell[81];
        for (int i = 0; i < 81; i++) {
            cellsStub[i] = new Cell(i,i,i,i);
        }
        boardStub = new Board(cellsStub);
    }

    @Test
    @DisplayName("Test board for row duplicates")
    public void testRowValidator(){
        boardStub.getCells()[0].setValue(DUPLICATE_TEST_VALUE);
        boardStub.getCells()[4].setValue(DUPLICATE_TEST_VALUE);
        CorrectnessValidator correctnessValidator = new CorrectnessValidator(boardStub);
        Assertions.assertThrows(InvalidSudokuException.class, correctnessValidator::validate);
    }

    @Test
    @DisplayName("Test board for column duplicates")
    public void testColValidator(){
        boardStub.getCells()[7].setValue(DUPLICATE_TEST_VALUE);
        boardStub.getCells()[16].setValue(DUPLICATE_TEST_VALUE);
        CorrectnessValidator correctnessValidator = new CorrectnessValidator(boardStub);
        Assertions.assertThrows(InvalidSudokuException.class, correctnessValidator::validate);
    }


}
