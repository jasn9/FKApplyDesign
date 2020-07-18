# FKApplyDesign

<B>Design and Implement Tic-Tac-Toe Game API</B>

<B>Run</B> this application by compiling and executing <I> main.java </I>.

<B>Interfaces:-</B>

1> Design starts with Game as abstract interface of TicTacToe game with infinite row and column.

2> This gets extend by Grid Interface and transform game into finite row and column
by defining row and column of grid.

3> Now GridUI Interface extends GameWithInterface and defines criteria of winning
of row ,column , and diagonal.

4> GamePlayer Interface is abstract type of players which play game. 

5> GridIterator Interface is for lambda.

<B>CLasses:-</B>

1> RectangleUI class implements GridUI and defines UI structure of grid. Also defines 
behavious of displaying grid, change in grid, checking if anyone win, then finding if grid is 
full or not.

2> PlayGame class is actual class where gameUI interacts with user, it has behaviour PlayWithHuman , 
PlayWithComputer with common behaviour play where user play game.

3> Human Class implements GamePlayer and defines Human Player.

4> Computer Class implements GamePlayer and defines Computer Player.

5> Main Class starts PlayGame class which starts game.

6> HexagonalUI implements GridUI and define UI for grid with Hexagonal cells.

7> Leaderboard class.

8> RectangleUIConnect4game extends RectangleUI to perform connect 4 game.

9> CommonGridOperations class implement all grid basic operations.

<B>Why this design?</B>

1> Well for one we can manipulate the size of grid.

2> Number of players can be dynamic.

3> Winning criteria is also dynammic.

4> Anyone can also define their own implementation of grid.

5> Make move function in Human and computer is purely dynammic and can be designed in any way
independently.


