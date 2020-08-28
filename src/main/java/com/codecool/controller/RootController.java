package com.codecool.controller;

import com.codecool.model.Board;
import com.codecool.model.Cell;
import com.codecool.service.Solver;

import java.util.Arrays;
import java.util.HashSet;

public class RootController {
    public static void main(String[] args) {
        RootController root = new RootController();
        Board board = root.getBoard(State.VALID);
        Solver solver = new Solver(board);
        SolverController solverController = new SolverController(solver);
        Thread thread = new Thread(solverController);
        thread.start();
    }


    public Board getBoard(State state) {
        int[][] values = getValues(state);
        Cell[] cells = new Cell[81];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int id = i + j * 9;
                cells[id] = new Cell(id, i, j , values[j][i]);
                fillWithPossibilitiesWhenHasNoValue(cells[id]);
            }
        }
        return new Board(cells);
    }

    private int[][] getValues(State state) {
        int[][] values;
        switch (state){
            case VALID:
                values = getValidValues();
                break;
            case SOLVED:
                values = getSolvedValues();
                break;
            case INVALID:
                values = getInvalidValues();
                break;
            case WORLD_HARDEST:
                values = getWorldHardestValues();
                break;
            default:
                throw new IllegalArgumentException();
        }
        return values;
    }

    private int[][] getInvalidValues() {
        return new int[][]{ {3, 1, 6, 5, 7, 8, 4, 9, 2},
                {3, 2, 9, 1, 3, 4, 7, 6, 8},
                {4, 8, 7, 6, 2, 9, 5, 5, 1},
                {2, 6, 4, 4, 1, 5, 9, 6, 7},
                {9, 7, 4, 8, 6, 3, 1, 2, 5},
                {8, 5, 5, 7, 9, 5, 6, 4, 3},
                {1, 3, 8, 9, 4, 7, 2, 5, 6},
                {6, 9, 2, 3, 5, 7, 8, 6, 4},
                {7, 4, 5, 2, 8, 6, 3, 1, 9}};
    }

    private int[][] getValidValues() {
        return new int[][]{ {0, 0, 6, 5, 0, 8, 4, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 0, 3, 0, 0, 5},
                {0, 5, 0, 0, 0, 0, 6, 0, 0},
                {0, 3, 0, 0, 0, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}};
    }

    private int[][] getSolvedValues() {
        return new int[][]{ {3, 1, 6, 5, 7, 8, 4, 9, 2},
                {5, 2, 9, 1, 3, 4, 7, 6, 8},
                {4, 8, 7, 6, 2, 9, 5, 3, 1},
                {2, 6, 3, 4, 1, 5, 9, 8, 7},
                {9, 7, 4, 8, 6, 3, 1, 2, 5},
                {8, 5, 1, 7, 9, 2, 6, 4, 3},
                {1, 3, 8, 9, 4, 7, 2, 5, 6},
                {6, 9, 2, 3, 5, 1, 8, 7, 4},
                {7, 4, 5, 2, 8, 6, 3, 1, 9}};
    }

    private int[][] getWorldHardestValues() {
        return new int[][]{ {8, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 6, 0, 0, 0, 0, 0},
                {0, 7, 0, 0, 9, 0, 2, 0, 0},
                {0, 5, 0, 0, 0, 7, 0, 0, 0},
                {0, 0, 0, 0, 4, 5, 7, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 3, 0},
                {0, 0, 1, 0, 0, 0, 0, 6, 8},
                {0, 0, 8, 5, 0, 0, 0, 1, 0},
                {0, 9, 0, 0, 0, 0, 4, 0, 0}};
    }
    private void fillWithPossibilitiesWhenHasNoValue(Cell cell){
        if(cell.getValue() == 0)
            cell.setPossibilities(new HashSet<>(Arrays.asList(1,2,3,4,5,6,7,8,9)));
    }

    enum State{
        VALID, SOLVED, INVALID, WORLD_HARDEST
    }
}
