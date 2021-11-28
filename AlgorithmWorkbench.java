package ch13java6thedition;
import javax.swing.*;
import java.awt.*;
/**
 * This is the Algorithm Workbench for chapter 13.
 * 
 * @author craig
 */
public class AlgorithmWorkbench extends JFrame {
	
	public static void main(String[]args) {

		/*
		1. Give an example of code that creates a read-only text field.

		answer: JTextField textField = new JTextField(10);
		            textField.setEditable(false);

		2. Write code that creates a list with the following items: Monday, Tuesday,
		    Wednesday, Thursday, Friday, Saturday, Sunday.

		answer: String array = {"Monday", "Tuesday", "Wednesday", "Thursday",
				     "Friday", "Saturday", "Sunday"};
			JList list = new JList(array);

		3. Write code that adds a scroll bar to the list you created in #2.

		answer: JScrollBar scrollBar = new JScrollBar();
		            scrollBar.add(list);

		4. Assume that the variable myList references a JList component, and 
		    'selection' is a String variable. Write code that assigns the selected 
		    item in the myList component to the 'selection' variable. 

		answer: String selection = (String) myList.getSelectedValue();

		5. Assume that the variable myComboBox references an uneditable 
		    combo box, and 'selectionIndex' is an int variable. Write code that
		    assigns the index of the selected item in the myComboBox component
		    to the selectionIndex variable.

		answer: Int selectionIndex = myComboBox.getSelectedIndex();

		6. Write code that stores the image in the file 'dog.jpg' in a label.

		answer: JLabel label = new JLabel();
		            ImageIcon icon = new ImageIcon("dog.jpg");
		            label.setIcon(icon);

		7. Assume that 'label' references a JLabel object. Write code that stores 
		    the image in the file 'picture.gif' in the label.

		answer: ImageIcon image = new ImageIcon("picture.gif");
		            label.setIcon(image);

		8. Write code that creates a button with the text "Open File". Assign 
		    the 'O' key as a mnemonic and assign "This button opens a file"
		    as its tooltip.

		answer: JButton button = new JButton("Open File");
		            button.setMnemonic(KeyEvent.VK_O);

		9. Write code that displays a file open dialog box. If the user selects a 
		    file, the code should store the file's path and name to a String variable.

		answer: JFileChooser fileChooser = new JFileChooser();
		            int status = fileChooser.showOpenDialog(null);
		            if(status == JFileChooser.APPROVE_OPTION) {
			    File selectedFile = fileChooser.getSelectedFile();
			    String fileName = selectedFile.getPath();
		             }

		10. Write code that creates a text area displaying 10 rows and 15 columns.
		     The text area should be capable of displaying scroll bars, when necessary.
		     It should also perform word-style wrapping.

		answer: JTextArea textArea = new JTextArea(10, 15);
		            JScrollPane scrollPane = new JScrollPane(textArea);
		            scrollPane.setVerticalScrollBarPolicy(JScrollpane.VERTICAL_SCROLLBAR_AS_NEEDED);

		11. Write the code that creates a menu bar with one menu named File.
		     The File menu should have the 'F' key assigned as a mnemonic. The
		     File menu should have three menu items: Open, Print, and Exit. Assign
		     Mnemonic keys of your choice to each of these items. Register an 
		     instance of the OpenListener class as an action listener for the Open
		     menu item, an instance of the PrintListener class as an action listener 
		     for the Print menu item, and an instance of the ExitListener class as 
		     an action listener for the Exit menu item. Assume these classes have 
		     already been created. 

		answer:  JMenuBar menuBar = new JMenuBar();
		             JMenu fileMenu = new JMenu("File");
		             fileMenu.setMnemonic(KeyEvent.VK_F);

		             JMenuItem openItem = new JMenuItem("Open");
		             openItem.setMnemonic(KeyEvent.VK_O);
		             openItem.setActionListener(new OpenListener());

		            JMenuItem printItem = new JMenuItem("Print");
		            printItem.setMnemonic(KeyEvent.VK_P);
		            printItem.setActionListener(new PrintListener());
		 
		            JMenuItem exitItem = new JMenuItem("Exit");
		            exitItem.setMnemonic(KeyEvent.VK_E);
		            exitItem.setActionListener(new ExitListener());


		12. Write code that creates a JSlider component. The component should be
		      horizontally oriented and its range should be 0 - 1000. Labels and tick
		      marks should be displayed. Major tick marks should be displayed every
		      100th number, and minor tick marks should be displayed every 25th
		      number. The initial value of the slider should be set at 500. 
		
		answer: JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 500);
		             slider.setMajorTickSpacing(100);
		             slider.setMinorTickSpacing(25);
	                             slider.setPaintTicks(true);
		             slider.setPaintLabels(true);
		*/


	}


	
}
