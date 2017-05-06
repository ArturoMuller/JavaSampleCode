package connectfourTest;

import static org.junit.Assert.*;


import org.junit.Test;

import connectfour.ConnectFour;
import connectfour.ConnectFourFactory;
import connectfour.ConnectFourListener;
import connectfour.GameType;
import connectfour.chipColor;



/**
 * Creates a MultiPlayer game with pre-Registered Observers
 * @author Arturo Muller
 */
public class FactoryTestC {
	
	ConnectFour game;
	String ob1start;
	String ob2start;
	
	@Test
	public void getMultiPlayerGameNoInformer() {
		ConnectFourListener a = new Observer1();
		ConnectFourListener b = new Observer2();
		ConnectFourFactory.registerObserver(a);
		ConnectFourFactory.registerObserver(b);
		
		game = ConnectFourFactory.getConnectFour(GameType.MultiPlayer);
		game.gameStart();
		assertEquals(ob1start, "start");
		assertEquals(ob2start, "start");
		
	}
	
	
	

	public class Observer1 implements ConnectFourListener{

		@Override
		public void gameStart() {
			ob1start = "start";
		}

		@Override
		public void chipInserted(chipColor c, int i, int j) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void gameWon(int[][] winningMove, chipColor c) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class Observer2 implements ConnectFourListener{

		@Override
		public void gameStart() {
			ob2start = "start";
			
		}

		@Override
		public void chipInserted(chipColor c, int i, int j) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void gameWon(int[][] winningMove, chipColor c) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
