package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
/**
 * Interface graphique qui recupere les entrees clavier des noms des joueurs humains presents 
 * @author moras
 *
 */
public class InterfaceGraphiqueNomJoueurs extends Observable {

	public  JFrame frame;
	/**
	 * Boite de texte du pseudo du joueur humain numero 1 
	 */
	private JTextField txt1;
	/**
	 * Boite de texte du pseudo du joueur humain numero 2
	 */
	private JTextField txt2;
	/**
	 * Boite de texte du pseudo du joueur humain numero 3
	 */
	private JTextField txt3;
	/**
	 * Boite de texte du pseudo du joueur humain numero 4 
	 */
	private JTextField txt4;

	/**
	 * Cree la fenetre
	 */
	public InterfaceGraphiqueNomJoueurs(int nbHumains,int nbJ) {
		initialize(nbHumains,nbJ);
		
	}

	/**
	 * Initialise le contenu de la fenetre
	 */
	private void initialize(int nbHumains, int nbJ) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txt1 = new JTextField();
		txt1.setBounds(100, 60, 116, 22);
		frame.getContentPane().add(txt1);
		txt1.setColumns(10);
		
		txt2 = new JTextField();
		txt2.setBounds(100, 100, 116, 22);
		frame.getContentPane().add(txt2);
		txt2.setColumns(10);
		
		txt3 = new JTextField();
		txt3.setBounds(100, 140, 116, 22);
		frame.getContentPane().add(txt3);
		txt3.setColumns(10);
		
		txt4 = new JTextField();
		txt4.setBounds(100, 180, 116, 22);
		frame.getContentPane().add(txt4);
		txt4.setColumns(10);
		
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
		
		JButton term = new JButton("Termin\u00E9 ");
		term.setBounds(327, 223, 97, 25);
		frame.getContentPane().add(term);
		frame.setVisible(false);
	
		if (nbHumains<=1) {
			txt2.setEnabled(false);
		}
		if (nbHumains<=2) {
			txt3.setEnabled(false);		
		}
		if (nbHumains<=3 || nbJ==3) {
			txt4.setEnabled(false);
		}
		term.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				if (nbJ==3) {
					String[] ls= {txt1.getText(),txt2.getText(),txt3.getText(),txt4.getText()};
					InterfaceGraphiqueTour3 window3 = new InterfaceGraphiqueTour3(ls);
					window3.frame.setVisible(true);
				}else {
					String[] ls= {txt1.getText(),txt2.getText(),txt3.getText(),txt4.getText()};
					InterfaceGraphiqueTour4 window3 = new InterfaceGraphiqueTour4(ls);
					window3.frame.setVisible(true);
				}
			}
		});
		
	}
}
