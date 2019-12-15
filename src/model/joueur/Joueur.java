package model.joueur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import model.Visiteur;
import model.cards.Carte;
import model.cards.Couleur;

public class Joueur implements Visitable {
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
	
	private HashMap<Couleur, HashSet<Carte>> cartesTri;
	
	
	/** 
	 * Chaîne de caractère représentant le pseudonyme du joueur affiché en jeu.
	 * Les Ia seront nommées de la sorte : strategie_id (où l'id est unique de 1 jusqu'à 3)
	 */
	private String nom;
	
	public Joueur(String pseudo, int iaType) {
		this.aJoue=false;
		this.main = new ArrayList<Carte>(2);
		this.jest = new HashSet<Carte>();
		this.cartesTri = new HashMap<Couleur, HashSet<Carte>>(4);
		
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
	public int accept(Visiteur visiteur) {
		//On envoie au visiteur le joueur actuel pour qu'il puisse avoir accès aux différentes méthodes
		return visiteur.visit(this);
	}
	
	/**
	 * Cette fonction définie le choix de l'offre pour chaque joueur 
	 * S'il est reel : un choix entre les cartes des autres joueurs (qui n'ont pas déja donné une offre)
	 * S'il est une IA : une décision random parmis ces mêmes personnes
	 * @param lJoueurs
	 * lJoueurs est la liste des joueurs qui peuvent donner une carte (i.e qui en ont encore 2)
	 */
	public Joueur prendreOffre(ArrayList<Joueur> lJoueurs) {
		return this.strat.choisir(lJoueurs, this);
	}
	
	/**
	 * Cette fonction est le choix de l'offre par le joueur 
	 * S'il est reel : un choix entre la carte 1ere et la 2eme carte 
	 * S'il est une IA : une décision random
	 */
	public void choisirFaceCachee() {
		this.strat.offrir(this.main, this);
	}
	
	public void ajouterCartesRestantesJest() {
		Carte c = this.getCarteRestante();
		c.antiCacherCarte();
		this.jest.add(c);
	}
	
	public void accepterCarte(Carte c) {
		if(c != null)
			this.main.add(c);
	}
	
	public void jestRemoveCarte(int i) {
		int j = 1; //on initialise un compteur (il n'y en a pas dans les hashset ...)
		for(Carte c : this.jest) { //on parcoure toutes les cartes du jest
			if (j==i) { // si l'indice est le bon 
				System.out.println("a choisi de nullifier la carte : "+ c.afficher()+ " du jest du joueur "+this.nom +" !");
				this.jest.remove(c); //on enlève la carte indiquée
				break;
			}
			j++; //on incremente j
		}
	}
	
	public int nombreCartesJest() {
		return this.jest.size(); 
	}
	
	public boolean hasJoker() {
		for(Carte c : this.jest) 
			if(c.getCouleur() == Couleur.JOKER)
				return true;
		return false;
	}
	
	public Carte removeJoker() {
		Carte joker;
		for(Carte c : this.jest)
			if(c.getCouleur() == Couleur.JOKER) {
				//On fait pointer la référence vers un autre objet, celui-ci allant être détruit
				joker = new Carte(c);
				this.jest.remove(c);
				return joker;
			}
		return null;
	}
	
	private void generateSortJest() {
		
		//Si les listes n'ont pas été déjà générées
		if(this.cartesTri.isEmpty()) {
	
			//On crée une liste de cartes par couleur 
			HashSet<Carte> carreau = new HashSet<Carte>();
			HashSet<Carte> trefle = new HashSet<Carte>();
			HashSet<Carte> pique = new HashSet<Carte>();
			HashSet<Carte> coeur = new HashSet<Carte>();
			
			//En fonction de la couleur de chaque carte on range dans le bon paquet
			for(Carte c : this.jest) {
				switch(c.donnerCouleurString()) {
					case "Carreau":
						carreau.add(c);
						break;
					case "Coeur":
						coeur.add(c);
						break;
					case "Pique":
						pique.add(c);
						break;
					case "Trefle":
						trefle.add(c);
						break;
				}
			}
			//On range les différents paquet dans la Map précédemment créé.
			this.cartesTri.put(Couleur.CARREAU, carreau);
			this.cartesTri.put(Couleur.PIQUE, pique);
			this.cartesTri.put(Couleur.TREFLE, trefle);
			this.cartesTri.put(Couleur.COEUR, coeur);
		}
	}
	
	public Carte getCarteRestante() {
		if(this.main.size() == 1)
			return this.main.remove(0);
		return null;
	}

	public Carte getVisibleCard() {
		for(Carte c : this.main)
			if(!c.isCacher())
				return c;
		
		return null;
	}
	
	public IAStrategie getStrat() {
		return this.strat;
	}
	
	public int tailleMain() {
		return this.main.size();
	}
	
	public HashMap<Couleur, HashSet<Carte>> getCartesTri() {
		if(this.cartesTri.isEmpty())
			generateSortJest();
		
		return this.cartesTri;
	}
	
	public HashSet<Carte> getCartesParCouleur(Couleur coul) {
		if(this.cartesTri.isEmpty())
			generateSortJest();
		
		return this.cartesTri.get(coul);
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
	
	public Carte meilleurCartePlusDe(int faceValueToCheck) {
		Iterator<Carte> ite = this.jest.iterator();
		Carte cMax = ite.next();
		Carte cActu;
		while(ite.hasNext()) {
			cActu = ite.next();
			if(cActu.getFaceValue() == faceValueToCheck) 
				if(cActu.getOrdre() > cMax.getOrdre())
					cMax = cActu;
		}
		return cMax;
	}
	
	public int compterNbCartesMemeValue(int faceValueToSearch) {
		int compteur = 0;
		for(Carte c : this.jest) {
			if(c.getFaceValue() == faceValueToSearch)
				compteur++;
		}
		return compteur;
	}
	
	public int plusGrandeFaceValue() {
		int faceValue = -1;
		for(Carte c : this.jest) 
			if(c.getFaceValue() > faceValue)
				faceValue = c.getFaceValue();
		return faceValue;
	}
	
	/**
	 * Cette methode renvoie la carte (cachée ou non) après l'avoir enlevé 
	 * @param cache
	 * signifie que l'on veut la carte cachée ou visible 
	 * @return 
	 * renvois la carte que l'on a enlevé de la main
	 */
	public Carte prendreCarte(boolean cache) {
		
		for (Carte c : this.main) {
			if (c.isCacher()==cache) {
				this.main.remove(c);
				return c;
			}
		}
		
		throw new Error("Pas de carte correspondant au critère :" + cache + " dans " + this.main);
	}
	
	/**
	 * Cette fonction ajoute la crte en parametre dans le jest
	 * @param carte
	 */
	public void ajouterDansJest(Carte carte) {
		carte.antiCacherCarte();
		this.jest.add(carte);
	}
	
	public void afficherMain() {
		System.out.println("<======>");
		System.out.println("Voici les cartes de votre main");
		if (this.main.size()==0) {
			System.out.println("Votre main est vide");
		}else {
			for (Carte c : this.main) {
				System.out.println(c.toString());
			}
		}
		System.out.println("<======>");
	}
	
	public void afficherJest() {
		System.out.println("<======>");
		int ite = 1;
		if (this.jest.size()==0) {
			System.out.println("Votre jest est vide");
		}else {
			for (Carte c : this.jest) {
				
				System.out.println(ite +"- "+ c.toString());
				ite++;
			}
		}
		System.out.println("<======>");
	}
}