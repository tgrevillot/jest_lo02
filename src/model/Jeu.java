package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Jeu {
	
	/** Le deck est l'ensemble des cartes jouables du jeu. Il comporte 17 cartes différentes au départ. 
	 * Il contient un ensemble vide, partiel ou total des cartes disponibles sans redondance.
	 */
	private LinkedList<Carte> deck;
	
	/** joueurs est la liste des joueurs présents dans la partie.
	 * Cette liste contient 3 ou 4 Joueurs 
	 */
	private ArrayList<Joueur> joueurs;
	
	
	
	
	
	
	
	public Jeu(int nbJoueurs,int nbHumains, int difficulte) {
		
		
		//on créée le deck de cartes
		this.deck = new LinkedList<Carte>();
		
		this.deck.add(new Joker());
		for (int i = 1; i<5; i++) {
			this.deck.add(new Carreau(i));
			this.deck.add(new Coeur(i));
			this.deck.add(new Pique(i));
			this.deck.add(new Trefle(i));
		}
		
		
		//On initialise maintenant les joueurs 
		this.joueurs = new ArrayList<Joueur>();
		
		//TODO FAIRE LA CREATION DES JOUEURS 
		
		
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
		

		System.out.println("Bienvenue dans le Jest !");
		System.out.println("Suivez les instructions suivantes pour configurer la partie et pouvoir jouer \n\n");
		
		//choix du nombre de joueurs	
			System.out.println("Veuillez entrer le nombre de joueurs (3 ou 4) : ");
			int nbJoueurs = scan.nextInt();
			//on vérifie que le nombre entré est bien 3 ou 4 
			while ( (nbJoueurs != 4) && (nbJoueurs != 3)  ){
				System.out.println("Entrée incorrecte, vous devez choisir 3 ou 4 : ");
				nbJoueurs = scan.nextInt();
			}
			System.out.println("Vous avez choisi "+nbJoueurs+" joueurs \n");
		
		//choix du nombre de joueur humains
			System.out.println("Veuillez entrer le nombre de joueurs humains (entre 1 et "+nbJoueurs+" inclus) : ");
			int nbHumains = scan.nextInt();
			//on vérifie que le nombre entré est bien compatible 
			while ( (nbHumains < 1) || (nbHumains > nbJoueurs)  ){
				System.out.println("Entrée incorrecte, vous devez choisir entre 1 et "+nbJoueurs+" inclus : ");
				nbHumains = scan.nextInt();
			}
			System.out.println("Il y aura donc "+nbHumains+" joueurs humains \n");
		
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
				System.out.println("Le niveau est donc reglé sur "+difficulte+" \n");
			} else {
				System.out.println("Il n'y a pas d'IA dans cette partie");
			}
		
		Jeu j = new Jeu(nbJoueurs,nbHumains,difficulte);
		
		
		
	}
	
	
	
	
}
