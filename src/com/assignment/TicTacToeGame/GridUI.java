package com.assignment.TicTacToeGame;

public interface GridUI extends Grid {

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

    String getRes();

    void setRowCriteria(int rowCriteria);
    void setColumnCriteria(int columnCriteria);
    void setDiagonalCriteria(int diagonalCriteria);
    //void setLevel(int level);

    int getRowCriteria();
    int getColumnCriteria();
    int getDiagonalCriteria();

    String checkWinner();
    void setLevel(int level);
    int getLevel();
    void createUI();
    void printGame();
    boolean isFull();
    boolean changeState(int x,int y,String state,boolean back_move);
    GridUI getBoard(int x,int y);
    //int getLevel();

}
