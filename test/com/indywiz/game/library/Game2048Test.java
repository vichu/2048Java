package com.indywiz.game.library;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by viswanathanr on 4/11/14.
 */

public class Game2048Test extends TestCase{

    Game2048 game2048;

    @Before
    public void setUp() {
        game2048 = new Game2048();
    }

    @Test
    public void testCanary() {
        assertTrue(true);
    }

    @Test
    public void testGridHavingGirdSizeOtherThanDefault4() {
        game2048 = new Game2048(3);
        assertEquals(3, game2048.gridArray[0].length);
    }

    //Initialize the board with a cell;

    @Test
    public void testInitializeBoardWithAllEmptyStates() {
        assertNotNull(game2048.gridArray);
    }

    @Test
    public void testBoardSetUpInSquare() {
        for (int i=0; i<4; i++)
            assertEquals(4, game2048.gridArray[i].length);
    }

    @Test
    public void testSetUpBoardWithInitalTwo() {
        game2048.initializeBoard(2, 2);
        assertEquals(2, game2048.gridArray[2][2]);
    }

    @Test
    public void testSetUpBoardWithGridArrayBeingNull() {
        game2048.gridArray = null;
        assertFalse(game2048.initializeBoard(2, 2));
    }

    @Test
    public void testSetUpBoardWithGridArrayIndexBeingInvalid() {
        assertFalse(game2048.initializeBoard(5, 5));
    }

    @Test
    public void testSetUpBoardWithRowBeingInvalid() {
        assertFalse(game2048.initializeBoard(5, 2));
    }

    @Test
    public void testSetUpBoardWithColBeingInvalid() {
        assertFalse(game2048.initializeBoard(2, 5));
    }

    @Test
    public void testSetUpBoardWithRowValueBeingNegative() {
        assertFalse(game2048.initializeBoard(-1, 2));
    }

    @Test
    public void testSetUpBoardWithRowAndColValueBothBeingNegative() {
        assertFalse(game2048.initializeBoard(-1, -2));
    }

    @Test
    public void testSetUpBoardWithRowBeingNegativeAndColIllegal() {
        assertFalse(game2048.initializeBoard(-1, 5));
    }

    @Test
    public void testSetUpBoardWithColValueColBeingNegativeAndRowLegal() {
        assertFalse(game2048.initializeBoard(2, -2));
    }

    @Test
    public void testAlreadyInitializedGrid() {
        game2048.initializeBoard(2, 1);
        assertFalse(game2048.initializeBoard(1, 3));
    }

    //Spawn a new block in grid

    @Test
    public void testSpawnABlockInTheGrid() {
        game2048.initializeBoard(1, 1);
        game2048.spawnABlockAt(2, 1, 2);
        assertEquals(2, game2048.gridArray[2][1]);
    }

    @Test
    public void testSpawnABlockWithoutTheGridBeingInitialized() {
        game2048.spawnABlockAt(1, 2, 4);
        assertEquals(2, game2048.gridArray[1][2]);
    }

    @Test
    public void testSpawnABlockWithIllegalRowAndColumnValues() {
        game2048.initializeBoard(1, 2);
        assertFalse(game2048.spawnABlockAt(1, -1, 4));
    }

    @Test
    public void testSpawnABlockWithAlreadyIntializedRowAndColumnValues() {
        game2048.initializeBoard(1, 2);
        game2048.spawnABlockAt(1, 1, 4);
        assertFalse(game2048.spawnABlockAt(1, 1, 4));
    }

    @Test
    public void testSpawnABlockWithNumberBeingANonPowerOfTwo() {
        game2048.initializeBoard(1, 2);
        assertFalse(game2048.spawnABlockAt(2, 1, 5));
    }

    @Test
    public void testSpawnABlockWithNegativeNumber() {
        game2048.initializeBoard(1, 2);
        assertFalse(game2048.spawnABlockAt(2,1, -4));
    }


    //Move to right
    @Test
    public void testMoveBlocksToRight() {
        game2048 = new Game2048();
        game2048.initializeBoard( 0, 0 );
        game2048.moveTo(Game2048.Directions.RIGHT);
        assertEquals(2, game2048.gridArray[0][3]);
    }

    @Test
    public void testMoveBlocksToRightWithTwoBlocks() {
        game2048 = new Game2048();
        game2048.initializeBoard(0, 0);
        game2048.spawnABlockAt(0, 1, 4);
        game2048.spawnABlockAt(0, 3, 4);
        int[] resultArray = new int[]{0, 0, 2, 8};
        game2048.moveTo(Game2048.Directions.RIGHT);
        for (int i = 0; i < 4; i++)
            assertEquals(resultArray[i], game2048.gridArray[0][i]);
    }

    @Test
    public void testMoveBlocksToRightWithTwoRepeatingBlocks() {
        game2048 = new Game2048();
        game2048.initializeBoard(0, 0);
        game2048.spawnABlockAt(0, 1, 2);
        game2048.spawnABlockAt(0, 2, 2);
        game2048.spawnABlockAt(0, 3, 2);
        game2048.moveTo(Game2048.Directions.RIGHT);

        int[] resultArray = new int[]{0, 2, 2, 4};
        for (int i = 0; i < 4; i++)
            assertEquals(resultArray[i], game2048.gridArray[0][i]);
    }

    //Move blocks down
    @Test
    public void testMoveTheBlockDown() {
        game2048.initializeBoard(0, 2);
        game2048.moveTo(Game2048.Directions.DOWN);
        assertEquals(2, game2048.gridArray[3][2]);
    }

    @Test
    public void testMoveTheBlockDownWithNoMergings() {
        game2048.initializeBoard(0, 2);
        game2048.spawnABlockAt(1, 2, 4);
        game2048.spawnABlockAt(2, 2, 2);
        game2048.moveTo(Game2048.Directions.DOWN);

        int[] resultArray = new int[]{0, 2, 4, 2};
        for (int i = 0; i < 4; i++)
            assertEquals(resultArray[i], game2048.gridArray[i][2]);
    }

    @Test
    public void testMoveTheBlocksDownWithTwoRepeatingBlocks() {
        game2048.initializeBoard(0, 0);
        game2048.spawnABlockAt(1, 0, 2);
        game2048.spawnABlockAt(2, 0, 2);
        game2048.spawnABlockAt(3, 0, 2);
        game2048.moveTo(Game2048.Directions.DOWN);
        int[] resultArray = new int[]{0, 2, 2, 4};
        for (int i=0; i<game2048.gridArray.length; i++) {
            assertEquals(resultArray[i], game2048.gridArray[i][0]);
        }
    }

    @Test
    public void testMoveTheBlocksDownWithTwoRepeatingBlocksTwice() {
        game2048.initializeBoard(0, 0);
        game2048.spawnABlockAt(1, 0, 2);
        game2048.spawnABlockAt(2, 0, 2);
        game2048.spawnABlockAt(3, 0, 2);
        game2048.moveTo(Game2048.Directions.DOWN);
        game2048.moveTo(Game2048.Directions.DOWN);
        int[] resultArray = new int[]{0, 0, 4, 4};
        for (int i=0; i<game2048.gridArray.length; i++) {
            assertEquals(resultArray[i], game2048.gridArray[i][0]);
        }
    }

    //Move left
    @Test
    public void testMoveLeft() {
        game2048.initializeBoard(0, 3);
        game2048.moveTo(Game2048.Directions.LEFT);
        assertEquals(2, game2048.gridArray[0][0]);
    }

    @Test
    public void testMoveBlocksToLeftWithTwoBlocks() {
        game2048.initializeBoard(0, 0);
        game2048.spawnABlockAt(0, 1, 4);
        game2048.spawnABlockAt(0, 3, 4);
        int[] resultArray = new int[]{2, 8, 0, 0};
        game2048.moveTo(Game2048.Directions.LEFT);
        for (int i = 0; i < 4; i++)
            assertEquals(resultArray[i], game2048.gridArray[0][i]);
    }

    @Test
    public void testMoveBlocksToLeftWithTwoRepeatingBlocks() {
        game2048.initializeBoard(0, 0);
        game2048.spawnABlockAt(0, 1, 2);
        game2048.spawnABlockAt(0, 2, 2);
        game2048.spawnABlockAt(0, 3, 2);
        game2048.moveTo(Game2048.Directions.LEFT);

        int[] resultArray = new int[]{4, 2, 2, 0};
        for (int i = 0; i < 4; i++)
            assertEquals(resultArray[i], game2048.gridArray[0][i]);
    }

    //Move up

    @Test
    public void testBlocksUp() {
        game2048.initializeBoard(3, 0);
        game2048.moveTo(Game2048.Directions.UP);
        assertEquals(2, game2048.gridArray[0][0]);
    }


    @Test
    public void testMoveTheBlockUpWithNoMergings() {
        game2048.initializeBoard(0, 2);
        game2048.spawnABlockAt(1, 2, 4);
        game2048.spawnABlockAt(2, 2, 2);
        game2048.moveTo(Game2048.Directions.UP);

        int[] resultArray = new int[]{2, 4, 2, 0};
        for (int i = 0; i < 4; i++)
            assertEquals(resultArray[i], game2048.gridArray[i][2]);
    }

    @Test
    public void testMoveTheBlocksUpWithTwoRepeatingBlocks() {
        game2048.initializeBoard(0, 0);
        game2048.spawnABlockAt(1, 0, 2);
        game2048.spawnABlockAt(2, 0, 2);
        game2048.spawnABlockAt(3, 0, 2);
        game2048.moveTo(Game2048.Directions.UP);
        int[] resultArray = new int[]{4, 2, 2, 0};
        for (int i=0; i<game2048.gridArray.length; i++) {
            assertEquals(resultArray[i], game2048.gridArray[i][0]);
        }
    }

    @Test
    public void testMoveTheBlocksUpWithTwoRepeatingBlocksTwice() {
        game2048.initializeBoard(0, 0);
        game2048.spawnABlockAt(1, 0, 2);
        game2048.spawnABlockAt(2, 0, 2);
        game2048.spawnABlockAt(3, 0, 2);
        game2048.moveTo(Game2048.Directions.UP);
        game2048.moveTo(Game2048.Directions.UP);
        int[] resultArray = new int[]{4, 4, 0, 0};
        for (int i=0; i<game2048.gridArray.length; i++) {
            assertEquals(resultArray[i], game2048.gridArray[i][0]);
        }
    }

    @Test
    public void testMoveTheBlocksRightWithMultipleRowsOfNonEmptyBlocks() {
        game2048.initializeBoard(0, 1);
        game2048.spawnABlockAt(0, 2, 2);
        game2048.spawnABlockAt(0, 3, 4);
        game2048.spawnABlockAt(1, 0, 2);
        game2048.spawnABlockAt(1, 3, 2);

        game2048.moveTo(Game2048.Directions.RIGHT);

        int[][] resultArray = new int[][]{
                {0, 0, 4, 4},
                {0, 0, 0, 4},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        Assert.assertArrayEquals(resultArray, game2048.gridArray);
    }

    @Test
    public void testBlockStatusIsEmpty() {
        game2048.initializeBoard(0, 0);
        assertEquals(0, game2048.blockStatus(3, 3) );
    }

    @Test
    public void testBlockNotEmpty() {
        game2048.initializeBoard(0, 0);
        assertEquals(2, game2048.gridArray[0][0]);
    }

    //Check game over

    @Test
    public void testGameOverToBeFalseIfThereAreMoreStepsPossibleWithEmptyBlocks() {
        game2048.initializeBoard(2,3);
        assertFalse(game2048.checkGameOver());
    }

    @Test
    public void testGameOverToBeTrueIfThereAreNoSameBlockSurroundingTheCurrentBlock() {
        game2048 = new Game2048(3);
        game2048.initializeBoard(0, 0);
        game2048.spawnABlockAt(0, 1, 4);
        game2048.spawnABlockAt(0, 2, 2);
        game2048.spawnABlockAt(1, 0, 4);
        game2048.spawnABlockAt(1, 1, 2);
        game2048.spawnABlockAt(1, 2, 4);
        game2048.spawnABlockAt(2, 0, 2);
        game2048.spawnABlockAt(2, 1, 4);
        game2048.spawnABlockAt(2, 2, 2);

        assertTrue(game2048.checkGameOver());
    }

    @Test
    public void testGameOverToBeFalseIfThereAreSameBlockSurroundingTheCurrentBlock() {
        game2048 = new Game2048(3);
        game2048.initializeBoard(0, 0);
        game2048.spawnABlockAt(0, 1, 4);
        game2048.spawnABlockAt(0, 2, 2);
        game2048.spawnABlockAt(1, 0, 4);
        game2048.spawnABlockAt(1, 1, 2);
        game2048.spawnABlockAt(1, 2, 4);
        game2048.spawnABlockAt(2, 0, 4);
        game2048.spawnABlockAt(2, 1, 4);
        game2048.spawnABlockAt(2, 2, 2);

        assertFalse(game2048.checkGameOver());
    }

    @Test
    public void testGameWin() {
        game2048.initializeBoard(0,0);
        game2048.spawnABlockAt(0, 1, 1024);
        game2048.spawnABlockAt(0, 2, 1024);
        game2048.moveTo(Game2048.Directions.RIGHT);
        assertTrue(game2048.gameWin);
    }



}
