package com.codecool.service;

import com.codecool.model.Board;
import com.codecool.service.TestUtills.State;
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
        Board board = testUtills.getBoard(State.SOLVED);
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
        Board board = testUtills.getBoard(State.VALID);
        // Act
        // Assert
        assertFalse(isFullFilledWithValues()
                .and(areBoxesValid())
                .and(areColsValid())
                .and(areRowsValid())
                .apply(board));
    }

    @Test
    public void shouldReturnFalseWhenSudokuIsNotValid() {
        // Arrange
        Board board = testUtills.getBoard(State.INVALID);
        // Act
        // Assert
        assertFalse(isFullFilledWithValues()
                .and(areBoxesValid())
                .and(areColsValid())
                .and(areRowsValid())
                .apply(board));
    }
}