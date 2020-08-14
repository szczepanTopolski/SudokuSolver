package com.codecool.service;

import com.codecool.model.Board;
import com.codecool.model.Cell;

import java.util.Arrays;

import static com.codecool.service.BoardPartitioner.*;

public class PossibilitiesValidator {

    public Cell validatePossibilities(Board board, Cell cell){
        removeDuplicatedPossibilities(cell, getBox(board, cell.getId()));
        removeDuplicatedPossibilities(cell, getColumn(board, cell.getX()));
        removeDuplicatedPossibilities(cell, getRow(board, cell.getY()));
        return cell;
    }

    void removeDuplicatedPossibilities(Cell cellToValidate, Cell[] box) {
        Arrays.stream(box)
                .map(Cell::getValue)
                .filter(cell->cell > 0)
                .forEach(cellToValidate::removePossibility);
    }
}
