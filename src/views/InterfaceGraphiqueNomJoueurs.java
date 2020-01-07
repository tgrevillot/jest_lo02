package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import java.util.Observable;

import javax.swing.JButton;

public class InterfaceGraphiqueNomJoueurs extends Observable {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceGraphiqueNomJoueurs window = new InterfaceGraphiqueNomJoueurs();
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
	public InterfaceGraphiqueNomJoueurs() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(100, 60, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(100, 100, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(100, 140, 116, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(100, 180, 116, 22);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JTextArea txtrJoueur = new JTextArea();
		txtrJoueur.setEditable(false);
		txtrJoueur.setText("Joueur 1");
		txtrJoueur.setBounds(12, 60, 79, 22);
		frame.getContentPane().add(txtrJoueur);
		
		JTextArea txtrJoueur_1 = new JTextArea();
		txtrJoueur_1.setEditable(false);
		txtrJoueur_1.setText("Joueur 2");
		txtrJoueur_1.setBounds(12, 100, 79, 22);
		frame.getContentPane().add(txtrJoueur_1);
		
		JTextArea txtrJoueur_2 = new JTextArea();
		txtrJoueur_2.setEditable(false);
		txtrJoueur_2.setText("Joueur 3");
		txtrJoueur_2.setBounds(12, 140, 79, 22);
		frame.getContentPane().add(txtrJoueur_2);
		
		JTextArea txtrJoueur_3 = new JTextArea();
		txtrJoueur_3.setEditable(false);
		txtrJoueur_3.setText("Joueur 4");
		txtrJoueur_3.setBounds(12, 180, 79, 22);
		frame.getContentPane().add(txtrJoueur_3);
		
		JTextArea txtrEntrerLesPseudos = new JTextArea();
		txtrEntrerLesPseudos.setEditable(false);
		txtrEntrerLesPseudos.setFont(new Font("Monospaced", Font.PLAIN, 20));
		txtrEntrerLesPseudos.setText("Entrer les pseudos des joueurs ");
		txtrEntrerLesPseudos.setBounds(28, 13, 370, 34);
		frame.getContentPane().add(txtrEntrerLesPseudos);
		
		JButton btnNewButton = new JButton("Termin\u00E9 ");
		btnNewButton.setBounds(327, 223, 97, 25);
		frame.getContentPane().add(btnNewButton);
	}
}
