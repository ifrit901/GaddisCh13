package ch13java6thedition;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author craig
 */
public class Picture extends JFrame {

	
	public Picture() {

		setTitle("Phone Picture");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLocation(400, 250);
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		ImageIcon image = new ImageIcon("/home/craig/Pictures/phone.jpeg");
		JLabel label = new JLabel(image);
		//panel1.add(new ShadeDesigner());
		panel.add(label);
		//add(panel1, BorderLayout.CENTER);
		add(panel, BorderLayout.EAST);

		pack();
		setVisible(true);

	}
	
	public static void main(String[]args) {
		new Picture();
	}
}
