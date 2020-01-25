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

    private int[][] Board;

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

        Board = new int[row][column];
        //Arrays.fill(Board,-1);
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++)
            {
                Board[i][j] = -1;
            }
        }
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
        for (int i=0;i<row;i++) {
            for(int j=0;j<column;j++) {

                int val = Board[i][j];
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

    }

    public boolean changeState(int x,int y,int state)
    {



        if((x>row) || (x<1) || (y<1) || (y>column))
        {
            return false;
        }

        if(Board[x-1][y-1]!=-1)
        {
            return false;
        }

        Board[x-1][y-1] = state;
        printGame();

        return true;

    }
    public int chechWinner()
    {

        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++)
            {
                int val = Board[i][j];
                int count = 0;
                for(int k=0;(k<rowCriteria)&&(k+j<column);k++)
                {
                    if(val==Board[i][j+k])
                    {
                        count++;
                    }
                    else
                        break;
                }
                if(count==rowCriteria)
                {
                    return val;
                }
            }
        }

        for(int j=0;j<column;j++)
        {
            for(int i=0;i<row;i++)
            {
                int val = Board[i][j];
                int count = 0;
                for(int k=0;(k<columnCriteria)&&(k+i<row);k++)
                {
                    if(val==Board[i+k][j])
                    {
                        count++;
                    }
                    else
                        break;
                }
                if(count==columnCriteria)
                {
                    return val;
                }
            }
        }

        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++) {
                int val = Board[i][j];
                int curx = i;
                int cury = j;
                int count = 0;
                while(curx<row && cury<column)
                {
                    if(val==Board[curx][cury])
                    {
                        count++;
                    }
                    else
                    {
                        break;
                    }
                    curx++;
                    cury++;
                }
                if(count==diagonalCriteria)return val;
            }
        }

        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++)
            {
                int val = Board[i][j];
                int curx = i;
                int cury = j;
                int count = 0;

                while(curx<row && cury>=0)
                {
                    if(val==Board[curx][cury])
                    {
                        count++;
                    }
                    else
                        break;

                    curx++;
                    cury--;
                }

                if(count==diagonalCriteria)return val;

            }
        }

        return -1;

    }
    public boolean isFull()
    {
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++) {
                int val = Board[i][j];
                if (val == -1) {
                    return false;
                }
            }
        }
        return true;
    }



}
