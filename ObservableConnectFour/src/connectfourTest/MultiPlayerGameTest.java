package connectfourTest;
import static org.junit.Assert.*;


import java.util.Arrays;


import org.junit.Before;
import org.junit.Test;

import connectfour.ConnectFour;
import connectfour.ConnectFourFactory;
import connectfour.ConnectFourListener;

import connectfour.GameInformer;
import connectfour.GameType;
import connectfour.MultiPlayerConnectFour;
import connectfour.SinglePlayerConnectFour;
import connectfour.chipColor;

public class MultiPlayerGameTest implements ConnectFourListener {
	
	
	ConnectFour g;
	int[][] winningMove;
	chipColor winningColor;
	chipColor[][] board;
	String display;
	
	@Before
	public void initialize(){
		ConnectFourFactory.registerObserver(this);
		g = ConnectFourFactory.getConnectFour(GameType.MultiPlayer);
		board = new chipColor[6][7];
	}
	

	public void finalize(){
		g = null;
		winningMove = null;
		winningColor = null;
		board = null;
		display = null;
	}
	

	@Test(expected = IllegalArgumentException.class)
	public void testFirstColumnInsert() {
		g.gameStart();
		assertEquals(display, "start");
		g.insertChip(0);
		g.insertChip(0);
		g.insertChip(0);
		g.insertChip(0);
		g.insertChip(0);
		g.insertChip(0);
		g.insertChip(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLastColumnInsert() {
		g.gameStart();
		g.insertChip(6);
		g.insertChip(6);
		g.insertChip(6);
		g.insertChip(6);
		g.insertChip(6);
		g.insertChip(6);
		g.insertChip(6);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testOutOfBoundsColumnInsert() {
		g.gameStart();
		g.insertChip(7);
	}
	
	
	@Test
	public void winSouth() {
		g.gameStart();
		g.insertChip(6);
		g.insertChip(5);
		g.insertChip(6);
		g.insertChip(5);
		g.insertChip(6);
		g.insertChip(5);
		g.insertChip(6);
		assertTrue(winningColor == chipColor.red);
		int[][] correctMove = {{2,6},{3,6},{4,6},{5,6}};
		assertTrue(Arrays.deepEquals(winningMove, correctMove));
	}
	
	@Test
	public void winEast() {
		g.gameStart();
		g.insertChip(0);
		g.insertChip(6);
		g.insertChip(0);
		g.insertChip(4);
		g.insertChip(1);
		g.insertChip(5);
		g.insertChip(2);
		g.insertChip(3);
		assertTrue(winningColor == chipColor.black);
		int[][] correctMove = {{5,3},{5,4},{5,5},{5,6}};
		assertTrue(Arrays.deepEquals(winningMove, correctMove));
	}
	
	
	@Test
	public void winWest() {
		g.gameStart();
		g.insertChip(0);
		g.insertChip(4);
		g.insertChip(1);
		g.insertChip(5);
		g.insertChip(2);
		g.insertChip(6);
		g.insertChip(3);
		assertTrue(winningColor == chipColor.red);
		int[][] correctMove = {{5,3},{5,2},{5,1},{5,0}};
		assertTrue(Arrays.deepEquals(winningMove, correctMove));
	}
	
	@Test
	public void winCenterEastWest() {
		g.gameStart();
		g.insertChip(0);
		g.insertChip(4);
		g.insertChip(1);
		g.insertChip(5);
		g.insertChip(3);
		g.insertChip(6);
		g.insertChip(2);
		assertTrue(winningColor == chipColor.red);
		int[][] correctMove = {{5,2},{5,3},{5,1},{5,0}};
		assertTrue(Arrays.deepEquals(winningMove, correctMove));
	}
	
	
	@Test
	public void winNorthEast() {
		g.gameStart();
		g.insertChip(3);
		g.insertChip(2);
		g.insertChip(1);
		g.insertChip(1);
		g.insertChip(2);
		g.insertChip(3);
		g.insertChip(3);
		g.insertChip(2);
		g.insertChip(4);
		g.insertChip(3);
		g.insertChip(4);
		g.insertChip(0);
		assertTrue(winningColor == chipColor.black);
		int[][] correctMove = {{5,0},{4,1},{3,2},{2,3}};
		assertTrue(Arrays.deepEquals(winningMove, correctMove));
	}
	
	@Test
	public void winNorthWest() {
		g.gameStart();
		g.insertChip(3);
		g.insertChip(4);
		g.insertChip(5);
		g.insertChip(5);
		g.insertChip(4);
		g.insertChip(3);
		g.insertChip(3);
		g.insertChip(4);
		g.insertChip(2);
		g.insertChip(3);
		g.insertChip(2);
		g.insertChip(6);
		assertTrue(winningColor == chipColor.black);
		int[][] correctMove = {{5,6},{4,5},{3,4},{2,3}};
		assertTrue(Arrays.deepEquals(winningMove, correctMove));
		
	}
	
	@Test
	public void winSouthWest() {
		g.gameStart();
		g.insertChip(0);
		g.insertChip(1);
		g.insertChip(1);
		g.insertChip(2);
		g.insertChip(2);
		g.insertChip(3);
		g.insertChip(2);
		g.insertChip(3);
		g.insertChip(3);
		g.insertChip(6);
		g.insertChip(3);
		assertTrue(winningColor == chipColor.red);
		int[][] correctMove = {{2,3},{3,2},{4,1},{5,0}};
		assertTrue(Arrays.deepEquals(winningMove, correctMove));
	}
	
	@Test
	public void winSouthEast() {
		g.gameStart();
		g.insertChip(3);
		g.insertChip(2);
		g.insertChip(2);
		g.insertChip(1);
		g.insertChip(1);
		g.insertChip(0);
		g.insertChip(1);
		g.insertChip(0);
		g.insertChip(0);
		g.insertChip(4);
		g.insertChip(0);
		assertTrue(winningColor == chipColor.red);
		int[][] correctMove = {{2,0},{3,1},{4,2},{5,3}};
		assertTrue(Arrays.deepEquals(winningMove, correctMove));
	}
	
	@Test
	public void midNorthEasttoSouthWest() {
		g.gameStart();
		g.insertChip(0);
		g.insertChip(1);
		g.insertChip(2);
		g.insertChip(3);
		g.insertChip(4);
		g.insertChip(2);
		g.insertChip(3);
		g.insertChip(4);
		g.insertChip(2);
		g.insertChip(3);
		g.insertChip(3);
		g.insertChip(4);
		g.insertChip(1);
		assertTrue(winningColor == chipColor.red);
		int[][] correctMove = {{4,1},{3,2},{2,3},{5,0}};
		assertTrue(Arrays.deepEquals(winningMove, correctMove));
	}
	
	@Test
	public void midNorthWestToSouthEast() {
		g.gameStart();
		g.insertChip(6);
		g.insertChip(5);
		g.insertChip(4);
		g.insertChip(3);
		g.insertChip(2);
		g.insertChip(4);
		g.insertChip(3);
		g.insertChip(2);
		g.insertChip(4);
		g.insertChip(3);
		g.insertChip(3);
		g.insertChip(2);
		g.insertChip(5);
		assertTrue(winningColor == chipColor.red);
		int[][] correctMove = {{4,5},{3,4},{2,3},{5,6}};
		assertTrue(Arrays.deepEquals(winningMove, correctMove));
	}
	
	
	
	@Test
	public void fivechipsOrMoreTest() {
		g.gameStart();
		g.insertChip(0);
		g.insertChip(0);
		g.insertChip(1);
		g.insertChip(1);
		g.insertChip(3);
		g.insertChip(3);
		g.insertChip(4);
		g.insertChip(4);
		g.insertChip(2);
		assertTrue(winningColor == chipColor.red);
		int[][] correctMove = {{5,2},{5,3},{5,4},{5,1}};
		assertTrue(Arrays.deepEquals(winningMove, correctMove));
	}

	@Test
	public void builderTest(){
		MultiPlayerConnectFour gg = (MultiPlayerConnectFour) ConnectFourFactory.getConnectFourWithPlayerNames(GameType.MultiPlayer, "Arturo", "Jhon");   
		assertEquals("Arturo", gg.getPlayer1());
		assertEquals("Jhon", gg.getPlayer2());
	}
	
	@Test
	public void builderTest2(){
		SinglePlayerConnectFour gg = (SinglePlayerConnectFour) ConnectFourFactory.getConnectFourWithPlayerNames(GameType.SinglePlayer, "Arturo");   
		assertEquals("Arturo", gg.getPlayer1());
	}

	@Override
	public void gameStart() {
		display = "start";
	}



	@Override
	public void chipInserted(chipColor c, int i, int j) {
		board[i][j] = c;		
	}



	@Override
	public void gameWon(int[][] winningMove, chipColor c) {
		this.winningColor = c;
		this.winningMove = winningMove;
	}
	
	@SuppressWarnings("unused")
	private void printBoard(){
		for(chipColor a[]: board){
			for(chipColor b: a){
				System.out.print(b);
			}
			System.out.print("\n");
		}
	}
	
}
