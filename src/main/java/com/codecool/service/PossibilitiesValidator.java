package com.codecool.service;

import com.codecool.model.Board;
import com.codecool.model.Cell;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codecool.service.BoardPartitioner.*;

public class PossibilitiesValidator {

    public void validatePossibilities(Board board, Cell cell){ //GET BOX ID!!
        removeDuplicatedPossibilities(cell, getBoxByCellId(board, cell.getId()));
        removeDuplicatedPossibilities(cell, getColumn(board, cell.getX()));
        removeDuplicatedPossibilities(cell, getRow(board, cell.getY()));
    }

    void removeDuplicatedPossibilities(Cell cellToValidate, Cell[] box) {
        Arrays.stream(box)
                .map(Cell::getValue)
                .filter(cell->cell > 0)
                .forEach(cellToValidate::removePossibility);
    }

    public boolean countPossibilities(Cell[] cells){
        Map<Integer, Integer> occurrences = new HashMap<>();
        Arrays.stream(cells)
                .filter(cell -> cell.getValue() == 0)
                .flatMap(cell -> cell.getPossibilities().stream())
                .forEach(value->occurrences.merge(value,1,Integer::sum));
        return updateCells(cells, occurrences);
    }

    private boolean updateCells(Cell[] cells, Map<Integer,Integer> occurrences) {
        List<Integer> values = occurrences.entrySet().stream().filter(k-> k.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        boolean cellsUpdated = false;
        for(Integer value: values){
            for(Cell cell: cells){
                if(cell.getPossibilities().contains(value))
                    cell.setValue(value);
                    cellsUpdated = true;
            }
        }
        return cellsUpdated;
    }
}
