package com.indywiz.game.library;

/**
 * Created by viswanathanr on 4/11/14.
 */
public class Game2048 {

    private final int GRID_SIZE = 4;
    public int[][] gridArray;

    public Game2048() {
        gridArray = new int[GRID_SIZE][GRID_SIZE];
    }


    public boolean initializeBoard(int row, int column) {
        if(gridArray == null ||
                (row >= GRID_SIZE) || (column >= GRID_SIZE) ||
                (row < 0) || (column < 0) ) {
            return false;
        }

        gridArray[row][column] = 2;
        return true;
    }
}
