package ch13java6thedition;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * This is Code Listing 13.5, which demonstrates how 
 * to use an ImageIcon and a JLabel to display an image.
 * 
 * @author craig
 */
public class CodeListing13_5 extends JFrame {

	private JPanel imagePanel;	// To hold an image
	private JPanel buttonPanel;	// To hold a button
	private JLabel imageLabel;	// To show an image
	private JButton button;	// To get an image

	/**
	 *  Constructor.
	 */
	public CodeListing13_5() {

		// Set the title 
		setTitle("My Planet");

		// Specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create a BorderLayout manager
		setLayout(new BorderLayout());

		// Set the window location
		setLocation(500, 250);

		// Set the window size
		setSize(600, 400);

		// Build the panels
		buildImagePanel();
		buildButtonPanel();

		// Add the panels to the content pane
		add(imagePanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		// Pack and display the window
		pack();
		setVisible(true);
	}

	/**
	 * The buildImagePanel method adds a label to a panel.
	 */
	public void buildImagePanel() {

		// Create the panel
		imagePanel = new JPanel();

		ImageIcon catImage = new ImageIcon("/home/craig/Pictures/cat.jpeg");

		// Create a label
		imageLabel = new JLabel("Click the button to see an "
			+ "image of my planet", catImage, SwingConstants.LEFT);
		imageLabel.setToolTipText("meow -_-");

		// Add the label to the panel
		imagePanel.add(imageLabel);

	}

	/**
	 * The buildButtonPanel method adds a button to
	 * a panel.
	 */
	public void buildButtonPanel() {
		
		// Create the panel
		buttonPanel = new JPanel();

		// Create the button
		button = new JButton("Get Image");
		button.setMnemonic(KeyEvent.VK_G);

		// Register the button with an action listener
		button.addActionListener(new ButtonListener());

		// Add the button to the panel
		buttonPanel.add(button);

	}

	/**
	 * Private inner class to handle the events generated by the
	 * button.
	 */
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			// Create the button image
			ImageIcon earthImage = new ImageIcon
					("/home/craig/Pictures/earth.jpeg");

			// Disappear the text from the label
			imageLabel.setText(null);
			imageLabel.setToolTipText("I am round boy");

			// Display the image in the label
			imageLabel.setIcon(earthImage);

			pack();
		}

	}

	/**
	 * Main method: Creates an instance of the class causing
	 * it to display its window.
	 */
	public static void main(String[]args) {

		new CodeListing13_5(); 

	}
}
