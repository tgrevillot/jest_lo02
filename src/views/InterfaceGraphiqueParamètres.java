package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class InterfaceGraphiqueParamètres {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceGraphiqueParamètres window = new InterfaceGraphiqueParamètres();
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
	public InterfaceGraphiqueParamètres() {
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
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("3");
		chckbxNewCheckBox.setBounds(179, 28, 35, 25);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("4");
		chckbxNewCheckBox_1.setBounds(218, 28, 40, 25);
		frame.getContentPane().add(chckbxNewCheckBox_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 4, 1));
		spinner.setBounds(184, 80, 30, 22);
		frame.getContentPane().add(spinner);
		
		JTextArea txtrNombreDeJoueurs = new JTextArea();
		txtrNombreDeJoueurs.setEditable(false);
		txtrNombreDeJoueurs.setText("Nombre de joueurs");
		txtrNombreDeJoueurs.setBounds(12, 29, 159, 22);
		frame.getContentPane().add(txtrNombreDeJoueurs);
		
		JTextArea txtrNombreDhumains = new JTextArea();
		txtrNombreDhumains.setEditable(false);
		txtrNombreDhumains.setText("Nombre d'humains");
		txtrNombreDhumains.setBounds(12, 80, 159, 19);
		frame.getContentPane().add(txtrNombreDhumains);
		
		JTextArea txtrRglesDuJeu = new JTextArea();
		txtrRglesDuJeu.setEditable(false);
		txtrRglesDuJeu.setText("R\u00E8gles bonus");
		txtrRglesDuJeu.setBounds(12, 190, 159, 25);
		frame.getContentPane().add(txtrRglesDuJeu);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Troph\u00E9e nullifieur");
		chckbxNewCheckBox_2.setBounds(179, 189, 136, 25);
		frame.getContentPane().add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxACoeurOuvert = new JCheckBox("A coeur ouvert");
		chckbxACoeurOuvert.setBounds(311, 189, 113, 25);
		frame.getContentPane().add(chckbxACoeurOuvert);
		
		JTextArea txtrNiveauDeLia = new JTextArea();
		txtrNiveauDeLia.setEditable(false);
		txtrNiveauDeLia.setText("Niveau de l'IA");
		txtrNiveauDeLia.setBounds(12, 134, 159, 25);
		frame.getContentPane().add(txtrNiveauDeLia);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(1, 1, 1, 1));
		spinner_1.setBounds(184, 134, 30, 22);
		frame.getContentPane().add(spinner_1);
		
		JButton button = new JButton("Termin\u00E9 ");
		button.setBounds(327, 223, 97, 25);
		frame.getContentPane().add(button);
	}
}
