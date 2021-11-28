package ch13java6thedition;

/**
 * This application takes the Tic Tac Toe computer vs computer
 * game and makes it playable by the user.
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicTacToePlayer extends JFrame {
	
	// Array to hold the values for the 9x9 grid
	int [][] array = new int [3][3];  	

	// Array to hold the tiles that will be eliminated
	// once populated with an X or an O. They will
	// be converted to a list, shuffled and removed
	// once populated.
	Integer [] tiles = {1, 2, 3, 4, 5, 6, 7, 8, 9};

	// Turn counter
	int turnNumber = 0;

	// Subscripts for tiles computer needs as blocking moves
	int blockingSubscript1, blockingSubscript2;

	// Variables to cancel rows and diagonals that have been
	// cleared for twoInARows
	boolean     row1Tested = false,
		row2Tested = false,
		row3Tested = false,
	                column1Tested = false,
		column2Tested = false,
		column3Tested = false,
	                diagonal1Tested = false,
		diagonal2Tested = false;

	int row, column, diagonal; 

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

	// Panel for player choosing X or O
	JPanel playerChoicePanel;

	// Random class object for determining player choices and placement
	Random randy;
	Random randyLabel;
	
	// Boolean to determine if anybody won
	boolean xWins = false;
	boolean oWins = false;
	boolean twoInARowX = false;
	boolean twoInARowO = false;


	// Button for new game and player choice
	JButton newGameButton;
	JButton chooseXButton;
	JButton chooseOButton;

	String playerChoice;
	String computerChoice;

	// A pauser to simulate thinking
	Timer timer;
	TimerTask timerTask;

	/**
	 * Constructor.
	 */
	public TicTacToePlayer() {

		setTitle("Tic Tac Toe Player");  	// Set title
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

		// Set the turnNumber variable to 0
		turnNumber = 0;

		// Timer object
		timer = new Timer();

		// Create the labels and add MouseListeners
		label1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		label1.addMouseListener(new PlayerMoveListener());
		label2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		label2.addMouseListener(new PlayerMoveListener());
		label3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		label3.addMouseListener(new PlayerMoveListener());
		label4.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		label4.addMouseListener(new PlayerMoveListener());
		label5.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		label5.addMouseListener(new PlayerMoveListener());
		label6.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		label6.addMouseListener(new PlayerMoveListener());
		label7.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		label7.addMouseListener(new PlayerMoveListener());
		label8.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		label8.addMouseListener(new PlayerMoveListener());
		label9.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		label9.addMouseListener(new PlayerMoveListener());

		// Starts a fresche game
		newGameButton = new JButton("New Game");
		newGameButton.setFont(new Font("Sans Serif", Font.BOLD, 20));
		newGameButton.addActionListener(new NewGameButtonListener());

		// Player chooses to be X or O
		playerChoicePanel = new JPanel();
		playerChoicePanel.setLayout(new GridLayout(2, 1));
		playerChoicePanel.setPreferredSize(new Dimension(100, 400));
		chooseXButton = new JButton("X");
		chooseXButton.setFont(new Font("Sans Serif", Font.BOLD, 50));
		chooseXButton.addActionListener(new PlayerButtonListener());
		chooseOButton = new JButton("O");
		chooseOButton.setFont(new Font("Sans Serif", Font.BOLD, 50));
		chooseOButton.addActionListener(new PlayerButtonListener());
		playerChoicePanel.add(chooseXButton);
		playerChoicePanel.add(chooseOButton);

		// Create the panel for the game squares
		gridPanel = new JPanel();
		
		// Add the game board to the center field
		gridPanel.setLayout(new GridLayout(3, 3));
		gridPanel.setPreferredSize(new Dimension(400, 350));
		gridPanel.add(label1);
		gridPanel.add(label2);
		gridPanel.add(label3);
		gridPanel.add(label4);
		gridPanel.add(label5);
		gridPanel.add(label6);
		gridPanel.add(label7);
		gridPanel.add(label8);
		gridPanel.add(label9);

		gameOverLabel = new JLabel("Pick a side!");
		gameOverLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
		gameOverLabel.setHorizontalAlignment
			(SwingConstants.CENTER);

		// Add the grid panels to the content pane
		add(newGameButton, BorderLayout.NORTH);
		add(playerChoicePanel, BorderLayout.WEST);
		add(gridPanel, BorderLayout.CENTER);
		add(gameOverLabel, BorderLayout.SOUTH);

		// Pack and display the window
		pack();
		setVisible(true);
		
	}

	/**
	 * Private inner class to determine the choice of the user to
	 * be either X or O.
	 */
	private class PlayerButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == chooseXButton) {
				playerChoice = "X";
				computerChoice = "O";
				gameOverLabel.setText("Hey, I wanted to be X!");
				chooseXButton.setEnabled(false);
			} else if (e.getSource() == chooseOButton) {
				playerChoice = "O";
				computerChoice = "X";
				gameOverLabel.setText("Hey, I wanted to be O!");
				chooseXButton.setEnabled(false);
			}

		}

	}

	/**
	 * Private inner class to handle the events when a player adds
	 * an X or an O to a vacant square.
	 */
	private class PlayerMoveListener implements MouseListener {

		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource() == label1 && !label1.getText().equals(playerChoice)
			   && !label1.getText().equals(computerChoice)){
				setMove(label1, playerChoice);
				turnNumber++;
				if(playerChoice.equals("O")) {
					array[0][0] = 0;
				} else
					array[0][0] = 1;
			}else if(e.getSource() == label2 && !label2.getText().equals(playerChoice)
			   && !label2.getText().equals(computerChoice)){
				setMove(label2, playerChoice);
				turnNumber++;
				if(playerChoice.equals("O")) {
					array[0][1] = 0;
				} else
					array[0][1] = 1;

			} else if(e.getSource() == label3 && !label3.getText().equals(playerChoice)
			   && !label3.getText().equals(computerChoice)){
				setMove(label3, playerChoice);
				turnNumber++;
				if(playerChoice.equals("O")) {
					array[0][2] = 0;
				} else
					array[0][2] = 1;

			} else if(e.getSource() == label4 && !label4.getText().equals(playerChoice)
			   && !label4.getText().equals(computerChoice)){
				setMove(label4, playerChoice);
				turnNumber++;
				if(playerChoice.equals("O")) {
					array[1][0] = 0;
				} else
					array[1][0] = 1;

			}else if(e.getSource() == label5 && !label5.getText().equals(playerChoice)
			   && !label5.getText().equals(computerChoice)){
				setMove(label5, playerChoice);
				turnNumber++;
				if(playerChoice.equals("O")) {
					array[1][1] = 0;
				} else
					array[1][1] = 1;

			}else if(e.getSource() == label6 && !label6.getText().equals(playerChoice)
			   && !label6.getText().equals(computerChoice)){
				setMove(label6, playerChoice);
				turnNumber++;
				if(playerChoice.equals("O")) {
					array[1][2] = 0;
				} else
					array[1][2] = 1;

			}else if(e.getSource() == label7 && !label7.getText().equals(playerChoice)
			   && !label7.getText().equals(computerChoice)){
				setMove(label7, playerChoice);
				turnNumber++;
				if(playerChoice.equals("O")) {
					array[2][0] = 0;
				} else
					array[2][0] = 1;

			}else if(e.getSource() == label8 && !label8.getText().equals(playerChoice)
			   && !label8.getText().equals(computerChoice)){
				setMove(label8, playerChoice);
				turnNumber++;
				if(playerChoice.equals("O")) {
					array[2][1] = 0;
				} else
					array[2][1] = 1;

			}else if(e.getSource() == label9 && !label9.getText().equals(playerChoice)
			   && !label9.getText().equals(computerChoice)){				setMove(label9, playerChoice);
				setMove(label9, playerChoice);
				turnNumber++;
				if(playerChoice.equals("O")) {
					array[2][2] = 0;
				} else
					array[2][2] = 1;

			}

			// Check for a winner
			checkForWinner();
			if(oWins) {
				gameOverLabel.setText("O is the winner!");
				gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
				gameOverLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
				testMethod();
				return;

			} else if (xWins) {
				gameOverLabel.setText("X is the winner!");
				gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
				gameOverLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
				testMethod();
				return;
			}

			// If all squares are filled, check for a tie
			if(turnNumber == 5) {
				if (oWins == false && xWins == false) {
					gameOverLabel.setText("It's a tie :(");
					gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
					gameOverLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
					testMethod();
					return;
				}
			}
			
			// Computer player checks for a move to prevent player from winning 
			if (turnNumber > 1) {
				checkForTwoInARow();
				if (playerChoice.equals("X")) {
					if (twoInARowX) {
						if (!getLabel(blockingSubscript1, 
						    blockingSubscript2).getText().equals(computerChoice)) {
						              setMove(getLabel(blockingSubscript1, 
						              blockingSubscript2), computerChoice);
							array[blockingSubscript1][blockingSubscript2] = 0;
							testMethod();
							return;
						}
											}
				} else if (playerChoice.equals("O")) {
					if (twoInARowO) {
						if (!getLabel(blockingSubscript1, 
						    blockingSubscript2).getText().equals(computerChoice))
						setMove(getLabel(blockingSubscript1, 
							blockingSubscript2), computerChoice);
							array[blockingSubscript1][blockingSubscript2] = 1;
							testMethod();
							return;
					}
				}
			}

			// If none of the above checks come back true,
			// computer plays a blank square
			if(!oWins && !xWins && !twoInARowX && !twoInARowO) {
				if (turnNumber % 2 == 0) {
					for (int i = 0; i < labelArray.length; i++) {
						if (!labelArray[i].getText().equals(computerChoice)
							&& !labelArray[i].getText().equals(playerChoice)){
							setMove(labelArray[i], computerChoice);
							switch(i) {
								case 0 : 
									if(playerChoice.equals("O")) {
										array[0][0] = 0;
									} else
										array[0][0] = 1;
								case 1 : 
									if(playerChoice.equals("O")) {
										array[0][1] = 0;
									} else
										array[0][1] = 1;
								case 2 : 
									if(playerChoice.equals("O")) {
										array[0][2] = 0;
									} else
										array[0][2] = 1;
								case 3 : 
									if(playerChoice.equals("O")) {
										array[1][0] = 0;
									} else
										array[1][0] = 1;
								case 4 : 
									if(playerChoice.equals("O")) {
										array[1][1] = 0;
									} else
										array[1][1] = 1;
								case 5 : 
									if(playerChoice.equals("O")) {
										array[1][2] = 0;
									} else
										array[1][2] = 1;
								case 6 : 
									if(playerChoice.equals("O")) {
										array[2][0] = 0;
									} else
										array[2][0] = 1;
								case 7 : 
									if(playerChoice.equals("O")) {
										array[2][1] = 0;
									} else
										array[2][1] = 1;
								case 8 : 
									if(playerChoice.equals("O")) {
										array[2][2] = 0;
									} else
										array[2][2] = 1;

							}
							

							testMethod();
							return;
						}
					}
				} else {
					for (int i = labelArray.length - 1; i >= 0; i--) {
						if (!labelArray[i].getText().equals(computerChoice)
							&& !labelArray[i].getText().equals(playerChoice)) {
							setMove(labelArray[i], computerChoice);
							switch(i) {
								case 0 : 
									if(playerChoice.equals("O")) {
										array[0][0] = 1;
									} else
										array[0][0] = 0;
								case 1 : 
									if(playerChoice.equals("O")) {
										array[0][1] = 1;
									} else
										array[0][1] = 0;
								case 2 : 
									if(playerChoice.equals("O")) {
										array[0][2] = 1;
									} else
										array[0][2] = 0;
								case 3 : 
									if(playerChoice.equals("O")) {
										array[1][0] = 1;
									} else
										array[1][0] = 0;
								case 4 : 
									if(playerChoice.equals("O")) {
										array[1][1] = 1;
									} else
										array[1][1] = 0;
								case 5 : 
									if(playerChoice.equals("O")) {
										array[1][2] = 1;
									} else
										array[1][2] = 0;
								case 6 : 
									if(playerChoice.equals("O")) {
										array[2][0] = 1;
									} else
										array[2][0] = 0;
								case 7 : 
									if(playerChoice.equals("O")) {
										array[2][1] = 1;
									} else
										array[2][1] = 0;
								case 8 : 
									if(playerChoice.equals("O")) {
										array[2][2] = 1;
									} else
										array[2][2] = 0;

							}
							
							testMethod();
							return;
						}
					}
				}
			}

		}	
		public void mouseClicked(MouseEvent e) {

		} 				
		public void mouseReleased(MouseEvent e) {

		}
		public void mouseEntered(MouseEvent e) {
			
		}
		public void mouseExited(MouseEvent e){

		}

	}

	/**
	 * Private inner class to handle the events generated by the 
	 * new game button.
	 */
	private class NewGameButtonListener implements ActionListener {
 
		public void actionPerformed(ActionEvent e) {
			new TicTacToePlayer();
		}
			/*
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
			chooseXButton.setEnabled(true);
			chooseOButton.setEnabled(true);
		}
	}
			// Had to look up how to shuffle an array of 
			// objects, that's beyond my current knowledge
			List<Integer> intList = Arrays.asList(tiles);
			Collections.shuffle(intList);
			intList.toArray(tiles);

			for (int n = 0; n < tiles.length; n++) {
				System.out.print("tiles["+n+"] = "+tiles[n]+", ");
			}
			System.out.println();
			////////////////////////////////////////////////////////
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
		*/
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
					return;	
				} else if (horizontalXCount == 3) {
					xWins = true;
					return;
				}
			}
			
			horizontalXCount = 0;
			horizontalOCount = 0;

		}

		int verticalOCount = 0;
		int verticalXCount = 0;
		// Check the vertical rows for winner				
		for (int t = 0; t < 3; t++) {
			for (int q = 0; q < 3; q++) {
				if (array[q][t] == 0) {
					verticalOCount++;
				} else if (array[q][t] == 1) {
					verticalXCount++;
				}
				if (verticalOCount == 3) {
					oWins = true;
				} else if (verticalXCount == 3) {
					xWins = true;
				}

			}
			verticalOCount = 0;
			verticalXCount = 0;
		}

		// Check the diagonals for a winner
		if (array[0][0] == 0 && array[1][1] == 0 && array[2][2] == 0) {
			oWins = true;
		} else  if (array[2][0] == 0 && array[1][1] == 0 && array[0][2] == 0) {
			oWins = true;
		} else if (array[0][0] == 1 && array[1][1] == 1 && array[2][2] == 1) {
			xWins = true;
		} else  if (array[2][0] == 1 && array[1][1] == 1 && array[0][2] == 1) {
			xWins = true;
		}	

	}

	/**
	 * The checkForTwoInARow method checks the horizontal and 
	 * diagonal rows for two in a row - to see if the user is about to
	 * win or if the computer can win in one move.
	 */
	public void checkForTwoInARow() {

		int horizontalXCount = 0;
		int horizontalOCount = 0;
		int verticalXCount = 0;
		int verticalOCount = 0;
		twoInARowX = false;
		twoInARowO = false;
		
		// Check the horizontal rows for two in a row
		for (int i = 0; i < 3; i++) {
			for (int q = 0; q < 2; q++) {
				if (array[i][q] == 0) {
					horizontalOCount++;
				} else if (array[i][q] == 1) {
					horizontalXCount++;
				}
				if (horizontalOCount == 2) {
					twoInARowO = true;
					blockingSubscript1 = i;
					blockingSubscript2 = 2;
					return;
				} else if (horizontalXCount == 2) {
					twoInARowX = true;
					blockingSubscript1 = i;
					blockingSubscript2 = 2;
					return;
				}
			}
			horizontalXCount = 0;
			horizontalOCount = 0;
		}	

		
		for (int i = 0; i < 3; i++) {
			for (int q = 1; q < 3; q++) {
				
				if (array[i][q] == 0) {
					horizontalOCount++;
				} else if (array[i][q] == 1) {
					horizontalXCount++;
				}
				if (horizontalOCount == 2) {
					twoInARowO = true;
					blockingSubscript1 = i;
					blockingSubscript2 = 0;
					return;
				} else if (horizontalXCount == 2) {
					twoInARowX = true;
					blockingSubscript1 = i;
					blockingSubscript2 = 0;
					return;
				}
			}
			horizontalXCount = 0;
			horizontalOCount = 0;

		}		


		// Check the vertical rows for two in a row 
		for (int t = 0; t < 3; t++) { 		// This is the top two
			for (int q = 0; q < 2; q++) {
				if (array[q][t] == 0) {
					verticalOCount++;
				} else if (array[q][t] == 1) {
					verticalXCount++;
				}
				if (verticalOCount == 2) {
					twoInARowO = true;
					blockingSubscript1 = 2;
					blockingSubscript2 = t;
					return;	
				} else if (verticalXCount == 2) {
					twoInARowX = true;
					blockingSubscript1 = 2;
					blockingSubscript2 = t;
					return;
				}
			}
			verticalOCount = 0;
			verticalXCount = 0;

		}

		// Check the vertical rows for two in a row 
		// This is the bottom two
		for (int r = 0; r < 3; r++) { 		
			for (int f = 1; f < 3; f++) {
				if (array[f][r] == 0) {
					verticalOCount++;
				} else if (array[f][r] == 1) {
					verticalXCount++;
				}
				if (verticalOCount == 2) {
					twoInARowO = true;
					blockingSubscript1 = f;
					blockingSubscript2 = 0;
					return;
				} else if (verticalXCount == 2) {
					twoInARowX = true;
					blockingSubscript1 = f;
					blockingSubscript2 = 0;
					return;
				}
			}
			verticalOCount = 0;
			verticalXCount = 0;
		}
					

		// Check the diagonals for a two in a row
		if (array[0][0] == 0 && array[1][1] == 0 ) {
			twoInARowO = true;
			blockingSubscript1 = 2;
			blockingSubscript2 = 2;
		}else if (array[1][1] == 0 && array[2][2] == 0) {
			twoInARowO = true;
			blockingSubscript1 = 0;
			blockingSubscript2 = 0;
		} else  if (array[2][0] == 0 && array[1][1] == 0 ) {
			twoInARowO = true;
			blockingSubscript1 = 0;
			blockingSubscript2 = 2;
		} else if (array[1][1] == 0 && array[0][2] == 0) {
			twoInARowO = true;
			blockingSubscript1 = 2;
			blockingSubscript2 = 0;
		} else if (array[0][0] == 1 && array[1][1] == 1) {
			twoInARowX = true;
			blockingSubscript1 = 2;
			blockingSubscript2 = 2;
		} else if (array[1][1] == 1 && array[2][2] == 1) {
			twoInARowX = true;
			blockingSubscript1 = 0;
			blockingSubscript2 = 0;
		} else  if (array[2][0] == 1 && array[1][1] == 1) {
			twoInARowX = true;
			blockingSubscript1 = 0;
			blockingSubscript2 = 2;
		} else if (array[1][1] == 1 && array[0][2] == 1) {
			twoInARowX = true;
			blockingSubscript1 = 2;
			blockingSubscript2 = 0;
		}	

	}

	/**
	 * The setMove method accepts a label and String parameters 
	 * to determine what character to add to a square.
	 * @param label
	 * @param player 
	 */
	public void setMove(JLabel label, String player) {
		label.setText(player);
		label.setFont(new Font("Sans Serif", Font.BOLD, 60));
		label.setHorizontalAlignment
			(SwingConstants.CENTER);
	}

	/**
	 * The getLabel method accepts integers to identify which
	 * label is associated with the 2D integer array.
	 * @param sub1
	 * @param sub2 
	 * @return The label to be found
	 */
	public JLabel getLabel(int sub1, int sub2) {
		JLabel returnLabel = new JLabel();
		/*
		if (playerChoice.equals("X")) {
			array[sub1][sub2] = 0;
		} else if (playerChoice.equals("O")) {
			array[sub1][sub2] = 1;
		}
		*/
		if (sub1 == 0 && sub2 == 0) {
			returnLabel = labelArray[0];
		} else if (sub1 == 0 && sub2 == 1) {
			returnLabel = labelArray[1];
		} else if (sub1 == 0 && sub2 == 2) {
			returnLabel = labelArray[2];
		}else if (sub1 == 1 && sub2 == 0) {
			returnLabel = labelArray[3];
		}else if (sub1 == 1 && sub2 == 1) {
			returnLabel = labelArray[4];
		}else if (sub1 == 1 && sub2 == 2) {
			returnLabel = labelArray[5];
		}else if (sub1 == 2 && sub2 == 0) {
			returnLabel = labelArray[6];
		}else if (sub1 == 2 && sub2 == 1) {
			returnLabel = labelArray[7];
		}else if (sub1 == 2 && sub2 == 2) {
			returnLabel = labelArray[8];
		}

		return returnLabel;

	}
	
	/**
	 * The pause method pauses the thread to simulate 
	 * the computer thinking about the next move.
	 */
	public void pause() {
		try {
			TimeUnit.SECONDS.sleep(3000);
		} catch (InterruptedException ex) {
			Logger.getLogger
		(TicTacToePlayer.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * Test method.
	 */
	public void testMethod() {
		System.out.print("Turn number = " + turnNumber + "\n"
			+ "twoInARowX = " + twoInARowX + "\n"
			+ "twoInARowO = " + twoInARowO + "\n"
			+ "blockingSubscript1 = " + blockingSubscript1 + "\n"
			+ "blockingSubscript2 = " + blockingSubscript2 + "\n"
			+ "oWins = " + oWins + "\n"
			+ "xWins = " + xWins + "\n"
			+ "array matrix:\n"
			+ array[0][0] + " " + array[0][1] + " " + array[0][2] + "\n"
			+ array[1][0] + " " + array[1][1] + " " + array[1][2] + "\n"
			+ array[2][0] + " " + array[2][1] + " " + array[2][2] + "\n");
		
		

	}

	/**
	 * The main method creates an instance of the class, 
	 * causing it to display its window.
	 */
	public static void main(String[]args) {

		new TicTacToePlayer();

	}
	
}



