package ch13java6thedition;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
/**
 * This is Code Listing 13-7, the TempConverter 
 * class, which demonstrates a simple thermometer
 * converter between Celsius and Fahrenheit using 
 * a slider bar.
 * 
 * @author craig
 */
public class TempConverter extends JFrame {
	
	private JLabel label1, label2;		// Message labels
	private JTextField fahrenheitField;	// Fahrenheit temperature
	private JTextField celsiusField;		// Celsius temperature
	private JPanel fpanel;			// Fahrenheit panel
	private JPanel cpanel;			// Celsius panel
	private JPanel sliderPanel;		// Slider panel
	private JSlider slider;			// Slider bar 

	
	/**
	 * Constructor.
	 */
	public TempConverter() {

		// Set the title
		setTitle("Temperatures");

		// Specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set window location
		setLocation(500, 250);

		// Create the message labels
		label1 = new JLabel("Fahrenheit: ");
		label2 = new JLabel("Celsius: ");

		// Create the read-only text fields
		fahrenheitField = new JTextField("32.0", 10);	
		fahrenheitField.setEditable(false);
		celsiusField = new JTextField("0.0", 10);
		celsiusField.setEditable(false);

		// Create the slider
		slider = new JSlider(0, 100, 0);
		slider.setMajorTickSpacing(20); 		// Major tick every 20
		slider.setMinorTickSpacing(5); 		// Minor tick every 5
		slider.setPaintTicks(true); 		// Display the ticks
		slider.setPaintLabels(true);		// Display numbers
		slider.addChangeListener(new SliderListener());

		// Create the panels and put the components in them
		fpanel = new JPanel();
		fpanel.add(label1);
		fpanel.add(fahrenheitField);
		cpanel = new JPanel();
		cpanel.add(label2);
		cpanel.add(celsiusField);
		sliderPanel = new JPanel();
		sliderPanel.add(slider);

		// Create a GridLayout manager
		setLayout(new GridLayout(3, 1));

		// Add the panels to the content pane
		add(fpanel);
		add(cpanel);
		add(sliderPanel);

		// Pack and display the frame
		pack();
		setVisible(true);

	}

	/**
	 * Private inner class to handle the change events that are
	 * generated when the slider is moved.
	 */
	private class SliderListener implements ChangeListener {

		public void stateChanged(ChangeEvent e) {

			double fahrenheit, celsius;

			// Get the slider value
			celsius = slider.getValue();

			// Convert the value to fahrenheit
			fahrenheit = (9 / 5.0) * celsius + 32;

			// Store the celsius temperature in its display field
			celsiusField.setText(Double.toString(celsius));

			// Store the fahrenheit temperature in its display field
			fahrenheitField.setText(String.format("%.1f", fahrenheit));
			
		}
		 
	}

	/**
	 * Main method creates an instance of the class
	 * causing its window with slider to display.
	 */
	public static void main(String[]args) {

		new TempConverter();

	}
}
