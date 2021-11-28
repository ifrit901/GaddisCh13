package ch13java6thedition;

/**
 * This is Check Point 13.12 - 13.22. 
 * 
 * @author craig
 */
public class CheckPoint13_12 {
	
	/*
	12. Briefly describe each of the following menu system items:
		a. Menu bar - The menu bar lists the names of one or more menus. 

		b. Menu item - A menu item is something in a menu that is 
		selected by the user and usually performs some action.

		c. Check box menu item - A menu item with a small box beside it.
	 	The box can be checked or unchecked and usually turns an option
		on or off.

		d. Radio button menu item - A menu item with a small circle next 
		to it. The circle can be selected or deselected. When placed in a 
		button group, only one selection can be chosen at a time.

		e. Submenu - A menu within a menu.

		f. Separator bar - A horizontal bar that is used to separate groups 
		of items in a menu.

	13. What class do you use to create a regular menu item? What do you 
	      pass to the class constructor?

	answer: The JMenuItem class. The text that you want to appear is passed 
	to the class constructor.

	14. What class do you use to create a radio button menu item? What do you 
	      pass to the class constructor? How do you cause a radio button to be
	      initially selected?

	answer: With the JRadioButtonMenuItem class. You can pass the text you 
	want to appear next to the button to the constructor as well as a boolean 
	that can cause it to be automatically selected. (by passing 'true')

	15. How do you create a relationship between radio button menu items 
	      so that only one may be selected at a time?

	answer: By creating a button group and adding the radio button menu 
	items to it.

	16. What class do you use to create a check box menu item? What do you
	      pass to the class constructor? How do you cause it to be initially selected?

	answer: You use the JCheckBoxMenuItem class. Text is passed to the constructor
	as well as a boolean if you want it to be initially selected.

	17. What class do you use to create a menu? What do you pass to the class 
	      constructor?

	answer: The JMenu class is used to create a menu.  Text is passed to the class
	constructor.

	18. What class do you use to create a menu bar?

	answer: The JMenuBar class. 

	19. How do you place a menu bar in a JFrame?

	answer: by calling the setJMenuBar method on the menu bar object.

	20. What type of event do menu items genarate when selected by the user?

	answer: Action Events.

	21. How do you change the size of a component such as a JLabel after it 
	      has been created?

	answer: by calling the setPreferredSize method.
	*/
}
