package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import views.InterfaceGraphiqueNomJoueurs;

public class ControllerGraphiqueParametres {
	/**
	 * le nombre de joueurs dans la partie
	 */
	private int nbJoueurs;
	/**
	 * le nombre d'humains dans la partie
	 */
	private int nbHumains;
	/**
	 * le niveau de difficulté des IA
	 */
	private int difficulte;
	/**
	 * les regles additionnelles a utiliser
	 */
	private int regles;
	/**
	 * les conditions de victoires a utiliser
	 */
	private int condition;
	/**
	 * checkbox pour definir qu'il y aura 3 joueurs
	 */
	private JCheckBox check3;
	/**
	 * checkbox pour definir qu'il y aura 4 joueurs
	 */
	private JCheckBox check4;
	/**
	 * checkbox pour definir qu'il y aura 1 joueur humain
	 */
	private JCheckBox checkH1;
	/**
	 * checkbox pour definir qu'il y aura 2 joueur humain
	 */
	private JCheckBox checkH2;
	/**
	 * checkbox pour definir qu'il y aura 3 joueur humain
	 */
	private JCheckBox checkH3;
	/**
	 * checkbox pour definir qu'il y aura 4 joueur humain
	 */
	private JCheckBox checkH4;
	/**
	 * checkbox pour definir si on utilise le trophée bonus nulifieur
	 */
	private JCheckBox checkNuli;
	/**
	 * checkbox pour definir si on utilise la variante a coeur ouvert
	 */
	private JCheckBox checkCoeur;
	/**
	 * Bouton pour passer a la fenetre suivante
	 */
	private JButton term;
	
	
	/**
	 * constructeur, sert a designer toutes les actions a effectuer
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
	 */
	public ControllerGraphiqueParametres (JCheckBox check3,JCheckBox check4,JCheckBox checkH1,JCheckBox checkH2,
JCheckBox checkH3,JCheckBox checkH4,JCheckBox checkNuli,JCheckBox checkCoeur,JButton term, InterfaceGraphiqueNomJoueurs inter){

		// L'appuie sur le bouton
		check3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nbJoueurs=3;
				//décocher l'autre ! 
			}
		});
		check4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nbJoueurs=4;
				//décocher l'autre ! 
			}
		});
		checkH1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nbHumains=1;
				//décocher les autres ! 
			}
		});
		checkH2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nbHumains=2;
				//décocher les autres ! 
			}
		});
		checkH3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nbHumains=3;
				//décocher les autres ! 
			}
		});
		checkH4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nbHumains=4;
				//décocher les autres ! 
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
