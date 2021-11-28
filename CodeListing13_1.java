package ch13java6thedition;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

/**
 * This is Code Listing 13_1, which demonstrates 
 * a List component.
 * 
 * @author craig
 */
public class CodeListing13_1 extends JFrame {

	private JPanel monthPanel;		// To hold components
	private JPanel selectedMonthPanel;	// To hold components
	private JList monthList;		// The months
	private JTextField selectedMonth;	// The selected month
	private JLabel label;			// A message

	// The following array holds the values that will
	// be displayed in the monthList list component.
	private String [] months = {"January", "February", "March", "April"
			          , "May", "June", "July", "August", "September"
			          , "October", "November", "December"};

	/**
	 * Constructor.
	 */
	public CodeListing13_1() {

		// Set the title
		setTitle("List Demo");

		// Specify an action for the close window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add a Layout manager
		setLayout(new BorderLayout());

		// Set the location 
		setLocation(500, 250);

		// Build the month and selectedMonth panels
		buildMonthPanel();
		buildSelectedMonthPanel();

		// Add the panels to the content pane
		add(monthPanel, BorderLayout.CENTER);
		add(selectedMonthPanel, BorderLayout.SOUTH);

		// Pack and display the window
		pack();
		setVisible(true);

	}

	/**
	 * The buildMonthPanel method adds a list containing
	 * the names of the months to a panel.
	 */
	public void buildMonthPanel() {

		// Create a panel to hold the months
		monthPanel = new JPanel();

		// Create the list
		monthList = new JList(months);

		// Add a border to the month list
		monthList.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		// Set the selection mode to single selection
		monthList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Register the list selection listener
		monthList.addListSelectionListener(new ListListener());

		// Add the list to the panel
		monthPanel.add(monthList);

	}

	/**
	 * The buildSelectedMonthPanel method adds an 
	 * un-editable text field to a panel.
	 */
	public void buildSelectedMonthPanel() {

		// Create a panel to hold the text field
		selectedMonthPanel = new JPanel();

		// Create the label
		label = new JLabel("You selected: ");

		// Create the text field
		selectedMonth = new JTextField(10);

		// Make the text field un-editable
		selectedMonth.setEditable(false);

		// Add the label and text field to the panel
		selectedMonthPanel.add(label);
		selectedMonthPanel.add(selectedMonth);
	}

	/**
	 * Private inner class that handles the event when 
	 * the user selects an item from the list.
	 */
	private class ListListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {

			// Get the selected month
			String selection = (String) monthList.getSelectedValue();

			// Put the selected month in the text field
			selectedMonth.setText(selection);


		}
	}
	/**
	 * The main method creates an instance of the class
	 * which causes it to display its window.
	 */
	public static void main(String[]args) {
		new CodeListing13_1();
	}
}
