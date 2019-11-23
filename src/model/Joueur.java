package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Joueur implements Compteur {
	/**
	 * Indique si le joueur a déja joué pendant ce tour
	 * par défaut a false 
	 */
	private boolean aJoue;
	
	/**
	 * La liste des cartes présentes dans la main du joueur pendant un tour précis
	 * Par défaut vide. Contient 0, 1 ou 2 cartes selon le moment.
	 */
	private ArrayList<Carte> main;
	
	/**
	 * Correspond au jest du joueur, un ensemble de cartes qui lui sont propre
	 * Selon les règles officielles, il peut contenir entre 0 et 7 cartes selon le type et l'avancée de la partie.
	 */
	private HashSet<Carte> jest;
	
	/**
	 * 
	 */
	private IAStrategie strat;
	
	
	/** 
	 * Chaîne de caractère représentant le pseudonyme du joueur affiché en jeu.
	 * Les Ia seront nommées de la sorte : strategie_id (où l'id est unique de 1 jusqu'à 3)
	 */
	private String nom;
	
	public Joueur(String pseudo, int iaType) {
		this.aJoue=false;
		this.main = new ArrayList<Carte>(2);
		this.jest = new HashSet<Carte>();
		
		//on associe le type de comportement selon l'entier iaType
		// De même on associe pseudo aux reel et strategie_pseudo (ou pseudo est un chiffre) aux IA 
		switch (iaType) {
			case 0 :
				this.nom = pseudo;
				this.strat= new Reel();
				break;
			case 1 :
				this.nom = "Random_"+pseudo;
				this.strat= new Random();
				break;
			case 2 :
				this.nom = "Basique_"+pseudo;
				this.strat= new Basique();
				break;
			default :
				this.nom = pseudo;
				this.strat= new Reel();
				break;
		}
		
	}
	
	@Override
	public void compterPointsCarte() {
		throw new Error("A COMPLETER");
	}
	
	public void prendreOffre() {
		//J'ai changer la signature de la méthode pour pouvoir l'utiliser dans Jeu.
		//L'idée ici c'est d'utiliser le patron stratégie pour savoir à quelle joueur on va prendre l'offre
		//et quelle carte va être choisie. Hésites pas à changer les méthodes d'IAStratégie si besoin.
		throw new Error("A COMPLETER");
	}
	
	public void montrer2emeCarte() {
		throw new Error("A COMPLETER");
	}
	/**
	 * Cette fonction est le choix de l'offre par le joueur 
	 * S'il est reel : un choix entre la carte 1ere et la 2eme carte 
	 * S'il est une IA : une décision random
	 */
	public void choisirFaceCachee() {
		this.strat.offre(this.main);
	}
	
	public HashSet<Carte> consulterJest() {
		throw new Error("A COMPLETER");
	}
	
	public void ajouterCartesRestantesJest() {
		throw new Error("A COMPLETER");
	}
	
	public void accepterCarte(Carte c) {
		if(c != null)
			this.main.add(c);
	}
	
	public int compterScore() {
		throw new Error("A COMPLETER");
	}
	
	public Carte getCarteRestante() {
		if(this.main.size() == 1)
			return this.main.get(0);
		return null;
	}

	public Carte getVisibleCard() {
		for(Carte c : this.main)
			if(!c.isCacher())
				return c;
		
		return null;
	}
	
	public boolean aJoue() {
		return aJoue;
	}

	public void vientDeJouer() {
		this.aJoue = true;
	}
	
	public void peutJouer() {
		this.aJoue = false;
	}

}
