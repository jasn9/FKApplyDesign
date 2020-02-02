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


    public String checkGrid(GridUI parent,GridIterator iterx,GridIterator itery,int criteria)
    {
        for(int i=0;i<parent.getRow();i++)
        {
            for(int j=0;j<parent.getColumn();j++)
            {
                if(parent.getBoard(i,j)==null)continue;
                String val = parent.getBoard(i,j).getRes();

                if(val.equals("-1"))continue;

                int curx = 0;
                int cury = 0;
                int count = 0;
                while(((curx+i)<parent.getRow() && (curx+i)>=0)&&((cury+j)<parent.getColumn() && (cury+j)>=0))
                {
                    if(parent.getBoard(i+curx,j+cury)==null)break;
                    if(val.equals(parent.getBoard(curx+i,cury+j).getRes()))
                    {
                        count++;
                        if(count>=criteria){
                            return val;
                        }

                    }
                    else
                        break;
                    curx = iterx.Iterator(curx);
                    cury = itery.Iterator(cury);
                }

            }
        }
        return "-1";
    }

    public boolean isFull(GridUI parent)
    {
        for(int i=0;i<Math.pow(parent.getRow(),parent.getLevel());i++)
        {
            for(int j=0;j<Math.pow(parent.getColumn(),parent.getLevel());j++) {
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
