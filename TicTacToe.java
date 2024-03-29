package ch13java6thedition;

/**
 * 11. Tic Tac Toe
 * 
 * Create a GUI application that simulates a game of Tic Tac Toe. Figure 13-35 
 * shows an example of the application's window. The window shown in the 
 * figure uses nine large JLabel components to display the X's and O's. 
 * 
 * One approach in designing this application is to use a two-dimensional int 
 * array to simulate the game board in memory. When the user clicks the new
 * New Game button, the application should step through the array, storing 
 * a random number in the range of 0 through 1 in each element. The number 
 * 0 represents the letter O, and the number 1 represents the letter X. The 
 * JLabel components should then be updated to display the game board. The 
 * application should display a message indicating whether player X won, 
 * player Y won, or it was a tie. 
 * 
 * @author craig
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class TicTacToe extends JFrame {
	
	// Array to hold the values for the 9x9 grid
	int [][] array = new int [3][3];  	

	// Array to hold the tiles that will be eliminated
	// once populated with an X or an O. They will
	// be converted to a list, shuffled and removed
	// once populated.
	Integer [] tiles = {1, 2, 3, 4, 5, 6, 7, 8, 9};

	// Labels to display the X's and O's
	JLabel label1 = new JLabel();
	JLabel label2 = new JLabel();
	JLabel label3 = new JLabel();
	JLabel label4 = new JLabel();
	JLabel label5 = new JLabel();
	JLabel label6 = new JLabel();
	JLabel label7 = new JLabel();
	JLabel label8 = new JLabel();
	JLabel label9 = new JLabel();

	// Panels for the X and O labels
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();
	JPanel panel6 = new JPanel();
	JPanel panel7 = new JPanel();
	JPanel panel8 = new JPanel();
	JPanel panel9 = new JPanel();

	// An array for each of the labels and panels the grid labels
	JLabel [] labelArray = {label1, label2, label3, label4, label5, 
			  label6, label7, label8, label9};
	JPanel [] panelArray = {panel1, panel2, panel3, panel4, panel5, 
			    panel6, panel7, panel8, panel9};
	
	// Lable and panel for win, lose or tie
	JLabel gameOverLabel = new JLabel();
	JPanel gameOverPanel = new JPanel();
		
	// Panel for the game board
	JPanel gridPanel;

	// Random class object for determining player choices and placement
	Random randy;
	Random randyLabel;
	
	// Boolean to determine if anybody won
	boolean xWins = false;
	boolean oWins = false;

	// Button for new game
	JButton newGameButton;

	/**
	 * Constructor.
	 */
	public TicTacToe() {

		setTitle("Tic Tac Toe");  	// Set title
		setLocation(500, 200); 	// Set location

		// Set the layout to BorderLayou 
		setLayout(new BorderLayout());

		// Specify the size of the window
		setSize(500, 500);

		// Specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Initialize the values of the int array to something other 
		// than zero
		for (int i = 0; i < 3; i++) {
			for (int p = 0; p < 3; p++) {
				array[i][p] = -9;
			}
		}

		panel1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panel1.add(label1);
		panel2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panel2.add(label2);
		panel3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panel3.add(label3);
		panel4.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panel4.add(label4);
		panel5.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panel5.add(label5);
		panel6.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panel6.add(label6);
		panel7.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panel7.add(label7);
		panel8.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panel8.add(label8);
		panel9.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panel9.add(label9);

		newGameButton = new JButton("New Game");
		newGameButton.setFont(new Font("Sans Serif", Font.BOLD, 20));
		newGameButton.addActionListener(new NewGameButtonListener());

		gridPanel = new JPanel();
		
		// Add the game board to the center field
		gridPanel.setLayout(new GridLayout(3, 3));
		gridPanel.setPreferredSize(new Dimension(400, 350));
		gridPanel.add(panel1);
		gridPanel.add(panel2);
		gridPanel.add(panel3);
		gridPanel.add(panel4);
		gridPanel.add(panel5);
		gridPanel.add(panel6);
		gridPanel.add(panel7);
		gridPanel.add(panel8);
		gridPanel.add(panel9);

		// Add the grid panels to the content pane
		add(newGameButton, BorderLayout.NORTH);
		add(gridPanel, BorderLayout.CENTER);
		add(gameOverLabel, BorderLayout.SOUTH);

		// Pack and display the window
		pack();
		setVisible(true);
		
	}

	/**
	 * Private inner class to handle the events generated by the 
	 * new game button.
	 */
	private class NewGameButtonListener implements ActionListener {
 
		public void actionPerformed(ActionEvent e) {
			// Initialize the values of the int array to something other 
			// than zero
			for (int i = 0; i < 3; i++) {
				for (int p = 0; p < 3; p++) {
					array[i][p] = -9;
				}
			}

			// Clear all the tiles
			for (int h = 0; h < labelArray.length; h++) {
				labelArray[h].setText(null);
			}
			oWins = false;
			xWins = false;

			// Had to look up how to shuffle an array of 
			// objects, that's beyond my current knowledge
			List<Integer> intList = Arrays.asList(tiles);
			Collections.shuffle(intList);
			intList.toArray(tiles);

			for (int n = 0; n < tiles.length; n++) {
				System.out.print("tiles["+n+"] = "+tiles[n]+", ");
			}
			System.out.println();
			int num; 
			String x = "X";
			String o = "O";
			randy = new Random();
			randyLabel = new Random();


			for (int i = 0; i < 9; i++) {
				System.out.println("*Turn number " + (i+1) + "*");
				// Start populating the squares
				num = randy.nextInt(2);
				System.out.println("num = " + num);
				System.out.println("tiles[i] = " + tiles[i]);
				if (tiles[i] == 1) 
					array[0][0] = num;
				else if (tiles[i] == 2)
					array[0][1] = num;
				else if (tiles[i] == 3)
					array[0][2] = num;
				else if (tiles[i] == 4) 
					array[1][0] = num;
				else if (tiles[i] == 5) 
					array[1][1] = num;
				else if (tiles[i] == 6)
					array[1][2] = num;
				else if (tiles[i] == 7)
					array[2][0] = num;
				else if (tiles[i] == 8)
					array[2][1] = num;
				else if (tiles[i] == 9)
					array[2][2] = num;

				for (int r = 0; r < 3; r++) {
					for (int t = 0; t < 3; t++) {
						System.out.print(array[r][t] + " ");
					}
					System.out.println();
				}

				if (num == 0) {
					labelArray[tiles[i]-1].setText(o);
					labelArray[tiles[i]-1].setHorizontalAlignment
						(SwingConstants.CENTER);
					labelArray[tiles[i]-1].setVerticalAlignment
						(SwingConstants.BOTTOM);
					labelArray[tiles[i]-1].setFont
						(new Font("Sans Serif", Font.BOLD, 80));

				} else if (num == 1) {
					labelArray[tiles[i]-1].setText(x);
					labelArray[tiles[i]-1].setHorizontalAlignment
						(SwingConstants.CENTER);
					labelArray[tiles[i]-1].setVerticalAlignment
						(SwingConstants.BOTTOM);
					labelArray[tiles[i]-1].setFont
						(new Font("Sans Serif", Font.BOLD, 80));
				}
				panelArray[tiles[i]-1].add(labelArray[tiles[i]-1]);

				if (i > 2) {
					checkForWinner();
					System.out.println("checkForWinner is passed " + (i-2) + " times.");
				
				System.out.println("oWins = " + oWins + "\n"
					+ "xWins = " + xWins);
				if(oWins) {
					gameOverLabel.setText("O is the winner!");
					gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
					gameOverLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));

					break;
				} else if (xWins) {
					gameOverLabel.setText("X is the winner!");
					gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
					gameOverLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));

					break;
				} 
			}
		} 

			// If no winners are produced, declare a tie
			if (oWins == false && xWins == false) {
				gameOverLabel.setText("It's a tie :(");
				gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
				gameOverLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
			}
			 
		}
			
	}

	/**
	 * The checkForWinner method searches the rows, columns
	 * and diagonals for a line of three X's or O's. It is called after 
	 * both players have made three moves since it is impossible to 
	 * have a winner before then. If it finds a winner, the loop is broken
	 * and a winner declared, else a tie is determined.
	 */
	public void checkForWinner() {
		// Determine a winner or tie
		int horizontalXCount = 0;
		int horizontalOCount = 0;

		// Check the horizontal rows for winner				
		for (int i = 0; i < 3; i++) {
			for (int q = 0; q < 3; q++) {
				if (array[i][q] == 0) {
					horizontalOCount++;
				} else if (array[i][q] == 1) {
					horizontalXCount++;
				}
				if (horizontalOCount == 3) {
					oWins = true;
					System.out.println("O gets a horizontal win");
					break;
				} else if (horizontalXCount == 3) {
					xWins = true;
					System.out.println("X gets a horizontal win");
					break;
				}
			}
			
			horizontalXCount = 0;
			horizontalOCount = 0;

		}

		int verticalOCount = 0;
		int verticalXCount = 0;
		// Check the vertical rows for winner				
		for (int t = 0; t < 3; t++) {
			if (oWins)
				break;
			if(xWins)
				break;
			else {
				for (int q = 0; q < 3; q++) {
					if (array[q][t] == 0) {
						verticalOCount++;
					} else if (array[q][t] == 1) {
						verticalXCount++;
					}
					if (verticalOCount == 3) {
						oWins = true;
						System.out.println("O gets a vertical win");
					} else if (verticalXCount == 3) {
						xWins = true;
						System.out.println("X gets a vertical win");
					}

				}

			}
			verticalOCount = 0;
			verticalXCount = 0;
			
		}

		// Check the diagonals for a winner
		if (array[0][0] == 0 && array[1][1] == 0 && array[2][2] == 0) {
			oWins = true;
			System.out.println("O gets a diagonal win");
		} else  if (array[2][0] == 0 && array[1][1] == 0 && array[0][2] == 0) {
			oWins = true;
			System.out.println("O gets a diagonal win");
		} else if (array[0][0] == 1 && array[1][1] == 1 && array[2][2] == 1) {
			xWins = true;
			System.out.println("X gets a diagonal win");
		} else  if (array[2][0] == 1 && array[1][1] == 1 && array[0][2] == 1) {
			xWins = true;
			System.out.println("X gets a diagonal win");
		}	

	}
	
	/**
	 * The main method creates an instance of the class, 
	 * causing it to display its window.
	 */
	public static void main(String[]args) {

		new TicTacToe();

	}
	
}

