package com.assignment.TicTacToeGame;

public class Main {

    public static void main(String[] args) {
	// write your code here
         GameUI game = new GameUI();
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
