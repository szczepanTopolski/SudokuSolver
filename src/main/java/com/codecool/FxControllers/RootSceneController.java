package com.codecool.FxControllers;

import com.codecool.controller.SolverController;
import com.codecool.model.Board;
import com.codecool.service.ConverterCSV;
import com.codecool.service.Solver;
import com.codecool.service.ValidatorCSV;
import com.codecool.view.BoardGridPaneConverter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.IOUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RootSceneController {


    private Stage primaryStage;
    private FXMLLoader loader;
    private Board board;
    private ConverterCSV converterCSV = new ConverterCSV();
    public static BlockingQueue<Board> blockingQueue = new LinkedBlockingQueue<>();





    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void findCsvBoard(ActionEvent actionEvent){
        System.out.println("looking for file");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt files (.txt, .csv)", "*.txt")
        );
        File file = fileChooser.showOpenDialog(primaryStage);
        ValidatorCSV vcsv = new ValidatorCSV();
        try {
            vcsv.validate(stringFromFile(file));
            board = converterCSV.convert(stringFromFile(file));
            GridPane gridBoard = BoardGridPaneConverter.getBoardAsGridPane(stringFromFile(file));
            VBox boardPane = (VBox) loader.getNamespace().get("boardVbox");
            boardPane.getChildren().add(gridBoard);
        }catch (Exception exception){
            exception.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong board Exception");
            alert.setHeaderText("Something went wrong with file ");
            alert.setContentText("check if the board is a valid board");
            alert.showAndWait();
        }
    }

    public void solveSudoku() throws InterruptedException {
        if(board != null) {
            Solver solver = new Solver(board);
            SolverController solverController = new SolverController(solver);
            solverController.setListener(this);
            Thread thread = new Thread(solverController);
            thread.start();
        }
        else{Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Didnt find any Board");
            alert.setContentText("please find and set board first");
            alert.showAndWait();
        }
    }

    public void notifySolution(Board board, int threadCounter, double timeSolved){
        Platform.runLater(()->{
            VBox solvedPlace = (VBox) loader.getNamespace().get("solvedGridsVbox");
            solvedPlace.setSpacing(50);
            AnchorPane newAnchor = new AnchorPane(BoardGridPaneConverter.getBoardAsGridPane(board));
            solvedPlace.getChildren().add(newAnchor);
            String threadCounterString = "Threads used: "+threadCounter;
            Text threadCounterText = (Text) loader.getNamespace().get("ThreadCounter");
            threadCounterText.setText(threadCounterString);
            Text timerText = (Text) loader.getNamespace().get("timer");
            String timeString = "Solved in: " + timeSolved + " ms";
            timerText.setText(timeString);
        });
    }

    //utils
    private String stringFromFile(File file){
        String contents = "";
        try (InputStream in = new FileInputStream(file)) {
            contents = IOUtils.toString(in, StandardCharsets.UTF_8);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }


}
