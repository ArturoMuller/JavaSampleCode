package connectfour;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

/**
 * Represents the board positions of the ConnecFourSwing class
 * stores each position
 * @author Arturo Muller
 */
class BoardButton extends JButton implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton chip = new JButton();
	private final int i;
	private final int j;

	/**
	 * constructor for a position
	 * @param row
	 * @param col
	 */
	public BoardButton(int row, int col) {
		this.i = row;
		this.j = col;
		this.addActionListener(this);
	}

	/**
	 * Sets a standard color for an action
	 */
	public void actionPerformed(ActionEvent e) {
		this.setForeground(Color.BLACK);
	}

	/**
	 * returns the row of this position
	 * @return int of this positions row
	 */
	public int getI() {
		return i;
	}
	/**
	 * returns the column of this position
	 * @return int of this positions column
	 */
	public int getJ() {
		return j;
	}
}
