package connectfour;

import java.util.Random;

/**
 * This class implements ConnectFour for 1 player
 * it is Observable by implementing the ConnectFourListener Interface
 * it contains the builder pattern for players to register their names 
 * parameters are immutable
 * red will be the first move and the computer will play black
 * @author Arturo Muller 
 * @version 1.0 August 7, 2016
 */
public class SinglePlayerConnectFour extends ConnectFour {
	private final String player1;
	private Random generator = new Random();


	/**
	 * This method takes in the board position where the chip will be inserted
	 * and then notifies the observers which row and column to place the red chip
	 * then it will call another method that is playing as the computer
	 * it will inform the observer where to place the black chip
	 * @param  Column where chip will be inserted
	 * @throws IndexOutOfBoundsException with message 
	 * @throws IllegalArgumentException with message
	 */
	public void insertChip(int column) {
		if (column < 0 && column > 6) {
			throw new IllegalArgumentException("Column out of bounds");
		}
		int row;
		try{ 
			row = chipDrop(column);
			
		}
		catch(Throwable e){
			throw e;
		}
		table[row][column] = chipColor.red;
		notifyObserversChipInsert(row, column, chipColor.red);
		int winningMove[][] = checkWin(row, column, chipColor.red);
		if (winningMove != null) {
			notifyObserversGameWon(winningMove, chipColor.red);
			return;
		}
		if (hasSpace()) {
			computerMove(chipColor.black);
			}
		}
	

	private void computerMove(chipColor c) {
		for (int i = 0; i < table[0].length; i++) {
			if(table[0][i] == null){
			int row = chipDrop(i);
			table[row][i] = c;
			int[][] winningMove = checkWin(row, i, c);
			if (winningMove != null) {
				notifyObserversChipInsert(row, i, c);
				notifyObserversGameWon(winningMove, c);
				return;
			}
			table[row][i] = null;
			}
		}
		int randomNum = generator.nextInt(availableColumns.size());
		int colComputer = availableColumns.get(randomNum);
		int rowComputer = chipDrop(colComputer);
		table[rowComputer][colComputer] = c;
		notifyObserversChipInsert(rowComputer, colComputer, c);
	}
	
	 /**
     * Constructs and initializes a SinglePlayerConnectFour
     * contains a mandatory field GameInformer 
     * contains optional fields player names 
     * @param GameInformer of ConnectFour Listeners
     */	
	protected static class Builder {
		private final GameInformer b;
		private String player1;

	    /**
	     * Constructor
	     * adds the GameInformer to ConnectFour
	     * @param GameInformer 
	     */
		public Builder(GameInformer b) {
			this.b = b;
		}
		
	    /**
	     * Optional Parameter Player1 name
	     * @param player1 name of player1
	     * @return Returns a reference to the Builder being constructed.
	     */
		public Builder Player1(String player1) {
			this.player1 = player1;
			return this;
		}
		
	    /**
	     * @return Returns a reference to a MultiplayerConnectFour being constructed. 
	     */
		public SinglePlayerConnectFour build() {
			return new SinglePlayerConnectFour(this);
		}
	}
	
	private SinglePlayerConnectFour(Builder builder) {
		informer = builder.b;
		player1 = builder.player1;
	}

	/**
	 * returns the name of player1
	 * @return player 1 name
	 */
	public String getPlayer1() {
		return player1;
	}


	
}
