package com.assignment.TicTacToeGame;

import java.util.HashMap;
import java.util.Scanner;

public class PlayGame {

    private Scanner in;
    private GameUI game;
    private HashMap<String,Integer> mp;
    private static Leaderboard leaderboard;
    private int level = 0;

    public void playGame()
    {
        leaderboard = new Leaderboard();
        boolean check = true;

        do {
            in = new Scanner(System.in);
            System.out.print("Enter The Level: ");
            level = in.nextInt();
            System.out.print("Enter 1:Play With Friends\nEnter 2:Play With Computer\nEnter 0:To Exit\nChoice: ");

            int choice = in.nextInt();

            if((choice>=0)&&(choice<3))
            {
                check = true;
                switch (choice){
                    case 0:return;
                    case 1:playerInit(false);check=false;continue;
                    case 2:playerInit(true);check=false;continue;
                }
            }
            else{
                System.out.println("Invalid Input");
                check = false;
            }

        }
        while(!check);
    }

    public void playerInit(boolean flg)
    {
        //System.out.print("Enter The Number of Player: ");
        //numberOfPlayer = in.nextInt();
        GamePlayer[] players;

        int numberOfPlayer = 2;
        players = new GamePlayer[numberOfPlayer];


        int index = 0;
        mp= new HashMap<>();

        // for only computer play
        if(flg) {
            index = 1;
            players[numberOfPlayer-1] = new Computer();
            players[numberOfPlayer-1].setName("Bot");
            mp.put("O",numberOfPlayer-index);
            players[numberOfPlayer-1].setState("O");
        }

        for(int i=0;i<numberOfPlayer-index;i++)
        {
            System.out.print("Enter The Name Of Player "+(i+1)+": ");
            String name;
            name = in.next();
            //System.out.println(name);
            players[i] = new Human();
            players[i].setName(name);

            System.out.println("Enter the state with which you want to play: ");
            name = in.next();

            while (mp.containsKey(name)){
                System.out.println("State Already Taken: ");
                name = in.next();
            }

            mp.put(name,i);
            (players[i]).setState(name);
        }

        // in computer init it


        System.out.println("Game Starts: ");

        //play(players);

        for(GamePlayer obj: players)
        {
            System.out.println("NAME: "+obj.getName()+" STATE: "+ obj.getState()+" SERIAL NO: "+mp.get(obj.getState()));
        }

        createInterface();
        play(players);
        return;

    }





    private void play(GamePlayer[] players)
    {
        while(true) {
            int st = 0;
            for (GamePlayer player : players) {
                System.out.println("Turn Of: " + player.getName());
                boolean check = true;
                int back_move = 2;
                do {
                    if (!check) {
                        System.out.println("Invalid Move, Try Again");
                    }
                    int[] res;
                    if(player instanceof Human)
                        res= ((Human)player).makeMove();
                    else
                        res = ((Computer)player).makeMove(game.getRow(),game.getColumn());

                    check = game.changeState(res[0], res[1], player.getState(),false);

                    if(player instanceof Human) {
                        System.out.println("Want To Make Back Move Press 1!\nTo Continue Press 2!");
                        back_move = in.nextInt();
                        if (back_move == 1) {
                            game.changeState(res[0], res[1], "-1", true);

                        }
                    }

                    if(check)
                    {
                        if(player instanceof Computer)
                            System.out.println("\n\nBot move: "+res[0]+" "+res[1]+"\n");
                    }
                }
                while ((!check) || (back_move==1));

                st++;
                String val = game.checkWinner();
                if (!val.equals("-1")) {
                    System.out.println("\n\nWinner is: " + players[mp.get(val)].getName()+"\n\n");
                    leaderboard.addIn(players[mp.get(val)].getName(),level);
                    System.out.print("Leader Board\n");
                    leaderboard.display();
                    return;
                }
                game.printGame();
                if (game.isFull()) {
                    System.out.println("\n\nGame Draw!!\n\n");
                    leaderboard.display();
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
        game.setLevel(level);
        if(!game.getExists())
        {
            System.out.println("Game Does Not Exists");
            return;
        }
        game.createUI();
        game.printGame();

    }

}
