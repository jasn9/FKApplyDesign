package com.assignment.TicTacToeGame;

import java.lang.management.BufferPoolMXBean;
import java.util.Stack;

public class HexagonalUI implements GameWithWinningCriteria{

    private boolean exists;
    private int row;
    private int column;
    private int rowCriteria;
    private int columnCriteria;
    private int diagonalCriteria;
    private int level;
    private HexagonalUI[][] Board;
    private String res;
    private static int flg = 0;


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

    public void createUI()
    {
        Board = new HexagonalUI[row][column];
        this.res = "-1";

        if(level!=0)
        {
            for(int i=0;i<row;i++)
            {
                for(int j=Math.abs(i-(row/2));j<column-Math.abs(i-(row/2));j+=2)
                {
                    //System.out.println(i+" "+j);
                    Board[i][j] = new HexagonalUI();
                    Board[i][j].setRow(row);
                    Board[i][j].setColumn(column);
                    Board[i][j].setColumnCriteria(columnCriteria);
                    Board[i][j].setDiagonalCriteria(diagonalCriteria);
                    Board[i][j].setRowCriteria(rowCriteria);
                    Board[i][j].setLevel(level - 1);
                    Board[i][j].createUI();
                }
            }
        }

    }

    public HexagonalUI findDepthVal(HexagonalUI parent,int idx,int idy)
    {
        HexagonalUI cur = parent;
        while(cur!=null && (cur.getLevel()!=0))
        {
            //System.out.println(cur.getLevel()+" "+idx+" "+idy);
            int ix = idx/(int)Math.pow(row,cur.getLevel()-1);
            int iy = idy/(int)Math.pow(column,cur.getLevel()-1);
            idx = idx%((int)Math.pow(row,cur.getLevel()-1));
            idy = idy%((int)Math.pow(column,cur.getLevel()-1));
            cur = cur.Board[ix][iy];
        }
        return cur;
    }

    public void printGame()
    {
        System.out.println();
        System.out.print("   ");
        for(int i=0;i<Math.pow(column,level);i++)
        {
            System.out.print((i+1)+"  ");
        }

        System.out.println();
        for(int i=0;i<Math.pow(row,level);i++)
        {
            System.out.print((i+1)+" ");
            for(int j=0;j<Math.pow(column,level);j++)
            {
                HexagonalUI val = findDepthVal(this,i,j);
                if(val==null)
                {
                    System.out.print("   ");
                }
                else{
                    if(val.res.equals("-1"))
                    {
                        System.out.print(" * ");
                    }
                    else{
                        System.out.print(" "+val.res+" ");
                    }
                }
            }
            System.out.println();
        }
    }

    public boolean changeState(int x,int y,String state,boolean back_move)
    {
        if((x>Math.pow(row,level)) || (x<1) || (y<1) || (y>Math.pow(column,level)))
        {
            return false;
        }
        x--;
        y--;

        HexagonalUI val = findDepthVal(this,x,y);

        if((val == null) || ((!val.res.equals("-1")) && (back_move==false)))
        {
            return false;
        }
        //System.out.println(val.res+state);
        val.res = state;
        return true;

    }

    public boolean isFull()
    {
        for(int i=0;i<Math.pow(row,level);i++)
        {
            for(int j=0;j<Math.pow(column,level);j++)
            {
                HexagonalUI val = findDepthVal(this,i,j);
                if(val==null)
                {
                    continue;
                }
                else{
                    if(val.res.equals("-1"))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private String checkRow()
    {
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++)
            {
                if(Board[i][j]==null)
                {
                    continue;
                }

                String val = Board[i][j].res;
                if(val=="-1")continue;
                int count = 0;
                int cur = 0;

                while(((cur/2)<rowCriteria)&&(cur+j<column))
                {
                    if(Board[i][j+cur]==null)break;
                    //System.out.println(Board[i][j+cur].res+" "+i+" "+j+" "+cur);
                    if((Board[i][j+cur].res).equals(val))
                    {
                        count++;
                    }
                    else{
                        break;
                    }
                    cur+=2;
                }
                if(count==rowCriteria)
                {
                    return val;
                }

            }
        }
        return "-1";
    }

    private String checkRightDiagonal()
    {
        for(int i=0;i<row;i++) {
            for (int j = 0; j < column; j++) {
                if (Board[i][j] == null) {
                    continue;
                }
                String val = Board[i][j].res;
                if(val=="-1")continue;
                int count = 0;
                int cur = 0;
                //System.out.println(val);
                while ((cur < diagonalCriteria) && (cur + j < column) && ((i + cur) < row)) {
                    //System.out.println(cur+" "+i+" "+j+" "+Board[i+cur][j+cur].res);
                    if(Board[i+cur][j+cur]==null)break;
                    if ((Board[i + cur][j+cur].res).equals(val)) {
                        //System.out.println("*");
                        count++;
                    } else {
                        break;
                    }
                    cur++;
                }
                System.out.println();
                if (count == diagonalCriteria) {
                    return val;
                }

            }
        }
        return "-1";
    }

    public String checkLeftDiagonal()
    {
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++)
            {
                if(Board[i][j]==null)
                {
                    continue;
                }
                String val = Board[i][j].res;
                if(val=="-1")continue;
                int count = 0;
                int cur = 0;
                while((cur<diagonalCriteria)&&(cur+j<column)&&((i-cur)>=0))
                {
                    if(Board[i-cur][j+cur]==null)break;
                    if((Board[i-cur][cur+j].res).equals(val))
                    {
                        count++;
                    }
                    else{
                        break;
                    }
                    cur++;
                }
                if(count==diagonalCriteria)
                {
                    return val;
                }

            }
        }
        return "-1";
    }

    public String checkWinner()
    {
        //System.out.println("&");
        if(level==0)
        {
            return this.res;
        }
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++)
            {
                if(Board[i][j]!=null)
                {
                    Board[i][j].res = Board[i][j].checkWinner();

                    //System.out.println(level+" "+i+" "+j+" "+Board[i][j].res);
                }
            }
        }

        String val = checkRow();
        if(!val.equals("-1"))return val;

        val = checkRightDiagonal();
        if(!val.equals("-1"))return val;

        val = checkLeftDiagonal();
        if(!val.equals("-1"))return val;


        return "-1";
    }




}
