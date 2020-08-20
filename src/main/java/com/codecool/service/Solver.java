package com.codecool.service;

import com.codecool.model.Board;
import com.codecool.model.Cell;

import java.util.Arrays;
import java.util.function.BiFunction;

import static com.codecool.service.CompletenessValidator.*;

public class Solver {
    private final Board board;
    private boolean isCyclePossible;

    public Solver(Board board) {
        this.board = board;
        this.isCyclePossible = true;
    }

    public boolean solve(){

        while(isCyclePossible){
            isCyclePossible = false;
            solve(BoardPartitioner::getBox);
            solve(BoardPartitioner::getColumn);
            solve(BoardPartitioner::getRow);
        }

        return isSolved();
    }

    private boolean isSolved() {
        return isFullFilledWithValues()
                .and(areBoxesValid())
                .and(areColsValid())
                .and(areRowsValid())
                .apply(board);
    }


    private void solve(BiFunction<Board,Integer,Cell[]> boardPartitioner) {
        fillWithPossibilities();
        for (int i = 0; i < 9; i++) {
            if(isSuccessfullyUpdated(boardPartitioner, i))
                isCyclePossible = true;
        }
    }

    private boolean isSuccessfullyUpdated(BiFunction<Board, Integer, Cell[]> boardPartitioner, int i) {
        return PossibilitiesValidator.countPossibilities(boardPartitioner.apply(board, i));
    }

    private void fillWithPossibilities() {
        Arrays.stream(board.getCells())
                .filter(cell -> cell.getValue() == 0)
                .forEach(cell -> PossibilitiesValidator.validatePossibilities(board, cell));
    }

    public Board getBoard() {
        return board;
    }
}
