package com.assignment.TicTacToeGame;

import java.util.Scanner;

public class Human implements GamePlayer {

    private String name;
    private String  state;


    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setState(String state)
    {
        this.state = state;
    }

    @Override
    public String getState()
    {
        return state;
    }

    public int[] makeMove() {


            Scanner in = new Scanner(System.in);
            System.out.print("Enter Row: ");
            int x = in.nextInt();
            System.out.print("Enter Column: ");
            int y = in.nextInt();


            int[] res =   new int[]{x,y};

            return res;


    }
}
