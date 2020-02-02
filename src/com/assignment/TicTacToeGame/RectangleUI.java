package com.assignment.TicTacToeGame;

public class RectangleUI implements GridUI {

    private boolean exists;
    private int row;
    private int column;
    private int rowCriteria;
    private int columnCriteria;
    private int diagonalCriteria;
    private int level;
    private RectangleUI[][] Board;
    private String res;
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
    public String getRes(){ return res; }
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


    public void setOperations()
    {
        this.operations = new commonGrid();
    }

    public void createUI() {

        Board = new RectangleUI[row][column];
        this.res = "-1";
        this.setOperations();

        //Arrays.fill(Board,-1);
        //System.out.println(level);
        if (level>0) {

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    Board[i][j] = new RectangleUI();
                    Board[i][j].setRow(this.row);
                    Board[i][j].setColumn(this.column);
                    Board[i][j].setDiagonalCriteria(this.diagonalCriteria);
                    Board[i][j].setRowCriteria(this.rowCriteria);
                    Board[i][j].setColumnCriteria(this.columnCriteria);
                    Board[i][j].setLevel(level - 1);
                    Board[i][j].createUI();

                }
            }
        }
    }


    public RectangleUI getBoard(int x,int y)
    {
        return this.Board[x][y];
    }


    public void printGame()
    {
        operations.printGame(this);
    }

    public RectangleUI findDepthVal(int x,int y)
    {
        return (RectangleUI) operations.findDepthVal(this,x,y);
    }

    public boolean changeState(int x,int y,String  state,boolean backMove)
    {

        if((x>Math.pow(row,level)) || (x<1) || (y<1) || (y>Math.pow(column,level)))
        {
            return false;
        }
        x--;
        y--;

        RectangleUI val = findDepthVal(x,y);

        if((!(val.res).equals("-1")) && (backMove==false))
        {
            return false;
        }

        val.res = state;
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
                String z = Board[i][j].checkWinner();
                Board[i][j].res = z;
            }
        }

        String val = operations.checkRow(this,(int x)->(x+1));
        if(!val.equals("-1")){
            return val;
        }

        val = operations.checkColumn(this,(int x)->(x+1));
        if(!val.equals("-1")) {
            return val;
        }
        val = operations.checkRightDiagonal(this,(int x)->(x+1));

        if(!val.equals("-1")){
            return val;
        }

        val = operations.checkLeftDiagonal(this,(int x)->(x+1));
        if(!val.equals("-1")){
            return val;
        }

        return "-1";

    }

    public boolean isFull()
    {
        return operations.isFull(this);
    }


}