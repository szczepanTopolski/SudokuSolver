package com.codecool.service;

import com.codecool.model.Board;
import com.codecool.model.Cell;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static com.codecool.service.TestUtills.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PossibilitiesValidatorTest {

    private final  TestUtills testUtills;

    PossibilitiesValidatorTest() {
        testUtills = new TestUtills();
    }

    @ParameterizedTest
    @MethodSource("possibilitiesProvider")
    public void shouldRemovePossibilityValueWhenFoundAnother(Cell cell, Cell[] cells, Set<Integer> expected) {
        // Arrange
        // Act
        PossibilitiesValidator.removeDuplicatedPossibilities(cell, cells);
        Set<Integer> actual = cell.getPossibilities();
        // Assert
        assertEquals(expected.toString(), actual.toString());
    }


    @Test
    public void shouldRemovePossibilitiesFromCellWhenItsValuesFound() {
        // Arrange
        Board board = testUtills.getBoard(State.VALID);
        Cell cellToValidate = board.getCells()[1];
        // Act
        PossibilitiesValidator.validatePossibilities(board, cellToValidate);
        // Assert
        assertEquals(new HashSet<>(Arrays.asList(1,9)), cellToValidate.getPossibilities());
    }

    private static Stream<Arguments> possibilitiesProvider() {
        return Stream.of(arguments(getCell(),
                new Cell[]{new Cell(1, 0, 1, 1),
                        new Cell(2, 0, 2, 2),
                        new Cell(3, 0, 3, 3)},
                new HashSet<>(Arrays.asList(4, 5, 6, 7, 8, 9))),
                arguments(getCell(),
                        new Cell[]{new Cell(1, 0, 1, 0),
                                new Cell(2, 0, 2, 0),
                                new Cell(3, 0, 3, 0)},
                        new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))),
                arguments(getCell(),
                        new Cell[]{new Cell(1, 0, 1, 4),
                                new Cell(2, 0, 2, 5),
                                new Cell(3, 0, 3, 6)},
                        new HashSet<>(Arrays.asList(1, 2, 3, 7, 8, 9))),
                arguments(getCell(),
                        new Cell[]{new Cell(7, 0, 1, 7),
                                new Cell(8, 0, 2, 8),
                                new Cell(9, 0, 3, 9)},
                        new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6))));
    }

    private static Cell getCell() {
        Cell cell = new Cell(0, 0, 0, 0);
        HashSet<Integer> possibilities = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        cell.setPossibilities(possibilities);
        return cell;
    }
}