package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class InterfaceGraphiqueNullifieur {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceGraphiqueNullifieur window = new InterfaceGraphiqueNullifieur();
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
	public InterfaceGraphiqueNullifieur() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
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
	}
}
