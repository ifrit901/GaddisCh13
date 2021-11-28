package ch13java6thedition;

/**
 * This class holds the phrases that will be used 
 * by the gameVoice in TicTacToe2.
 * 
 * @author craig parker
 * September 2021
 * Written on ambulances at Acadian
 */
import java.util.Random;
public class GameVoices {

	// Phrases used after player chooses X or O
	private final String [] playerChoiceArray = 
		{"Hey, I wanted that one!", "Ha! That one always loses", 
		  "Bad choice..yer doomed", "Typical..", "It begins..."};

	// Phrases used after the user makes a move
	private final String [] labelChoiceArray = {"I knew you'd do that", "Wanna give up?", 
				       "Hmmm..", "Dangit!"};

	// Phrases that appear as player's cursor hovers above squares
	private final String [] labelHoverArray = {"Yeah, go there", "No, not there!", "Waiting...", 
			 	       "Decisions, decisions"};

	// To mix up the phrases
	private Random randy; 	
	private int randomNum = 0;

	/**
	 * no-arg Constructor.
	 */
	public GameVoices() {

	}

	/**
	 * The getPlayerChoiceVoice method returns a string
	 * responding to the choice of the user picking to be
	 * X or O.
	 * @return The snarky phrase for the player's choice of X or O. 
	 */
	public String getPlayerChoiceVoice() {
		randy = new Random();
		randomNum = randy.nextInt(5);
		return playerChoiceArray[randomNum];
	} 

	/**
	 * The getLabelChoiceVoice method returns a string 
	 * responding to the player's move.
	 * @return A snarky phrase after the player makes a move.
	 */
	public String getLabelChoiceVoice() {
		randy = new Random();
		randomNum = randy.nextInt(4);
		return labelChoiceArray[randomNum];
	}	

	/**
	 * The getLabelHoverChoiceVoice method returns a string
	 * when the player hovers the mouse over a particular
	 * square.
	 * @return An intimidating phrase mocking the player's 
	 *                potential move.
	 */
	public String getLabelHoverChoiceVoice() {
		randy = new Random();
		randomNum = randy.nextInt(4);
		return labelHoverArray[randomNum];
	}

}
