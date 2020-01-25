package com.assignment.TicTacToeGame;

public interface GameWithWinningCriteria extends GameWithInterface{

    @Override
    boolean isExists();

    @Override
    void setColumn(int column);

    @Override
    void setRow(int row);

    @Override
    int getColumn();

    @Override
    int getRow();

    void setRowCriteria(int rowCriteria);
    void setColumnCriteria(int columnCriteria);
    void setDiagonalCriteria(int diagonalCriteria);

    int getRowCriteria();
    int getColumnCriteria();
    int getDiagonalCriteria();

}
