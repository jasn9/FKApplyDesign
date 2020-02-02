package com.assignment.TicTacToeGame;

public class HexagonalUI implements GridUI {

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
    private commonGrid operations;

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

    public void setOperations(){ this.operations = new commonGrid(); }

    public void createUI()
    {
        Board = new HexagonalUI[row][column];
        this.res = "-1";
        this.setOperations();

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

    public String getRes(){ return this.res; }


    public HexagonalUI findDepthVal(int x,int y)
    {
        return (HexagonalUI) operations.findDepthVal(this,x,y);
    }


    public HexagonalUI getBoard(int x,int y)
    {
        return this.Board[x][y];
    }


    public void printGame()
    {
        operations.printGame(this);
    }

    public boolean changeState(int x,int y,String state,boolean back_move)
    {
        if((x>Math.pow(row,level)) || (x<1) || (y<1) || (y>Math.pow(column,level)))
        {
            return false;
        }
        x--;
        y--;

        HexagonalUI val = findDepthVal(x,y);

        if((val == null) || ((!val.res.equals("-1")) && (back_move==false)))
        {
            return false;
        }

        val.res = state;
        return true;

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

        String val = operations.checkRow(this,(int x)->(x+2));
        if(!val.equals("-1"))return val;

        val = operations.checkRightDiagonal(this,(int x)->(x+1));
        if(!val.equals("-1"))return val;

        val = operations.checkLeftDiagonal(this,(int x)->(x+1));
        if(!val.equals("-1"))return val;


        return "-1";
    }


    public boolean isFull()
    {
        return operations.isFull(this);
    }


}
