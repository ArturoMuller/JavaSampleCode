package connectfour;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class that contains a list of ConnectFourListeners
 * and calls the methods that ConnectFourListenersImplement
 * @author Arturo Muller 
 * @version 1.0 August 7, 2016
 */
public class GameInformer {
	private List<ConnectFourListener> observers = new ArrayList<ConnectFourListener>();
	static private GameInformer firstInstance = null;

	private GameInformer() {
	}

	/**
	 * singleton
	 * returns the first instance of this class
	 * @return GameInformer instance
	 */
	public static GameInformer getInstance() {
		if (firstInstance == null) {
			firstInstance = new GameInformer();
		}

		return firstInstance;
	}

	protected void registerObserver(ConnectFourListener o) {
		if(!observers.contains(o)){
		observers.add(o);
		}
	}

	protected void removeObserver(ConnectFourListener o) {
		observers.remove(o);
	}

	protected void notifyObserversChipInsert(int i, int j, chipColor c) {
		for (ConnectFourListener o : observers) {
			o.chipInserted(c, i, j);
		}

	}

	protected void notifyObserversGameWon(int[][] winningMove, chipColor c) {
		for (ConnectFourListener o : observers) {
			o.gameWon(winningMove, c);
		}
	}

	protected void notifyObserversGameStart() {
		for (ConnectFourListener o : observers) {
			o.gameStart();
		}
	}

}
