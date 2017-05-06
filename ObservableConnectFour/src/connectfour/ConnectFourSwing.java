package connectfour;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * This class creates a Graphic User Interface of a ConnectFourGame
 * it registers itself through the factory and makes sure that any instance
 * called is of the current game being played
 * @author Arturo Muller
 */
public class ConnectFourSwing extends JFrame implements ConnectFourListener {

	/**
	 * since this class extends JFrame it must contain this ID for its
	 * serializing property
	 */
	private static final long serialVersionUID = 1L;
	JPanel p = new JPanel();
	JButton s = new JButton("Single Player");
	ConnectFourFactory gameMaker;
	static ConnectFour game;
	JButton m = new JButton("Multi Player");
	JLabel t = new JLabel("", (int) JFrame.CENTER_ALIGNMENT);
	BoardButton buttons[][] = new BoardButton[6][7];
	Color panelColor = p.getBackground ();

	ConnectFourSwing() {
		super("Connect Four");
		setSize(800, 800);
		setResizable(true);
		p.setLayout(new GridLayout(7, 8));
		ConnectFourFactory.registerObserver(this);
		s.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game = ConnectFourFactory.getConnectFour(GameType.SinglePlayer);
				game.gameStart();
			}
		});
		m.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game = ConnectFourFactory.getConnectFour(GameType.MultiPlayer);
				game.gameStart();
			}
		});
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				buttons[i][j] = new BoardButton(i, j);
				int column = buttons[i][j].getJ();
				buttons[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (game != null) {
							try {
								t.setText("");
								game.insertChip(column);
							} catch (Throwable x) {
								t.setText(x.getMessage());
							}
						}
					}
				});
				p.add(buttons[i][j]);
			}
		}
		p.add(t);
		p.add(s);
		p.add(m);
		add(p);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
    
	/**
	 * implements that this game has started and
	 * displays it on the board
	 */
	@Override
	public void gameStart() {
		t.setText("Game Started");

		for (BoardButton bs[] : buttons) {
			for (BoardButton b : bs) {
				b.setBackground(null);
				b.setForeground(null);
				b.setBorder(UIManager.getBorder("Button.border"));
			}
		}
	}
	
	/**
	 * implements that a a chip has been inserted
	 * and displays it on the board
	 */
	@Override
	public void chipInserted(chipColor c, int i, int j) {
		Border inBetween = new LineBorder(panelColor, 3);
		if (c == chipColor.red) {
			buttons[i][j].setBackground(Color.RED);
			buttons[i][j].setBorder(inBetween);
			buttons[i][j].setOpaque(true);
		} else {
			buttons[i][j].setBackground(Color.BLACK);
			buttons[i][j].setBorder(inBetween);
			buttons[i][j].setOpaque(true);
		}

	}

	/**
	 * implements that a winning move has been done
	 * and displays it on the board
	 */
	@Override
	public void gameWon(int[][] winningMove, chipColor c) {
		t.setText("WON");
		Border thickBorder = new LineBorder(Color.YELLOW, 12);
		for (int i = 0; i < winningMove.length; i++) {
			buttons[winningMove[i][0]][winningMove[i][1]].setBorder(thickBorder);
		}
	}
}
