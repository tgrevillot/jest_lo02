package controllers;

import java.util.Scanner;

/**
 * Classe du controleur de la vue textuelle et du mode de jeu en ligne de commandes
 * @author moras
 *
 */
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
	 * Booleen indiquant si on attend une entree utilisateur. 
	 * S'il est vrai, alors on enregistre la valeur utilisateur, sinon rien ne se passe.
	 */
	private boolean waitEntry;
	
	/**
	 * Indique aux classe si une nouvelle valeur est disponible
	 */
	private boolean valueDispo;
	
	/**
	 * Chaine lue au clavier. Elle sera recuperee par le controllerText 
	 * pour les prises de décisions utilisateur.
	 */
	private String valeurLue;
	
	/**
	 * Singleton de notre controller
	 */
	private static ControllerText controller;
	
	/**
	 * Constructeur instanciant tous les attributs et lancant le Thread de ce controller
	 * @param model
	 * 		La partie de Jest en cours, si le parametre est null on renvoie une erreur
	 */
	private ControllerText() {
		this.sc = new Scanner(System.in);
		this.waitEntry = false;
		this.valueDispo = false;
		this.valeurLue = "";
		new Thread(this).start();
	}
	
	public static synchronized ControllerText getControllerText() {
		if(controller == null)
			controller = new ControllerText();
		return controller;
	}
	
	/**
	 * Permet a une autre entite (par exemple le modele) d'indiquer que la prochaine entree clavier
	 * est utile au bon deroulement du jeu.
	 * Plus techniquement, il met l'attribut modifModele a vrai.
	 */
	public synchronized void enableModifModele() {
		this.waitEntry = true;
	}
	
	/**
	 * Indique si l'utilisateur a terminé sa saisie
	 * @return valueDispo
	 * 		Vrai si une valeur a ete saisie par l'utilisateur
	 */
	public synchronized boolean isValueDispo() {
		return this.valueDispo;
	}
	
	public String getEntree() {
		this.waitEntry = false;
		this.valueDispo = false;
		return this.valeurLue;
	}
	
	/**
	 * Methode principale du Thread, elle boucle jusqu'a ce que l'utilisateur arrete le programme
	 * ou que le jeu se termine normalement. En fonction de modifModele, des actions sont effectues sur le 
	 * modele.
	 */
	@Override
	public void run() {
		//On récupère l'entrée utilisateur
		String line = sc.nextLine();
		//S'il souhaite arrêter la partie on arrête tout
		if(line.equals(ARRET))
			System.exit(0);
		//Si on attend une valeur on la traite sinon on ne fait rien
		else if(this.waitEntry) {
			this.valeurLue = line;
			this.valueDispo = true;
		}
		//On relance le Thread à chaque fois que la lecture se fasse en continue
		new Thread(this).start();
	}
}
