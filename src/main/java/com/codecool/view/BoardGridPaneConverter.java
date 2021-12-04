package com.codecool.view;

import com.codecool.model.Board;
import com.codecool.model.Cell;
import com.codecool.service.ConverterCSV;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class BoardGridPaneConverter {

    private final static ConverterCSV converterCSV = new ConverterCSV();

    public static GridPane getBoardAsGridPane(String string){
        Board board = converterCSV.convert(string);
        GridPane gp = new GridPane();
        Cell[] cells = board.getCells();
        for (Cell cell : cells) {
            Label lb = new Label(Integer.toString(cell.getValue()));
            lb.setStyle("-fx-border-color: black; -fx-font-size: 25");
            lb.setPrefWidth(50);
            lb.setPrefHeight(25);
            lb.setWrapText(true);
            gp.add(lb, cell.getY(), cell.getX());
        }
        return gp;
    }

    public static GridPane getBoardAsGridPane(Board board){
        GridPane gp = new GridPane();
        Cell[] cells = board.getCells();
        for (Cell cell : cells) {
            Label lb = new Label(Integer.toString(cell.getValue()));
            lb.setStyle("-fx-border-color: black; -fx-font-size: 25");
            lb.setPrefWidth(50);
            lb.setPrefHeight(25);
            lb.setWrapText(true);
            gp.add(lb, cell.getY(), cell.getX());
        }
        return gp;
    }
}
