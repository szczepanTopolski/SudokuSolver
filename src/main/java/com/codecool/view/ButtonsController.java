package com.codecool.view;

import com.codecool.controller.SolverController;
import com.codecool.model.Board;
import com.codecool.service.ConverterCSV;
import com.codecool.service.Solver;
import com.codecool.service.ValidatorCSV;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ButtonsController {

    private  File file;
    private final ConverterCSV converterCSV = new ConverterCSV();
    private  BlockingQueue<Board> solutions;
    private HBox solutionsHbox;




    public  void solveSudokuButtonPressed(Stage primaryStage, GridPane gridBoard, Button solveSudokuButton) {
        solveSudokuButton.setOnAction(ev -> {
            Board newBoard = converterCSV.convert(stringFromFile(file));
            solutions= new LinkedBlockingQueue<>();
            Solver solver = new Solver(newBoard);
            SolverController solverController = new SolverController(solver);
            Thread thread = new Thread(solverController);
            thread.start();
            solutionsHbox = new HBox();
            long finish = System.currentTimeMillis() + 10000; // end time
            while (System.currentTimeMillis() < finish) {
                try {
                    solutionsHbox.getChildren().add((BoardGridPaneConverter.getBoardAsGridPane(solutions.take())));
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
            Scene solutionsScene = new Scene(solutionsHbox,960, 600);
            primaryStage.setScene(solutionsScene);


        });
    }



//    public void ChooseFileButtonPressed(Stage primaryStage, FileChooser fileChooser, Button button) {
//        button.setOnAction(e -> {
//            file = fileChooser.showOpenDialog(primaryStage);
//            ValidatorCSV vcsv = new ValidatorCSV();
//            try {
//                vcsv.validate(stringFromFile(file));
//                GridPane gridBoard = BoardGridPaneConverter.getBoardAsGridPane(stringFromFile(file));
//                Button solveSudokuButton = new Button("solve sudoku");
//                solveSudokuButtonPressed(primaryStage, gridBoard, solveSudokuButton);
//                Scene newBoardScene = new Scene(new VBox(gridBoard,solveSudokuButton), 960, 600);
//                primaryStage.setScene(newBoardScene);
//            }catch (Exception exception){
//                exception.printStackTrace();
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Wrong board Exception");
//                alert.setHeaderText("Something went wrong with file ");
//                alert.setContentText("check if the board is a valid board");
//                alert.showAndWait();
//            }
//        });
//    }

    private String stringFromFile(File file){
        String contents = "";
        try (InputStream in = new FileInputStream(file)) {
            contents = IOUtils.toString(in, StandardCharsets.UTF_8);
            System.out.println(contents);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }




}
