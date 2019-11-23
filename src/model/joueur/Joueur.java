package model.joueur;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import model.cards.Carte;

public class Joueur implements Compteur {
	/**
	 * Indique si le joueur a d�ja jou� pendant ce tour
	 * par d�faut a false 
	 */
	private boolean aJoue;
	
	/**
	 * La liste des cartes pr�sentes dans la main du joueur pendant un tour pr�cis
	 * Par d�faut vide. Contient 0, 1 ou 2 cartes selon le moment.
	 */
	private ArrayList<Carte> main;
	
	/**
	 * Correspond au jest du joueur, un ensemble de cartes qui lui sont propre
	 * Selon les r�gles officielles, il peut contenir entre 0 et 7 cartes selon le type et l'avanc�e de la partie.
	 */
	private HashSet<Carte> jest;
	
	/**
	 * 
	 */
	private IAStrategie strat;
	
	
	/** 
	 * Cha�ne de caract�re repr�sentant le pseudonyme du joueur affich� en jeu.
	 * Les Ia seront nomm�es de la sorte : strategie_id (o� l'id est unique de 1 jusqu'� 3)
	 */
	private String nom;
	
	public Joueur(String pseudo, int iaType) {
		this.aJoue=false;
		this.main = new ArrayList<Carte>(2);
		this.jest = new HashSet<Carte>();
		
		//on associe le type de comportement selon l'entier iaType
		// De m�me on associe pseudo aux reel et strategie_pseudo (ou pseudo est un chiffre) aux IA 
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
	/**
	 * Cette fonction d�finie le choix de l'offre pour chaque joueur 
	 * S'il est reel : un choix entre les cartes des autres joueurs (qui n'ont pas d�ja donn� une offre)
	 * S'il est une IA : une d�cision random parmis ces m�mes personnes
	 * @param lJoueurs
	 * lJoueurs est la liste des joueurs qui peuvent donner une carte (i.e qui en ont encore 2)
	 */
	public Joueur prendreOffre(ArrayList<Joueur> lJoueurs) {
		return this.strat.choisir(lJoueurs, this);
	}
	
	/**
	 * Cette fonction est le choix de l'offre par le joueur 
	 * S'il est reel : un choix entre la carte 1ere et la 2eme carte 
	 * S'il est une IA : une d�cision random
	 */
	public void choisirFaceCachee() {
		this.strat.offrir(this.main, this.nom);
	}
	
	
	public void montrer2emeCarte() {
		throw new Error("A COMPLETER");
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
	
	public int tailleMain() {
		return this.main.size();
	}
	
	
	public boolean aJoue() {
		return aJoue;
	}
	
	public String getNom() {
		return this.nom;
	}

	public void vientDeJouer() {
		this.aJoue = true;
	}
	
	public void peutJouer() {
		this.aJoue = false;
	}
	
	/**
	 * Cette methode renvois la carte (cach�e ou non) apr�s l'avoir enlev� 
	 * @param cache
	 * signifie que l'on veut la carte cach�e ou visible 
	 * @return 
	 * renvois la carte que l'on a enlev� de la main
	 */
	public Carte prendreCarte(boolean cache) {
		
		for (Carte c : this.main) {
			if (c.isCacher()==cache) {
				this.main.remove(c);
				return c;
			}
		}
		
		throw new Error("Pas de carte correspondant au crit�re :" + cache + "dans " + this.main);
	}
	
	/**
	 * Cette fonction ajoute la crte en parametre dans le jest
	 * @param carte
	 */
	public void ajouterDansJest(Carte carte) {
		this.jest.add(carte);
	}
}
