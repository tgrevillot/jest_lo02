package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
/**
 * interface graphique pour l'implementation du nullifieur 
 * (pas encore implementee
 * @author moras
 *
 */
public class InterfaceGraphiqueNullifieur {

	private JFrame frame;

	/**
	 * Cree la fenetre
	 */
	public InterfaceGraphiqueNullifieur() {
		initialize();
	}

	/**
	 * Initialise le contenu de la fenetre
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 350, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea txtrVeuillezChoisirUne = new JTextArea();
		txtrVeuillezChoisirUne.setText("Veuillez choisir une carte a nullifier \r\nparmis les propositions suivantes :");
		txtrVeuillezChoisirUne.setBounds(12, 13, 316, 271);
		frame.getContentPane().add(txtrVeuillezChoisirUne);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner.setBounds(66, 307, 47, 48);
		frame.getContentPane().add(spinner);
		
		JButton button = new JButton("Termin\u00E9 ");
		button.setBounds(223, 414, 97, 25);
		frame.getContentPane().add(button);
	}
}
