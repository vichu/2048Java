package com.indywiz.game.ui;


import com.indywiz.game.library.Game2048;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public GridPane mainGrid;

    public Label[][] gridLabels;

    public Game2048 game2048;

    @FXML
    private void someKeyPressed(KeyEvent event) {
        if(event.getCode().isNavigationKey()) {
            String action = event.getCode().getName();
            if(action.equals("Left")) {
                game2048.moveTo(Game2048.Directions.LEFT);
            }
            else if(action.equals("Right")) {
                game2048.moveTo(Game2048.Directions.RIGHT);
            }
            else if(action.equals("Up")) {
                game2048.moveTo(Game2048.Directions.UP);
            }
            else {
                game2048.moveTo(Game2048.Directions.DOWN);
            }

            if(!game2048.checkGameOver())
                generateNewBlock();
            refreshGrid();

        }
    }

    private void generateNewBlock() {
        Random randomNumber = new Random(System.nanoTime());
        int random = randomNumber.nextInt(4);
        int row = random;
        randomNumber.setSeed(System.nanoTime());
        int column = randomNumber.nextInt(4);
        int nextNumber = 2;
        double probabilityOfNextNumber = Math.random();
        if(probabilityOfNextNumber > 0.7)
            nextNumber = 4;

        System.out.println("Row : "+ row+ " Col : "+ column+ " number : "+ nextNumber);

        if(!game2048.spawnABlockAt(row, column, nextNumber))
            generateNewBlock();
        else
            return;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainGrid.setStyle("-fx-background-color: #bbada0;");
        mainGrid.setPadding(new Insets(5, 5, 5, 5));
        mainGrid.setAlignment(Pos.CENTER);
        createButtons();
        initializeGame();
    }

    private void initializeGame() {
        game2048 = new Game2048();

        Random randomInt = new Random(System.currentTimeMillis());
        int rowRandom = randomInt.nextInt(4);
        randomInt.setSeed(System.currentTimeMillis());
        int colRandom = randomInt.nextInt(4);

        System.out.println("Game initialized : " + game2048.initializeBoard(rowRandom, colRandom));

        refreshGrid();

    }

    private void refreshGrid() {
        for(int i = 0; i < 4; i++)
            for( int j = 0; j < 4; j++) {
                int gridNumber = game2048.blockStatus(i, j);

                if(gridNumber > 0) {
                    gridLabels[i][j].setText(""+gridNumber);
                }
                else {
                    gridLabels[i][j].setText("");
                }
            }
    }

    private void createButtons() {
        gridLabels = new Label[4][4];
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gridLabels[i][j] = new Label();
                gridLabels[i][j].setStyle("-fx-border-color: black;");
                gridLabels[i][j].setStyle("-fx-background-color: rgba(238, 228, 218, 0.35);-fx-text-fill: black;");
                gridLabels[i][j].setAlignment(Pos.CENTER);
                gridLabels[i][j].setMinWidth(34.0);
                gridLabels[i][j].setPrefHeight(38.0);
                gridLabels[i][j].setPrefWidth(41.0);
                mainGrid.add(gridLabels[i][j], j, i);
            }
        }
    }



}
