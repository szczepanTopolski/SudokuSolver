package com.codecool.service;

import com.codecool.exception.InvalidSudokuException;
import com.codecool.model.Board;
import com.codecool.model.Cell;

import java.util.*;

import static com.codecool.service.BoardPartitioner.*;


public class CorrectnessValidator {
    private final Board board;

    public CorrectnessValidator(Board board) {
        this.board = board;
    }

    public void validate() throws InvalidSudokuException{
        validateBoxes();
        validateCols();
        validateRows();
    }

    private void validateRows() throws InvalidSudokuException {
        for (int i = 0; i <9; i ++) {
            List<Cell> cells = Arrays.asList(getRow(board, i));
            List<Integer> cellValues = new ArrayList<>();
            cells.forEach(cell -> cellValues.add(cell.getValue()));
            Set<Integer> testSet = new HashSet<>(cellValues);
            if(testSet.size() < cellValues.size()){
                String message = "duplicates found in row" + i;
                throw new InvalidSudokuException(message);
            }
        }

    }
    private void validateCols() throws InvalidSudokuException {
        for (int i = 0; i < 9; i ++) {
            List<Cell> cells = Arrays.asList(getColumn(board, i));
            List<Integer> cellValues = new ArrayList<>();
            cells.forEach(cell -> cellValues.add(cell.getValue()));
            Set<Integer> testSet = new HashSet<>(cellValues);
            if(testSet.size() < cellValues.size()){
                String message = "duplicates found in column" + i;
                throw new InvalidSudokuException(message);
            }
        }
    }

    private void validateBoxes() throws InvalidSudokuException {
        for (int i = 0; i < 9; i ++) {
            List<Cell> cells = Arrays.asList(getBox(board, i));
            List<Integer> cellValues = new ArrayList<>();
            cells.forEach(cell -> cellValues.add(cell.getValue()));
            Set<Integer> testSet = new HashSet<>(cellValues);
            if(testSet.size() < cellValues.size()){
                String message = "duplicates found in column" + i;
                throw new InvalidSudokuException(message);
            }
        }
    }
}
