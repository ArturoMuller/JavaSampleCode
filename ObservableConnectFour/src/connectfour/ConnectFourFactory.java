package connectfour;

/**
 * Factory class that creates enforces single ConnectFour games of its
 * subclasses SinglePlayerConnectFour or MultiPlayerConnectFour allows to
 * pre-register ConnectFourListeners and Creates a GameInformer instance if it
 * is not previously created assures that the ConnectFourListeners will always
 * be maintained it also returns a view of a ConnectFourGame if asked
 * 
 * @author Arturo Muller
 * @version 1.0 August 7, 2016
 */
public class ConnectFourFactory {

	private static ConnectFour gameInstance = null;

	private static GameInformer informer = null;

	/**
	 * Creates a instance of Connect ConnectFourGame either a
	 * MultiPlayerConnectFour or a SinglePlayerConnectFour assures that the
	 * Listeners are maintained
	 * 
	 * @param t GameType
	 *            
	 * @return InstanceOf ConnectFourGame
	 */
	public static ConnectFour getConnectFour(GameType t) {
		if (t == GameType.MultiPlayer) {
			if (gameInstance == null) {
				if (informer == null) {
					informer = GameInformer.getInstance();
				}
				gameInstance = new MultiPlayerConnectFour.Builder(informer).build();
			} else if (!(gameInstance instanceof MultiPlayerConnectFour)) {
				gameInstance = new MultiPlayerConnectFour.Builder(informer).build();
			}
		} else {
			if (gameInstance == null) {
				if (informer == null) {
					informer = GameInformer.getInstance();
				}
				gameInstance = new SinglePlayerConnectFour.Builder(informer).build();
			} else if (!(gameInstance instanceof SinglePlayerConnectFour)) {
				gameInstance = new SinglePlayerConnectFour.Builder(informer).build();
			}
		}
		return gameInstance;
	}

	/**
	 * Creates a instance of Connect ConnectFourGame either a
	 * MultiPlayerConnectFour or a SinglePlayerConnectFour assures that the
	 * Listeners are maintained allows for insertion of player names
	 * 
	 * @param player1 name of red chip player
	 *            
	 * @param player2 name of black chip (MultiplayerConnectFour only)
	 *            
	 * @param t GameType
	 *            
	 * @return InstanceOf ConnectFourGame
	 */
	public static ConnectFour getConnectFourWithPlayerNames(GameType t, String player1, String player2) {
		if (t == GameType.MultiPlayer) {
			if (gameInstance == null) {
				if (informer == null) {
					informer = GameInformer.getInstance();
				}
				gameInstance = new MultiPlayerConnectFour.Builder(informer).Player1(player1).Player2(player2).build();
			} else if (!(gameInstance instanceof MultiPlayerConnectFour)) {
				gameInstance = new MultiPlayerConnectFour.Builder(informer).Player1(player1).Player2(player2).build();
			} else if ((gameInstance instanceof MultiPlayerConnectFour)
					&& ((((MultiPlayerConnectFour) gameInstance).getPlayer1() == null)
							|| !(((MultiPlayerConnectFour) gameInstance).getPlayer1().equals(player1))
							|| (((MultiPlayerConnectFour) gameInstance).getPlayer2() == null)
							|| !((MultiPlayerConnectFour) gameInstance).getPlayer2().equals(player2))) {
				gameInstance = new MultiPlayerConnectFour.Builder(informer).Player1(player1).Player2(player2).build();
			}
		} else {
			if (gameInstance == null) {
				if (informer == null) {
					informer = GameInformer.getInstance();
				}
				gameInstance = new SinglePlayerConnectFour.Builder(informer).Player1(player1).build();
			} else if (!(gameInstance instanceof SinglePlayerConnectFour)) {
				gameInstance = new SinglePlayerConnectFour.Builder(informer).Player1(player1).build();
			} else if ((gameInstance instanceof SinglePlayerConnectFour)
					&& ((((SinglePlayerConnectFour) gameInstance).getPlayer1() == null)
							|| !(((SinglePlayerConnectFour) gameInstance).getPlayer1().equals(player1)))) {

				gameInstance = new SinglePlayerConnectFour.Builder(informer).Player1(player1).build();
			}
		}
		return gameInstance;
	}
	
	/**
	 * Creates a instance of Connect ConnectFourGame either a
	 * MultiPlayerConnectFour or a SinglePlayerConnectFour assures that the
	 * Listeners are maintained allows for insertion of player names
	 * 
	 * @param player1 name of red chip player
	 *                       
	 * @param t GameType
	 *            
	 * @return InstanceOf ConnectFourGame
	 */
	public static ConnectFour getConnectFourWithPlayerNames(GameType t, String player1) {
		if (t == GameType.MultiPlayer) {
			if (gameInstance == null) {
				if (informer == null) {
					informer = GameInformer.getInstance();
				}
				gameInstance = new MultiPlayerConnectFour.Builder(informer).Player1(player1).build();
			} else if (!(gameInstance instanceof MultiPlayerConnectFour)) {
				gameInstance = new MultiPlayerConnectFour.Builder(informer).Player1(player1).build();
			} else if ((gameInstance instanceof MultiPlayerConnectFour)
					&& (((MultiPlayerConnectFour) gameInstance).getPlayer1() == null)
							|| !(((MultiPlayerConnectFour) gameInstance).getPlayer1().equals(player1))
			) {
				gameInstance = new MultiPlayerConnectFour.Builder(informer).Player1(player1).build();
			}
		} else {
			if (gameInstance == null) {
				if (informer == null) {
					informer = GameInformer.getInstance();
				}
				gameInstance = new SinglePlayerConnectFour.Builder(informer).Player1(player1).build();
			} else if (!(gameInstance instanceof SinglePlayerConnectFour)) {
				gameInstance = new SinglePlayerConnectFour.Builder(informer).Player1(player1).build();
			} else if ((gameInstance instanceof SinglePlayerConnectFour)
					&& ((((SinglePlayerConnectFour) gameInstance).getPlayer1() == null)
							|| !(((SinglePlayerConnectFour) gameInstance).getPlayer1().equals(player1)))) {

				gameInstance = new SinglePlayerConnectFour.Builder(informer).Player1(player1).build();
			}
		}
		return gameInstance;
	}

	/**
	 * Returns a view of a ConnectFourGame
	 * 
	 * @return a View of a ConnectFourGame
	 */
	public static ConnectFourSwing getConnectFourSwing() {
		return new ConnectFourSwing();
	}

	/**
	 * Register an Observer without initializing a game
	 * @param o ConnectFourListener
	 */
	public static void registerObserver(ConnectFourListener o) {
		if (informer == null) {
			informer = GameInformer.getInstance();
		}
		informer.registerObserver(o);
	}

}
