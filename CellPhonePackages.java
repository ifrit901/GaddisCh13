package ch13java6thedition;

/**
 * 6. Cell phone packages.
 *
 * Cell Solutions, a cell phone provider, sells the following packages:
 *
 * 300 minutes per month: $45.00 per month 800 minutes per month: $65.00 per
 * month 1500 minutes per month: $99.00 per month
 *
 * The provider sells the following phones (a 6 percent sales tax applies to the
 * sale of the phone):
 *
 * Model 100: $29.95 Model 110: $49.95 Model 200: $99.95
 *
 * Customers may also select the following options:
 *
 * Voice mail: $5.00 per month Text messaging: $10.00 per month
 *
 * Write an application that displays a menu system. The menu system should
 * allow the user to select one phone, one package, and any of the options
 * desired. As the user selects items from the menu, the application should show
 * the prices of the items selected.
 *
 * @author craig
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CellPhonePackages extends JFrame {

	// Phone picture
	ImageIcon image;

	// Menu variables
	JMenuBar menuBar;
	JMenu fileMenu, packageMenu, phoneMenu, optionsMenu;
	JMenuItem exitItem, clearSelectionsItem;

	// Package menu items
	JRadioButton package300Button, package800Button, package1500Button;
	JRadioButton model100Button, model110Button, model200Button;

	// Check boxes for extras
	JCheckBoxMenuItem voiceMailItem, textMessagesItem;

	// Areas to display charges
	JPanel packagePanel;
	JLabel packageLabel;
	JTextField packageTextField;

	JLabel phoneLabel;
	JTextField phoneTextField;

	JLabel optionsLabel;
	JTextField optionsTextField;

	// Panel for label
	JPanel labelPanel;
	JPanel totalPanel;
	JLabel totalLabel;
	JTextField totalTextField;

	// Label to display instructions
	JLabel welcomeLabel;

	// Plan prices
	double package300Price = 45.00, package800Price = 65.00, package1500Price = 99.00;

	// Phone prices
	double model100Price = 29.95, model110Price = 49.95, model200Price = 99.95;

	// Option prices
	double voiceMailPrice = 5.00, textMessagingPrice = 10.00;

	// Total price
	double totalPrice = 0;

	// model price
	double modelPrice = 0.0;

	// package price
	double packagePrice = 0.0;

	// options price
	double optionsPrice = 0.0;

	/**
	 * Constructor.
	 */
	public CellPhonePackages() {

		// Set the title of the window
		setTitle("Cell Phone Packages");

		// Set the location of the window
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// Set the layout manager
		setLayout(new BorderLayout());

		// Specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Phone picture stuff
		image = new ImageIcon("/home/craig/Pictures/phone.jpeg");
		JPanel imagePanel = new JPanel();
		JLabel imageLabel = new JLabel(image);
		imagePanel.add(imageLabel);

		// Set the initial text for the label
		welcomeLabel = new JLabel
			("<html>   Use the above menu to choose a phone plan,</br>"
			+ "a phone, and any additional options.</html>");
		welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

		// Label panel
		labelPanel = new JPanel();
		labelPanel.add(welcomeLabel);
		welcomeLabel.setForeground(Color.BLACK);
		welcomeLabel.setBorder
		(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));

		// Package panel and label
		packagePanel = new JPanel();
		JPanel packagePanel1 = new JPanel();
		packagePanel.setLayout(new GridLayout(3, 1));
		packagePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		packageLabel = new JLabel("Package: $");
		packageLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		packagePanel1.add(packageLabel);
		packageTextField = new JTextField(10);
		packageTextField.setText("0.00");
		packageTextField.setFont(new Font("SansSerif", Font.BOLD, 20));
		packageTextField.setEditable(false);
		packagePanel1.add(packageTextField);
		packagePanel.add(packagePanel1);

		// Phone panel and label
		JPanel phonePanel = new JPanel();
		phoneLabel = new JLabel("Phone: $");
		phoneLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		phonePanel.add(phoneLabel);
		phoneTextField = new JTextField(10);
		phoneTextField.setText("0.00");
		phoneTextField.setFont(new Font("SansSerif", Font.BOLD, 20));
		phoneTextField.setEditable(false);
		phonePanel.add(phoneTextField);
		packagePanel.add(phonePanel);

		// Options section
		JPanel optionsPanel = new JPanel();
		optionsLabel = new JLabel("Options: $");
		optionsLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		optionsPanel.add(optionsLabel);
		optionsTextField = new JTextField(10);
		optionsTextField.setFont(new Font("SansSerif", Font.BOLD, 20));
		optionsTextField.setText("0.00");
		optionsTextField.setEditable(false);
		optionsPanel.add(optionsTextField);
		packagePanel.add(optionsPanel);

		// Totals section
		totalPanel = new JPanel();
		totalLabel = new JLabel("Total: $");
		totalLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		totalTextField = new JTextField(10);
		totalTextField.setText("0.00");
		totalTextField.setFont(new Font("SansSerif", Font.BOLD, 20));
		totalPanel.add(totalLabel);
		totalPanel.add(totalTextField);

		// Add the label and text area to the content pane
		add(labelPanel, BorderLayout.NORTH);
		add(packagePanel, BorderLayout.WEST);
		add(imagePanel, BorderLayout.CENTER);
		add(totalPanel, BorderLayout.SOUTH);

		// Build the menu bar
		buildMenuBar();

		// Pack and display the window
		pack();
		setVisible(true);

	}

	/**
	 * The buildMenuBar builds the menu bar and calls the methods that build
	 * the menus.
	 */
	public void buildMenuBar() {

		// Create the menu bar
		menuBar = new JMenuBar();

		// Create the menus
		buildFileMenu();	// To hold the exit button
		buildPackageMenu();
		buildPhoneMenu();
		buildOptionsMenu();

		// Add the menus to the menu bar
		menuBar.add(fileMenu);
		menuBar.add(packageMenu);
		menuBar.add(phoneMenu);
		menuBar.add(optionsMenu);

		// Set the menu bar
		setJMenuBar(menuBar);

	}

	/**
	 * The buildFileMenu method creates a menu item in the file menu that
	 * holds the exit button.
	 */
	public void buildFileMenu() {

		// Create a JMenu object for the file menu
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F); 	// In java.awt.event.*;
		fileMenu.setFont(new Font("SansSerif", Font.BOLD, 20));

		// Create a JMenuItem for the exit button
		exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_E);
		exitItem.setFont(new Font("SansSerif", Font.BOLD, 20));

		// Add an action listener to the exit button
		exitItem.addActionListener(new FileListener());

		// Create a JMenuItem for the clear selections button
		clearSelectionsItem = new JMenuItem("Clear Selections");
		clearSelectionsItem.setMnemonic(KeyEvent.VK_C);
		clearSelectionsItem.setFont(new Font("SansSerif", Font.BOLD, 20));

		// Add an action listener to the clear selections button
		clearSelectionsItem.addActionListener(new FileListener());

		fileMenu.add(clearSelectionsItem);
		fileMenu.add(exitItem);

	}

	/**
	 * The buildPackageMenu method builds the Package menu and returns a
	 * reference to its JMenu object.
	 */
	public void buildPackageMenu() {

		// Create the menu
		packageMenu = new JMenu("Packages");
		packageMenu.setMnemonic(KeyEvent.VK_P);
		packageMenu.setFont(new Font("SansSerif", Font.BOLD, 20));

		// Create the radio button menu items and add an
		// action listener to each one
		package300Button = new JRadioButton("300 minutes ($45 per month)");
		package300Button.setMnemonic(KeyEvent.VK_3);
		package300Button.setFont(new Font("SansSerif", Font.BOLD, 20));
		package300Button.addActionListener(new PackageListener());

		package800Button = new JRadioButton("800 minutes ($65 per month)");
		package800Button.setMnemonic(KeyEvent.VK_8);
		package800Button.addActionListener(new PackageListener());
		package800Button.setFont(new Font("SansSerif", Font.BOLD, 20));

		package1500Button = new JRadioButton("1500 minutes ($99 per month)");
		package1500Button.setMnemonic(KeyEvent.VK_1);
		package1500Button.addActionListener(new PackageListener());
		package1500Button.setFont(new Font("SansSerif", Font.BOLD, 20));

		// Create a button group for the radio buttons
		ButtonGroup packageGroup = new ButtonGroup();
		packageGroup.add(package300Button);
		packageGroup.add(package800Button);
		packageGroup.add(package1500Button);

		// Add the menu items to the menu
		packageMenu.add(package300Button);
		packageMenu.add(package800Button);
		packageMenu.add(package1500Button);

	}

	/**
	 * The buildPhoneMenu method builds the menu that displays the phones
	 * the user can choose.
	 */
	public void buildPhoneMenu() {

		// Create the menu
		phoneMenu = new JMenu("Phones");
		phoneMenu.setMnemonic(KeyEvent.VK_P);
		phoneMenu.setFont(new Font("SansSerif", Font.BOLD, 20));

		// Create the menu items
		model100Button = new JRadioButton("Model 100 ($29.95)");
		model100Button.setFont(new Font("SansSerif", Font.BOLD, 20));
		model100Button.addActionListener(new PhoneListener());

		model110Button = new JRadioButton("Model 110 ($49.95)");
		model110Button.setFont(new Font("SansSerif", Font.BOLD, 20));
		model110Button.addActionListener(new PhoneListener());

		model200Button = new JRadioButton("Model 200 ($99.95)");
		model200Button.setFont(new Font("SansSerif", Font.BOLD, 20));
		model200Button.addActionListener(new PhoneListener());

		// Create a button group for the radio buttons
		ButtonGroup phoneGroup = new ButtonGroup();
		phoneGroup.add(model100Button);
		phoneGroup.add(model110Button);
		phoneGroup.add(model200Button);

		// Add the menu items to the menu
		phoneMenu.add(model100Button);
		phoneMenu.add(model110Button);
		phoneMenu.add(model200Button);

	}

	/**
	 * The buildOptionsMenu method builds the menu that displays the
	 * additional options that can be added to the plan.
	 */
	public void buildOptionsMenu() {

		// Create the menu
		optionsMenu = new JMenu("Extra Options");
		optionsMenu.setMnemonic(KeyEvent.VK_O);
		optionsMenu.setFont(new Font("SansSerif", Font.BOLD, 20));

		// Create the check box menu items
		voiceMailItem = new JCheckBoxMenuItem("Voicemail ($5)");
		voiceMailItem.setFont(new Font("SansSerif", Font.BOLD, 20));
		voiceMailItem.addActionListener(new OptionsListener());

		textMessagesItem = new JCheckBoxMenuItem("Text Messages ($10)");
		textMessagesItem.setFont(new Font("SansSerif", Font.BOLD, 20));
		textMessagesItem.addActionListener(new OptionsListener());

		// Add the items to the menu
		optionsMenu.add(voiceMailItem);
		optionsMenu.add(textMessagesItem);

	}

	/**
	 * Private inner class that handles the event generated when the user
	 * clicks the exit button.
	 */
	private class FileListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == exitItem) {
				System.exit(0);
			} else if (e.getSource() == clearSelectionsItem) {
				totalPrice = 0.00;
				totalTextField.setText("0.00");
				packagePrice = 0.0;
				packageTextField.setText("0.00");
				modelPrice = 0.0;
				phoneTextField.setText("0.00");
				optionsPrice = 0.0;
				optionsTextField.setText("0.00");
			}

		}
	}

	/**
	 * Private inner class that handles events generated by the radio
	 * buttons in the package menu.
	 */
	private class PackageListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (package300Button.isSelected()) {
				if (packagePrice > 0) {
					totalPrice -= packagePrice;
					packagePrice -= packagePrice;
				}
				String price = String.format("%,.2f", package300Price);
				packagePrice += package300Price;
				totalPrice += package300Price;
				packageTextField.setText(price);
				totalTextField.setText(String.format("%,.2f", totalPrice));

			} else if (package800Button.isSelected()) {
				if (packagePrice > 0) {
					totalPrice -= packagePrice;
					packagePrice -= packagePrice;
				}
				String price = String.format("%,.2f", package800Price);
				packagePrice += package800Price;
				totalPrice += package800Price;
				packageTextField.setText(price);
				totalTextField.setText(String.format("%,.2f", totalPrice));

			} else if (package1500Button.isSelected()) {
				if (packagePrice > 0) {
					totalPrice -= packagePrice;
					packagePrice -= packagePrice;
				}
				String price = String.format("%,.2f", package1500Price);
				packagePrice += package1500Price;
				totalPrice += package1500Price;
				packageTextField.setText(price);
				totalTextField.setText(String.format("%,.2f", totalPrice));

			}

		}

	}

	/**
	 * Private inner class to handle the events generated when the phone
	 * radio buttons are clicked.
	 */
	private class PhoneListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (model100Button.isSelected()) {
				String price = String.format("%,.2f", model100Price);
				if (modelPrice > 0) {
					totalPrice -= modelPrice;
					modelPrice -= modelPrice;
				}
				modelPrice += model100Price;
				totalPrice += modelPrice;
				phoneTextField.setText(price);
				totalTextField.setText(String.format("%,.2f", totalPrice));

			} else if (model110Button.isSelected()) {
				String price = String.format("%,.2f", model110Price);
				if (modelPrice > 0) {
					totalPrice -= modelPrice;
					modelPrice -= modelPrice;
				}
				modelPrice += model110Price;
				totalPrice += model110Price;
				phoneTextField.setText(price);
				totalTextField.setText(String.format("%,.2f", totalPrice));

			} else if (model200Button.isSelected()) {
				String price = String.format("%,.2f", model200Price);
				if (modelPrice > 0) {
					totalPrice -= modelPrice;
					modelPrice -= modelPrice;
				}
				modelPrice += model200Price;
				totalPrice += model200Price;
				phoneTextField.setText(price);
				totalTextField.setText(String.format("%,.2f", totalPrice));

			}

		}

	}

	/**
	 * Private inner class to handle the events generated by the user
	 * clicking the option check box choices.
	 */
	private class OptionsListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == voiceMailItem) {
				if (voiceMailItem.isSelected()) {
					String price = 
					String.format("%,.2f", voiceMailPrice);
					optionsPrice += voiceMailPrice;
					totalPrice += voiceMailPrice;
					optionsTextField.setText(price);
					totalTextField.setText
						(String.format("%,.2f", totalPrice));

				} else {
					totalPrice -= voiceMailPrice;
					optionsPrice -= voiceMailPrice;
					optionsTextField.setText
					           (String.format("%,.2f", optionsPrice));
					totalTextField.setText
						(String.format("%,.2f", totalPrice));

				}
			} else if (e.getSource() == textMessagesItem) {
				if (textMessagesItem.isSelected()) {
					String price = String.format
						("%,.2f", textMessagingPrice);
					optionsPrice += textMessagingPrice;
					totalPrice += textMessagingPrice;
					optionsTextField.setText(price);
					totalTextField.setText(String.format
						("%,.2f", totalPrice));

				} else {
					totalPrice -= textMessagingPrice;
					optionsPrice -= textMessagingPrice;
					optionsTextField.setText
					          (String.format("%,.2f", optionsPrice));
					totalTextField.setText(String.format
					           ("%,.2f", totalPrice));

				}
			}

		}

	}

	/**
	 * The main method creates an instance of the class causing its window
	 * to be displayed.
	 */
	public static void main(String[] args) {

		new CellPhonePackages();

	}

}
