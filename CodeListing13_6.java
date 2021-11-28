package ch13java6thedition;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This is Code Listing 13.6, which demonstrates 
 * a menu system.
 * 
 * @author craig
 */
public class CodeListing13_6 extends JFrame {
	
	private JLabel messageLabel; 			// Displays a message
	private final int LABEL_WIDTH = 500; 		// The label's width
	private final int LABEL_HEIGHT = 200; 		// The label's height

	// The following will reference menu components
	private JMenuBar menuBar;			// The menubar
	private JMenu fileMenu;			// The file menu
	private JMenu textMenu;			// The text menu
	private JMenuItem exitItem;			// To exit
	private JRadioButtonMenuItem blackItem;		// Makes text black
	private JRadioButtonMenuItem redItem;		// Makes text red
	private JRadioButtonMenuItem blueItem;		// Makes text blue
	private JCheckBoxMenuItem visibleItem;		// Toggle visibility


	/**
	 * Constructor.
	 */
	public CodeListing13_6() {

		// Set the title
		setTitle("Example Menu System");

		// Specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set location on the screen
		setLocation(500, 250);

		// Create the messageLabel label
		messageLabel = new JLabel("Use the text menu to change"
			+ "my color and make me invisible.", SwingConstants.CENTER);

		// Set the label's preferred size
		messageLabel.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
			
		// Set the label's foreground color
		messageLabel.setForeground(Color.BLACK);

		// Add the label to the content pane
		add(messageLabel);

		// Build the menu bar
		buildMenuBar();

		// Pack and display the window
		pack();
		setVisible(true);

	}

	/**
	 * The buildMenuBar method builds the menu bar.
	 */
	public void buildMenuBar() {

		// Create the menu bar
		menuBar = new JMenuBar();

		// Create the file and text menus
		buildFileMenu();
		buildTextMenu();

		// Add the file and text menus to the menu bar
		menuBar.add(fileMenu);
		menuBar.add(textMenu);

		// Set the window's menu bar
		setJMenuBar(menuBar);

	}

	/**
	 * The buildFileMenu method builds the File menu
	 * and returns a reference to its JMenu object.
	 */
	public void buildFileMenu() {

		// Create an Exit menu item
		exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.addActionListener(new ExitListener());

		// Create a JMenu object for the file menu
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		// Add the Exit menu item to the File menu
		fileMenu.add(exitItem);

	}

	/**
	 * The buildTextMenu method builds a Text menu
	 * and returns a reference to its JMenu object.
	 */
	public void buildTextMenu() {

		// Create the radio button menu items to change
		// the color of the text. Add an action listener to
		// each one.
		blackItem = new JRadioButtonMenuItem("Black", true);
		blackItem.setMnemonic(KeyEvent.VK_B);
		blackItem.addActionListener(new ColorListener());

		redItem = new JRadioButtonMenuItem("Red");
		redItem.setMnemonic(KeyEvent.VK_R);
		redItem.addActionListener(new ColorListener());

		blueItem = new JRadioButtonMenuItem("Blue");
		blueItem.setMnemonic(KeyEvent.VK_U);
		blueItem.addActionListener(new ColorListener());

		// Create a button group for the radio buttons
		ButtonGroup group = new ButtonGroup();
		group.add(blackItem);
		group.add(redItem);
		group.add(blueItem);

		// Create a check box menu item to make the text
		// visible or invisible
		visibleItem = new JCheckBoxMenuItem("Visible", true);
		visibleItem.setMnemonic(KeyEvent.VK_V);
		visibleItem.addActionListener(new VisibleListener());

		// Create a JMenu object for the Text menu
		textMenu = new JMenu("Text");
		textMenu.setMnemonic(KeyEvent.VK_T);
		
		// Add the menu items to the text menu
		textMenu.add(redItem);
		textMenu.add(blackItem);
		textMenu.add(blueItem);
		textMenu.addSeparator();	// Add a separator bar
		textMenu.add(visibleItem);

	}

	/**
	 * Private inner class that handles the event when the 
	 * user selects Exit from the file menu.
	 */
	private class ExitListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			System.exit(0);

		}	

	}

	/**
	 * Private inner class that handles the event generated when the user
	 * selects a color from the Text menu.
	 */
	private class ColorListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		
			if (blackItem.isSelected()) {
				messageLabel.setForeground(Color.BLACK);
			}
			else if (redItem.isSelected()) {
				messageLabel.setForeground(Color.RED);
			}
			else if (blueItem.isSelected()) {
				messageLabel.setForeground(Color.BLUE);
			}

		}

	}

	/**
	 * Private inner class that handles the event generated 
	 * when the user selects Visible from the Text menu.
	 */
	private class VisibleListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (visibleItem.isSelected()) {
				messageLabel.setVisible(true);
			}
			else
				messageLabel.setVisible(false);

		}

	}

	/**
	 * The main method creates an instance of the class, which 
	 * causes it to display its window.
	 */
	public static void main (String[]args) {
		new CodeListing13_6();
	}
}
