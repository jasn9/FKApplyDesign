package com.assignment.TicTacToeGame;

public interface Grid extends Game {

    @Override
    void setExists(boolean exists);

    @Override
    boolean getExists();

    void setRow(int row);
    void setColumn(int column);
    int getRow();
    int getColumn();


}
