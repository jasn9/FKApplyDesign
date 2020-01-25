package com.assignment.TicTacToeGame;

public interface GameWithWinningCriteria extends GameWithInterface{

    @Override
    void setExists(boolean exists);

    @Override
    boolean getExists();

    @Override
    void setColumn(int column);

    @Override
    int getColumn();

    @Override
    void setRow(int row);

    @Override
    int getRow();

    void setRowCriteria(int rowCriteria);
    void setColumnCriteria(int columnCriteria);
    void setDiagonalCriteria(int diagonalCriteria);

    int getRowCriteria();
    int getColumnCriteria();
    int getDiagonalCriteria();

}
