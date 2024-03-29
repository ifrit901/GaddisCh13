package ch13java6thedition;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
/**
 * 9. Dice Simulator
 * 
 * Write a GUI application that simulates a pair of dice. Each time the 
 * button is clicked, the application should roll the dice, using random 
 * numbers to determine the value of each die. 
 * 
 * I don't have access to the online materials so I am using an array
 * of JLabels to simulate the die faces.
 * 
 * @author craig
 * 
 */
public class DiceSimulator extends JFrame {
	
	String [] images = {"die1.jpeg", "die2.jpeg", "die3.jpeg", 
		              "die4.jpeg", "die5.jpeg", "die6.jpeg"};

	// Labels to represent dice
	JLabel dice1 = new JLabel();
	JLabel dice2 = new JLabel();
		
	// Die array
	ImageIcon [] dieArray;

	// Panels
	JPanel panel1, panel2;
	
	// Roll button
	JButton rollButton;

	// Object of class Random 
	Random randy1;
	Random randy2;

	// Label for instructions
	JLabel intro = new JLabel("Click the roll button to roll the dice.");

	public DiceSimulator() {

		// Set the title for the window
		setTitle("Dice Simulator");

		// Set the location of the window
		setLocation(400, 200);

		// Set the layout manager
		setLayout(new BorderLayout());

		// Specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Dice appearance
		dice1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		dice2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		// Create the panels
		panel1 = new JPanel();
		panel1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panel2 = new JPanel();
		panel2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		// Scale the labels to the images
		dice1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass()
			    .getResource(images[0])).getImage()
			    .getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
		dice2.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass()
			    .getResource(images[0])).getImage()
			    .getScaledInstance(200, 200, Image.SCALE_SMOOTH)));

		// Start the panel with some dice
		panel1.add(dice1);
		panel2.add(dice2);

		// Create the button
		rollButton = new JButton("Roll");
		rollButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		rollButton.addActionListener(new ButtonListener());
		
		
		intro.setFont(new Font("SansSerif", Font.BOLD, 20));
		add(intro, BorderLayout.NORTH);
		add(panel1, BorderLayout.EAST);
		add(panel2, BorderLayout.WEST);
		add(rollButton, BorderLayout.SOUTH);

		// Pack and display the window
		pack();
		setVisible(true);

	}

	/**
	 * Private inner class to handle the events generated by
	 * the user clicking the roll button.
	 */
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			randy1 = new Random();			
			int number = randy1.nextInt(6);

			dice1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass()
			    .getResource(images[number])).getImage()
			    .getScaledInstance(200, 200, Image.SCALE_SMOOTH)));

			randy2 = new Random();
			int number2 = randy2.nextInt(6);
			dice2.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass()
			    .getResource(images[number2])).getImage()
			    .getScaledInstance(200, 200, Image.SCALE_SMOOTH)));


		}		

	}
	/**
	 * The main method creates an instance of the class and 
	 * causes its window to be displayed.
	 */
	public static void main(String[]args) {

		new DiceSimulator();

	}


}
