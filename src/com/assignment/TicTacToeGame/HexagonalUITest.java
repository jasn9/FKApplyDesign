package com.assignment.TicTacToeGame;

import junit.framework.TestCase;

import static org.junit.jupiter.api.Assertions.*;

public class HexagonalUITest extends TestCase {

    private HexagonalUI grid;

    public HexagonalUITest()
    {

    }

    public void testAdd()
    {
        checkGrid();
        checkDiagonal();
        checkRow();
    }

    public void checkGrid() {

        grid = new HexagonalUI();
        grid.setExists(true);
        grid.setRow(7);
        grid.setColumn(13);
        grid.setRowCriteria(4);
        grid.setColumnCriteria(4);
        grid.setDiagonalCriteria(4);
        grid.setLevel(1);
        grid.setOperations();
        grid.createUI();
        assertTrue(grid.getExists());
        grid.printGame();
    }

    public void checkDiagonal()
    {
        assertTrue(grid.changeState(1,4,"X",false));
        assertTrue(grid.changeState(2,3,"X",false));
        assertTrue(grid.changeState(3,2,"X",false));
        assertTrue(grid.changeState(4,1,"X",false));
        grid.printGame();
        assertTrue(grid.checkWinner().equals("X"));
        grid.createUI();
    }

    public void checkRow()
    {
        assertTrue(grid.changeState(1,4,"X",false));
        assertTrue(grid.changeState(1,6,"X",false));
        assertTrue(grid.changeState(1,8,"X",false));
        assertTrue(grid.changeState(1,10,"X",false));
        grid.printGame();
        assertTrue(grid.checkWinner().equals("X"));
        grid.createUI();
    }


}