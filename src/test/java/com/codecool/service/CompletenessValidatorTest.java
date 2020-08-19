package com.codecool.service;

import com.codecool.model.Board;
import org.junit.jupiter.api.Test;

import static com.codecool.service.CompletenessValidator.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CompletenessValidatorTest {
    private  final TestUtills testUtills;

    CompletenessValidatorTest() {
        testUtills = new TestUtills();
    }

    @Test
    public void shouldReturnTrueWhenSudokuIsSolved() {
        // Arrange
        Board board = testUtills.getBoard(true);
        // Act
        // Assert
        assertTrue(isFullFilledWithValues()
                .and(areBoxesValid())
                .and(areColsValid())
                .and(areRowsValid())
                .apply(board));
    }

    @Test
    public void shouldReturnFalseWhenSudokuIsNotSolved() {
        // Arrange
        Board board = testUtills.getBoard(false);
        // Act
        // Assert
        assertFalse(isFullFilledWithValues()
                .and(areBoxesValid())
                .and(areColsValid())
                .and(areRowsValid())
                .apply(board));
    }
}