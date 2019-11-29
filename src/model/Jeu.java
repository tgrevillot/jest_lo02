package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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
import model.cards.Condition;
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
		initialiser();
		faireUnTour(new LinkedList<Carte>());
		Joueur gagnant = determinerGagnant();
		
		System.out.println(System.getProperty("line.separator"));
		System.out.println("Félicitation à " + gagnant.getNom() + " ! Vous remportez la partie !");
	}
	
	/**
	 * fonction d'initialisation des parametres de base pour la création du jeu 
	 * inclus : les demandes a l'utilisateur, la création d'un deck mélangé, le choix du [des] trophé[s]  
	 */
	public void initialiser() {
		//On Lance la partie
		System.out.println("Bienvenue dans le Jest !");
		System.out.println("Suivez les instructions suivantes pour configurer la partie et pouvoir jouer "+"\n");
		//choix du nombre de joueurs	
		System.out.println("Veuillez entrer le nombre de joueurs (3 ou 4) : ");
		int nbJoueurs=initNBJoueurs();
		//choix du nombre de joueur humains
		System.out.println("Veuillez entrer le nombre de joueurs humains (entre 1 et "+nbJoueurs+" inclus) : ");
		int nbHumains=initNBHumains(nbJoueurs);
		//Choix de la difficulté 
		int difficulte=0;
		if (nbJoueurs > nbHumains) { //seulement si tous les joueurs ne sont pas humains
			System.out.println("Veuillez choisir le niveau des IA (1 ou 2): ");
			difficulte=initDifficulte(nbJoueurs,nbHumains);
		} else {
			System.out.println("Il n'y a pas d'IA dans cette partie");
		}
		//choix des pseudos des joueurs 
		String[] tableauPseudos = initPseudos(nbHumains);

		remplirPaquet();
		ajouterJoueurs(nbHumains, nbJoueurs, tableauPseudos, difficulte);		
		creerTrophees();
	}
	private int initNBJoueurs() {
		Scanner scan = new Scanner(System.in);
		String nbJoueurs = scan.next();
		try {
			if ((Integer.parseInt(nbJoueurs) != 4) && (Integer.parseInt(nbJoueurs) != 3)) {
				System.out.println("Entrée incorrecte, vous devez choisir 3 ou 4 : ");
				return initNBJoueurs();
			}else {
				System.out.println("Vous avez choisi "+nbJoueurs+" joueurs \n");
				return Integer.parseInt(nbJoueurs);
			}
		}catch (Exception e) {
			System.out.println("Entrée incorrecte, vous devez choisir 3 ou 4 : ");
			return initNBJoueurs();
		}
	}
	private int initNBHumains(int nbJoueurs) {
		Scanner scan = new Scanner(System.in);
		String lineSeparator = System.getProperty("line.separator");
		String nbHumains = scan.next();
		//on vérifie que le nombre entré est bien compatible 
		try {
			Integer.parseInt(nbHumains);
			if ((Integer.parseInt(nbHumains) < 1) || (Integer.parseInt(nbHumains) > nbJoueurs)) {
				System.out.println("Entrée incorrecte, vous devez choisir entre 1 et "+nbJoueurs+" inclus : ");
				return initNBHumains(nbJoueurs);
			} else {
				System.out.println("Il y aura donc "+nbHumains+" joueurs humains " + lineSeparator);
				return Integer.parseInt(nbHumains);
			}
		} catch (Exception e) {
			System.out.println("Entrée incorrecte, vous devez choisir entre 1 et "+nbJoueurs+" inclus : ");
			return initNBHumains(nbJoueurs);
		}
	}
	
	private int initDifficulte(int nbJoueurs, int nbHumains) {
		Scanner scan = new Scanner(System.in);
		String difficulte = scan.next();
		try {//on vérifie que le nombre entré est bien compatible 
			if ((Integer.parseInt(difficulte) < 1) || (Integer.parseInt(difficulte) > 2)) {
				System.out.println("Entrée incorrecte, vous devez choisir entre 1 et 2 ");
				return initDifficulte(nbJoueurs, nbHumains);
			}else {
				System.out.println("Le niveau est donc reglé sur "+difficulte+ "\n");
				return Integer.parseInt(difficulte);
			}
		} catch (Exception e) {
			System.out.println("Entrée incorrecte, vous devez choisir entre 1 et 2 ");
			return initDifficulte(nbJoueurs, nbHumains);
		}
	}
	
	private String[] initPseudos(int nbHumains) {
		Scanner scan = new Scanner(System.in);
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
		System.out.println("");
		return tableauPseudos;
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
		// s'il y a 3 joueurs on rajoute un deuxième trophée
		if (this.joueurs.size()==3) {
			Carte carteTrophee2 =  this.deck.pop();
			Trophee trophee2= new Trophee(carteTrophee2);
			Trophee[] trophees = {trophee1,trophee2};
			this.trophees = trophees; 
		}else { //il y a 4 joueurs donc 1 seul trophee 
			Trophee[] trophee = {trophee1};
			this.trophees = trophee;
		}
		
		//On affiche quelles cartes sont les trophées
		System.out.println("Le(s) trophée(s) a/ont été choisis ! Le(s) voici :");
		for(int i = 0; i < this.trophees.length; i++) {
			if(this.trophees[i] != null)
				System.out.println(this.trophees[i].getCarte() + " avec la condition " + this.trophees[i].getCondition());
		}
		
		System.out.println(System.getProperty("line.separator"));		
	}
	
	private void attribuerTrophee() {
		Joueur j;
		for(int i = 0; i < this.trophees.length; i++) {
			if(this.trophees[i] != null) {
				j = Condition.attribution(this.trophees[0].getCondition(), this.joueurs);
				j.ajouterDansJest(this.trophees[i].getCarte());
				System.out.println("Le trophée " + this.trophees[i].getCarte() + " a été attribué à "
						+ j.getNom() + " !");
			}
		}
		System.out.println();
	}

	
	private HashMap<Joueur, Integer> compterPoints() {
		//On va utiliser un annuaire pour associer à chaque joueur son nombre de points
		HashMap<Joueur, Integer> tabPoints = new HashMap<Joueur, Integer>();
		//Pour chaque joueur on compte le nombre de points
		for(Joueur j : this.joueurs)
			tabPoints.put(j, j.compterPointsCarte());
		return tabPoints;
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
		String lineSeparator = System.getProperty("line.separator");
		
		//On annonce que c'est la fin de la partie
		System.out.println(lineSeparator + lineSeparator);
		System.out.println("La partie est terminée ! Faisons le point sur les scores : ");
		//Pour chaque joueur on va générer les listes triées de cartes
		for(Joueur j : this.joueurs)
			j.generateSortJest();
		
		//Attribution des trophées
		attribuerTrophee();
		
		//Récupération des scores sous forme de HashMap
		HashMap<Joueur, Integer> resultats = compterPoints();
		
		//Affichage des résultats et détermination du gagnant
		Joueur gagnant = this.joueurs.get(0);
		int scoreActu;
		int scoreMax = resultats.get(this.joueurs.get(0));
		for(Joueur j : resultats.keySet()) {
			scoreActu = resultats.get(j);
			if(scoreActu > scoreMax) {
				scoreMax = scoreActu;
				gagnant = j;
			}
			//On affiche le score de chaque joueur également
			System.out.println("Le joueur " + j.getNom() + " a obtenu " + scoreActu + " points.");
		}
		return gagnant;
	}
	
	private void faireUnTour(LinkedList<Carte> cartesRestantes) {
		//On mélange les cartes restantes avec les cartes du deck
		LinkedList<Carte> buffer = new LinkedList<Carte>();
		
		//On commence par ajouter 3 ou 4 cartes depuis le deck dans le buffer 
		//(suivant le nombre de joueur)
		for(int i = 0; i < this.joueurs.size(); i++)
			buffer.add(this.deck.removeFirst());
		
		//Si les cartes ne sont pas vides
		if(!cartesRestantes.isEmpty()) {
			
			//On indique qu'on vient de finir un tour.
			System.out.println("Fin du tour ! Redistribution des cartes ...");
			
			//On boucle dans cartesRestantes pour décacher les cartes 
			for(Carte c : cartesRestantes)
				c.antiCacherCarte();
			
			//On indique que les joueurs peuvent jouer
			for(Joueur j : this.joueurs)
				j.peutJouer();
			
			//On regroupe le buffer et les cartes restantes
			buffer.addAll(cartesRestantes);
			//On mélange le tout
			Collections.shuffle(buffer);
			
			//On indique que le jeu est prêt
			System.out.println("Le jeu a été distribué !");
		} else {
			//On repioche 3 ou 4 cartes comme on se trouve en début de partie
			for(int i = 0; i < this.joueurs.size(); i++)
				buffer.add(this.deck.removeFirst());
		}
			
		//On distribue les cartes
		for(int i = 0; i < 2; i++)
			for(Joueur j : this.joueurs)
				j.accepterCarte(buffer.removeFirst());
		
		//Chacun choisie sa carte à mettre face cachée
		//Place donc les cartes en mode defense ou attaque
		Joueur ancienJ;
		for(Joueur j : this.joueurs)
			j.choisirFaceCachee();
		//On détermine le tour de l'utilisateur
		Joueur j = null;
		for(int k = 0; k < this.joueurs.size(); k++) {
			j = determinerTour(j);
			ArrayList<Joueur> lJoueurs = new ArrayList<Joueur>();
			//on definit la liste de joueurs auxquels on peut prendre une carte
			for (Joueur joueur : this.joueurs) {
				if (joueur.tailleMain()==2 && joueur!=j) {
					lJoueurs.add(joueur);
				}
			}
			
			//Chaque joueur choisis une offre
			ancienJ = j;
			j = ancienJ.prendreOffre(lJoueurs);
			//On indique que ce joueur vient de jouer
			ancienJ.vientDeJouer();
		}
		
		//On vérifie s'il y a encore des cartes dans le deck
		if(!this.deck.isEmpty())
			//Si c'est le cas on repart pour un nouveau tour
			faireUnTour(recupererCartesRestantes());
		else {
			//Sinon c'est la fin de la partie donc on doit répartir les cartes en jeu
			//dans les decks de chacun
			for(Joueur jo : this.joueurs)
				jo.ajouterCartesRestantesJest();
		}
		
	}
	
	private LinkedList<Carte> recupererCartesRestantes() {
		//Récupère à la fin de chaque tour les différentes cartes restantes après le
		//choix des offres
		LinkedList<Carte> cartes = new LinkedList<Carte>();
		for(Joueur j : this.joueurs)
			cartes.add(j.getCarteRestante());
		return cartes;
	}
	
	public static void main(String[] args) {

		new Jeu();

	}
	
	
	
	
}
