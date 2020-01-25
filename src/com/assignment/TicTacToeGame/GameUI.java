package com.assignment.TicTacToeGame;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import sun.jvm.hotspot.debugger.posix.elf.ELFSectionHeader;

import java.math.BigInteger;
import java.util.Arrays;

final public class GameUI implements GameWithWinningCriteria {

    private boolean exists;
    private int row;
    private int column;
    private int rowCriteria;
    private int columnCriteria;
    private int diagonalCriteria;

    private int[] Board;

    @Override
    public void setExists(boolean exists) {
        this.exists = exists;
    }

    @Override
    public boolean getExists() {
        return exists;
    }

    @Override
    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public void setRowCriteria(int rowCriteria) {
        this.rowCriteria = rowCriteria;
        if(rowCriteria > row)
        {
            this.exists = false;
        }
    }

    @Override
    public int getRowCriteria() {
        return rowCriteria;
    }

    @Override
    public void setColumnCriteria(int columnCriteria) {
        this.columnCriteria = columnCriteria;
        if(columnCriteria > column){
            this.exists = false;
        }
    }

    @Override
    public int getColumnCriteria() {
        return columnCriteria;
    }

    @Override
    public void setDiagonalCriteria(int diagonalCriteria) {
        this.diagonalCriteria = diagonalCriteria;
        if(diagonalCriteria > Math.min(row,column)){
            this.exists = false;
        }
    }

    @Override
    public int getDiagonalCriteria() {
        return diagonalCriteria;
    }

    public void createUI()
    {
        BigInteger size = new BigInteger("1");
        size = BigInteger.valueOf(row);
        size = size.multiply(BigInteger.valueOf(column));

        Board = new int[size.intValue()];
        Arrays.fill(Board,-1);
        printGame();

    }

    private void printGame() {

        String underRow = "-";
        for (int j = 0; j < 3; j++)
            for (int i = 0; i < column; i++) {
                underRow += "-";
            }
        underRow += "-";
        int cur_row = 0;
        int cur_col = 0;
        System.out.print("\n");
        for (int val : Board) {
            if (val != -1)
                System.out.print(" " + val + " ");
            else
                System.out.print("   ");


            cur_col++;
            if (cur_col == column) {
                cur_col = 0;
                cur_row++;
                if (cur_row != row)
                    System.out.print("\n" + underRow + "\n");
                else
                    System.out.print("\n");

            } else {
                System.out.print("|");
            }

        }

    }

    public boolean changeState(int x,int y,int state)
    {
        int cur_row = 1;
        int cur_col = 0;
        int index = 0;
        for(int val:Board)
        {
            cur_col++;
            if(cur_col>column)
            {
                cur_col = 1;
                cur_row++;
            }

            System.out.println(cur_col+" "+cur_row);
            if((cur_col==y) && (cur_row==x))
            {
                Board[index] = state;
                printGame();
                return true;
            }
            index++;
        }

        return false;

    }

}
