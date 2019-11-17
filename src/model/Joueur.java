package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

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
		this.main = new ArrayList<Carte>();
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
	
	public void prendreOffre(Joueur j) {
		throw new Error("A COMPLETER");
	}
	
	public void montrer2emeCarte() {
		throw new Error("A COMPLETER");
	}
	/**
	 * Cette fonction est le choix de l'offre par le joueur 
	 * S'il est reel : un choix entre la carte 1ere et la 2eme carte 
	 * S'il est une IA : une d�cision random
	 */
	public void choisirFaceCachee() {
		throw new Error("A COMPLETER");
		/*
		Scanner scan = new Scanner(System.in);
		ArrayList<Carte> main = this.main;
		
		
		//TODO FAIRE LE SWITCH CASE AVEC LE COMPORTEMENT()
		
		
		//_____CAS du joueur reel
		System.out.println("Voici votre main : ");
		System.out.println((main.get(0)).afficher() + " ET " + (main.get(1)).afficher()  );
		System.out.println("Quelle carte voulez vous cacher dans votre offre ? (1 ou 2)");
		int carteARetourner= Integer.parseInt(scan.next());
		while (carteARetourner != 1 && carteARetourner != 2) {
			System.out.println("Vous devez choisir 1 ou 2 :");
			carteARetourner= Integer.parseInt(scan.next());
		}
		switch (carteARetourner) {
		case 1 : 
			this.main.get(0).cacherCarte();
			System.out.println("Vous avez choisi de cacher la carte " + (this.main.get(0)).afficher());
			break;
		case 2 :
			this.main.get(1).cacherCarte();
			System.out.println("Vous avez choisi de cacher la carte " + (this.main.get(1)).afficher());
			break;
		}
		
		//_____CAS du joueur IA
		//on utilise une d�cision random pour ce choix 
		if (Math.random()<0.5) {
			this.main.get(0).cacherCarte();
		} else {
			this.main.get(1).cacherCarte();
		}
		*/
	}
	
	public HashSet<Carte> consulterJest() {
		throw new Error("A COMPLETER");
	}
	
	public void ajouterCartesRestantesJest() {
		throw new Error("A COMPLETER");
	}
	
	public int compterScore() {
		throw new Error("A COMPLETER");
	}
}
