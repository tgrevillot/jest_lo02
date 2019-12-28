package controllers;

import java.util.Scanner;

import model.PartieJest;

public class ControllerText implements Runnable {
	/**
	 * Attribut nous servant a recuperer les entrees clavier utilisateur.
	 */
	private Scanner sc;
	/**
	 * Permet d'identifier la situation ou l'utilisateur souhaite arreter de jouer.
	 */
	private final static String ARRET = "QUIT";
	/**
	 * Booleen indiquant si le modele attend une modification. 
	 * S'il est vrai, alors on accepte la modification, sinon rien ne se passe.
	 */
	private boolean modifModel;
	/**
	 * Partie de Jest en cours nous servant de modele dans le patron MVC
	 */
	private PartieJest model;
	
	/**
	 * Constructeur instanciant tous les attributs et lancant le Thread de ce controller
	 * @param model
	 * 		La partie de Jest en cours, si le parametre est null on renvoie une erreur
	 */
	public ControllerText(PartieJest model) {
		this.sc = new Scanner(System.in);
		this.modifModel = false;
		if(model == null)
			throw new Error("Le modele fournie est NULL (ControlleurTexte)");
		this.model = model;
		new Thread(this).start();
	}
	
	/**
	 * Permet a une autre entite (par exemple le modele) d'indiquer que la prochaine entree clavier
	 * est utile au bon deroulement du jeu.
	 * Plus techniquement, il met l'attribut modifModele a vrai.
	 */
	public synchronized void enableModifModele() {
		this.modifModel = true;
	}
	
	/**
	 * Methode principale du Thread, elle boucle jusqu'a ce que l'utilisateur arrete le programme
	 * ou que le jeu se termine normalement. En fonction de modifModele, des actions sont effectues sur le 
	 * modele.
	 */
	@Override
	public void run() {
		String line;
		//Pour chaque ligne lue au clavier, on vérifie si l'utilisateur souhaite quitter le programme
		while((line = sc.nextLine()).equals(ARRET))
			//Si ce n'est pas le cas on vérifie qu'une modification du modèle est attendue
			if(this.modifModel) {
				System.out.println(line);
				//TODO : FAIRE LES MODIFS SUR LE MODELE
				this.model.notifier();
				this.modifModel = false;
			}
		System.exit(0);
	}
}
