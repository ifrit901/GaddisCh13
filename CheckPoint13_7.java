package ch13java6thedition;

/**
 * This is Code Listing 13.7 - 13.9.
 * Also I've included the next check point since it's
 * only two problems.
 * 
 * @author craig
 */
public class CheckPoint13_7 {
	
	/*
	7. How do you store an image in a JLabel component? How do you 
	    store an image and text in a JLabel component?

	answer: An image is stored in a JLabel component by creating a 
	ImageIcon object and passing it as an argument to the JLabel 
	constructor, or with the setIcon method. You can store text with 
	the image by using the JLabel constructor that takes text, an 
	ImageIcon object and a SwingConstants int argument for placement
	of the text in relation to the image.

	8. How do you store an image in a JButton component? How do you
	    store both an image and text in a JButton component?

	answer: Storing an image in a JButton component works the same as
	a JLabel component. You pass a reference to an image file to the 
	constructor or use the setIcon method. Text for the button is also 
	passed as an argument.

	9. What method do you use to store an image in an existing JLabel 
	    or JButton component?

	answer: The setIcon method.

	10. What is a mnemonic? How do you assign a mnemonic to a 
	      component?

	answer: A mnemonic is a keyboard short cut for a button or other 
	component. You can access it by pressing the ALT key and a predetermined
	key associated with it. It is assigned to the component by calling the 
	setMnemonic(KeyEvent.VK_X) on the variable referencing the component
	where 'X' is a key chosen by the programmer.

	11. 
	*/	


}
