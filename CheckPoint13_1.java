package ch13java6thedition;

/**
 * This is Check Point 13.1 - 13.6.
 * 
 * @author craig
 */
public class CheckPoint13_1 {
	
	/*
	1. How do you make a text field read-only? In code, how do you
	    store text in a text field?

	answer: To make a text field read-only, you call the setEditable 
	method on the text field object and pass false as the argument.

	2. What is the index of the first item stored in a JList or a 
	    JComboBox component? If one of these components holds 12
	    items, what is the index of the 12th item?

	answer: The index of items in JList and JComboBox components 
	follow the same rule as array indexes. They start at 0 and go up
	incrementally from there. So the first stored item in either component
	would have an index of 0 and the 12th item would have an index of 
	11.

	3. How do you retrieve the selected item from a JList component?
	    How do you get the index of the selected item?

	answer: By creating a string variable to pass as an argument to the
	getSelectedValue method which is called on the JList object. The 
	call must be preceeded by a cast to the String class (String). You 
	can also retrieve the index of the selected item with the 
	getSelectedIndex method which returns an int value. (-1) if the item
	is not present in the list.

	4. How do you cause a scroll bar to be displayed with a JList component?

	answer: First you set the number of visible rows in the JList component 
	with the setVisibleRowCount method, then you create a JScrollPane 
	object and add the JList object to it. Then you add the JScrollPane to 
	any containers/panels.

	5. How do you retrieve the selected item from a JComboBox component?
	    How do you get the index of the selected item?

	answer: The selected item from a JComboBox is retrieved the same
	ways as from a JList component. For the string value you cast the 
	value returned from the getSelectedValue method called on the 
	JComboBox object to a String. You also have the option to get the 
	index of the item with the getSelectedIndex method.

	6. What is the difference between an un-editable and an editable 
	    combo box? Which of these is a combo box by default?

	answer: An editable combo box allows the user to type a selection
	in to the text field to find an option in the list. An un-editable combo
	box does not allow this. Combo boxes are un-editable by default. 
	*/
}
