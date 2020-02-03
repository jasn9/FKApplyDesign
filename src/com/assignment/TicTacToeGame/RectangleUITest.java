package com.assignment.TicTacToeGame;

import junit.framework.TestCase;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleUITest extends TestCase {

    RectangleUI grid;

    public RectangleUITest()
    {

    }
    public void testAdd()
    {
        checkGrid();
        checkDiagonal();
        checkRow();
        checkColumn();
    }

    public void checkGrid() {

        grid = new RectangleUI();
        grid.setExists(true);
        grid.setRow(3);
        grid.setColumn(3);
        grid.setRowCriteria(3);
        grid.setColumnCriteria(3);
        grid.setDiagonalCriteria(3);
        grid.setLevel(1);
        grid.setOperations();
        grid.createUI();
        assertTrue(grid.getExists());
        grid.printGame();
    }

    public void checkDiagonal()
    {
        assertTrue(grid.changeState(1,1,"X",false));
        assertTrue(grid.changeState(2,2,"X",false));
        assertTrue(grid.changeState(3,3,"X",false));
        grid.printGame();
        assertTrue(grid.checkWinner().equals("X"));
        grid.createUI();
    }

    public void checkRow()
    {
        assertTrue(grid.changeState(1,1,"X",false));
        assertTrue(grid.changeState(1,2,"X",false));
        assertTrue(grid.changeState(1,3,"X",false));
        grid.printGame();
        assertTrue(grid.checkWinner().equals("X"));
        grid.createUI();
    }

    public void checkColumn()
    {
        assertTrue(grid.changeState(1,1,"X",false));
        assertTrue(grid.changeState(2,1,"X",false));
        assertTrue(grid.changeState(3,1,"X",false));
        grid.printGame();
        assertTrue(grid.checkWinner().equals("X"));
        grid.createUI();
    }

}