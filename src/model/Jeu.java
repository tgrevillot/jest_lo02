package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Jeu {
	
	/** 
	 * Le deck est l'ensemble des cartes jouables du jeu. Il comporte 17 cartes différentes au départ. 
	 * Il contient un ensemble vide, partiel ou total des cartes disponibles sans redondance.
	 */
	private LinkedList<Carte> deck;
	
	/** 
	 * joueurs est la liste des joueurs présents dans la partie.
	 * Cette liste contient 3 ou 4 Joueurs 
	 */
	private ArrayList<Joueur> joueurs;
	
	/**
	 * Trophee est le tableau de trophee du jeu 
	 * Ce tableau contient 1 [resp. 2] trophee pour 4 [resp. 3] joueurs
	 */
	private Trophee[] trophees;
	
	
	public Jeu(int nbJoueurs,int nbHumains, int difficulte, String[] tabPseudos) {
		initialiser();		
		ajouterJoueurs(nbHumains, nbJoueurs, tabPseudos, difficulte);
		creerTrophees();
	}
	
	private void initialiser() {
		remplirPaquet();
	}
	
	/**
	 * Cree les cartes et remplis le paquet avec
	 */
	private void remplirPaquet() {
		//on créé et on remplit le deck de cartes
		this.deck = new LinkedList<Carte>();
				
		this.deck.add(new Joker());
		for (int i = 1; i<5; i++) {
			this.deck.add(new Carreau(i));
			this.deck.add(new Coeur(i));
			this.deck.add(new Pique(i));
			this.deck.add(new Trefle(i));
		}
		//on mélange le deck
		Collections.shuffle(this.deck);			
	}
	
	/**
	 * Cree les joueurs en fonction des parametres d'entrees
	 * @param nbHumains
	 * 		Nombre de joueurs reels allant s'affronter
	 * @param nbJoueurs
	 * 		Nombre de joueurs total, bots compris
	 * @param tabPseudos
	 * 		Tableaux contenant l'ensemble des pseudos
	 * @param difficulte
	 * 		Franchement je sais pas pourquoi t'as mis une difficulte
	 */
	private void ajouterJoueurs(int nbHumains, int nbJoueurs, String[] tabPseudos, int difficulte) {
		//On initialise maintenant les joueurs 
				ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
				//on ajoute nbJoueurs joueurs
				// on ajoute d'abbord nbHumains Humains
				for (int j=0 ; j<nbHumains ; j++) {
					joueurs.add(new Joueur(tabPseudos[j],0));
				}
				//les joueurs restants sont des IA
				for (int k=0 ; k<(nbJoueurs-nbHumains) ; k++) {
					joueurs.add(new Joueur(""+k,difficulte));
				}
				this.joueurs= joueurs;
				//on choisi le(s) trophé(s)
	}
	
	/**
	 * Cree le trophee a utiliser durant la partie
	 */
	private void creerTrophees() {
		/*
		Carte carteTrophee1 =  deck.pop();

		// TODO Faire en sorte que cela créé le bon trophé : trophee2
		
		// s'il y a 3 joueurs on rajoute un deuxièeme trophée
		if (nbJoueurs==3) {
			Carte carteTrophee2 =  deck.pop();
			// TODO Faire en sorte que cela créé le bon trophé : trophee2
			Trophee[] trophees = {trophee1,trophee2};
			this.trophees = trophees;
		}else {
			Trophee[] trophees = {trophee1};
			this.trophees= trophees;
		}
		*/
	}
	
	private void attribuerTrophee(Trophee t) {
		throw new Error("A COMPLETER");
	}
	
	private int compterPoints() {
		throw new Error("A COMPLETER");
	}
	
	private void determinerTour(Joueur j) {
		throw new Error("A COMPLETER");
	}
	
	private Joueur determinerPlusGrand() {
		throw new Error("A COMPLETER");
	}

	private Joueur determinerGagnant() {
		throw new Error("A COMPLETER");
	}
	
	public void faireUnTour(LinkedList<Carte> cartesRestantes) {
		throw new Error("A COMPLETER");
	}
	
	public LinkedList<Carte> recupererCartesRestantes() {
		throw new Error("A COMPLETER");
	}
	
	public static void main(String[] args) {
		//On Lance la partie 
		Scanner scan = new Scanner(System.in);
		String lineSeparator = System.getProperty("line.separator");
		

		System.out.println("Bienvenue dans le Jest !");
		System.out.println("Suivez les instructions suivantes pour configurer la partie et pouvoir jouer" + lineSeparator);
		
		//choix du nombre de joueurs	
			System.out.println("Veuillez entrer le nombre de joueurs (3 ou 4) : ");
			int nbJoueurs = scan.nextInt();
			//on vérifie que le nombre entré est bien 3 ou 4 
			while ( (nbJoueurs != 4) && (nbJoueurs != 3)  ){
				System.out.println("Entrée incorrecte, vous devez choisir 3 ou 4 : ");
				nbJoueurs = scan.nextInt();
			}
			System.out.println("Vous avez choisi "+nbJoueurs+" joueurs " + lineSeparator);
		
		//choix du nombre de joueur humains
			System.out.println("Veuillez entrer le nombre de joueurs humains (entre 1 et "+nbJoueurs+" inclus) : ");
			int nbHumains = scan.nextInt();
			//on vérifie que le nombre entré est bien compatible 
			while ( (nbHumains < 1) || (nbHumains > nbJoueurs)  ){
				System.out.println("Entrée incorrecte, vous devez choisir entre 1 et "+nbJoueurs+" inclus : ");
				nbHumains = scan.nextInt();
			}
			System.out.println("Il y aura donc "+nbHumains+" joueurs humains " + lineSeparator);
		
		int difficulte = 0;
		//Choix de la difficulté 	
			//seulement si tous les joueurs ne sont pas humains
			if (nbJoueurs > nbHumains) {
				System.out.println("Veuillez choisir le niveau des IA (1 ou 2): ");
				difficulte = scan.nextInt();
				//on vérifie que le nombre entré est bien compatible 
				while ( (difficulte < 1) || (difficulte > 2)  ){
					System.out.println("Entrée incorrecte, vous devez choisir entre 1 et 2 ");
					difficulte = scan.nextInt();
				}
				System.out.println("Le niveau est donc reglé sur "+difficulte+ lineSeparator);
			} else {
				System.out.println("Il n'y a pas d'IA dans cette partie");
			}
		//On demande le(s) pseudo(s) a l'utilisateur 
			String[] tableauPseudos = {"J1","J2","J3","J4"};
			//on regarde le nombre d'humains
			if (nbHumains==1) {
				System.out.println("Vous allez maintenant devoir entrer votre pseudo");
			} else {
				System.out.println("Vous allez maintenant devoir entrer les pseudos des joueurs humains");
			}
			//on demande nbHumains pseudos
			for (int i=0; i<nbHumains;i++) {
				System.out.println("Veuillez entrer le pseudo du joueur "+ (i+1));
				tableauPseudos[i]= scan.next();
			}
		
		new Jeu(nbJoueurs,nbHumains,difficulte, tableauPseudos);
		scan.close();
	}
	
	
	
	
}
