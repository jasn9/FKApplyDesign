package com.assignment.TicTacToeGame;

public class RectangleUIConnect4Game extends RectangleUI {

    @Override
    public boolean changeState(int x, int y, String state, boolean backMove) {


        if((x>Math.pow(this.getRow(),this.getLevel())) || (x<1) || (y<1) || (y>Math.pow(this.getColumn(),this.getLevel())))
        {
            return false;
        }
        x--;
        y--;
        RectangleUI val = findDepthVal(x,y);
        if((!(val.getRes()).equals("-1")) && (backMove==false))
        {
            return false;
        }

        if(x+1 == val.getRow())
        {
            return super.changeState(x+1,y+1,state,backMove);
        }
        val = findDepthVal(x+1,y);

        if(val.getRes().equals("-1"))
        {
            return false;
        }

        return super.changeState(x+1, y+1, state, backMove);
    }
}
