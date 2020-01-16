package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class InterfaceGraphiqueTour3 {

	public JFrame frame;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public InterfaceGraphiqueTour3(String[] s) {
		initialize(s);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[] s) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new java.awt.Color(0, 100, 0));
		frame.setBounds(100, 100, 633, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(12, 117, 75, 109);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("");
		button.setEnabled(false);
		button.setBounds(92, 117, 75, 109);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("");
		button_1.setEnabled(false);
		button_1.setBounds(218, 366, 75, 109);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setEnabled(false);
		button_2.setBounds(300, 366, 75, 109);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.setEnabled(false);
		button_3.setBounds(446, 117, 75, 109);
		frame.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.setEnabled(false);
		button_4.setBounds(527, 117, 75, 109);
		frame.getContentPane().add(button_4);
		
		JButton btnNewButton_1 = new JButton("Voir la Main");
		btnNewButton_1.setBounds(491, 423, 112, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Voir le Jest");
		btnNewButton_2.setBounds(491, 450, 112, 25);
		frame.getContentPane().add(btnNewButton_2);
		
		JTextArea txtrJoueur = new JTextArea();
		txtrJoueur.setEditable(false);
		txtrJoueur.setText("Joueur : "+s[0]);
		txtrJoueur.setBounds(12, 85, 155, 25);
		frame.getContentPane().add(txtrJoueur);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText("Joueur : "+s[1]);
		textArea.setBounds(218, 328, 155, 25);
		frame.getContentPane().add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setText("Joueur : "+s[2]);
		textArea_1.setBounds(446, 85, 155, 25);
		frame.getContentPane().add(textArea_1);
		
		JTextArea txtrAuTourDu = new JTextArea();
		txtrAuTourDu.setEditable(false);
		txtrAuTourDu.setText("Au tour du joueur :");
		txtrAuTourDu.setBounds(12, 370, 155, 67);
		frame.getContentPane().add(txtrAuTourDu);
		
		JButton button_5 = new JButton("");
		button_5.setEnabled(false);
		button_5.setBounds(300, 153, 75, 109);
		frame.getContentPane().add(button_5);
		
		JButton button_6 = new JButton("");
		button_6.setEnabled(false);
		button_6.setBounds(220, 153, 75, 109);
		frame.getContentPane().add(button_6);
	}

}
