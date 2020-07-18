package com.assignment.TicTacToeGame;

import junit.framework.TestCase;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleUIConnect4GameTest extends TestCase {

    RectangleUIConnect4Game grid;

    public RectangleUIConnect4GameTest()
    {

    }
    public void testAdd()
    {
        checkGrid();
        checkColumn();
    }

    public void checkGrid() {

        grid = new RectangleUIConnect4Game();
        grid.setExists(true);
        grid.setRow(6);
        grid.setColumn(7);
        grid.setRowCriteria(4);
        grid.setColumnCriteria(4);
        grid.setDiagonalCriteria(4);
        grid.setLevel(1);
        grid.setOperations();
        grid.createUI();
        assertTrue(grid.getExists());
        grid.printGame();
    }

    public void checkColumn()
    {
        assertTrue(grid.changeState(6,1,"X",false));
        assertTrue(grid.changeState(5,1,"X",false));
        assertTrue(grid.changeState(4,1,"X",false));
        assertTrue(grid.changeState(3,1,"X",false));
        grid.printGame();
        assertTrue(grid.checkWinner().equals("X"));
        grid.createUI();
    }


}