package com.assignment.TicTacToeGame;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import sun.jvm.hotspot.debugger.posix.elf.ELFSectionHeader;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;

final public class GameUI implements GameWithWinningCriteria {

    private boolean exists;
    private int row;
    private int column;
    private int rowCriteria;
    private int columnCriteria;
    private int diagonalCriteria;
    private int level;

    private GameUI[][] Board;
    private String res;


    @Override
    public void setExists(boolean exists) {
        this.exists = exists;
    }

    @Override
    public boolean getExists() {
        return exists;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getLevel()
    {
        return level;
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

    public void createUI() {

        Board = new GameUI[row][column];
        this.res = "-1";
        //Arrays.fill(Board,-1);
        //System.out.println(level);
        if (level>0) {

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    Board[i][j] = new GameUI();
                    Board[i][j].setRow(row);
                    Board[i][j].setColumn(column);
                    Board[i][j].setDiagonalCriteria(diagonalCriteria);
                    Board[i][j].setRowCriteria(rowCriteria);
                    Board[i][j].setLevel(level - 1);
                    Board[i][j].createUI();

                }
            }
        }
    }

    public GameUI findDepthVal(GameUI parent,int idx,int idy)
    {
        GameUI cur = parent;
        while(cur.getLevel()!=0)
        {
            //System.out.println(cur.getLevel()+" "+idx+" "+idy);
            int ix = idx/(int)Math.pow(row,cur.getLevel()-1);
            int iy = idy/(int)Math.pow(column,cur.getLevel()-1);
            idx = idx%row;
            idy = idy%column;
            cur = cur.Board[ix][iy];

        }
        return cur;
    }

    public void printGame() {


        //System.out.print("\n");
        for (int i = 0; i < Math.pow(row,level); i++) {
            for (int j = 0; j < Math.pow(column,level); j++) {
                //GameUI cur = this;
                int idx = i;
                int idy = j;

                GameUI val = findDepthVal(this,idx,idy);

                if (!val.res.equals("-1"))
                    System.out.print(" " + val.res + " ");
                else
                    System.out.print(" N ");

                if((j+1)==Math.pow(column,level))
                {
                    System.out.println();
                }
            }

        }

    }

    public boolean changeState(int x,int y,String  state,boolean backMove)
    {



        if((x>Math.pow(row,level)) || (x<1) || (y<1) || (y>Math.pow(column,level)))
        {
            return false;
        }
        x--;
        y--;
        //System.out.println(this.getLevel()+" "+x+" "+y);
        GameUI val = findDepthVal(this,x,y);
        if((!(val.res).equals("-1")) && (backMove==false))
        {
            return false;
        }
        //System.out.println(state+" "+val.getLevel()+" "+val.res);
        //printGame();
        val.res = state;
        System.out.println();
        //printGame();
        return true;

    }
    public String checkWinner()
    {

        if(this.level==0)
        {
            return this.res;
        }

        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++)
            {
                //System.out.println(this.getLevel()+" "+i+" "+j);
                String z = Board[i][j].checkWinner();
                Board[i][j].res = z;
                //System.out.println(this.getLevel()+" "+i+" "+j+"*"+z);
            }
        }

        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++)
            {
                String val = Board[i][j].res;
                int count = 0;
                for(int k=0;(k<rowCriteria)&&(k+j<column);k++)
                {
                    if(val.equals(Board[i][j+k].res))
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
                String  val = Board[i][j].res;
                int count = 0;
                for(int k=0;(k<columnCriteria)&&(k+i<row);k++)
                {
                    if(val.equals(Board[i+k][j].res))
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
                String  val = Board[i][j].res;
                int curx = i;
                int cury = j;
                int count = 0;
                while(curx<row && cury<column)
                {
                    if(val.equals(Board[curx][cury].res))
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
                String val = Board[i][j].res;
                int curx = i;
                int cury = j;
                int count = 0;

                while(curx<row && cury>=0)
                {
                    if(val.equals(Board[curx][cury].res))
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

        return "-1";

    }
    public boolean isFull()
    {
        for(int i=0;i<Math.pow(row,level);i++)
        {
            for(int j=0;j<Math.pow(column,level);j++) {
                GameUI val = findDepthVal(this,i,j);
                if ((val.res).equals("-1")) {
                    return false;
                }
            }
        }
        return true;
    }



}