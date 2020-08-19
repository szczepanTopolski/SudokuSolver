package com.codecool.service;

import com.codecool.model.Board;
import com.codecool.model.Cell;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.codecool.service.BoardPartitioner.*;

public interface CompletenessValidator extends Function<Board, Boolean> {

    static CompletenessValidator isFullFilledWithValues() {
        return board -> (Arrays.stream(board.getCells())
                .map(Cell::getValue)
                .noneMatch(value-> value == 0));
    }

    static CompletenessValidator areBoxesValid(){
        return board -> IntStream.range(0, 8)
                .allMatch(id -> {
                    List<Integer> values = Arrays.stream(getBox(board, id))
                            .map(Cell::getValue)
                            .collect(Collectors.toList());
                    return values.stream()
                            .allMatch(new HashSet<Integer>()::add);
                });
    }

    static CompletenessValidator areColsValid(){
        return board -> IntStream.range(0, 8)
                .allMatch(id -> {
                    List<Integer> values = Arrays.stream(getColumn(board, id))
                            .map(Cell::getValue)
                            .collect(Collectors.toList());
                    return values.stream()
                            .allMatch(new HashSet<Integer>()::add);
                });
    }

    static CompletenessValidator areRowsValid(){
        return board -> IntStream.range(0, 8)
                .allMatch(id -> {
                    List<Integer> values = Arrays.stream(getRow(board, id))
                            .map(Cell::getValue)
                            .collect(Collectors.toList());
                    return values.stream()
                            .allMatch(new HashSet<Integer>()::add);
                });
    }

    default CompletenessValidator and (CompletenessValidator other){
        return board -> this.apply(board) ? other.apply(board) : false;
    }
}
