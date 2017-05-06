package connectfourTest;

import static org.junit.Assert.*;
import org.junit.Test;
import connectfour.ConnectFour;
import connectfour.ConnectFourFactory;
import connectfour.GameType;
import connectfour.MultiPlayerConnectFour;


/**
 * Tests the Factory for a new SingleplayerGame with a new informer
 * and for a MultiplayerGame with an existing informer  
 * @author Arturo Muller
 */
public class FactoryTestB {
	
	ConnectFour game;
	
	
	@Test
	public void getMultiPlayerGameNoInformer() {
		game = ConnectFourFactory.getConnectFour(GameType.MultiPlayer);
		assertTrue(game instanceof MultiPlayerConnectFour);
	}
	

}
