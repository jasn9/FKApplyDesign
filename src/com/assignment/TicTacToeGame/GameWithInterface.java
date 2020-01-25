package com.assignment.TicTacToeGame;

public interface GameWithInterface extends Game {

    @Override
    boolean isExists();

    void setRow(int row);
    void setColumn(int column);
    int getRow();
    int getColumn();

}
