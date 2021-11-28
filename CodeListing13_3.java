package ch13java6thedition;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This is Code Listing 13_3, which demonstrates
 * a list set to multiple instance selection mode.
 * 
 * @author craig
 */
public class CodeListing13_3 extends JFrame {
	
	private JPanel monthPanel;		// To hold components
	private JPanel selectedMonthPanel;	// To hold components
	private JPanel buttonPanel;		// To hold the button

	private JList monthList;		// To hold months
	private JList selectedMonthList;		// To hold the selected months

	private JScrollPane scrollPane1;		// Scroll pane - first list
	private JScrollPane scrollPane2;		// Scroll pane - second list

	private JButton button;		// A button

	// The following array holds the values that will
	// be displayed in the monthList list component
	String [] months = {"January", "February", "March", "April",
		               "May", "June", "July", "August", "September",
		               "October", "November", "December"};
	
	/**
	 * Constructor.
	 */
	public CodeListing13_3() {

		// Set the title
		setTitle("List Demo");

		// Specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add a BorderLayout manager
		setLayout(new BorderLayout());

		// Set the window location
		setLocation(500, 250);

		// Build the panels
		buildMonthPanel();
		buildSelectedMonthPanel();
		buildButtonPanel();

		// Add the panels to the content pane
		add(monthPanel, BorderLayout.NORTH);
		add(selectedMonthPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

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

		// Set the selection mode to multiple interval
		// selection mode
		monthList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		// Set the number of visible rows to 6
		monthList.setVisibleRowCount(6);

		// Add the list to a scroll pane
		scrollPane1 = new JScrollPane(monthList);

		// Add the scroll pane to the panel
		monthPanel.add(scrollPane1);

	}

	/**
	 * The buildSelectedMonthsPanel method adds a list
	 * to a panel - this will hold the selected months.
	 */
	public void buildSelectedMonthPanel() {

		// Create the panel
		selectedMonthPanel = new JPanel();

		// Create the list
		selectedMonthList = new JList();

		// Set the number of visible rows to 6
		selectedMonthList.setVisibleRowCount(6);

		// Add the list to a scroll pane
		scrollPane2 = new JScrollPane(selectedMonthList);

		// Add the scroll pane to the panel
		selectedMonthPanel.add(scrollPane2);

	}

	/**
	 * The buildButtonPanel method adds a button
	 * to a panel.
	 */
	public void buildButtonPanel() {

		// Create the panel
		buttonPanel = new JPanel();

		// Create the button
		button = new JButton("Get selections");

		// Add an action listener to the button
		button.addActionListener(new ButtonListener());

		// Add the button to the panel
		buttonPanel.add(button);

	}


	/**
	 * Private inner class that handles the event when
	 * the button is clicked.
	 */
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		

			// Get the selected values
			Object [] selections = monthList.getSelectedValues();

			// Store the selected items in selectedMonthList
			selectedMonthList.setListData(selections);

		}		

	}

	/**
	 * The main method creates an instance of 
	 * the class and causes it to display its window.
	 */
	public static void main(String[]args) {
		new CodeListing13_3();
	}

}
