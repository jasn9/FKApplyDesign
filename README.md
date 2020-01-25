# FKApplyDesign

Design and Implemente Tic-Tac-Toe Game API

Interfaces:-

1> Design starts with Game as abstract interface of TicTacToe game with infinite row and column.

2> This gets extend by GameWithInterface Interface and transform game into finite row and column
by defining row and column of grid.

3> Now GameWithWinningCriteria Interface extends GameWithInterface and defines criteria of winning
of row ,column , and diagonal.

4> GamePlayer Interface is abstract type of players which play game. 

CLasses:-

1> GameUI class implements GameWithWinningCriteria and defines UI datastructure of grid. Also defines 
behavious of displaying grid, change in grid, checking if anyone win, then finding if grid is 
full or not.

2> PlayGame class is actual class where gameUI interacts with user, it has behaviour PlayWithHuman , 
PlayWithComputer with common behaviour play where user play game.

3> Human Class implements GamePlayer and defines Human Player.

4> Computer Class implements GamePlayer and defines Computer Player.

5> Main Class starts PlayGame class which starts game.

Why this design?

1> Well for one we can manipulate the size of grid.

2> Number of players can be dynamic.

3> Winning criteria is also dynammic.

4> Anyone can also define their own implementation of grid.

5> Make move function in Human and computer is purely dynammic and can be designed in any way
independently.
