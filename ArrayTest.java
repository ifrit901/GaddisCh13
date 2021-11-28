package ch13java6thedition;

/**
 *
 * @author craig
 */
import javax.swing.*;
import java.awt.*;
public class ArrayTest extends JFrame {

	
	public ArrayTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setLocation(400, 250);
		JLabel label1 = new JLabel(), label2 = new JLabel();
		JLabel [] labelArray = {label1, label2};
		label1.setText("something");
		label2.setText("something else");
		System.out.println(labelArray[0].getText());
		labelArray[0].setText("change");
		add(label1);
		add(label2);
		pack();
		setVisible(true);

	}

	public static void main(String[]args) {
		new ArrayTest();
	}
	
}
