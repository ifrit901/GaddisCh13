package ch13java6thedition;

/**
 * This is the 'Find the Error' portion of the review questions
 * and exercises of chapter 13.
 * 
 * @author craig
 */
public class FindTheError {
	
	/*
	1. // Create a read-only text field
	    JTextField textField = new JTextField(10);
	    textField.setEditable(true);

	answer: The argument in the JTextField constructor is just '10'. 
	It should be two integer arguments, one for rows and one for columns.

	2. // Create a 1 - pixel border around  'list', a JList component
	   list.setBorder(Color.BLACK, 1);

	answer: The argument passed to the setBorder method should be:
	list.setBorder(BorderFactor.createLineBorder(Color.BLACK, 1); 

	3. // Create a JList and add it to a scroll pane
	   // Assume that array already exists
	   JList list = new JList(array);
	   JScrollPane scrollPane = new JScrollPane();
	   scrollPane.add(list);

	answer: First, the list should have the number of rows declared with
	the setVisibleRowCount method. This should be done before creating 
	the scroll pane and adding the list to it. 

	4. // Assume that nameBox is a combo box and is properly
	    // set up with a list of names to choose from.
	    // Get value of the selected item.
	    String selectedName = nameBox.getSelectedIndex();

	answer: The getSelectedIndex method returns an integer. String
	selectedName will not accept this, the method used should be 
	(String) nameBox.getSelectedItem().

	5. JLabel label = new JLabel("Have a nice day!");
	    label.setImage(image);

	answer: The method for setting 'image' should be: label.setIcon(image);

	6. // Add a menu bar to the menu
	    JMenuBar menuBar = new JMenuBar(menuItem);

	answer: The JMenuBar constructor doesn't(?) accept arguments like this, 
	The menuBar object should be created and then the components added
	with menuBar.add(menuItem);

	7. // Create a text area with 20 columns and 5 rows.
	    JTextArea textArea = new JTextArea(20, 5);

	answer: The arguments are reversed, it should be 
	JTextArea textArea = new JTextArea(5, 20);

	*/
}
