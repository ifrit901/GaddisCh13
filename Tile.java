package ch13java6thedition;

/**
 * The Tile class models the squares that will appear 
 * on the TicTacToe board.
 * @author craig
 */
import javax.swing.*;
public class Tile extends JFrame {

	private JLabel label;
	private String symbol;

	public Tile(String symbol, JLabel label) {
		this.symbol = symbol;
		this.label = label;
	}
	public void setFont() {

	}
	public void setSize() {
		
	}
	
}
