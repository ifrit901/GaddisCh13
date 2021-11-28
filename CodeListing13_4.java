package ch13java6thedition;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This is Code Listing 13_4, which demonstrates 
 * a combo-box window.
 * 
 * @author craig
 */
public class CodeListing13_4 extends JFrame {
	
	private JPanel coffeePanel;		// To hold components
	private JPanel selectedCoffeePanel;	// To hold components
	private JComboBox coffeeBox;		// A list of coffees
	private JLabel label;			// Displays a message
	private JTextField selectedCoffee;	// Selected coffee

	// The following array holds the values that will
	// be displayed in the coffeeBox combo box
	private String [] coffee = {"Regular Coffee", "Dark Roast", "Cappucino",
		 	        "Espresso", "Decaf"};

	/**
	 * Constructor.
	 */
	public CodeListing13_4() {

		// Set the combo box title
		setTitle("Combo Box Demo");

		// Specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the location of the window
		setLocation(500, 250);

		// Create a BorderLayout manager
		setLayout(new BorderLayout());

		// Build the panels
		buildCoffeePanel();
		buildSelectedCoffeePanel();

		// Add the panels to the content pane
		add(coffeePanel, BorderLayout.CENTER);
		add(selectedCoffeePanel, BorderLayout.SOUTH);

		// Pack and display the window
		pack();
		setVisible(true);

	}

	/**
	 * The buildCoffeePanel method adds a combo box
	 * with the types of coffee to a panel.
	 */
	public void buildCoffeePanel() {

		// Create the panel
		coffeePanel = new JPanel();

		// Create the combo box
		coffeeBox = new JComboBox(coffee);

		// Register an action listener
		coffeeBox.addActionListener(new ComboBoxListener());

		// Add the combo box to the panel
		coffeePanel.add(coffeeBox);

	}

	/**
	 * The buildSelectedCoffeePanel method adds a 
	 * read-only text field to a panel.
	 */
	public void buildSelectedCoffeePanel() {

		// Create the panel
		selectedCoffeePanel = new JPanel();

		// Create the label 
		label = new JLabel("You selected: ");

		// Create the un-editable text field
		selectedCoffee = new JTextField(10);
		selectedCoffee.setEditable(false);

		// Add the components to the panel
		selectedCoffeePanel.add(label);
		selectedCoffeePanel.add(selectedCoffee);

	}

	/**
	 * Private inner class to hold the event when the 
	 * user selects a type of coffee from the combo box.
	 */
	private class ComboBoxListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			// Get the selected coffee
			String selected = (String) coffeeBox.getSelectedItem();

			// Display the selected coffee in the text field
			selectedCoffee.setText(selected);

		}

	}

	/**
	 * The main method creates an instance of the class
	 * anonymously and causes it to display its window.
	 */
	public static void main(String[]args) {

		new CodeListing13_4();

	}
}
