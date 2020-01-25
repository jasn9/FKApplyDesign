package com.assignment.TicTacToeGame;

import java.util.Random;

public class Computer implements GamePlayer {

    String name;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }


    public int[] makeMove(int row,int column) {
        Random r = new Random();
        int x = r.nextInt(row);
        int y = r.nextInt(column);
        return new int[]{x+1,y+1};
    }
}
