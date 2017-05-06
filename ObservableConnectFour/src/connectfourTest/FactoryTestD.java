package connectfourTest;

import static org.junit.Assert.*;
import org.junit.Test;
import connectfour.ConnectFour;
import connectfour.ConnectFourFactory;
import connectfour.GameType;
import connectfour.MultiPlayerConnectFour;


/**
 * Tests the Factory for a new MultiplayerGame with
 * an existing SinglePlayer game
 * @author Arturo Muller
 */
public class FactoryTestD {
	
	ConnectFour game;
	
	@Test
	public void getMultiPlayerGameInformerAndInstance() {
		game = ConnectFourFactory.getConnectFour(GameType.SinglePlayer);
		game = ConnectFourFactory.getConnectFour(GameType.MultiPlayer);
		assertTrue(game instanceof MultiPlayerConnectFour);
	}
	

}
