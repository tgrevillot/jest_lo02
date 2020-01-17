package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import views.InterfaceGraphiqueNomJoueurs;

/**
 * La classe qui devrait servir de controlleur pour le moteur graphique
 * Statut : Pas terminee
 * @author moras
 *
 */
public class ControllerGraphiqueParametres {
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
	 * constructeur, sert a designer toutes les actions a effectuer
	 * Voir les attributs de la classe pour des details sur les parametres
	 * @param check3
	 * @param check4
	 * @param checkH1
	 * @param checkH2
	 * @param checkH3
	 * @param checkH4
	 * @param checkNuli
	 * @param checkCoeur
	 * @param term
	 * @param inter
	 * 		inter est l'interface graphique que l'on reliera avec ce controlleur 
	 */
	public ControllerGraphiqueParametres (JCheckBox check3,JCheckBox check4,JCheckBox checkH1,JCheckBox checkH2,
JCheckBox checkH3,JCheckBox checkH4,JCheckBox checkNuli,JCheckBox checkCoeur,JButton term, InterfaceGraphiqueNomJoueurs inter){

		// L'appuie sur le bouton
		check3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nbJoueurs=3;
				//decocher l'autre ! 
			}
		});
		check4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nbJoueurs=4;
				//decocher l'autre ! 
			}
		});
		checkH1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nbHumains=1;
				//decocher les autres ! 
			}
		});
		checkH2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nbHumains=2;
				//decocher les autres ! 
			}
		});
		checkH3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nbHumains=3;
				//decocher les autres ! 
			}
		});
		checkH4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nbHumains=4;
				//decocher les autres ! 
			}
		});
		checkNuli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (regles==1) {
					regles=0;
				} else {
					regles=1;
				}
			}
		});
		checkCoeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (condition==1) {
					condition=0;
				} else {
					condition=1;
				}
			}
		});
		term.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//APPELLER ICI LE CONSTRUCTEUR 
			}
		});
		
	}

}
