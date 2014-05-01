package com.indywiz.game.library;

public class Game2048 {

    private int _gridSize = 4; //default
    int[][] gridArray = null;

    enum Directions{
      RIGHT, LEFT, DOWN;
    };

    public Game2048() {
        gridArray = new int[_gridSize][_gridSize];
    }

    public Game2048(int gridSize) {
        _gridSize = gridSize;
        gridArray = new int[_gridSize][_gridSize];
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

        if(checkIfInvalid(row, column)) {
            return false;
        }

        for (int i = 0; i < _gridSize; i++)
            for(int j = 0; j < _gridSize; j++) {
                if(gridArray[i][j] != 0)
                    return false;
            }

        gridArray[row][column] = 2;
        return true;
    }

    private boolean checkIfInvalid(int row, int column) {
        return gridArray == null ||
                (row >= _gridSize) || (column >= _gridSize) ||
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
     * The number that is to be spawned. Only powers of 2 are considered as valid number.
     * @return
     * True if the initialization is success and False if it fails. Also initializes the grid if not called after
     * initializeBoard() method.
     */
    public boolean spawnABlockAt(int row, int column, int number) {

        if( initializeBoard(row, column) )
            return false;

        if ( checkIfInvalid(row, column) )
            return false;

        if(number <= 0 || ( (number & (number-1)) != 0 ) )    //Check for power of two.
            return false;

        gridArray[row][column] = number;
        return true;
    }

    public void moveTo(Directions direction) {

        switch (direction) {
            case RIGHT:
                moveToRight();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
        }


    }

    private void moveLeft() {
        int[][] reverseArray = reverseMatrix(gridArray);
        int[][] shiftedArray = new int[_gridSize][_gridSize];
        for(int i=0; i<_gridSize; i++) {
            int[] arrayToShift = reverseArray[i];
            shiftedArray[i] = shiftToRight(arrayToShift);
        }
        int[][] leftShiftedArray = reverseMatrix(shiftedArray);
        gridArray = leftShiftedArray;
    }

    private int[][] reverseMatrix(int[][] inputMatrix) {
        int[][] resultArray = new int[_gridSize][_gridSize];
        for(int i=0;i<_gridSize;i++) {
            for(int j=0;j<_gridSize;j++) {
                resultArray[i][j] = inputMatrix[(_gridSize-1)-i][(_gridSize-1)-j];
            }
        }
        return resultArray;
    }

    private void moveDown() {
        int[][] transposeArray = matrixTranspose(gridArray);
        int[][] shiftedArray = new int[_gridSize][_gridSize];
        for(int i = 0; i < _gridSize; i++) {
            int[] arrayToShift = transposeArray[i];
            shiftedArray[i] = shiftToRight(arrayToShift);
        }

        shiftedArray = matrixTranspose(shiftedArray);
        gridArray = shiftedArray;
    }

    private void moveToRight() {
        for(int i = 0; i < _gridSize; i++) {
            int[] arrayToShift = gridArray[i];
            gridArray[i] = shiftToRight(arrayToShift);
        }
    }

    private int[][] matrixTranspose(int[][] inputArray) {
        int[][] resultArray = new int[_gridSize][_gridSize];

        for(int i = 0; i < _gridSize; i++) {
            for(int j = 0; j < _gridSize; j++) {
                resultArray[i][j] = inputArray[j][i];
            }
        }
        return resultArray;
    }


    private int[] shiftToRight( int[] arrayToShift ) {

        boolean merged = false;
        for(int j = _gridSize-1; j >= 0; j--) {

            if( arrayToShift[j] == 0)
                continue;
            int farthestIndex = j;
            int current = farthestIndex;
            int next = current + 1;

            while (next < _gridSize) {

                if ( (arrayToShift[current] == arrayToShift[next]) && (!merged) ) {
                    arrayToShift[next] = arrayToShift[current] * 2;
                    arrayToShift[current] = 0;
                    merged = true;
                }

                else if (arrayToShift[next] == 0) {
                    arrayToShift[next] = arrayToShift[current];
                    arrayToShift[current] = 0;
                    current++;
                    next++;
                }
                else {
                    break;
                }
            }
        }

        return arrayToShift;
    }

}
