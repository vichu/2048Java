package com.indywiz.game.library;

public class Game2048 {

    private final int GRID_SIZE = 4;
    int[][] gridArray = null;


    public Game2048() {
        gridArray = new int[GRID_SIZE][GRID_SIZE];
    }

    /**
     * This method is used to initialize the board initially with a 2 in the position specified.
     * @param row
     * Is the row in with you want to place a 2 during the initialization.
     * @param column
     * Is the column in with you want to place a 2 during the initialization.
     * @return true if the initialization is successful or false if something goes wrong.
     */
    public boolean initializeBoard(int row, int column) {

        if(checkGridValidity(row, column)) {
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

    private boolean checkGridValidity(int row, int column) {
        return gridArray == null ||
                (row >= GRID_SIZE) || (column >= GRID_SIZE) ||
                (row < 0) || (column < 0);
    }


    /**
     * This method is similar to initialize in creating a new block in the grid. It can generate any
     * number that is specified in its argument. This method initializes the grid if not called after initialization.
     * @param row
     * Is the row in with you want to place the number during the initialization.
     * @param column
     * Is the column in with you want to place the number during the initialization.
     * @param number
     * The number that is to be spawned. Generally powers of 2.
     * @return
     * True if the initialization is success and False if it fails. Also initializes the grid if not called after
     * initializeBoard() method.
     */
    public boolean spawnABlockAt(int row, int column, int number) {

        if( initializeBoard(row, column) )
            return false;

        if ( checkGridValidity(row, column) )
            return false;

        gridArray[row][column] = number;
        return true;
    }
}
