package com.codecool.controller;

import com.codecool.FxControllers.RootSceneController;
import com.codecool.exception.InvalidSudokuException;
import com.codecool.model.Board;
import com.codecool.model.Cell;
import com.codecool.service.Solver;
import com.codecool.view.ButtonsController;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

import static com.codecool.service.Properties.MAX_HEIGHT;
import static com.codecool.service.Properties.MAX_WIDTH;

public class SolverController implements Runnable{

    private final Solver solver;
    private static int threadCounter = 0;
    public static RootSceneController listener;
    long startTimer;


    public  void setListener(RootSceneController rootController) {
        listener = rootController;
    }
    public SolverController(Solver solver) {
        this.solver = solver;
        startTimer = System.currentTimeMillis();
    }


    @Override
    public void run() {

        synchronized (SolverController.class) {
            threadCounter++;
        }

        boolean isSolved = solver.solve();

        if(isSolved){

//            synchronized (SolverController.class){
//                System.out.println("ITS SOLVED!!");
//                System.out.println("Threads used: " + threadCounter);
//                for (int i = 0; i < 81; i++) {
//                    if (i % 9 == 0)
//                        System.out.println("");
//                    System.out.print(solver.getBoard().getCells()[i].getValue() + " ,");
//
//                }
//            }

            double timeSolved = System.currentTimeMillis() - startTimer;
            listener.notifySolution(solver.getBoard(), threadCounter, timeSolved);


        }else{
            try {
                splitThreads();
            } catch (InvalidSudokuException e) {
                synchronized (SolverController.class) {
                    System.out.println("NOT SOLVABLE!!");
                    System.out.println(threadCounter);
                    for (int i = 0; i < 81; i++) {
                        if (i % 9 == 0)
                            System.out.println("");
                        System.out.print(solver.getBoard().getCells()[i].getValue() + " ,");
                    }
                }
            }

        }

    }

    private void splitThreads() throws InvalidSudokuException {
        Cell cell = findCellWithLeastPossibilities();

        for (Integer value : cell.getPossibilities()) {
            Board boardCloned = clone(solver.getBoard());
            boardCloned.getCells()[cell.getId()].setValue(value);
            Solver splitSolver = new Solver(boardCloned);
            SolverController splitSolverController = new SolverController(splitSolver);
            Thread thread = new Thread(splitSolverController);
            thread.start();
        }
    }

    private Cell findCellWithLeastPossibilities() throws InvalidSudokuException {
        List<Cell> emptyCells = Arrays.stream(solver.getBoard().getCells())
                .filter(cell-> cell.getValue() == 0)
                .collect(Collectors.toList());
        int possibility = 2;
        while(possibility <= 9){
            for(Cell cell: emptyCells){
                if(cell.getPossibilities().size() == possibility)
                    return cell;
                possibility++;
            }
        }
        throw new InvalidSudokuException("No splitThread method possible");
    }

    Board clone(Board board){
        int boardSize = MAX_WIDTH.getValue() * MAX_HEIGHT.getValue();
        Cell[] cells = new Cell[boardSize];
        for(int i = 0; i < boardSize; i++){
            Cell cellCloneable = board.getCells()[i];
            cells[i] = new Cell(cellCloneable.getId(), cellCloneable.getX(), cellCloneable.getY(), cellCloneable.getValue());
            if(cellCloneable.getPossibilities() != null)
                cells[i].setPossibilities(new HashSet<>(cellCloneable.getPossibilities()));
        }
        return new Board(cells);
    }
}
