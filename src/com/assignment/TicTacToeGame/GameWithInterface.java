package com.assignment.TicTacToeGame;

public interface GameWithInterface extends Game {

    @Override
    void setExists(boolean exists);

    @Override
    boolean getExists();

    void setRow(int row);
    void setColumn(int column);
    int getRow();
    int getColumn();
    public String checkWinner();
    void setLevel(int level);
    int getLevel();
    void createUI();
    void printGame();
    boolean isFull();
    boolean changeState(int x,int y,String state,boolean back_move);

}
