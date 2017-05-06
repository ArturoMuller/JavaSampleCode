package connectfourTest;


import org.junit.Before;
import org.junit.Test;
import connectfour.ConnectFour;
import connectfour.ConnectFourFactory;
import connectfour.ConnectFourListener;
import connectfour.GameType;
import connectfour.chipColor;

/**
 * hard to test for random output
 * @author Arturo Muller
 *
 */
public class SinglePlayerGameTest implements ConnectFourListener {
	ConnectFour g;
	int[][] winningMove;
	chipColor winningColor;
	chipColor[][] board;
	String display;
	
	@Before
	public void initialize(){
		ConnectFourFactory.registerObserver(this);
		g = ConnectFourFactory.getConnectFour(GameType.SinglePlayer);
		board = new chipColor[6][7];
	}
	
	@Test
	public void test() {
		g.gameStart();
		g.insertChip(0);
		g.insertChip(0);
		g.insertChip(0);
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
