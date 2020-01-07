package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;

import javafx.scene.paint.Color;

import javax.swing.ImageIcon;

public class InterfaceGraphiqueTour4 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceGraphiqueTour4 window = new InterfaceGraphiqueTour4();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfaceGraphiqueTour4() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new java.awt.Color(0, 100, 0));
		frame.setBounds(100, 100, 735, 539);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton button = new JButton("");
		button.setEnabled(false);
		button.setBounds(29, 202, 75, 109);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("");
		button_1.setEnabled(false);
		button_1.setBounds(109, 202, 75, 109);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(null);
		button_2.setEnabled(false);
		button_2.setBounds(248, 370, 75, 109);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.setEnabled(false);
		button_3.setBounds(330, 370, 75, 109);
		frame.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.setEnabled(false);
		button_4.setBounds(477, 202, 75, 109);
		frame.getContentPane().add(button_4);
		
		JButton button_5 = new JButton("");
		button_5.setEnabled(false);
		button_5.setBounds(558, 202, 75, 109);
		frame.getContentPane().add(button_5);
		
		JButton button_6 = new JButton("Voir la Main");
		button_6.setBounds(521, 427, 112, 25);
		frame.getContentPane().add(button_6);
		
		JButton button_7 = new JButton("Voir le Jest");
		button_7.setBounds(521, 454, 112, 25);
		frame.getContentPane().add(button_7);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText("Joueur : ");
		textArea.setBounds(29, 170, 155, 25);
		frame.getContentPane().add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setText("Joueur : ");
		textArea_1.setBounds(248, 332, 155, 25);
		frame.getContentPane().add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setEditable(false);
		textArea_2.setText("Joueur : ");
		textArea_2.setBounds(477, 170, 155, 25);
		frame.getContentPane().add(textArea_2);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setEditable(false);
		textArea_3.setText("Au tour du joueur :");
		textArea_3.setBounds(42, 374, 155, 67);
		frame.getContentPane().add(textArea_3);
		
		JButton button_8 = new JButton("");
		button_8.setEnabled(false);
		button_8.setBounds(330, 32, 75, 109);
		frame.getContentPane().add(button_8);
		
		JButton button_9 = new JButton("");
		button_9.setEnabled(false);
		button_9.setBounds(249, 32, 75, 109);
		frame.getContentPane().add(button_9);
		
		JTextArea textArea_4 = new JTextArea();
		textArea_4.setEditable(false);
		textArea_4.setText("Joueur : ");
		textArea_4.setBounds(249, 0, 155, 25);
		frame.getContentPane().add(textArea_4);
		
		JButton button_10 = new JButton("");
		button_10.setEnabled(false);
		button_10.setBounds(287, 187, 75, 109);
		frame.getContentPane().add(button_10);
	}
}
