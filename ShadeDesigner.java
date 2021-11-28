package ch13java6thedition;

/**
 * 7. Shade Designer
 * 
 * A custom window shade designer charges a base fee of $50 per shade. 
 * In addition, charges are added for certain styles, sizes, and colors as follows:
 * 
 * Styles:
 * 	Regular shades: Add $0
 * 	Folding shades: Add $10
 * 	Roman shades: Add $15
 * 
 * Sizes:
 * 	25 inches wide: Add $0
 * 	27 inches wide: Add $2
 * 	32 inches wide: Add $4
 * 	40 inches wide: Add $6
 * 
 * Colors:
 * 	Natural: Add $5
 * 	Blue: Add $0
 * 	Teal: Add $0
 * 	Red: Add $0
 * 	Green: Add $0
 * 
 * Create an application that allows the user to select the style, size, 
 * color, and number of shades from lists or combo boxes. The total
 * charges should be displayed.
 * 
 * @author craig
 * 9/20/21
 * @05:48 am at work with Patrick Ward
 */
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


public class ShadeDesigner extends JFrame {
	
	// Style prices
	double regularStylePrice = 0.0, foldingStylePrice = 10.0, romanStylePrice = 15.0,
	           totalStylePrice = 0.0;

	// Size prices
	double twentyFiveInches = 0.0, twentySevenInches = 2.0, thirtyTwoInches = 4.0,
	           fourtyInches = 6.0, totalSizePrice = 0.0;

	
	// Color prices
	double natural = 5.0, blue = 0.0, teal = 0.0, red = 0.0, green = 0.0, 
	           totalColorPrice = 0.0;

	// South panel total price
	double totalPrice = 0.0;

	// Number of shades
	int numberOfShades = 0;

	// Style names
	String [] styles = {"Regular", "Folding", "Roman"};

	// Size names 
	String [] sizes  = {"25 inches", "27 inches", "32 inches", "40 inches"};

	// Color names
	String [] colors = {"Natural", "Blue", "Teal", "Red", "Green"};

	// JPanels
	JPanel northPanel, centerPanelMain, centerPanel1, centerPanel2, centerPanel3, southPanel;

	// JLists
	JList styleList, sizeList, colorList;

	// Slider bar for number of shades
	JSlider slider;

	JButton clearButton;

	// Label and text field for south panel total price display
	JLabel totalLabel;
	JTextField totalTextField;

	/**
	 * Constructor.
	 */
	public ShadeDesigner() {

		
		// Set the title of the window
		setTitle("Shade Designer");

		// Specify the action of the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Size the window 
		setSize(400, 400);

		// Set the location of the window
		setLocation(300, 150);

		// Set the layout manager
		setLayout(new BorderLayout());

		// Build the panels
		buildNorthPanel();
		buildCenterPanel();
		buildSouthPanel();

		// Add the panels to the content pane
		add(slider, BorderLayout.NORTH);
		add(centerPanelMain, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);

		// Pack and display the window
		pack();
		setVisible(true);
	}

	/**
	 * The buildNorthPanel builds the panel that contains
	 * the slider that lets the user select the number of 
	 * shades.
	 */
	public void buildNorthPanel() {

		// Create the panel object
		northPanel = new JPanel();

		// Create the slider object
		slider = new JSlider(JSlider.HORIZONTAL, 0, 50, 1);
		slider.setFont(new Font("SansSerif", Font.BOLD, 20));

		// Specify the tick spacing and paint the ticks and labels
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(new SlideListener());

		// Add the slider to the panel
		//northPanel.add(slider);
	}

	/**
	 * The buildCenterPanel method builds the panels that contain
	 * the JLists the user can make choices from.
	 */
	public void buildCenterPanel() {

		// Create the panels
		centerPanelMain = new JPanel();
		centerPanelMain.setLayout(new GridLayout(1, 3));
		centerPanelMain.setLayout(new FlowLayout());
		centerPanel1 = new JPanel();
		centerPanel2 = new JPanel();
		centerPanel3 = new JPanel();


		// Create the JLists for user options
		styleList = new JList(styles);		
		styleList.setFont(new Font("SansSerif", Font.BOLD, 20));
		styleList.setBorder(BorderFactory.createEtchedBorder());
		styleList.addListSelectionListener(new StyleListener());

		sizeList = new JList(sizes);
		sizeList.setFont(new Font("SansSerif", Font.BOLD, 20));
		sizeList.setBorder(BorderFactory.createEtchedBorder());
		sizeList.addListSelectionListener(new SizeListener());

		colorList = new JList(colors);
		colorList.setFont(new Font("SansSerif", Font.BOLD, 20));
		colorList.setBorder(BorderFactory.createEtchedBorder());
		colorList.addListSelectionListener(new ColorListener());

		// Set the lists to single selection mode
		styleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Add the lists to the panels
		centerPanel1.add(styleList);
		centerPanel2.add(sizeList);
		centerPanel3.add(colorList);

		// Add the three panels to the main panel
		centerPanelMain.add(centerPanel1);
		centerPanelMain.add(centerPanel2);
		centerPanelMain.add(centerPanel3);
	}

	/**
	 * The buildSouthPanel builds the panel that holds the label 
	 * and text field to display the total price.
	 */
	public void buildSouthPanel() {

		// Create the panel
		southPanel = new JPanel();

		// Create the label and text field for total price
		totalLabel = new JLabel("Total Price: $");
		totalLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		totalTextField = new JTextField(6);
		totalTextField.setFont(new Font("SansSerif", Font.BOLD, 20));

		// Set the text field to uneditable
		totalTextField.setEditable(false);

		// Add button to clear selections
		clearButton = new JButton("Clear Selections");
		clearButton.addActionListener(new ButtonListener());

		// Add the components to the panel
		southPanel.add(totalLabel);
		southPanel.add(totalTextField);
		southPanel.add(clearButton);

	}

	/**
	 * Private inner class to hold the events generated by the 
	 * user clicking on items from the lists.
	 */
	private class StyleListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {

			if(!e.getValueIsAdjusting()) {

				totalPrice -= totalStylePrice;
				totalStylePrice = 0.0;
				if (styleList.getSelectedIndex() == 0) {
					totalStylePrice = regularStylePrice;
				} else if (styleList.getSelectedIndex() == 1) {
					totalStylePrice = foldingStylePrice;
				} else if (styleList.getSelectedIndex() == 2) {
					totalStylePrice = romanStylePrice;
				}
				totalPrice += totalStylePrice;
				totalTextField.setText(String.format("%,.2f", totalPrice));

			}

		}

	}

	/**
	 * Private inner class to handle the events generated by the 
	 * user clicking a choice from the size list.
	 */
	private class SizeListener implements ListSelectionListener{

		public void valueChanged(ListSelectionEvent e) {

			if(!e.getValueIsAdjusting()) {

				totalPrice -= totalSizePrice;
				totalSizePrice = 0.0;

				if (sizeList.getSelectedIndex() == 0) {
					totalSizePrice = twentyFiveInches;
				} else if (sizeList.getSelectedIndex() == 1) {
					totalSizePrice = twentySevenInches;
				} else if (sizeList.getSelectedIndex() == 2) {
					totalSizePrice = thirtyTwoInches;
				} else if (sizeList.getSelectedIndex() == 3) {
					totalSizePrice = fourtyInches;
				}

				totalPrice += totalSizePrice;
				totalTextField.setText(String.format("%,.2f", totalPrice));

			}

		}

	}

	/**
	 * Private inner class to handle the events generated when the 
	 * user clicks an option from the color list.
	 */
	private class ColorListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {

			if (!e.getValueIsAdjusting()) {

				totalPrice -= totalColorPrice;
				totalColorPrice = 0.0;

				if (colorList.getSelectedIndex() == 0) {
					totalColorPrice = natural;
				} else if (colorList.getSelectedIndex() == 1) {
					totalColorPrice = blue;
				} else if (colorList.getSelectedIndex() == 2) {
					totalColorPrice = teal;
				} else if (colorList.getSelectedIndex() == 3) {
					totalColorPrice = green;
				} else if (colorList.getSelectedIndex() == 4) {
					totalColorPrice = red;
				}

				totalPrice += totalColorPrice;
				totalTextField.setText(String.format("%,.2f", totalPrice));

			}

		}
		

	}

	/**
	 * Private inner class to handle the events generated when the 
	 * user changes the value on the slide bar.
	 */
	private class SlideListener implements ChangeListener {

		public void stateChanged(ChangeEvent e) {

			totalPrice = totalColorPrice + totalSizePrice + totalStylePrice;
			numberOfShades = slider.getValue();
			totalPrice *= numberOfShades;
			totalTextField.setText(String.format("%,.2f", totalPrice));

		}

	}

	/**
	 * Private inner class to clear the current selections when 
	 * the user clicks the clear selections button.
	 */
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			styleList.clearSelection();
			totalStylePrice = 0.0;
			sizeList.clearSelection();
			totalSizePrice = 0.0;
			colorList.clearSelection();
			slider.setValue(1);
			totalColorPrice = 0.0;
			totalPrice = 0.0;
			totalTextField.setText(String.format("%,.2f", totalPrice));

		}

	}
	/**
	 * The main method creates an instance of the class
	 * causing it to display its window.
	 */
	public static void main(String[]args) {

		new ShadeDesigner();

	}
}
