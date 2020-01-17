package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import controllers.ControllerGraphiqueParametres;
import model.CompteurClassique;
import model.PartieJest;
import model.RepartiteurTropheeClassique;

import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class InterfaceGraphiqueParametres {
	/**
	 * le nombre de joueurs dans la partie
	 * attendu entre 3 et 4
	 */
	private int nbJoueurs;
	/**
	 * le nombre d'humains dans la partie
	 * attendu entre 1 et nbJoueurs
	 */
	private int nbHumains;
	/**
	 * le niveau de difficulte des IA
	 *  attendu entre 1 et 2 
	 */
	private int difficulte;
	/**
	 * les regles additionnelles a utiliser
	 * attendu entre 0 et 1 
	 */
	private int regles;
	/**
	 * les conditions de victoires a utiliser
	 */
	private int condition;
	
	public JFrame frame;
	
	/**
	 * checkbox pour definir qu'il y aura 3 joueurs
	 * etat initial : non coche 
	 * si coche alors check4 ne doit pas l'etre 
	 */
	private JCheckBox check3;
	/**
	 * checkbox pour definir qu'il y aura 4 joueurs
	 * etat initial : non coche 
	 * si coche alors check3 ne doit pas l'etre 
	 */
	private JCheckBox check4;
	/**
	 * checkbox pour definir qu'il y aura 1 joueur humain
	 * etat initial : non coche 
	 * si coche alors les autres checkH ne doivent pas l'etre 
	 */
	private JCheckBox checkH1;
	/**
	 * checkbox pour definir qu'il y aura 2 joueur humain
	 * etat initial : non coche 
	 * si coche alors les autres checkH ne doivent pas l'etre 
	 */
	private JCheckBox checkH2;
	/**
	 * checkbox pour definir qu'il y aura 3 joueur humain
	 * etat initial : non coche 
	 * si coche alors les autres checkH ne doivent pas l'etre 
	 */
	private JCheckBox checkH3;
	/**
	 * checkbox pour definir qu'il y aura 4 joueur humain
	 * etat initial : non coche 
	 * si coche alors les autres checkH ne doivent pas l'etre 
	 */
	private JCheckBox checkH4;
	/**
	 * checkbox pour definir si on utilise le trophee bonus nulifieur
	 * etat initial : non coche 
	 */
	private JCheckBox checkNuli;
	/**
	 * checkbox pour definir si on utilise la variante a coeur ouvert
	 * etat initial : non coche 
	 */
	private JCheckBox checkCoeur;
	/**
	 * Bouton pour passer a la fenetre suivante
	 */
	private JButton term;


	/**
	 * Cree la fenetre
	 */
	public InterfaceGraphiqueParametres() {
		initialize();
		
	}

	/**
	 * Initialise le contenu de la fenetre
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		String url = "././ressourcesImages/carte2Carreau.png";
		ImageIcon icone = new ImageIcon(url);
		JLabel label = new JLabel(icone,JLabel.CENTER);
		frame.getContentPane().add(label);
		frame.getContentPane().setLayout(null);
		
		check3 = new JCheckBox("3");
		check3.setBounds(179, 28, 35, 25);
		frame.getContentPane().add(check3);
		
		
		check4 = new JCheckBox("4");
		check4.setBounds(218, 28, 40, 25);
		frame.getContentPane().add(check4);
		
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
		
		checkNuli = new JCheckBox("Troph\u00E9e nullifieur");
		checkNuli.setBounds(179, 189, 136, 25);
		frame.getContentPane().add(checkNuli);
		
		checkCoeur = new JCheckBox("A coeur ouvert");
		checkCoeur.setBounds(311, 189, 113, 25);
		frame.getContentPane().add(checkCoeur);
		
		JTextArea txtrNiveauDeLia = new JTextArea();
		txtrNiveauDeLia.setEditable(false);
		txtrNiveauDeLia.setText("Niveau de l'IA");
		txtrNiveauDeLia.setBounds(12, 134, 159, 25);
		frame.getContentPane().add(txtrNiveauDeLia);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(1, 1, 1, 1));
		spinner_1.setBounds(184, 134, 30, 22);
		frame.getContentPane().add(spinner_1);
		
		term = new JButton("Termin\u00E9 ");
		term.setBounds(327, 223, 97, 25);
		frame.getContentPane().add(term);
		
		checkH1 = new JCheckBox("1");
		checkH1.setBounds(179, 79, 35, 25);
		frame.getContentPane().add(checkH1);
		
		checkH2 = new JCheckBox("2");
		checkH2.setBounds(218, 79, 35, 25);
		frame.getContentPane().add(checkH2);
		
		checkH3 = new JCheckBox("3");
		checkH3.setBounds(257, 79, 35, 25);
		frame.getContentPane().add(checkH3);
		
		checkH4 = new JCheckBox("4");
		checkH4.setBounds(296, 79, 35, 25);
		frame.getContentPane().add(checkH4);
		
		// L'appuie sur le bouton
		check3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nbJoueurs=3;
				check3.setSelected(true);
				check4.setSelected(false); 
			}
		});
		check4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nbJoueurs=4;
				check3.setSelected(false);
				check4.setSelected(true);
			}
		});
		checkH1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nbHumains=1;
				checkH1.setSelected(true);
				checkH2.setSelected(false);
				checkH3.setSelected(false);
				checkH4.setSelected(false);
			}
		});
		checkH2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nbHumains=2;
				checkH1.setSelected(false);
				checkH2.setSelected(true);
				checkH3.setSelected(false);
				checkH4.setSelected(false);
			}
		});
		checkH3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nbHumains=3;
				checkH1.setSelected(false);
				checkH2.setSelected(false);
				checkH3.setSelected(true);
				checkH4.setSelected(false); 
			}
		});
		checkH4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nbHumains=4;
				checkH1.setSelected(false);
				checkH2.setSelected(false);
				checkH3.setSelected(false);
				checkH4.setSelected(true); 
			}
		});
		checkNuli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (regles==1) {
					checkNuli.setSelected(false);
					regles=0;
				} else {
					checkNuli.setSelected(true);
					regles=1;
				}
			}
		});
		checkCoeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (condition==1) {
					checkCoeur.setSelected(false);
					condition=0;
				} else {
					checkCoeur.setSelected(true);
					condition=1;
				}
			}
		});
		term.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (nbJoueurs>=nbHumains) {
					frame.setVisible(false);
					InterfaceGraphiqueNomJoueurs window2 = new InterfaceGraphiqueNomJoueurs(nbHumains,nbJoueurs);
					window2.frame.setVisible(true);
				}
				
			}
		});
	}
	

}
