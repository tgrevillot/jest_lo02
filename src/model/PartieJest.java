package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Observable;

import model.cards.Carte;
import model.cards.Couleur;
import model.cards.Trophee;
import model.joueur.Joueur;


public class PartieJest extends Observable {
	
	/** 
	 * Le deck est l'ensemble des cartes jouables du jeu. Il comporte 17 cartes diff�rentes au d�part. 
	 * Il contient un ensemble vide, partiel ou total des cartes disponibles sans redondance.
	 */
	private LinkedList<Carte> deck;
	
	/** 
	 * joueurs est la liste des joueurs pr�sents dans la partie.
	 * Cette liste contient 3 ou 4 Joueurs 
	 */
	private ArrayList<Joueur> joueurs;
	
	/**
	 * Trophee est le tableau de trophee du jeu 
	 * Ce tableau contient 1 [resp. 2] trophee pour 4 [resp. 3] joueurs
	 */
	private Trophee[] trophees;
	
	/**
	 * Le compteur de point � utiliser
	 * Pour l'instant il n'existe qu'une seule mani�re de compter les points
	 */
	private Visiteur compteur;
	
	/**
	 * Cet objet va servir a d�terminer a quel joueur donner le trophee.
	 * Pour le moment, il n'existe qu'un seul moyen de r�partir les trophees
	 */
	private RepartiteurTrophee repartiteur;
	
	/**
	 * Identifiant permettant de detecter la regle additionnelle choisie par l'utilisateur
	 * 0 = Aucune regle additionnelle utilisee
	 */
	private int conditionsVictoire;

	public PartieJest() {
		initialiser();
		faireUnTour(new LinkedList<Carte>());
		Joueur gagnant = determinerGagnant();
		
		System.out.println(System.getProperty("line.separator"));
		System.out.println("F�licitation � " + gagnant.getNom() + " ! Vous remportez la partie !");
	}
	
	/**
	 * fonction d'initialisation des parametres de base pour la cr�ation du jeu 
	 * inclus : les demandes a l'utilisateur, la cr�ation d'un deck m�lang�, le choix du [des] troph�[s]  
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
		//Choix de la difficult� 
		int difficulte=0;
		if (nbJoueurs > nbHumains) { //seulement si tous les joueurs ne sont pas humains
			System.out.println("Veuillez choisir le niveau des IA (1 ou 2): ");
			difficulte=initDifficulte(nbJoueurs,nbHumains);
		} else {
			System.out.println("Il n'y a pas d'IA dans cette partie");
		}
		
		
		//choix des pseudos des joueurs 
		String[] tableauPseudos = initPseudos(nbHumains);
		
		//Choix des troph�es add
		System.out.println("Veuillez choisir le troph�e additionnel que vous voulez utiliser : \n0- Aucun \n1- Troph�e Nullifieur");
		int regles = initRegles();
		
		//Choix des conditions de victoires add
		System.out.println("Veuillez choisir la r�gle additionnelle que vous voulez utiliser : \n0- Aucune \n1- A Coeur Ouvert");
		int condi = initCondiVictoires();
		
		//On cr�e le compteur de point
		this.compteur = new CompteurClassique();
		
		//On cr�e le r�partiteur de troph�e
		this.repartiteur = new RepartiteurTropheeClassique(this.compteur);
		
		remplirPaquet();
		ajouterJoueurs(nbHumains, nbJoueurs, tableauPseudos, difficulte,condi );		
		creerTrophees(regles);
		
	}
	
	public void notifier() {
		this.setChanged();
		this.notifyObservers();
	}
	
	private int initNBJoueurs() {
		Scanner scan = new Scanner(System.in);
		String nbJoueurs = scan.next();
		try {
			if ((Integer.parseInt(nbJoueurs) != 4) && (Integer.parseInt(nbJoueurs) != 3)) {
				System.out.println("Entr�e incorrecte, vous devez choisir 3 ou 4 : ");
				return initNBJoueurs();
			}else {
				System.out.println("Vous avez choisi "+nbJoueurs+" joueurs \n");
				return Integer.parseInt(nbJoueurs);
			}
		}catch (Exception e) {
			System.out.println("Entr�e incorrecte, vous devez choisir 3 ou 4 : ");
			return initNBJoueurs();
		}
	}
	private int initNBHumains(int nbJoueurs) {
		Scanner scan = new Scanner(System.in);
		String lineSeparator = System.getProperty("line.separator");
		String nbHumains = scan.next();
		//on v�rifie que le nombre entr� est bien compatible 
		try {
			Integer.parseInt(nbHumains);
			if ((Integer.parseInt(nbHumains) < 1) || (Integer.parseInt(nbHumains) > nbJoueurs)) {
				System.out.println("Entr�e incorrecte, vous devez choisir entre 1 et "+nbJoueurs+" inclus : ");
				return initNBHumains(nbJoueurs);
			} else {
				System.out.println("Il y aura donc "+nbHumains+" joueurs humains " + lineSeparator);
				return Integer.parseInt(nbHumains);
			}
		} catch (Exception e) {
			System.out.println("Entr�e incorrecte, vous devez choisir entre 1 et "+nbJoueurs+" inclus : ");
			return initNBHumains(nbJoueurs);
		}
	}
	
	private int initDifficulte(int nbJoueurs, int nbHumains) {
		Scanner scan = new Scanner(System.in);
		String difficulte = scan.next();
		try {//on v�rifie que le nombre entr� est bien compatible 
			if ((Integer.parseInt(difficulte) < 1) || (Integer.parseInt(difficulte) > 2)) {
				System.out.println("Entr�e incorrecte, vous devez choisir entre 1 et 2 ");
				return initDifficulte(nbJoueurs, nbHumains);
			}else {
				System.out.println("Le niveau est donc regl� sur "+difficulte+ "\n");
				return Integer.parseInt(difficulte);
			}
		} catch (Exception e) {
			System.out.println("Entr�e incorrecte, vous devez choisir entre 1 et 2 ");
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
	
	private int initRegles() {
		Scanner scan = new Scanner(System.in);
		String choix = scan.next();
		try {//on v�rifie que le nombre entr� est bien compatible 
			switch (choix) {
			case "0" : 
				System.out.println("Vous allez jouer avec les troph�es de base !");
				return 0;
			case "1" :
				System.out.println("Vous allez jouer avec le troph�e : \"nullifieur\" ");
				return 1;
			default : 
				System.out.println("Entr�e incorrecte, vous devez choisir entre 0 et 1 ");
				return initRegles();
			}
		} catch (Exception e) {
			System.out.println("Entr�e incorrecte, vous devez choisir entre 0 et 1 ");
			return initRegles();
		}
	}
	
	private int initCondiVictoires() {
		Scanner scan = new Scanner(System.in);
		String choix = scan.next();
		try {//on v�rifie que le nombre entr� est bien compatible 
			switch (choix) {
			case "0" : 
				System.out.println("Vous allez jouer avec les r�gles de base !");
				return 0;
			case "1" :
				System.out.println("Vous allez jouer avec l'extension : \"A Coeur Ouvert\" ");
				return 1;
			default : 
				System.out.println("Entr�e incorrecte, vous devez choisir entre 0 et 1 ");
				return initCondiVictoires();
			}
		} catch (Exception e) {
			System.out.println("Entr�e incorrecte, vous devez choisir entre 0 et 1 ");
			return initCondiVictoires();
		}
	}
	
	
	/**
	 * Cree les cartes et remplis le paquet avec
	 */
	public void remplirPaquet() {
		//on cr�� et on remplit le deck de cartes
		this.deck = new LinkedList<Carte>();
		
		for (int i = 1; i<5; i++) {
			this.deck.add(new Carte(i, Couleur.CARREAU));
			this.deck.add(new Carte(i, Couleur.COEUR));
			this.deck.add(new Carte(i, Couleur.TREFLE));
			this.deck.add(new Carte(i, Couleur.PIQUE));
		}
		this.deck.add(new Carte(0, Couleur.JOKER));
		//on m�lange le deck
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
	public void ajouterJoueurs(int nbHumains, int nbJoueurs, String[] tabPseudos, int difficulte, int condi) {
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
		this.conditionsVictoire=condi;
		
	}
	
	/**
	 * Cree le trophee a utiliser durant la partie
	 */
	public void creerTrophees(int regle) {
		
		Carte carteTrophee1 =  this.deck.pop();
		Trophee trophee1= new Trophee(carteTrophee1);
		switch (regle) {
		case 0 : //regles du jeu de base 
			// s'il y a 3 joueurs on rajoute un deuxi�me troph�e
			if (this.joueurs.size()==3) {
				Carte carteTrophee2 =  this.deck.pop();
				Trophee trophee2= new Trophee(carteTrophee2);
				Trophee[] trophees = {trophee1,trophee2};
				this.trophees = trophees; 
			}else { //il y a 4 joueurs donc 1 seul trophee 
				Trophee[] trophee = {trophee1};
				this.trophees = trophee;
			}
			break;
		case 1 : //regle du jeu avec le nullifieur
			//dans ce cas ci il y a aussi le troph�e nullifieur ! 
			Trophee tropheenullifieur = new Trophee(new Carte(0, Couleur.EXTENSION));
			// s'il y a 3 joueurs on rajoute un deuxi�me troph�e
			if (this.joueurs.size()==3) {
				Carte carteTrophee2 =  this.deck.pop();
				Trophee trophee2= new Trophee(carteTrophee2);
				Trophee[] trophees = {trophee1,trophee2,tropheenullifieur};
				this.trophees = trophees; 
			}else { //il y a 4 joueurs donc 1 seul trophee 
				Trophee[] trophee = {trophee1,tropheenullifieur};
				this.trophees = trophee;
			}
			break;
			
		}
		//S'il existe 2 troph�es et que le celui dans la deuxi�me case du tableau est le Joker,
		//On va le replacer dans la premi�re case du tableau pour �viter les probl�mes du type :
		//Aucun Joker dans le jeu
		if(this.trophees.length >= 2)
			if(this.trophees[1].getCarte().getCouleur() == Couleur.JOKER) {
				//On inverse la position des 2 troph�es
				Trophee t = this.trophees[0];
				this.trophees[0] = this.trophees[1];
				this.trophees[1] = t;
			}
		
		//On indique � l'utilisateur quelles sont les troph�es
		System.out.println("\n Le(s) troph�e(s) a/ont �t� choisis ! Le(s) voici :");
		for(int i = 0; i < this.trophees.length; i++) {
			if(this.trophees[i] != null)
				System.out.println(this.trophees[i].getCarte() + " avec la condition " + this.trophees[i].getCondition());
		}
		
		System.out.println(System.getProperty("line.separator"));		

		
	}
	
	private void attribuerTrophee() {
		Joueur[] j = new Joueur[this.trophees.length];
		//On va entrer dans le tableau les joueurs � qui distribuer les cartes
		for(int i = 0; i < this.trophees.length; i++) 
			if(this.trophees[i] != null) 
				j[i] = this.repartiteur.attribuer(this.trophees[i].getCondition(), this.joueurs);
		
		//On attribue ensuite les troph�es aux joueurs correspondant
		
		
		
		for(int i = 0; i < j.length; i++) {
			if(j[i] != null && i<(5-this.joueurs.size())) {
				j[i].ajouterDansJest(this.trophees[i].getCarte());
				System.out.println("Le troph�e " + this.trophees[i].getCondition() + " a �t� attribu� � "
						+ j[i].getNom() + " !");
			} else {
				if (!(i==1 && this.trophees.length==1) && i<(5-this.joueurs.size())) { //si on ne cherche pas de 2eme troph�e alors qu'il n'y en a qu'un seul 
					System.out.println("Le troph�e " + this.trophees[i].getCondition() + " n'a pas �t� attribu�.");
				}
			}
				
		}
				
		System.out.println();
	}

	
	private HashMap<Joueur, Integer> compterPoints() {
		//On va utiliser un annuaire pour associer � chaque joueur son nombre de points
		HashMap<Joueur, Integer> tabPoints = new HashMap<Joueur, Integer>();
		//Pour chaque joueur on compte le nombre de points
		for(Joueur j : this.joueurs)
			tabPoints.put(j, j.accept(this.compteur));
		return tabPoints;
	}
	
	private Joueur determinerTour(Joueur j) {
		//Joueur � faire jouer
		Joueur joueur;
		
		//Soit le joueur a deja joue et on se base sur la plus grande carte encore en lice
		if(j == null || j.aJoue()) 
			joueur = determinerPlusGrand();
		//Soit le joueur n'a pas encoure jou� et il joue
		else
			joueur = j;
		
		return joueur;
	}
	
	private Joueur determinerPlusGrand() {
		//On cr�e notre tampon contenant les joueurs n'ayant pas encore jou�
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
					//Si c'est le cas on met � jour la carte max et le joueur max
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
		System.out.println("La partie est termin�e ! Faisons le point sur les scores : ");
		
		//Attribution des troph�es
		attribuerTrophee();
	
		//Affichage des r�sultats et d�termination du gagnant
		
		//On applique les r�gles additionnelles choisies pr�c�demment
		Joueur jGagnant = null;
		switch(this.conditionsVictoire) {
			case 1:
				jGagnant = ConditionsVictoire.aCoeurOuvert(this.joueurs);
		}
		
		//Si la r�gle n'a pas renvoy� de vainqueur ou si aucune r�gle additionnelle n'a �t�
		//s�lectionn�e, on utilise la m�thode de calcul des points traditionnelle
		if(jGagnant != null) 
			System.out.println("La r�gle additionnelle s'applique, nous avons un gagnant !");
		else {
			//R�cup�ration des scores sous forme de HashMap
			HashMap<Joueur, Integer> resultats = compterPoints();
			
			jGagnant = this.joueurs.get(0);
			int scoreActu;
			int scoreMax = resultats.get(this.joueurs.get(0));
			for(Joueur j : resultats.keySet()) {
				scoreActu = resultats.get(j);
				if(scoreActu > scoreMax) {
					scoreMax = scoreActu;
					jGagnant = j;
				}
				//On affiche le score de chaque joueur �galement
				System.out.println("Le joueur " + j.getNom() + " a obtenu " + scoreActu + " points.");
			}
			/* pour checker les jest a la fin de la game 
			for(Joueur j : this.joueurs) {
				System.out.println(j.getNom());
				j.afficherJest();
				System.out.println("\n");
				
			}
			*/
		}
		return jGagnant;
	}
	
	private void faireUnTour(LinkedList<Carte> cartesRestantes) {
		//On m�lange les cartes restantes avec les cartes du deck
		LinkedList<Carte> buffer = new LinkedList<Carte>();
		
		//On commence par ajouter 3 ou 4 cartes depuis le deck dans le buffer 
		//(suivant le nombre de joueur)
		for(int i = 0; i < this.joueurs.size(); i++)
			
			buffer.add(this.deck.removeFirst());
		
		//Si les cartes ne sont pas vides
		if(!cartesRestantes.isEmpty()) {
			
			//On indique qu'on vient de finir un tour.
			System.out.println("Fin du tour ! Redistribution des cartes ...");
			
			//On boucle dans cartesRestantes pour d�cacher les cartes 
			for(Carte c : cartesRestantes)
				c.antiCacherCarte();
			
			//On indique que les joueurs peuvent jouer
			for(Joueur j : this.joueurs)
				j.peutJouer();
			
			//On regroupe le buffer et les cartes restantes
			buffer.addAll(cartesRestantes);
			//On m�lange le tout
			Collections.shuffle(buffer);
			
			//On indique que le jeu est pr�t
			System.out.println("Le jeu a �t� distribu� !");
		} else {
			//On repioche 3 ou 4 cartes comme on se trouve en d�but de partie
			for(int i = 0; i < this.joueurs.size(); i++)
				buffer.add(this.deck.removeFirst());
		}
			
		//On distribue les cartes
		for(int i = 0; i < 2; i++)
			for(Joueur j : this.joueurs)
				j.accepterCarte(buffer.removeFirst());
		
		//Chacun choisie sa carte � mettre face cach�e
		//Place donc les cartes en mode defense ou attaque
		Joueur ancienJ;
		for(Joueur j : this.joueurs)
			j.choisirFaceCachee();
		//On d�termine le tour de l'utilisateur
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
		
		//On v�rifie s'il y a encore des cartes dans le deck
		if(!this.deck.isEmpty())
			//Si c'est le cas on repart pour un nouveau tour
			faireUnTour(recupererCartesRestantes());
		else {
			//Sinon c'est la fin de la partie donc on doit r�partir les cartes en jeu
			//dans les decks de chacun
			for(Joueur jo : this.joueurs)
				jo.ajouterCartesRestantesJest();
		}
		
	}
	
	private LinkedList<Carte> recupererCartesRestantes() {
		//R�cup�re � la fin de chaque tour les diff�rentes cartes restantes apr�s le
		//choix des offres
		LinkedList<Carte> cartes = new LinkedList<Carte>();
		Carte c;
		for(Joueur j : this.joueurs) {
			c = j.getCarteRestante();
			c.antiCacherCarte();
			cartes.add(c);
		}
		return cartes;
	}
	
	public static void main(String[] args) {

		new PartieJest();

	}
	
}
