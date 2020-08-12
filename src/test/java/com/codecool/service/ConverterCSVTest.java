package com.codecool.service;

import com.codecool.model.Board;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConverterCSVTest {
    private final Converter converter;

    ConverterCSVTest() {
        this.converter = new ConverterCSV();
    }

    @Test
    public void shouldRaiseInvalidArgumentExceptionWhenEmptyStringPassed() {
        assertThrows(IllegalArgumentException.class, ()->converter.convert(null));
    }
    
    @Test
    public void shouldRaiseInvalidArgumentExceptionWhenTooShortRowPassed() {
        assertThrows(IllegalArgumentException.class, ()->converter.convert("0;1;2;3;4;5;6;7;"));
    }
    @Test
    public void shouldRaiseInvalidArgumentExceptionWhenNotTooShortColsPassed() {
        assertThrows(IllegalArgumentException.class, ()->converter.convert("0;0;0;0;0;0;0;0;0;\n0;0;0;0;0;0;0;0;0;\n"));
    }
    @Test
    public void shouldRaiseInvalidArgumentExceptionWhenNotOnlyDigitsRowPassed() {
        assertThrows(IllegalArgumentException.class, ()->converter.convert("0;1;L;3;D;5;X;7;D;"));
    }

    @Test
    public void shouldReturnBoardWhenProperStringPassed() {
        // Arrange
        String input =   "0;0;0;0;0;0;0;0;0;\n0;0;0;0;0;0;0;0;0;\n0;0;0;0;0;0;0;0;0;\n" +
                "0;0;0;0;0;0;0;0;0;\n0;0;0;0;0;0;0;0;0;\n0;0;0;0;0;0;0;0;0;\n" +
                "0;0;0;0;0;0;0;0;0;\n0;0;0;0;0;0;0;0;0;\n0;0;0;0;0;0;0;0;0;\n";
        // Act
        Board board = converter.convert(input);
        // Assert
        assertTrue(Arrays.stream(board.getCells()).allMatch(cell -> cell.getValue() == 0)
        && board.getCells().length == 81);
    } 

}