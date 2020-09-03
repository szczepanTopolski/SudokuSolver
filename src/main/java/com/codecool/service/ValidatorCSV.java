package com.codecool.service;

import java.util.Arrays;

public class ValidatorCSV {

    public void validate(String input) throws IllegalArgumentException {
        if (input == null) throw new IllegalArgumentException();
        String[] rows = input.split("\n");
        checkBoardFullFilledWithRows(rows);
        checkRowsFullFilledWIthDigits(rows);
    }

    private void checkBoardFullFilledWithRows(String[] rows) {
        if (rows.length != 9) throw new IllegalArgumentException();
    }

    private void checkRowsFullFilledWIthDigits(String[] rows) {
        if (!Arrays.stream(rows)
                .map(row -> row.split(";"))
                .allMatch(row -> row.length == 9 &&
                        Arrays.stream(row)
                                .allMatch(cell -> Character.isDigit(cell.charAt(0)))))
            throw new IllegalArgumentException();
    }
}
