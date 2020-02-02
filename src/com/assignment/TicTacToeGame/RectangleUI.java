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

    public void createUI() {

        Board = new RectangleUI[row][column];
        this.res = "-1";
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

    public RectangleUI findDepthVal(RectangleUI parent, int idx, int idy)
    {
        RectangleUI cur = parent;
        while(cur.getLevel()!=0)
        {
            int ix = idx/(int)Math.pow(row,cur.getLevel()-1);
            int iy = idy/(int)Math.pow(column,cur.getLevel()-1);
            idx = idx%((int)Math.pow(row,cur.getLevel()-1));
            idy = idy%((int)Math.pow(column,cur.getLevel()-1));
            cur = cur.Board[ix][iy];

        }
        return cur;
    }

    public void printGame() {

        System.out.println();
        System.out.print("   ");
        for(int i=0;i<Math.pow(column,level);i++)
        {
            System.out.print((i+1)+"  ");
        }
        System.out.println();
        for (int i = 0; i < Math.pow(row,level); i++) {
            System.out.print((i+1)+" ");
            for (int j = 0; j < Math.pow(column,level); j++) {
                //GameUI cur = this;
                int idx = i;
                int idy = j;

                RectangleUI val = findDepthVal(this,idx,idy);

                if (!val.res.equals("-1"))
                    System.out.print(" " + val.res + " ");
                else
                    System.out.print(" * ");


            }
            System.out.println();

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
        RectangleUI val = findDepthVal(this,x,y);
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

    private String checkRow()
    {
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++)
            {
                String val = Board[i][j].res;
                if(val.equals("-1"))continue;
                int count = 0;
                int cur = 0;

                while ((cur+j)<this.row)
                {
                    if(val.equals(this.Board[i][cur+j].res))
                    {
                        count++;
                        if(count>=this.rowCriteria)
                        {
                            return val;
                        }
                    }
                    else
                        break;

                    cur++;
                }
                if(count==rowCriteria)
                {
                    return val;
                }
            }
        }
        return "-1";
    }

    public String checkColumn()
    {
        for(int i=0;i<this.row;i++)
        {
            for(int j=0;j<this.column;j++)
            {
                String  val = this.Board[i][j].res;
                if(val.equals("-1"))continue;
                int count = 0;
                int cur = 0;

                while ((cur+i)<this.column)
                {
                    if(val.equals(this.Board[i+cur][j].res))
                    {
                        count++;
                        if(count>=this.columnCriteria)
                        {
                            return val;
                        }
                    }
                    else
                        break;

                    cur++;
                }

                if(count==this.columnCriteria)
                {
                    return val;
                }
            }
        }
        return "-1";
    }

    public String checkRightDiagonal()
    {
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++) {
                String  val = Board[i][j].res;
                if(val.equals("-1"))continue;

                int cur = 0;
                int count = 0;
                while((cur+i)<row && (cur+j)<column)
                {
                    if(val.equals(Board[cur+i][cur+j].res))
                    {
                        count++;
                    }
                    else
                    {
                        break;
                    }
                    cur++;
                }
                if(count==diagonalCriteria)return val;
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
                String val = Board[i][j].res;
                if(val.equals("-1"))continue;

                int cur = 0;
                int count = 0;

                while((cur+i)<row && (j-cur)>=0)
                {
                    if(val.equals(Board[cur+i][j-cur].res))
                    {
                        count++;
                    }
                    else
                        break;

                    cur++;
                }

                if(count==diagonalCriteria)return val;

            }
        }
        return "-1";
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

        String val = checkRow();
        if(!val.equals("-1")){
            return val;
        }

        val = checkColumn();
        if(!val.equals("-1")) {
            return val;
        }
        val = checkRightDiagonal();

        if(!val.equals("-1")){
            return val;
        }

        val = checkLeftDiagonal();
        if(!val.equals("-1")){
            return val;
        }

        return "-1";

    }
    public boolean isFull()
    {
        for(int i=0;i<Math.pow(row,level);i++)
        {
            for(int j=0;j<Math.pow(column,level);j++) {
                RectangleUI val = findDepthVal(this,i,j);
                if ((val.res).equals("-1")) {
                    return false;
                }
            }
        }
        return true;
    }




}