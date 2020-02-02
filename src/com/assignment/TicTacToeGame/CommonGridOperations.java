package com.assignment.TicTacToeGame;

public class CommonGridOperations {


    public GridUI findDepthVal(GridUI parent, int idx, int idy)
    {
        GridUI cur = parent;
        while(cur!=null && cur.getLevel()!=0)
        {
            int ix = idx/(int)Math.pow(cur.getRow(),cur.getLevel()-1);
            int iy = idy/(int)Math.pow(cur.getColumn(),cur.getLevel()-1);
            idx = idx%((int)Math.pow(cur.getRow(),cur.getLevel()-1));
            idy = idy%((int)Math.pow(cur.getColumn(),cur.getLevel()-1));
            cur = cur.getBoard(ix,iy);
            //cur = cur.Board[ix][iy];

        }
        return cur;
    }

    public void printGame(GridUI parent) {

        System.out.println();
        System.out.print("   ");
        for(int i=0;i<Math.pow(parent.getColumn(),parent.getLevel());i++)
        {
            System.out.print((i+1)+"  ");
        }
        System.out.println();
        for (int i = 0; i < Math.pow(parent.getRow(),parent.getLevel()); i++) {
            System.out.print((i+1)+" ");
            for (int j = 0; j < Math.pow(parent.getColumn(),parent.getLevel()); j++) {
                //GameUI cur = this;
                int idx = i;
                int idy = j;

                GridUI val = findDepthVal(parent,idx,idy);

                if(val==null)
                {
                    System.out.print("   ");
                }
                else {
                    if (!val.getRes().equals("-1"))
                        System.out.print(" " + val.getRes() + " ");
                    else
                        System.out.print(" * ");
                }


            }
            System.out.println();

        }

    }

    public String checkRow(GridUI parent,GridIterator iter)
    {
        for(int i=0;i<parent.getRow();i++)
        {
            for(int j=0;j<parent.getColumn();j++)
            {
                if(parent.getBoard(i,j)==null)continue;
                String val = parent.getBoard(i,j).getRes();
                if(val.equals("-1"))continue;
                int count = 0;
                int cur = 0;

                while ((cur+j)<parent.getColumn())
                {
                    if(parent.getBoard(i,cur+j)==null)break;

                    if(val.equals(parent.getBoard(i,cur+j).getRes()))
                    {
                        count++;
                        if(count>=parent.getRowCriteria())
                        {
                            return val;
                        }
                    }
                    else
                        break;

                    cur = iter.Iterator(cur);
                }
                if(count==parent.getRowCriteria())
                {
                    return val;
                }
            }
        }
        return "-1";
    }

    public String checkColumn(GridUI parent,GridIterator iter)
    {
        for(int i=0;i<parent.getRow();i++)
        {
            for(int j=0;j<parent.getColumn();j++)
            {
                if(parent.getBoard(i,j)==null)continue;

                String  val = parent.getBoard(i,j).getRes();
                if(val.equals("-1"))continue;
                int count = 0;
                int cur = 0;

                while ((cur+i)<parent.getRow())
                {
                    if(parent.getBoard(cur+i,j)==null)break;

                    if(val.equals(parent.getBoard(i+cur,j).getRes()))
                    {
                        count++;
                        if(count>=parent.getColumnCriteria())
                        {
                            return val;
                        }
                    }
                    else
                        break;

                    cur = iter.Iterator(cur);
                }

                if(count==parent.getColumnCriteria())
                {
                    return val;
                }
            }
        }
        return "-1";
    }

    public String checkRightDiagonal(GridUI parent,GridIterator iter)
    {
        for(int i=0;i<parent.getRow();i++)
        {
            for(int j=0;j<parent.getColumn();j++) {

                if(parent.getBoard(i,j)==null)continue;
                String  val = parent.getBoard(i,j).getRes();
                if(val.equals("-1"))continue;

                int cur = 0;
                int count = 0;
                while((cur+i)<parent.getRow() && (cur+j)<parent.getColumn())
                {
                    if(parent.getBoard(i+cur,j+cur)==null)break;
                    if(val.equals(parent.getBoard(i+cur,j+cur).getRes()))
                    {
                        count++;
                    }
                    else
                    {
                        break;
                    }
                    cur = iter.Iterator(cur);
                }
                if(count==parent.getDiagonalCriteria())return val;
            }
        }
        return "-1";
    }

    public String checkLeftDiagonal(GridUI parent,GridIterator iter)
    {
        for(int i=0;i<parent.getRow();i++)
        {
            for(int j=0;j<parent.getColumn();j++)
            {

                if(parent.getBoard(i,j)==null)continue;
                String val = parent.getBoard(i,j).getRes();
                if(val.equals("-1"))continue;

                int cur = 0;
                int count = 0;

                while((cur+i)<parent.getRow() && (j-cur)>=0)
                {
                    if(parent.getBoard(cur+i,j-cur)==null)break;
                    if(val.equals(parent.getBoard(cur+i,j-cur).getRes()))
                    {
                        count++;
                    }
                    else
                        break;

                    cur = iter.Iterator(cur);
                }

                if(count==parent.getDiagonalCriteria())return val;

            }
        }
        return "-1";
    }

    public boolean isFull(GridUI parent)
    {
        for(int i=0;i<Math.pow(parent.getRow(),parent.getLevel());i++)
        {
            for(int j=0;j<Math.pow(parent.getColumn(),parent.getColumn());j++) {
                GridUI val = findDepthVal(parent,i,j);

                if(val==null)continue;

                if ((val.getRes()).equals("-1")) {
                    return false;
                }
            }
        }
        return true;
    }




}
