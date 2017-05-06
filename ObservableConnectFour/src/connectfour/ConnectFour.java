package connectfour;

import java.util.ArrayList;
import java.util.List;

/**
 * A ConnectFour is a top Level class for a variation of 
 * ConnectFour is observable and contains a GameInformer for
 * to inform its Observers on what is happening
 * @author Arturo Muller 
 * @version 1.0 August 7, 2016
 */
abstract public class ConnectFour {
	protected static GameInformer informer;
	chipColor turn = chipColor.black;
	final int rows = 6;
	final int columns = 7;
	chipColor table[][] = new chipColor[rows][columns];
	boolean acceptChips = true;
	protected List<Integer> availableColumns = new ArrayList<Integer>();
	
	/**
	 * @param column on the table 
	 */
	abstract public void insertChip(int column);

	public void gameStart() {
		table = new chipColor[rows][columns];
		acceptChips = true;
		turn = chipColor.black;
		for (int i = 0; i < columns; i++) {
			availableColumns.add(i);
		}
		notifyObserversGameStart();
	}

	/**
	 * Takes in table column where they want to insert the chip 
	 * and returns in what row it drops or Exceptions if
	 * there is no space
	 * @param column
	 * @return row number where to insert chip or -1 if there is no space in
	 *         that column
	 * @throws IndexOutOfBoundsException
	 * @throws IllegalArgumentException
	 */
	protected int chipDrop(int column) {
		if (column < 0 || column > 6) {
			throw new IndexOutOfBoundsException("Column out of bounds");
		}
		if (hasSpace(column))
			for (int i = 5; i >= 0; i--) {
				if (table[i][column] == null) {
					return i;
				}
			}
		throw new IllegalArgumentException("No space for Chip.");
	}

	protected int[][] checkWin(int i, int j, chipColor c) {
		int iTemp;
		int jTemp;
		int pos = 1;
		int win = 1;
		int[][] winningMove = new int[4][2];
		winningMove[0][0] = i;
		winningMove[0][1] = j;
		for (int d = 1; d <= 7; d++) {
			iTemp = i;
			jTemp = j;
			switch (d) {
			// check South
			case 1:
				while (trueSouth(iTemp, j) && c == table[++iTemp][j] && win < 4) {
					win++;
					winningMove[pos][0] = iTemp;
					winningMove[pos][1] = j;
					pos++;
				}
				break;
			// reset and check East
			case 2:
				pos = 1;
				win = 1;
				while (trueEast(i, jTemp) && c == table[i][++jTemp] && win < 4) {
					win++;
					winningMove[pos][0] = i;
					winningMove[pos][1] = jTemp;
					pos++;
				}
				break;
			// check West
			case 3:
				jTemp = j;
				while (trueWest(i, jTemp) && c == table[i][--jTemp] && win < 4) {
					win++;
					winningMove[pos][0] = i;
					winningMove[pos][1] = jTemp;
					pos++;
				}
				break;
			// reset and check NorthEast
			case 4:
				pos = 1;
				win = 1;
				while (trueNorthEast(iTemp, jTemp) && c == table[--iTemp][++jTemp] && win < 4) {
					win++;
					winningMove[pos][0] = iTemp;
					winningMove[pos][1] = jTemp;
					pos++;
				}
				break;
			// check SouthWest
			case 5:
				while (trueSouthWest(iTemp, jTemp) && c == table[++iTemp][--jTemp] && win < 4) {
					win++;
					winningMove[pos][0] = iTemp;
					winningMove[pos][1] = jTemp;
					pos++;
				}
				break;
			// reset and check NorthWest
			case 6:
				pos = 1;
				win = 1;
				while (trueNorthWest(iTemp, jTemp) && c == table[--iTemp][--jTemp] && win < 4) {
					win++;
					winningMove[pos][0] = iTemp;
					winningMove[pos][1] = jTemp;
					pos++;
				}
				break;
			// check SouthEast
			case 7:
				while (trueSouthEast(iTemp, jTemp) && c == table[++iTemp][++jTemp] && win < 4) {
					win++;
					winningMove[pos][0] = iTemp;
					winningMove[pos][1] = jTemp;
					pos++;
				}
				break;
			}
			// check if there is four connected
			if (win > 3) {
				acceptChips = false;
				return winningMove;
			}
		}
		return null;
	}

	protected boolean hasSpace() {
		if (!acceptChips) {
			return false;
		}
		for (int i = 0; i < columns; i++) {
			if (table[0][i] != null) {
				availableColumns.remove(i);
			}
		}
		if (availableColumns.isEmpty()) {
			acceptChips = false;
			return false;
		}
		return true;
	}

	private boolean hasSpace(int col) {
		if (acceptChips) {
			return table[0][col] == null;
		}
		return false;
		
	}

	private boolean trueSouth(int i, int j) {
		return (i + 1) < rows && table[i + 1][j] != null;
	}

	private boolean trueEast(int i, int j) {
		return (j + 1) < columns && table[i][j + 1] != null;
	}

	private boolean trueWest(int i, int j) {
		return (j - 1) >= 0 && table[i][j - 1] != null;
	}

	private boolean trueNorthEast(int i, int j) {
		return (j + 1) < columns && (i - 1) >= 0 && table[i - 1][j + 1] != null;
	}

	private boolean trueNorthWest(int i, int j) {
		return (i - 1) >= 0 && (j - 1) >= 0 && table[i - 1][j - 1] != null;
	}

	private boolean trueSouthEast(int i, int j) {
		return (i + 1) < rows && (j + 1) < columns && table[i + 1][j + 1] != null;
	}

	private boolean trueSouthWest(int i, int j) {
		return (i + 1) < rows && (j - 1) >= 0 && table[i + 1][j - 1] != null;
	}

	protected GameInformer getinformer() {
		return informer;
	}

	/**
	 * Registers a ConnectFourListener to this classes
	 * ConnectFour GameInformer
	 * @param o ConnectFourListener
	 */
	public void registerObserver(ConnectFourListener o) {
		informer.registerObserver(o);
	}

	/**
	 * Removes a ConnectFourListener from this classes
	 * ConnectFour GameInformer
	 * @param o ConnectFourListener
	 */
	public void removeObserver(ConnectFourListener o) {
		informer.removeObserver(o);
	}

	/**
	 * Notifies ConnectFourListeners from this classes
	 * ConnectFour GameInformer of a chipInsertion
	 * @param o ConnectFourListener
	 */
	public void notifyObserversChipInsert(int i, int j, chipColor c) {
		informer.notifyObserversChipInsert(i, j, c);
	}

	/**
	 * Notifies ConnectFourListeners from this classes
	 * ConnectFour GameInformer of a a WinningMove
	 * @param o ConnectFourListener
	 */
	public void notifyObserversGameWon(int[][] winningMove, chipColor c) {
		informer.notifyObserversGameWon(winningMove, c);
	}

	/**
	 * Notifies ConnectFourListeners from this classes
	 * ConnectFour GameInformer that the game has started
	 * @param o ConnectFourListener
	 */
	public void notifyObserversGameStart() {
		informer.notifyObserversGameStart();
	}
	
}
