package com.indywiz.game.ui;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public GridPane mainGrid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainGrid.setAlignment(Pos.CENTER);
        createButtons();
    }

    private void createButtons() {
        Label[][] gridLabels = new Label[4][4];
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gridLabels[i][j] = new Label(""+i+""+j);
                gridLabels[i][j].setStyle("-fx-border-color: black;");
                gridLabels[i][j].setStyle("-fx-background-color: #CCFF99;-fx-text-fill: grey;");
                gridLabels[i][j].setAlignment(Pos.CENTER);
                gridLabels[i][j].setMinWidth(34.0);
                gridLabels[i][j].setPrefHeight(38.0);
                gridLabels[i][j].setPrefWidth(41.0);
                mainGrid.add(gridLabels[i][j], i, j);
            }
        }
    }

}
