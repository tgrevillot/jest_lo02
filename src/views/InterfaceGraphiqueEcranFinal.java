package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Canvas;
import javax.swing.JButton;
/**
 * interface graphique pour l'affichage de l'attribution des trophees et des resultats de la partie 
 * @author moras
 *
 */
public class InterfaceGraphiqueEcranFinal {

	private JFrame frame;


	/**
	 * Cree la fenetre
	 */
	public InterfaceGraphiqueEcranFinal() {
		initialize();
	}

	/**
	 * Initialise le contenu de la fenetre
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
