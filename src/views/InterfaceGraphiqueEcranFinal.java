package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Canvas;
import javax.swing.JButton;

public class InterfaceGraphiqueEcranFinal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceGraphiqueEcranFinal window = new InterfaceGraphiqueEcranFinal();
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
	public InterfaceGraphiqueEcranFinal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 696, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(12, 13, 563, 249);
		frame.getContentPane().add(textArea);
		
		JTextArea txtrFlicitationJoueur = new JTextArea();
		txtrFlicitationJoueur.setEditable(false);
		txtrFlicitationJoueur.setText("F\u00E9licitation joueur  : ");
		txtrFlicitationJoueur.setBounds(157, 329, 362, 30);
		frame.getContentPane().add(txtrFlicitationJoueur);
		
		JButton button = new JButton("");
		button.setEnabled(false);
		button.setBounds(220, 372, 217, 67);
		frame.getContentPane().add(button);
	}
}
