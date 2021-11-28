package ch13java6thedition;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
/**
 * 3. Dorm and Meal Plan Calculator
 * 
 * A university has the following dormitories:
 * 
 * 	Allen Hall: $1,500 per semester
 * 	Pike Hall: $1,600 per semester
 * 	Farthing Hall: $1,200 per semester
 * 	University Suites: $1,800 per semester
 * 
 * The university also has the following meal plans:
 * 
 * 	7 meals per week: $560 per semester
 * 	14 meals per week: $1,090 per semester
 * 	Unlimited meals: $1,500 per semester
 * 
 * Create an application with two combo boxes. One should hold the names
 * of the dormitories, and the other should hold the meal plans. The user should
 * select a dormitory and a meal plan, and the application should show the 
 * total charges for the semester.
 * 
 * @author craig
 * 8/30/21
 * 7:59pm
 * at home with Sean
 */
public class MealPlanCalculator extends JFrame {
	
	private JComboBox dormitoryBox;
	private JComboBox mealPlanBox;
	private JLabel dormitoryLabel;
	private JLabel chargesLabel;
	private JLabel mealPlanLabel;
	private JTextField chargesTextField;
	private JButton calculateButton;
	private JPanel buttonPanel;
	private JPanel dormitoryPanel;
	private JPanel mealPlanPanel;
	private JPanel chargesPanel;

	double dormitoryCharge = 0;
	double mealPlanCharge = 0;

	private final String [] dormitories = {"Allen Hall", "Pike Hall", 
				       "Farthing Hall", "University Suites"};

	private final String [] mealPlans = {"7 meals per week", "14 meals per week", 
			                     "Unlimited meals"};

	private final double [] dormitoryCharges = {1500, 1600, 1200, 1800};

	private final double [] mealPlanCharges = {560, 1090, 1500};
		

	/**
	 * Constructor.
	 */
	public MealPlanCalculator() {

		// Set the title of the window
		setTitle("Meal Plan Calcualtor");

		// Set the window's location
		setLocation(200, 250);

		// Specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Assign a GridLayout manager
		setLayout(new GridLayout(2, 2));

		// Build the panels
		buildDormitoryPanel();
		buildMealPlanPanel();
		buildButtonPanel();
		buildChargesPanel();

		// Add the panels to the content pane
		add(dormitoryPanel);
		add(mealPlanPanel);
		add(buttonPanel);
		add(chargesPanel);

		// Pack and display the window
		pack();
		setVisible(true);

	}

	/**
	 * The buildDormitoryPanel method builds the panel
	 * for the dormitories.
	 */
	public void buildDormitoryPanel() {

		// Create the panel
		dormitoryPanel = new JPanel();

		// Create the dormitory label
		dormitoryLabel = new JLabel("Dormitories:");

		// Create the combo box
		dormitoryBox = new JComboBox(dormitories);
		dormitoryBox.addActionListener(new ComboBoxListener());

		// Add the label and combo box to the panel
		dormitoryPanel.add(dormitoryLabel);
		dormitoryPanel.add(dormitoryBox); 

	}

	/**
	 * The buildMealPlanPanel builds the panel containing
	 * the meal plans.
	 */
	public void buildMealPlanPanel() {

		// Create the panel
		mealPlanPanel = new JPanel();

		// Create the combo box
		mealPlanBox = new JComboBox(mealPlans);
		mealPlanBox.addActionListener(new ComboBoxListener());

		// Create the label
		mealPlanLabel = new JLabel("Meal Plans:");

		// Add the label and combo box to the panel
		mealPlanPanel.add(mealPlanLabel);
		mealPlanPanel.add(mealPlanBox);

	}

	/**
	 * The buildButtonPanel method builds the panel
	 * that holds the button.
	 */
	public void buildButtonPanel() {

		// Create the button panel
		buttonPanel = new JPanel();

		// Create the button
		calculateButton = new JButton("Calculate");
		calculateButton.addActionListener(new ButtonListener());

		// Add the button to the panel
		buttonPanel.add(calculateButton);

	}

	/**
	 * The buildChargesPanel builds the panel that displays
	 * the charges.
	 */
	public void buildChargesPanel() {

		// Create the charges panel
		chargesPanel = new JPanel();

		// Create the label
		chargesLabel = new JLabel("Charges: $");

		// Create the text field
		chargesTextField = new JTextField("0.00", 10);

		// Set the charges field to uneditable
		chargesTextField.setEditable(false);

		// Add the label and  text field to the panel
		chargesPanel.add(chargesLabel);
		chargesPanel.add(chargesTextField);

	}

	/**
	 * Private inner class to handle the events created by the user
	 * choosing items from the lists.
	 */
	private class ComboBoxListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			int selection;

			if (e.getSource() == dormitoryBox) {

				selection = dormitoryBox.getSelectedIndex();
				dormitoryCharge = dormitoryCharges[selection];

			} else if (e.getSource() == mealPlanBox){
				selection = mealPlanBox.getSelectedIndex();
				mealPlanCharge = mealPlanCharges[selection];
			}

		}

	}
	
	/**
	 * Private inner class to handle the events generated
	 * by the user clicking the button.
	 */
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			chargesTextField.setText
				(String.format("%,.2f", (dormitoryCharge 
					+ mealPlanCharge)));

		}
	}

	/**
	 * The main method creates an instance of the class
	 * causing it to display its window.
	 */
	public static void main(String[]args) {

		new MealPlanCalculator();

	}
}
