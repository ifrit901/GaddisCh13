package ch13java6thedition;


/**
 * 8. Conference Registration System
 * 
 * Create an application that calculates the registration fees for a conference.
 * The general conference registration fee is $895 per person, and student 
 * registration is $495 per person. There is also an optional opening night 
 * dinner with a keynote speech for $30 per person. In addition, the optional
 * pre-conference workshops listed in the table below are available. 
 * 
 *	Optional pre-conference workshops
 * =====================================
 *	Workshop			    Fee
 * ----------------------------------------------------------------------------------
 * Introduction to E-commerce		   $295	
 * The Future of th Web		   $295
 * Advanced Java Programming		   $395
 * Network Security			   $395
 * ---------------------------------------------------------------------------------- 
 * 
 * The application should allow the user to select the registration type, the 
 * optional opening night dinner and keynote speech, and as many 
 * pre-conference workshops as desired. The total cost should be displayed.
 * 
 * @author craig
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ConferenceRegistrationSystem extends JFrame {

	// Registration type
	double regularRegistrationPrice = 895.0, studentRegistrationPrice = 495.0,
	           registrationTotalPrice = 0.0;

	// Pre-conference workshop prices
	double IntroductionToECommerce = 295.0, theFutureOfTheWeb = 295.0,
	           advancedJavaProgramming = 395.0, networkSecurity = 395.0,
	           workshopTotalPrice = 0.0;

	double [] workshopPriceArray = {295.0, 295.0, 395.0, 395.0};

	// String array for workshops
	String [] workshops = {"Introduction to E-Commerce", "The Future of the Web", 
			   "Advanced Java Programming", "Network Security"};
	
	// Optional opening night dinner/speech
	double openingNightSpeech = 30.0;
	JCheckBox openingNightCheckBox;

	// Label for NORTH tag
	JLabel northLabel;

	// Total variable and display components
	double totalPrice = 0.0;

	JLabel totalPriceLabel;
	JTextField totalPriceTextField; 
	

	// Panel for radio buttons
	JPanel buttonGroupPanel;
	JPanel centerPanel;
	JPanel southPanel;
	JPanel eastPanel;

	// List for workshops
	JList workshopList;

	// Label for the radio buttons 
	JLabel registrationTypeLabel;

	// Clear selections button
	JButton clearSelectionsButton;

	// Radio buttons for regular or student rate
	JRadioButton regular, student;

	ButtonGroup buttonGroup;


	/**
	 * Constructor.
	 */
	public ConferenceRegistrationSystem() {

		// Set the title of the window
		setTitle("Conference Registration System"); 

		// Set the location on the screen
		setLocation(200, 250);

		// Specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setFont(new Font("SansSerif", Font.BOLD, 20));

		// Build the BorderLayout regions
		buildNorth();
		buildEast();
		buildCenter();
		buildWest();
		buildSouth();

		add(northLabel, BorderLayout.NORTH);
		add(eastPanel, BorderLayout.EAST);
		add(centerPanel, BorderLayout.CENTER);
		add(buttonGroupPanel, BorderLayout.WEST);
		add(southPanel, BorderLayout.SOUTH);

		// Pack and display the window
		pack();
		setVisible(true);

	}

	/**
	 * The buildNorth method creates the label for intro/instruction.
	 */
	public void buildNorth() {

		// Create the label
		northLabel = new JLabel("Register for the conference using the sections "
			+ "below:", SwingConstants.CENTER);
		northLabel.setBorder(BorderFactory.createDashedBorder(null));
		northLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

	}

	/**
	 * The buildEast method builds the radio button group allowing the 
	 * user to choose their registration type, either student or regular.
	 */
	public void buildWest() {

		// Create the panel
		buttonGroupPanel = new JPanel();
		buttonGroupPanel.setLayout(new GridLayout(3, 1));
		buttonGroupPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 1));

		// Create the label
		registrationTypeLabel = new JLabel("Registration Type");
		registrationTypeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		registrationTypeLabel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));

		// Create the radio buttons
		regular = new JRadioButton("Regular");
		regular.addActionListener(new RadioListener());
		regular.setFont(new Font("SansSerif", Font.BOLD, 20));
		student = new JRadioButton("Student");
		student.addActionListener(new RadioListener());
		student.setFont(new Font("SansSerif", Font.BOLD, 20));

		// Group the buttons
		buttonGroup = new ButtonGroup();
		buttonGroup.add(regular);
		buttonGroup.add(student);

		buttonGroupPanel.add(registrationTypeLabel);
		buttonGroupPanel.add(regular);
		buttonGroupPanel.add(student);

	}

	/**
	 * The buildCenter method creates the combo box listing all
	 * the possible workshops the user can register for.
	 */
	public void buildCenter() {

		// Create the panel
		centerPanel = new JPanel();
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 1));

		// Create the combo box
		workshopList = new JList(workshops);
		workshopList.setSelectionMode
		(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		workshopList.setFont(new Font("SansSerif", Font.BOLD, 20));
		workshopList.addListSelectionListener(new WorkShopListener());

		// Add the component to the panel
		centerPanel.add(workshopList);

	}

	/**
	 * The buildEast method builds the check box indicating that 
	 * the user would like to attend the optional pre-conference dinner.
	 */
	public void buildEast() {

		eastPanel = new JPanel();
		eastPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 1));

		// Create the check box
		openingNightCheckBox = new JCheckBox("Attend Opening Night Dinner");
		openingNightCheckBox.addItemListener(new CheckBoxListener());
		openingNightCheckBox.setFont(new Font("SansSerif", Font.BOLD, 20));
		eastPanel.add(openingNightCheckBox);

	}

	/**
	 * The buildSouth method builds the south region of the BorderLayout. 
	 */
	public void buildSouth() {

		// Create the panel
		southPanel = new JPanel();

		// Create the label and text field
		totalPriceLabel = new JLabel("Total Price: $");
		totalPriceLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		// Create the text field
		totalPriceTextField = new JTextField(10);
		totalPriceTextField.setFont(new Font("SansSerif", Font.BOLD, 20));

		// Create the clear button
		clearSelectionsButton = new JButton("Clear Selections");
		clearSelectionsButton.addActionListener(new ClearButtonListener());

		// Add the components to the panel
		southPanel.add(totalPriceLabel);
		southPanel.add(totalPriceTextField);
		southPanel.add(clearSelectionsButton);

	}

	/**
	 * The RadioListener class is a private class that handles the events 
	 * created by the user clicking on one of the registration type buttons.
	 */
	private class RadioListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == regular) {
				totalPrice -= registrationTotalPrice;
				registrationTotalPrice = regularRegistrationPrice;
				totalPrice += registrationTotalPrice;
				totalPriceTextField.setText
				(String.format("%,.2f", totalPrice));

			} else if (e.getSource() == student) {
				totalPrice -= registrationTotalPrice;
				registrationTotalPrice = studentRegistrationPrice;
				totalPrice += registrationTotalPrice;
				totalPriceTextField.setText
				(String.format("%,.2f", totalPrice));

			}

		}

	}

	/**
	 * Private class to handle the events generated by the 
	 * workshop combo box component.
	 */
	private class WorkShopListener implements ListSelectionListener {
		
		public void valueChanged(ListSelectionEvent e) {
			totalPrice -= workshopTotalPrice;
			workshopTotalPrice = 0.0;
			int [] chosenArray = workshopList.getSelectedIndices();

				for (int i = 0; i < chosenArray.length; i++) {
					if (!e.getValueIsAdjusting()){
						System.out.println(workshopPriceArray[i]);
						System.out.println(workshopList.isSelectedIndex(i));
						workshopTotalPrice += workshopPriceArray[chosenArray[i]];
					}

				}

			totalPrice += workshopTotalPrice;
			totalPriceTextField.setText
			(String.format("%,.2f", totalPrice));
		}
	}

	/**
	 * Private inner class to handle events generated by the user 
	 * checking/un-checking the box for the optional dinner.
	 */
	private class CheckBoxListener implements ItemListener  {

		public void itemStateChanged(ItemEvent e) {

			if (e.getStateChange() == ItemEvent.SELECTED) {
				totalPrice += openingNightSpeech;
				totalPriceTextField.setText
				(String.format("%,.2f", totalPrice));

			} else if (e.getStateChange() == ItemEvent.DESELECTED){

				if (totalPrice > 0) {
					System.out.println(totalPrice + ", " + openingNightSpeech);
					totalPrice -= openingNightSpeech;
					totalPriceTextField.setText
					(String.format("%,.2f", totalPrice));

				} 
							}				

		}

	}

	/**
	 * Private inner class to handle the event when the user 
	 * clicks the clear selections button.
	 */
	private class ClearButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == clearSelectionsButton) {
				totalPrice = 0.0;
				totalPriceTextField.setText
				(String.format("%,.2f", totalPrice));
				registrationTotalPrice = 0.0;
				workshopTotalPrice = 0.0;
				buttonGroup.clearSelection();
				openingNightCheckBox.setSelected(false);
				workshopList.clearSelection();

			}

		}

	}
	/**
	 * The main method creates an instance of the class 
	 * causing it to display its window.
	 */
	public static void main(String[]args) {

		new ConferenceRegistrationSystem();

	}

}
