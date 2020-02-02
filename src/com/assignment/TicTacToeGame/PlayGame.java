package com.assignment.TicTacToeGame;

import java.util.HashMap;
import java.util.Scanner;

public class PlayGame {

    private Scanner in;
    private GridUI game;
    private HashMap<String,Integer> nameHashMap;
    private Leaderboard leaderboard;
    private int level = 0;

    public void playGame()
    {
        leaderboard = new Leaderboard();

        boolean check = true;

        do {
            in = new Scanner(System.in);
            if(!createInterface())
            {
                return;
            }
            System.out.print("\nEnter 1:Play With Friends\nEnter 2:Play With Computer\nEnter 0:To Exit\nChoice: ");

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

    private void playerInit(boolean flg)
    {
        //System.out.print("Enter The Number of Player: ");
        //numberOfPlayer = in.nextInt();


        GamePlayer[] players;
        int numberOfPlayer = 2;
        players = new GamePlayer[numberOfPlayer];
        // flag
        int compterIsPlaying = 0;
        nameHashMap = new HashMap<>();

        // for only computer play
        if(flg) {
            compterIsPlaying = 1;
            players[numberOfPlayer-1] = new Computer();
            players[numberOfPlayer-1].setName("Bot");
            nameHashMap.put("O",numberOfPlayer-compterIsPlaying);
            players[numberOfPlayer-1].setState("O");
        }



        for(int i=0;i<numberOfPlayer-compterIsPlaying;i++)
        {
            System.out.print("Enter The Name Of Player "+(i+1)+": ");
            String name;
            name = in.next();
            //System.out.println(name);
            players[i] = new Human();
            players[i].setName(name);
            System.out.println("Enter the state with which you want to play: ");
            String state = in.next();

            while (nameHashMap.containsKey(state)){
                System.out.println("State Already Taken: ");
                state = in.next();
            }

            nameHashMap.put(state,i);
            (players[i]).setState(state);
        }

        // in computer init it


        System.out.println("Game Starts: ");

        //play(players);

        for(GamePlayer obj: players)
        {
            System.out.println("NAME: "+obj.getName()+" STATE: "+ obj.getState()+" SERIAL NO: "+ nameHashMap.get(obj.getState()));
        }


        play(players);
        return;

    }





    private void play(GamePlayer[] players)
    {
        while(true) {

            for (int i=0 ; i<players.length ;i++) {

                GamePlayer player = players[i];

                System.out.println("\nTurn Of: " + player.getName());
                boolean check = true;
                int back_move = 2;
                do {
                    if ((!check)&&(player instanceof Human)) {
                        System.out.println("\nInvalid Move, Try Again");
                    }
                    int[] res;
                    if(player instanceof Human)
                        res= ((Human)player).makeMove();
                    else
                        res = ((Computer)player).makeMove(game.getRow(),game.getColumn());


                    check = game.changeState(res[0], res[1], player.getState(),false);

                    if((check==true) && (player instanceof Human)) {

                        System.out.println("\nPress 1:Want To Make Back Move !\nPress 4:To Continue!");
                        back_move = in.nextInt();

                        if (back_move == 1) {
                            game.changeState(res[0], res[1], "-1", true);
                        }


                    }

                    if(check)
                    {
                        if(player instanceof Computer)
                            System.out.println("Bot move: "+res[0]+" "+res[1]);
                    }
                }
                while ((!check) || (back_move==1));



                String val = game.checkWinner();
                if (!val.equals("-1")) {
                    game.printGame();
                    System.out.println("\nWinner is: " + players[nameHashMap.get(val)].getName()+"!!\n");
                    leaderboard.addIn(players[nameHashMap.get(val)].getName(),level);
                    System.out.print("Leader Board!!\n");
                    leaderboard.display();
                    return;
                }
                game.printGame();
                if (game.isFull()) {
                    System.out.println("\nGame Draw!!\n");
                    leaderboard.display();
                    return;
                }
            }
        }
    }

    private boolean createInterface()
    {

        System.out.println("\nPress1:To Play Square Cell Game\nPress2:To Play Hexagonal Square Game\nPress3:To Play Connect 4 Game\nPress4:To Quit");
        boolean check = true;
        int choice = 0;
        do {
            System.out.print("Choice: ");
            choice = in.nextInt();

            int side = 0;
            if(choice>=1 && choice<=2) {
                System.out.print("Enter The Length Of Side: ");
                side = in.nextInt();
            }

            if (choice == 1) {

                game = new RectangleUI();
                game.setRow(side);
                game.setColumn(side);
                game.setRowCriteria(side);
                game.setColumnCriteria(side);
                game.setDiagonalCriteria(side);

            } else {
                if (choice == 2) {

                    game = new HexagonalUI();
                    int row = 2*side-1;
                    game.setRow(row);
                    int col = 2*row-1;
                    game.setColumn(col);
                    game.setRowCriteria(side);
                    game.setColumnCriteria(side);
                    game.setDiagonalCriteria(side);

                }
                else{
                    if(choice == 3)
                    {
                        game = new RectangleUIConnect4Game();
                        game.setRow(6);
                        game.setColumn(7);
                        game.setRowCriteria(4);
                        game.setColumnCriteria(4);
                        game.setDiagonalCriteria(4);
                        level = 1;
                        game.setLevel(1);

                    }
                    else {
                        if(choice==4)
                        {
                            System.out.println("Game Ends....!!!!");
                            return false;
                        }
                        System.out.println("Invalid Input");
                        check = false;
                    }
                }
            }
        }
        while (!check);

        if(choice!=3) {
            System.out.print("\nEnter The Level: ");
            level = in.nextInt();
            game.setLevel(level);
        }

        game.setExists(true);
        if (!game.getExists()) {
            System.out.println("Game Does Not Exists");
            return false;
        }

        game.createUI();
        System.out.println("\nInstructions - ");
        System.out.println(" 1. Here \"*\" Represents Cell Where You Can Make Move.\n 2. Numbers Represent Co-ordinate Axis Represents With Which You Can Make Move.");
        System.out.println(" 3. In this game \"State\" of a player is value which represent player in grid.\n 4. For instance \"X\" i.e cross is used in Tic-Tac-Toe Game To represent a player.");
        game.printGame();
        return true;
    }

}
