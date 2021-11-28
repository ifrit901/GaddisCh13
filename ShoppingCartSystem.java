package ch13java6thedition;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

/**
 * Update: I am officially giving up on this problem for now, I've been trying to
 * find a way to remove the elements from the selected list after the user has
 * initially selected some. I've spent almost a week researching how to do this.
 * The book isn't clear how to do this and I've consulted the Oracle documentation about
 * how to implement DefaultListModel into the JLists in order to handle the removal of
 * elements. However, it still doesn't work the way I want it to. I believe part of the problem
 * has to do with the age of the textbook, the chapter teaches methods to use that are
 * deprecated (the getSelectedValues method). So I am moving on hoping the rest of the
 * programming challenges aren't dependant on that method to function.
 */

/**
 * 5. Shopping Cart System
 * 
 * Create an application that works like a shopping cart system for 
 * a book store. In this chapter's source code folder, you'll find a 
 * file called BookPrices.txt. This file contains the names and prices 
 * of various books, formatted in the following fashion:
 * 
 * 	I Did It Your Way, 11.95
 * 	The History of Scotland, 14.50
 * 	Learn Calculus In One Day, 29.95
 * 	Feel the Stress, 18.50
 * 
 * Each line in the file contains the name of a book, followed by a comma, 
 * followed by the book's retail price. When your application begins 
 * execution, it should read the contents of the file, and store the book 
 * titles in a list component. The user should be able to select a title from 
 * the list and add it to a shopping cart, which is simply another list 
 * component. The application should have buttons or menu items that
 * allow the user to remove items from the shopping cart, clear the 
 * shopping cart of all selections, and check out. When the user checks 
 * out, the application should calculate and display the subtotal of all the
 * books in the shopping cart, the sales tax (which is 6 percent of the subtotal), 
 * and the total.
 * 
 * 
 * @author craig
 * 8/6/21
 * 06:57am
 * at work with P. Ward
 */

public class ShoppingCartSystem extends JFrame {
		
	private JList fileList;
	private JList selectionList;

	private JPanel filePanel;
	private JPanel listPanel;
	private JPanel buttonPanel;
	private JPanel selectionPanel;

	private JButton addButton;
	private JButton removeButton;
	private JButton clearButton;
	private JButton checkOutButton;

	private JScrollPane scrollPane;

	private JLabel chargesLabel;

	private File bookFile;
	private double totalCost = 0.0;

	private double [] costList;
	private String [] bookList;
	private String [] selectedBookList;

	private final double TAX = 0.06;

	DefaultListModel fileListModel;
	DefaultListModel removalListModel;
	/**
	 * Constructor.
	 */
	public ShoppingCartSystem() throws IOException {

		// Set the title for the window
		setTitle("Book Sales");

		// Set the window location
		setLocation(200, 250);

		// Specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set a layout manager
		setLayout(new GridLayout(1, 3));

		// Build the panels
		buildFileListPanel();
		buildSelectionListPanel();
		buildButtonPanel();
		//buildChargesPanel();
	

		// Add the panels to the content pane
		add(filePanel);
		add(buttonPanel);
		add(selectionPanel);

		// Pack and display the window
		pack();
		setVisible(true);

	}

	/**
	 * The buildFileListPanel method retrieves the file containing the data
	 * on all the available books and transfers it to the JList component.
	 */
	public void buildFileListPanel() throws IOException{
		filePanel = new JPanel();

		buildFileList();

		fileListModel = new DefaultListModel();
		for(int i = 0; i < bookList.length; i++) {
			fileListModel.addElement(bookList[i]);
		}
		fileList.setModel(fileListModel);

		fileList.setVisibleRowCount(4);

		scrollPane = new JScrollPane(fileList);

		filePanel.add(scrollPane);

		
	}
	public void buildFileList() throws IOException{

		// Variable to hold number of lines in file;
		int lineCount = 0;

		// Variable to hold line text from file
		String nextLine;

		// Get the data from the file
		String fileName = "/home/craig/Documents/BookPrices.txt";
		bookFile = new File(fileName);

		// Read the unknown number of lines in the file
		// and add them to the array list
		Scanner numberCounter = new Scanner(bookFile);
		while(numberCounter.hasNextLine()) {
			nextLine = numberCounter.nextLine();
			lineCount++;
		}

		// Initialize the string array with new info about line count
		// from the file and also the double array for prices
		bookList = new String [lineCount];
		costList = new double [lineCount];
		
		// Re-read the file and put the book titles in the string 
		// array
		Scanner fileReader = new Scanner(bookFile);
		int counter = 0;
		while(fileReader.hasNextLine()) {

			nextLine = fileReader.nextLine();

			// Split the line by commas/spaces
			String [] splitter = nextLine.split(", ");

			// Add a book title to the string array
			bookList[counter] = splitter[0];

			// Add the price of a book to the double array
			costList[counter] = Double.parseDouble(splitter[1]);

			// Increment the counter
			counter++;

		}

		fileReader.close();
		
	}
	/**
	 * The buildSelectionListPanel builds the panel that displays 
	 * the choices selected by the user.
	 */
	public void buildSelectionListPanel() {

		// Create the panel
		selectionPanel = new JPanel();

		// Create the list 
		removalListModel = new DefaultListModel();
		selectionList = new JList(removalListModel);

		selectionList.setVisibleRowCount(5);
		selectionList.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		// Add the list to the panel
		selectionPanel.add(selectionList);



	}

	/**
	 * The buildButtonPanel builds the panel with the buttons 
	 * on it.
	 */
	public void buildButtonPanel() {

		// Create the panel
		buttonPanel = new JPanel();

		buttonPanel.setLayout(new GridLayout(3, 1));

		// Create the buttons 
		addButton = new JButton("Add Books");
		addButton.addActionListener(new ButtonListener());

		removeButton = new JButton("Remove Books");
		removeButton.addActionListener(new ButtonListener());

		clearButton = new JButton("Clear Selections");
		clearButton.addActionListener(new ButtonListener());

		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(clearButton);

	}

	
	
	/**
	 * Private inner class to handle the events generated by 
	 * the user clicking buttons.
	 */
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			Object [] newList;
			Object [] selections;


			if (e.getSource() == addButton) {

				selections = fileList.getSelectedValues();
				selectionList.setListData(selections);
				
			} else if (e.getSource() == removeButton) {
				
				if (selectionList.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(rootPane, 
						"No items selected...", "Oops...", 1);
				} else {
					int [] indices = selectionList.getSelectedIndices();

					selections = new Object [indices.length];											
					for (int i = 0; i < selectionList.getModel().getSize(); i++) {
					}
					
					//selectionList.setListData();
					
				}
				
			} else if (e.getSource() == clearButton) {

				selectionList.removeAll();
			}			



		}

	}

	
	/**
	 * The main method creates an instance of the class 
	 * causing it to display its window.
	 */
	public static void main(String[]args) throws IOException{
		new ShoppingCartSystem();
	}
}
