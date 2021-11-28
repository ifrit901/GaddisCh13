package ch13java6thedition;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author craig
 */
public class SetText extends JFrame {

	private double number;
	private JTextField textField;
	private JButton button;
	private JPanel panel;

	public SetText() {
		setTitle("Set Text");
		setSize(200,100);
		setLocation(500,250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		textField = new JTextField(10);
		textField.setEditable(false);
		button = new JButton("Put number in box");
		button.addActionListener(new ButtonListener());
		panel.add(textField);
		panel.add(button);

		add(panel);

		setVisible(true);
	}
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			number = 100;
			String num = String.valueOf(number);
			textField.setText(num);
		}
	}
	public static void main(String[]args) {
		new SetText();
	}
}
