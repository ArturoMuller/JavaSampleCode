package connectfour;

/**
 * This class implements ConnectFour for 2 players
 * it is Observable by implementing the ConnectFourListener Interface
 * it contains the builder pattern for players to register their names 
 * parameters are immutable
 * red will be the first move
 * @author Arturo Muller 
 * @version 1.0 August 7, 2016
 */
public class MultiPlayerConnectFour extends ConnectFour {
	protected final String player1;
	private final String player2;

	/**
	 * This method takes in the board position where the chip will be inserted
	 * and then notifies the observers which row and column to place the chip
	 * it also toggles between red and black red being the first move
	 * @param  Column where chip will be inserted
	 * @throws IndexOutOfBoundsException with message 
	 * @throws IllegalArgumentException with message
	 */
	public void insertChip(int column) {
		int row = 0;
		try {
			row = chipDrop(column);
		} catch (Throwable t) {
			throw t;
		}
		turn = (turn == chipColor.red) ? chipColor.black : chipColor.red;
		table[row][column] = turn;
		notifyObserversChipInsert(row, column, turn);
		int winningMove[][] = checkWin(row, column, turn);
		if (winningMove != null) {
			notifyObserversGameWon(winningMove, turn);
		}
	}

	 /**
     * Constructs and initializes a MultiPlayerConnectFour
     * contains a mandatory field GameInformer 
     * contains optional fields player names 
     * @param GameInformer of ConnectFour Listeners
     */
	protected static class Builder {
		private final GameInformer b;
		private String player1;
		private String player2;

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
	     * Optional Parameter Player2 name
	     * @param player2 name of player2
	     * @return Returns a reference to the Builder being constructed.
	     */
		public Builder Player2(String player2) {
			this.player2 = player2;
			return this;
		}
		
	    /**
	     * @return Returns a reference to a MultiplayerConnectFour being constructed. 
	     */
		public MultiPlayerConnectFour build() {
			return new MultiPlayerConnectFour(this);
		}
	}

	private MultiPlayerConnectFour(Builder builder) {
		informer = builder.b;
		player1 = builder.player1;
		player2 = builder.player2;
	}
	
	/**
	 * returns the name of player1
	 * @return player 1 name
	 */
	public String getPlayer1() {
		return player1;
	}

	/**
	 * returns the name of player2
	 * @return player 2 name
	 */
	public String getPlayer2() {
		return player2;
	}



}
