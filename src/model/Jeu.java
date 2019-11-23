package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import model.cards.Carreau;
import model.cards.Carte;
import model.cards.Coeur;
import model.cards.Joker;
import model.cards.Pique;
import model.cards.Trefle;
import model.cards.Trophee;
import model.joueur.Joueur;

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
	

	private Jeu() {
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
		
		remplirPaquet();
		ajouterJoueurs(nbHumains, nbJoueurs, tableauPseudos, difficulte);		
		creerTrophees();
		faireUnTour(new LinkedList<Carte>());
		determinerGagnant();
		scan.close();
	}
	
	/**
	 * Cree les cartes et remplis le paquet avec
	 */
	private void remplirPaquet() {
		//on créé et on remplit le deck de cartes
		this.deck = new LinkedList<Carte>();
		
		for (int i = 1; i<5; i++) {
			this.deck.add(new Carreau(i));
			this.deck.add(new Coeur(i));
			this.deck.add(new Pique(i));
			this.deck.add(new Trefle(i));
		}
		this.deck.add(new Joker());
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
		
		Carte carteTrophee1 =  this.deck.pop();
		Trophee trophee1= new Trophee(carteTrophee1);
		// s'il y a 3 joueurs on rajoute un deuxièeme trophée
		if (this.joueurs.size()==3) {
			Carte carteTrophee2 =  this.deck.pop();
			Trophee trophee2= new Trophee(carteTrophee2);
			Trophee[] trophees = {trophee1,trophee2};
			this.trophees = trophees; 
		}else { //il y a 4 joueurs donc 1 seul trophee 
			this.trophees[0] = trophee1;
		}
		
		
	}
	
	private void attribuerTrophee(Trophee t) {
		throw new Error("A COMPLETER");
		/*
		switch (t.getCondition()) {
		case detenteurJoker :
		case bestJest :
		case plusPetitPique :
		case plusPetitTrefle :
		case plusPetitCarreau :
		case plusPetitCoeur :
		case plusGrandPique :
		case plusGrandTrefle :
		case plusGrandCarreau :
		case plusGrandCoeur :
		case bestJestWithoutJoker :
		case plusDeCartes2 :
		case plusDeCartes3 :
		case plusDeCartes4 :
		default : 
			throw new Error("CE N'EST PAS UNE CONDITION VALIDE");
		}*/
	}

	
	private int compterPoints() {
		throw new Error("A COMPLETER");
	}
	
	private Joueur determinerTour(Joueur j) {
		//Joueur à faire jouer
		Joueur joueur;
		
		//Soit le joueur a deja joue et on se base sur la plus grande carte encore en lice
		if(j == null || j.aJoue()) 
			joueur = determinerPlusGrand();
		//Soit le joueur n'a pas encoure joué et il joue
		else
			joueur = j;
		
		return joueur;
	}
	
	private Joueur determinerPlusGrand() {
		//On crée notre tampon contenant les joueurs n'ayant pas encore joué
		Stack<Joueur> buffer = new Stack<Joueur>();
		for(Joueur j : this.joueurs)
			if(!j.aJoue())
				buffer.add(j);
		
		//On fait une recherche de maximum de carte
		Joueur joueurMax = buffer.pop();
		Carte carteMax = joueurMax.getVisibleCard();
		Carte carteActu;
		for(Joueur j : buffer) {
			carteActu = j.getVisibleCard();
			//On va maintenant comparer les valeurs de ces cartes
			//Si la face value de carte2 est superieure a celle de carte2 on met a jour carteMax et
			//JoueurMax
			if(carteActu.getFaceValue() > carteMax.getFaceValue()) {
				carteMax = carteActu;
				joueurMax = j;
			}
			//Si jamais les face values sont identiques, on se base sur les valeurs prises par
			//les couleurs. Chaque couleur ayant un ordre predefini, on va se baser dessus
			else if(carteActu.getFaceValue() == carteMax.getFaceValue()) {
				//On recupere l'ordre de priorite de chaque carte et on les compare pour choisir 
				//la carte la plus forte et determiner le joueur Max
				if(carteActu.getOrdre() > carteMax.getOrdre()) {
					//Si c'est le cas on met à jour la carte max et le joueur max
					carteMax = carteActu;
					joueurMax = j;
				}
			}
		}
		
		return joueurMax;
	}

	private Joueur determinerGagnant() {
		throw new Error("A COMPLETER");
	}
	
	public void faireUnTour(LinkedList<Carte> cartesRestantes) {
		//On mélange les cartes restantes avec les cartes du deck
		LinkedList<Carte> buffer = new LinkedList<Carte>();
		
		//On commence par ajouter 4 cartes depuis le deck dans le buffer
		for(int i = 0; i < 4; i++)
			buffer.add(this.deck.removeFirst());
		
		//Si les cartes ne sont pas vides
		if(!cartesRestantes.isEmpty()) {
			//On regroupe le buffer et les cartes restantes
			buffer.addAll(cartesRestantes);
			//On mélange le tout
			Collections.shuffle(buffer);
		} else {
			//On repioche 4 cartes comme on se trouve en début de partie
			for(int i = 0; i < 4; i++)
				buffer.add(this.deck.removeFirst());
		}
			
		//On distribue les cartes
		for(int i = 0; i < 2; i++)
			for(Joueur j : this.joueurs)
				j.accepterCarte(buffer.removeFirst());
		
		//Chacun choisie sa carte à mettre face cachée
		//Place donc les cartes en mode defense ou attaque
		for(Joueur j : this.joueurs)
			j.choisirFaceCachee();
		
		//On détermine le tour de l'utilisateur
		Joueur j = null;
		for(int k = 0; k < this.joueurs.size(); k++) {
			j = determinerTour(j);
			
			//Chaque joueur fait son offre
			j.prendreOffre();
		}
		
		//On vérifie s'il y a encore des cartes dans le deck
		if(!this.deck.isEmpty())
			//Si c'est le cas on repart pour un nouveau tour
			faireUnTour(recupererCartesRestantes());
		
	}
	
	private LinkedList<Carte> recupererCartesRestantes() {
		throw new Error("A COMPLETER");
	}
	
	public static void main(String[] args) {

		new Jeu();

	}
	
	
	
	
}
