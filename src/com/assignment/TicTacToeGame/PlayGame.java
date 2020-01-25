package com.assignment.TicTacToeGame;

import java.util.Scanner;

public class PlayGame {

    private int numberOfPlayer;
    private Scanner in;
    private GameUI game;

    public void playGame()
    {

        boolean check = true;

        do {
            System.out.print("Enter 1:Play With Friends\nEnter 2:Play With Computer\nEnter 0:To Exit\nChoice: ");
            in = new Scanner(System.in);

            int choice = in.nextInt();

            if((choice>=0)&&(choice<3))
            {
                check = true;
                switch (choice){
                    case 0:return;
                    case 1:playOnlyHuman();check=false;continue;
                    case 2:playWithComputer();check=false;continue;
                }
            }
            else{
                System.out.println("Invalid Input");
                check = false;
            }

        }
        while(!check);
    }

    public void playWithComputer()
    {
        System.out.print("Enter The Number of Player: ");
        numberOfPlayer = in.nextInt();
        Human[] players;


        if((numberOfPlayer>=1)&&(numberOfPlayer<=Integer.MAX_VALUE))
        {
            players = new Human[numberOfPlayer];
        }
        else {
            System.out.println("Invalid Input");
            return;
        }

        int index = 1;
        for(int i=0;i<numberOfPlayer;i++)
        {
            System.out.print("Enter The Name Of Player "+index+": ");
            index++;
            String name;
            name = in.next();
            //System.out.println(name);
            players[i] = new Human();
            players[i].setName(name);
        }
        Computer comp = new Computer();
        comp.setName("Bot");

        System.out.println("Game Starts: ");
        createInterface();



        while(true) {
            int st = 0;
            for (Human player : players) {

                System.out.println("Turn Of: " + player.getName());
                boolean check = true;
                do {
                    if (!check) {
                        System.out.println("Invalid Move, Try Again");
                    }
                    int[] res = player.makeMove();
                    check = game.changeState(res[0], res[1], st);
                }
                while (!check);
                st++;
                int val = game.chechWinner();
                if (val != -1) {
                    System.out.println("\n\nWinner is: " + players[val].getName()+"\n\n");
                    return;
                }
                if (game.isFull()) {
                    System.out.println("\n\nGame Draw!!\n\n");
                    return;
                }
            }

            System.out.println("Turn Of: " + comp.getName());
            boolean check = true;
            do {
                if (!check) {
                    System.out.println("Invalid Move, Try Again");
                }
                int[] res = comp.makeMove(game.getRow(),game.getColumn());
                check = game.changeState(res[0], res[1], st);
            }
            while (!check);
            st++;
            int val = game.chechWinner();
            if (val != -1) {
                System.out.println("\n\nWinner is: " + comp.getName()+"\n\n");
                return;
            }
            if (game.isFull()) {
                System.out.println("\n\nGame Draw!!\n\n");
                return;
            }
        }

    }

    public void playOnlyHuman()
    {
        System.out.print("Enter The Number of Player: ");
        numberOfPlayer = in.nextInt();
        Human[] players;


        if((numberOfPlayer>1)&&(numberOfPlayer<=Integer.MAX_VALUE))
        {
            players = new Human[numberOfPlayer];
        }
        else {
            System.out.println("Invalid Input");
            return;
        }

        int index = 1;
        for(int i=0;i<numberOfPlayer;i++)
        {
            System.out.print("Enter The Name Of Player "+index+": ");
            index++;
            String name;
            name = in.next();
            //System.out.println(name);
            players[i] = new Human();
            players[i].setName(name);
        }

        System.out.println("Game Starts: ");
        createInterface();



        play(players);
        return;

    }



    private void play(Human[] players)
    {
        while(true) {
            int st = 0;
            for (Human player : players) {
                System.out.println("Turn Of: " + player.getName());
                boolean check = true;
                do {
                    if (!check) {
                        System.out.println("Invalid Move, Try Again");
                    }
                    int[] res = player.makeMove();
                    check = game.changeState(res[0], res[1], st);
                }
                while (!check);
                st++;
                int val = game.chechWinner();
                if (val != -1) {
                    System.out.println("\n\nWinner is: " + players[val].getName()+"\n\n");
                    return;
                }
                if (game.isFull()) {
                    System.out.println("\n\nGame Draw!!\n\n");
                    return;
                }
            }
        }
    }

    private void createInterface()
    {
        game = new GameUI();
        game.setExists(true);
        game.setRow(3);
        game.setColumn(3);
        game.setRowCriteria(3);
        game.setColumnCriteria(3);
        game.setDiagonalCriteria(3);

        if(!game.getExists())
        {
            System.out.println("Game Does Not Exists");
            return;
        }
        game.createUI();
    }

}