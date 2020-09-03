package com.codecool.controller;


import com.codecool.model.Board;
import com.codecool.service.Converter;
import com.codecool.service.ConverterCSV;
import com.codecool.service.ValidatorCSV;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.IOUtils;
import javafx.scene.text.Text;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class RootController extends Application {
    ConverterCSV converterCSV = new ConverterCSV();
    private  File file;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("multi thread Sudoku Solver");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt files (.txt, .csv)", "*.txt")
        );
        Button button = new Button("Select File");
        ChooseFileButtonPressed(primaryStage, fileChooser, button);

        VBox vBox = new VBox(button);
        Scene initialScene = new Scene(vBox, 960, 600);

        primaryStage.setScene(initialScene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        Application.launch(args);
//        RootController root = new RootController();
//        Board board = root.getBoard(State.VALID);
//        Solver solver = new Solver(board);
//        SolverController solverController = new SolverController(solver);
//        Thread thread = new Thread(solverController);
//        thread.start();

    }

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



    /***
     * Methods connected with a button onclick event handler
     */
    private void ChooseFileButtonPressed(Stage primaryStage, FileChooser fileChooser, Button button) {
        button.setOnAction(e -> {
            file = fileChooser.showOpenDialog(primaryStage);
            ValidatorCSV vcsv = new ValidatorCSV();
            try {
                vcsv.validate(stringFromFile(file));
                Text textBoard = new Text(stringFromFile(file));
                Scene newBoardScene = new Scene(new VBox(textBoard), 960, 600);
                primaryStage.setScene(newBoardScene);
            }catch (Exception exception){
                exception.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong board Exception");
                alert.setHeaderText("Something went wrong with file ");
                alert.setContentText("check if the board is a valid board");
                alert.showAndWait();
            }

        });
    }

}
