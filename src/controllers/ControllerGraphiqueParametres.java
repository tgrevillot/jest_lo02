package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import views.InterfaceGraphiqueNomJoueurs;

public class ControllerGraphiqueParametres {
	private int nbJoueurs;
	private int nbHumains;
	private int difficulte;
	private int regles;
	private int condition;
	private JCheckBox check3;
	private JCheckBox check4;
	private JCheckBox checkH1;
	private JCheckBox checkH2;
	private JCheckBox checkH3;
	private JCheckBox checkH4;
	private JCheckBox checkNuli;
	private JCheckBox checkCoeur;
	private JButton term;
	
	
	
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
