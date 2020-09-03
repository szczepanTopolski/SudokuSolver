package com.codecool.service;

import com.codecool.model.Board;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ConverterCSVTest {
    private final Converter<Board> converter;

    ConverterCSVTest() {
        this.converter = new ConverterCSV();
    }


    @Test
    public void shouldReturnFullFilledBoardWhenProperStringPassed() {
        // Arrange
        String values = getValues();
        // Act
        Board board = converter.convert(values);
        // Assert
        assertEquals(81, board.getCells().length);
    }

    @Test
    public void shouldReturnBoardFullFilledWithValuesWhenProperStringPassed() {
        // Arrange
        String values = getValues();
        // Act
        Board board = converter.convert(values);
        // Assert
        assertTrue(Arrays.stream(board.getCells()).allMatch(cell -> cell.getValue() == 0));
    }

    private String getValues() {
        return "0;0;0;0;0;0;0;0;0\n" +
                "0;0;0;0;0;0;0;0;0\n" +
                "0;0;0;0;0;0;0;0;0\n" +
                "0;0;0;0;0;0;0;0;0\n" +
                "0;0;0;0;0;0;0;0;0\n" +
                "0;0;0;0;0;0;0;0;0\n" +
                "0;0;0;0;0;0;0;0;0\n" +
                "0;0;0;0;0;0;0;0;0\n" +
                "0;0;0;0;0;0;0;0;0\n";
    }
}