package com.assignment.TicTacToeGame;

import java.util.Random;

public class Computer implements GamePlayer {

    private String name;
    private String state;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }

    public int[] makeMove(int row,int column) {
        Random r = new Random();
        int x = r.nextInt(row);
        int y = r.nextInt(column);
        return new int[]{x+1,y+1};
    }
}
