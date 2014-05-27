package com.indywiz.game.ui;


import com.indywiz.game.library.Game2048;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class MainController implements Initializable {

    @FXML
    public GridPane mainGrid;

    @FXML
    public Label scoreLabel;

    public Label[][] gridLabels;

    public Game2048 game2048;

    public Map<Integer, String> backgroundColor = new HashMap<Integer, String>();


    @FXML
    private void someKeyPressed(KeyEvent event) {

        int[][] prevState = getStateOfGame();
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

            int[][] currentState = getStateOfGame();

            boolean nextMoveDecision = Arrays.deepEquals(prevState, currentState);

            if(!game2048.checkGameOver() && !nextMoveDecision)
                generateNewBlock();
            refreshGrid();

        }
    }

    private int[][] getStateOfGame() {

        int[][] state = new int[4][4];

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++)
                state[i][j] = game2048.blockStatus(i, j);
        }

        return state;

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

        initializeColorMap();

        mainGrid.setStyle("-fx-background-color: #bbada0;");
        mainGrid.setPadding(new Insets(5, 5, 5, 5));
        mainGrid.setAlignment(Pos.CENTER);
        createButtons();
        initializeGame();
    }

    private void initializeColorMap() {
        backgroundColor.put(2, "eee4da");
        backgroundColor.put(4, "ede0c8");
        backgroundColor.put(8, "f2b179");
        backgroundColor.put(16, "f59563");
        backgroundColor.put(32, "f67c5f");
        backgroundColor.put(64, "f65e3b");
        backgroundColor.put(128, "edcf72");
        backgroundColor.put(256, "edcc61");
        backgroundColor.put(512, "edc850");
        backgroundColor.put(1024, "edc53f");
        backgroundColor.put(2048, "edc22e");
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
                    gridLabels[i][j].setStyle("-fx-background-color: #"+backgroundColor.get(gridNumber));
                }
                else {
                    gridLabels[i][j].setText("");
                    gridLabels[i][j].setStyle("-fx-background-color: rgba(238, 228, 218, 0.35);");
                }
            }
        if(game2048.checkGameOver())
            createDialogBox("Game over");
        scoreLabel.setText(""+game2048.score);
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


    void createDialogBox(String message)
    {
        final Stage myDialog = new Stage();
        myDialog.initModality(Modality.APPLICATION_MODAL);
        Button okButton = new Button("Restart game");
        okButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                myDialog.close();
                initializeGame();
            }
        });
        displayDialog(myDialog, okButton, message);
    }

    void displayDialog(final Stage myDialog, Button okButton, String message)
    {
        Scene myDialogScene;
        myDialogScene = new Scene(VBoxBuilder.create()
                .children(new Text(message), okButton)
                .alignment(Pos.CENTER)
                .padding(new Insets(50))
                .build());

        myDialog.setWidth(300);
        myDialog.setHeight(100);
        myDialog.centerOnScreen();
        myDialog.setTitle("Message");
        myDialog.setResizable(false);
        myDialog.setScene(myDialogScene);
        myDialog.show();
    }




}
