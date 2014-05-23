package com.indywiz.game.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(Main.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("view/Game2048.fxml"));

        Scene scene = new Scene(root, 600, 400, Color.WHEAT);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);

        stage.show();
    }
}
