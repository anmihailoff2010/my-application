import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SecretMessagesGUI extends JFrame {
	private JTextField textKey;
	private JTextArea textIn;
	private JTextArea textOut;
	private JSlider slider;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	public String encode(String message, int keyVal) {
		String output = "";
		char key = (char) keyVal;
		for (int x = 0; x < message.length(); x++) {
			char input = message.charAt(x);
			if (input >= 'A' && input <= 'Z')
			{
				input += key;
				if (input > 'Z')
				input -= 26;
				if (input < 'A')
				input += 26;
			}
			else if (input >= 'a' && input <= 'z')
			{
				input += key;
				if (input > 'z')
				input -= 26;
				if (input < 'a')
				input += 26;
			}
			else if (input >= '0' && input <= '9')
			{
				input += (keyVal % 10);
				if (input > '9')
				input -= 10;
				if (input < '0')
				input += 10;
			}
			output += input;
		}
		return output;
	}
	public SecretMessagesGUI() {
		getContentPane().setBackground(new Color(30, 144, 255));
		setTitle("Anton Secret Message App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 564, 140);
		getContentPane().add(scrollPane);
		
		textIn = new JTextArea();
		scrollPane.setViewportView(textIn);
		textIn.setWrapStyleWord(true);
		textIn.setLineWrap(true);
		textIn.setFont(new Font("Lucida Console", Font.PLAIN, 18));
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 210, 564, 140);
		getContentPane().add(scrollPane_1);
		
		textOut = new JTextArea();
		scrollPane_1.setViewportView(textOut);
		textOut.setWrapStyleWord(true);
		textOut.setLineWrap(true);
		textOut.setFont(new Font("Lucida Console", Font.PLAIN, 18));
		
		textKey = new JTextField();
		textKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int key = Integer.parseInt( textKey.getText() );
                slider.setValue(key);
			}
		});
		textKey.setHorizontalAlignment(SwingConstants.CENTER);
		textKey.setText("3");
		textKey.setBounds(268, 172, 47, 20);
		getContentPane().add(textKey);
		textKey.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Key:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(212, 171, 46, 20);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Encode/Decode");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton.setBackground(new Color(124, 252, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String message = textIn.getText();
					int key = Integer.parseInt(textKey.getText());
					String output = encode(message, key);
					textOut.setText(output);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
					"Please enter a whole number value for the encryption key.");
					textKey.requestFocus();
					textKey.selectAll();
				}
			}
		});
		btnNewButton.setBounds(325, 171, 138, 23);
		getContentPane().add(btnNewButton);
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textKey.setText("" + slider.getValue());
				String message = textIn.getText();
				int key = slider.getValue();
				String output = encode(message, key);
				textOut.setText(output);
			}
		});
		slider.setValue(3);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(13);
		slider.setMinorTickSpacing(1);
		slider.setMinimum(-26);
		slider.setMaximum(26);
		slider.setPaintLabels(true);
		slider.setBackground(new Color(255, 0, 255));
		slider.setBounds(21, 162, 200, 37);
		getContentPane().add(slider);
		
		JButton btnNewButton_1 = new JButton("Move Up ^");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = textIn.getText();
				textIn.setText(textOut.getText());
				slider.setValue(-slider.getValue());
			}
		});
		btnNewButton_1.setBackground(new Color(0, 255, 0));
		btnNewButton_1.setBounds(473, 171, 101, 23);
		getContentPane().add(btnNewButton_1);
	}

	public static void main(String[] args) {
		SecretMessagesGUI theApp = new SecretMessagesGUI();
		theApp.setSize(new java.awt.Dimension(600,400));
		theApp.setVisible(true);

	}
}
