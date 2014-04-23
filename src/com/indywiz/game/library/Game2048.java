package com.indywiz.game.library;

public class Game2048 {

    private final int GRID_SIZE = 4;
    int[][] gridArray = null;


    public Game2048() {
        gridArray = new int[GRID_SIZE][GRID_SIZE];
    }


    public boolean initializeBoard(int row, int column) {

        if( gridArray == null ||
                (row >= GRID_SIZE) || (column >= GRID_SIZE) ||
                (row < 0) || (column < 0) ) {
            return false;
        }

        for (int i = 0; i < GRID_SIZE; i++)
            for(int j = 0; j < GRID_SIZE; j++) {
                if(gridArray[i][j] != 0)
                    return false;
            }

        gridArray[row][column] = 2;
        return true;
    }


    public boolean spawnABlockAt(int row, int column, int number) {

        if(initializeBoard(row, column))
            return false;

        gridArray[row][column] = number;
        return true;
    }
}
