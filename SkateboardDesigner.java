package ch13java6thedition;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * 4. Skateboard Designer
 * 
 * The Skate Shop sells the skateboard products listed in Table 13-2:
 * 
 * Table 13-2
 * ---------------------------------------------------------------------------------------------------------------
 * Decks			Truck Assemblies		Wheels
 * ---------------------------------------------------------------------------------------------------------------
 * The Master Thrasher $60        7.75 inch axle $35		51mm $20
 * The Dictator $45		 8 inch axle $45		55mm $25
 * The Street King $50	 8.5 inch axle $45		58mm $24
 * 						61mm $28
 * ---------------------------------------------------------------------------------------------------------------
 * 
 * In addition, the Skate Shop sells the following miscellaneous products and services:
 * 
 * 	Grip Tape $10
 * 	Bearings $30
 * 	Riser Pads $2
 * 	Nuts & Bolts kit $3
 * 
 * Create an application that allows the user to select one deck, one truck assembly, 
 * and one wheel set from either list components or combo boxes. The application 
 * should also have a list component that allows the user to select multiple miscellaneous
 * products. The application should display the subtotal, the amount of sales  (at 6 percent),
 * and the total of the order.
 * 
 * @author craig
 */
public class SkateboardDesigner extends JFrame {

	private final String [] DECKS = {"The Master Thrasher", 
				"The Dictator", "The Street King"};

	private final String [] TRUCKS = {"7.75 inch axle", "8 inch axle", 
				"8.5 inch axle"};

	private final String [] WHEELS = {"51mm", "55mm", "58mm", 
				"61mm"};

	private final String [] MISCELLANEOUS = {"Grip Tape", "Bearings", 
				           "Riser Pads", "Nuts & Bolts kit"};

	private final double [] DECK_PRICES = {60, 45, 50};
	private final double [] TRUCK_PRICES = {35, 45, 50};
	private final double [] WHEEL_PRICES = {20, 25, 24, 28};
	private final double [] MISCELLANEOUS_PRICES = {10, 30, 2, 3};
	private ArrayList <Double> miscellaneousSelections = new ArrayList<>();
	private ArrayList <Double> newList = new ArrayList<>();

	private final double TAX = 0.06;

	private double deckCharge = 0.0;
	private double truckCharge = 0.0;
	private double wheelCharge = 0.0;
	private double miscellaneousCharge = 0.0;

	private JPanel deckLabelPanel;
	private JPanel truckLabelPanel;
	private JPanel wheelLabelPanel;
	private JPanel miscellaneousLabelPanel;
	private JPanel deckComboBoxPanel;
	private JPanel truckComboBoxPanel;
	private JPanel wheelComboBoxPanel;
	private JPanel miscellaneousListPanel;
	private JPanel totalLabelPanel;
	private JPanel buttonPanel;
	private JPanel totalTextFieldPanel;

	private JLabel deckLabel;
	private JLabel truckLabel;
	private JLabel wheelLabel;
	private JLabel miscellaneousLabel;
	private JLabel totalLabel;
	private JLabel subtotalLabel;
	private JLabel taxLabel;

	private JComboBox deckComboBox;
	private JComboBox truckComboBox;
	private JComboBox wheelComboBox;
	private JList miscellaneousList;
	private JButton button;
	private JTextField subtotalTextField;
	private JTextField taxTextField;		// Tax is 6 percent
	private JTextField totalTextField;
	
	private int counter = 0;
	/**
	 * Constructor.
	 */
	public SkateboardDesigner() {

		// Set the title for the window
		setTitle("Skateboard Designer");

		// Set the screen location for the window
		setLocation(200, 150);

		// Specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Specify a Layout
		setLayout(new GridLayout(3, 4));

		// Build the panels
		buildDeckLabelPanel();
		buildTruckLabelPanel();
		buildWheelLabelPanel();
		buildMiscellaneousLabelPanel();

		buildDeckComboBoxPanel();
		buildTruckComboBoxPanel();
		buildWheelComboBoxPanel();
		buildMiscellaneousListPanel();

		buildTotalLabelPanel();
		buildButtonPanel();
		buildTotalTextFieldPanel();

		// Add the panels to the content pane
		add(deckLabelPanel);
		add(truckLabelPanel);
		add(wheelLabelPanel);
		add(miscellaneousLabelPanel);

		add(deckComboBoxPanel);
		add(truckComboBoxPanel);
		add(wheelComboBoxPanel);
		add(miscellaneousListPanel);

		add(totalLabelPanel);
		add(buttonPanel);
		add(totalTextFieldPanel);

		// Pack and display the window
		pack();
		setVisible(true);

	}

	/**
	 * The buildDeckLabelPanel method builds the panel
	 * that displays the deck options and their prices.
	 */
	public void buildDeckLabelPanel() {

		// Create the panel
		deckLabelPanel = new JPanel();
		deckLabelPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));

		// Create the label
		deckLabel = new JLabel("<html>Decks<br/>"
			+ "----------------------------------<br/>"
			+ "The Master Thrasher $60<br/>"
				 + "The Dictator $45<br/>"
				 + "The Street King $50<html/>");

		// Add the label to the panel
		deckLabelPanel.add(deckLabel);

	}

	/**
	 * The buildTruckLabelPanel method builds the panel that
	 * displays the truck options and their prices.
	 */
	public void buildTruckLabelPanel() {

		// Create the panel
		truckLabelPanel = new JPanel();
		truckLabelPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));

		// Create the label
		truckLabel = new JLabel("<html>Trucks<br/>"
			+ "-------------------------<br/>"
			+ "7.75 inch axle $35<br/>"
			+ "8 inch axle $45<br/>"
			+ "8.5 inch axle $45<html/>");

		// Add the label to the panel
		truckLabelPanel.add(truckLabel);
	}

	/**
	 * The buildWheelLabelPanel method builds the panel
	 * that holds the wheel options and their prices.
	 */
	public void buildWheelLabelPanel() {

		// Create the panel
		wheelLabelPanel = new JPanel();
		wheelLabelPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));

		// Create the label
		wheelLabel = new JLabel("<html>Wheels<br/>"
				  + "-----------<br/>"
				  + "51mm $20<br/>"
				  + "55mm $25<br/>"
				  + "58mm $24<br/>"
				  + "61mm $28<html/>");

		// Add the label to the panel
		wheelLabelPanel.add(wheelLabel);
	}

	/**
	 * The buildMiscellaneousLabelPanel method builds the panel
	 * that lists the miscellaneous items and their prices.
	 */
	public void buildMiscellaneousLabelPanel() {

		// Create the panel
		miscellaneousLabelPanel = new JPanel();
		miscellaneousLabelPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));

		// Create the label
		miscellaneousLabel = new JLabel("<html>Miscellaneous<br/>"
					+ "------------------------<br/>"
					+ "Grip Tape $10<br/>"
				                + "Bearings $30<br/>"
					+ "Riser Pads $2<br/>"
					+ "Nuts & Bolts kit $3<html/>");

		// Add the label to the panel
		miscellaneousLabelPanel.add(miscellaneousLabel);
	}

	/**
	 * The buildDeckComboBoxPanel builds the panel that 
	 * holds the deck combo box.
	 */
	public void buildDeckComboBoxPanel() {

		// Create the panel
		deckComboBoxPanel = new JPanel();
		deckComboBoxPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));

		// Create the combo box
		deckComboBox = new JComboBox(DECKS);
		deckComboBox.addActionListener(new ComboBoxListener());

		// Add the combo box to the panel
		deckComboBoxPanel.add(deckComboBox);
	}

	/**
	 * The buildTruckComboBoxPanel method builds the panel
	 * that holds the combo box for TRUCKS.
	 */
	public void buildTruckComboBoxPanel() {

		// Create the panel
		truckComboBoxPanel = new JPanel();
		truckComboBoxPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));

		// Create the combo box
		truckComboBox = new JComboBox(TRUCKS);
		truckComboBox.addActionListener(new ComboBoxListener());

		// Add the combo box to the panel
		truckComboBoxPanel.add(truckComboBox);

	}

	/**
	 * The buildWheelComboBoxPanel method builds the panel
	 * that holds the combo box for WHEELS.
	 */
	public void buildWheelComboBoxPanel() {

		// Create the panel
		wheelComboBoxPanel = new JPanel();
		wheelComboBoxPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));

		// Create the combo box
		wheelComboBox = new JComboBox(WHEELS);
		wheelComboBox.addActionListener(new ComboBoxListener());

		// Add the combo box to the panel
		wheelComboBoxPanel.add(wheelComboBox);

	}

	/**
	 * The buildMiscellaneousListPanel builds the panel 
	 * that holds the choices for miscellaneous items. The 
	 */
	public void buildMiscellaneousListPanel() {

		// Create the panel
		miscellaneousListPanel = new JPanel();
		miscellaneousListPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));

		// Create the list
		miscellaneousList = new JList(MISCELLANEOUS);
		miscellaneousList.addListSelectionListener(new ListListener());

		// Add the combo box to the panel
		miscellaneousListPanel.add(miscellaneousList);

	}

	/**
	 * The buildTotalLabelPanel method builds the panel that 
	 * holds the total label.
	 */
	public void buildTotalLabelPanel() {

		// Create the panel
		totalLabelPanel = new JPanel();
		totalLabelPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));

		// Create the label
		totalLabel = new JLabel("Ready to purchase?");

		// Add the label to the panel
		totalLabelPanel.add(totalLabel);

	}

	/**
	 * The buildButtonPanel method builds the panel that 
	 * holds the purchase button.
	 */
	public void buildButtonPanel() {

		// Create the panel
		buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));

		// Create the button
		button = new JButton("Purchase");
		button.addActionListener(new ButtonListener());

		// Add the button to the panel
		buttonPanel.add(button);

	}

	/**
	 * The buildTotalTextFieldPanel builds the panel that holds
	 * the subtotal,  and total cost of the order.
	 */
	public void buildTotalTextFieldPanel() {

		// Create the panel
		totalTextFieldPanel = new JPanel();
		totalTextFieldPanel.setLayout(new GridLayout(3, 2));
		totalTextFieldPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));

		// Create the labels
		subtotalLabel = new JLabel("Subtotal: $");
		taxLabel = new JLabel("Tax: $");
		totalLabel = new JLabel("Total: $");

		// Create the text fields
		subtotalTextField = new JTextField("0.00", 10);
		subtotalTextField.setEditable(false);
		taxTextField = new JTextField("0.00", 10);
		taxTextField.setEditable(false);
		totalTextField = new JTextField("0.00", 10);
		totalTextField.setEditable(false);

		// Add the labels and text fields to the panel
		totalTextFieldPanel.add(subtotalLabel);
		totalTextFieldPanel.add(subtotalTextField);
		totalTextFieldPanel.add(taxLabel);
		totalTextFieldPanel.add(taxTextField);
		totalTextFieldPanel.add(totalLabel);
		totalTextFieldPanel.add(totalTextField);

	}

	/**
	 * Private inner class to handle the events generated 
	 * by the user clicking the button.
	 */
	private class ButtonListener implements ActionListener {

		double subtotal = 0.0;
		double taxes = 0.0;
		double total = 0.0;

		public void actionPerformed(ActionEvent e) {

			for (Double i : newList) {

				miscellaneousCharge += i;
			}

			subtotal = deckCharge + wheelCharge 
			             + truckCharge + miscellaneousCharge;
			subtotalTextField.setText(String.format("%,.2f", subtotal));

			taxes = subtotal * TAX;
			taxTextField.setText(String.format("%,.2f", taxes));

			total = subtotal + taxes;
			totalTextField.setText(String.format("%,.2f", total));
			
		}

	}

	/**
	 * Private inner class handling the events generated by
	 * choices made in the miscellaneous list.
	 */
	private class ListListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {

			int index;
			if(!e.getValueIsAdjusting()) {
				
				index = miscellaneousList.getSelectedIndex();
				miscellaneousCharge += MISCELLANEOUS_PRICES[index];
				
			}
		}
	}

	/**
	 * Private inner class to hold the events generated by 
	 * making a choice in the combo boxes.
	 */
	private class ComboBoxListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			int selection;

			if (e.getSource() == deckComboBox) {

				selection = deckComboBox.getSelectedIndex();
				deckCharge = DECK_PRICES[selection];
				System.out.println(deckCharge);

			} else if (e.getSource() == wheelComboBox) {

				selection = wheelComboBox.getSelectedIndex();
				wheelCharge = WHEEL_PRICES[selection];
				System.out.println(wheelCharge);

			} else if (e.getSource() == truckComboBox) {

				selection = truckComboBox.getSelectedIndex();
				truckCharge = TRUCK_PRICES[selection];
				System.out.println(truckCharge);

			} 
				
		}					

	}

	/**
	 * The main method creates an instance of the class
	 * causing it to display its window.
	 */
	public static void main(String[]args) {

		new SkateboardDesigner();

	}
}
