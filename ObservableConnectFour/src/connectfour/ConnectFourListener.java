package connectfour;

/**
* An object that can be to represent a ConnectFourGame.  The ConnectFour
* objects are created in the ConnectFourFactory.  Different implementers of 
* this class can share a single game.
*/
public interface ConnectFourListener {

    /**
	 * notifies the ConnectFour game has started.
	*/
	public void gameStart();

	/**
	 * notifies that a chip has been inserted 
	 * and gives the position in the board
	 * @param c chipColor
	 * @param i row to place chip
	 * @param j columnt to place chip
	 */
	public void chipInserted(chipColor c, int i, int j);
	
	/** 
	 * notifies which color chip wins the game 
	 * and gives the chip positions in the board
	 * @param winningMove is a 2d array the of 2columns and 4 rows
	 * columns 0 contain the row indexes columns 1 contain the column indexes
	 * paired by row of the winning move 
	 * @param c the chip color of the winner
	 */
	public void gameWon(int[][] winningMove, chipColor c);

}
