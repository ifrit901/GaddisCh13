package ch13java6thedition;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

/**
 * This is Code Listing 13_2, which shows how
 * to add a scroll bar to a list window.
 * 
 * @author craig
 */
public class CodeListing13_2 extends JFrame {

	private JPanel monthPanel;		// To hold components
	private JPanel selectedMonthPanel;	// To hold components 
	private JList monthList;		// The months
	private JScrollPane scrollPane;		// A scroll pane
	private JTextField selectedMonth;	// The selected month
	private JLabel label;			// A message

	// The following array holds the values that will be
	// displayed in the monthList list component.
	private String [] months = {"January", "February", "March", "April",
			          "May", "June", "July", "August", "September",
	  		          "October", "November", "December"};
	
	/**
	 * Constructor.
	 */
	public CodeListing13_2() {

		// Set the title 
		setTitle("List w/ scrollbar");

		// Specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add a BorderLayout manager
		setLayout(new BorderLayout());

		// Set the location of the window on the screen
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

		// Create a panel to hold the list
		monthPanel = new JPanel();

		// Create the list
		monthList = new JList(months);

		// Set the selection mode to single selection
		monthList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Register the list selection listener
		monthList.addListSelectionListener(new ListListener());

		// Set the number of visible rows to six.
		monthList.setVisibleRowCount(6);

		// Add the list to a scroll pane
		scrollPane = new JScrollPane(monthList);

		// Add the scroll pane to the window
		monthPanel.add(scrollPane);
	}

	/**
	 * The buildSelectedMonthPanel method adds an un-editable text 
	 * field to a panel.
	 */
	public void buildSelectedMonthPanel() {

		// Create the panel
		selectedMonthPanel = new JPanel();

		// Create the label
		label = new JLabel("You selected: ");

		// Create the text field
		selectedMonth = new JTextField(10);

		// Make the text field un-editable
		selectedMonth.setEditable(false);

		// Add the label and the text field to the panel
		selectedMonthPanel.add(label);
		selectedMonthPanel.add(selectedMonth);

	}

	/**
	 * Private inner class that handles the event when
	 * the user selects a month from the list.
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
	 * anonymously and causes its window to display.
	 */
	public static void main(String[]args) {
		new CodeListing13_2();
	}

}
