package com.indywiz.game.library;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by viswanathanr on 4/11/14.
 */
public class Game2048Test extends TestCase {

    Game2048 game2048;

    @Before
    public void setUp() {
        game2048 = new Game2048();
    }

    @Test
    public void testCanary() {
        assertTrue(true);
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
        assertFalse(game2048.spawnABlockAt(1, 2, 2));
    }

}