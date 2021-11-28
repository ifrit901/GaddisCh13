package ch13java6thedition;

/**
 * This is Check Point 13.23 - 13.28. 
 * 
 * @author craig
 */
public class CheckPoint13_23 {
	
	/*
	23. What arguments do you pass to the JTextArea constructor?

	answer: The JTextArea constructor takes integer arguments for 
	the number of rows and columns as well as a string for any text
	you want to appear initially.
	
	24. How do you retrieve the text that is stored in a JTextArea 
	      component?

	answer: With the getText method. 

	25. Does the JTextArea component automatically display scroll 
	      bars? If not, how do you accomplish this?

	answer: No, it does not automaticallyy display scroll bars. You can
	apply scroll bars by creating a JScrollBar object and passing the 
	JTextArea object to it as an argument.

	26. What is line wrapping? What are the two styles of line wrapping? 
	      How do you turn a JTextArea component's line wrapping on?
	      How do you select a line wrapping style?

	answer: Line wrapping is when the line being typed skips to the line
	below it when the current line runs out of room at the right of the 
	screen or text area. You turn on line wrapping for a JTextArea by 
	passing 'true' to the object's setLineWrap method. A style of line 
	wrapping is chosen by passing true or false to the object's 
	setWrapStyleWord method. True if you don't want the words to be
	broken up and false if you want it to break at the last character on 
	the line.

	27. What type of argument does a component's setFont method accept?

	answer: A component's setFont method accepts arguments for the name
	of the font, the style and the size. The arguments for style can 
	have more than one style concatenated together, for instance Serif + Italic.

	28. What are the arguments that you pass to the Font class constructor?

	answer: Name (of the font), style, and size.
	*/
	

}
