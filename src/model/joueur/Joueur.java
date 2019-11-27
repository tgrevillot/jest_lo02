package model.joueur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import model.cards.Carte;
import model.cards.Couleur;
import model.cards.Joker;

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
	
	private HashMap<Couleur, HashSet<Carte>> cartesTri;
	
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
	public int compterPointsCarte() {
		int score = 0;
		
		//On ajoute les points obtenues avec les cartes coeur
		score += scoreCoeur(this.cartesTri.get(Couleur.COEUR));
		
		//On repère ensuite les paires de cartes noires et pour chacune d'elles on ajoute 2 points
		score += scorePairesNoires(this.cartesTri.get(Couleur.PIQUE), this.cartesTri.get(Couleur.TREFLE));
		
		//Pour le reste des cartes on se contente d'aller chercher les faces values 
		//et de les ajouter au score.
		//On va lier les autres paquets de carte dans une même collection pour boucler plus facilement
		HashSet<Carte> melangeFinal = new HashSet<Carte>();
		melangeFinal.addAll(this.cartesTri.get(Couleur.CARREAU));
		melangeFinal.addAll(this.cartesTri.get(Couleur.PIQUE));
		melangeFinal.addAll(this.cartesTri.get(Couleur.TREFLE));
		
		//Addition des points finales
		score += addFaceValues(melangeFinal);
		
		return score;
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
		this.jest.add(this.getCarteRestante());
	}
	
	public void accepterCarte(Carte c) {
		if(c != null)
			this.main.add(c);
	}
	
	private int addFaceValues(HashSet<Carte> cartes) {
		int score = 0;
		for(Carte c : cartes)
			score += c.envoyerPoints();
		return score;
	}
	
	private int scorePairesNoires(HashSet<Carte> pique, HashSet<Carte> trefle) {
		int score = 0;
		
		//On va tout boucler sur l'ensemble des cartes des deux collections et vérifier s'il y en a
		//possédant la même face value. Si c'est le cas on ajoute 2 points.
		for(Carte carteP : pique)
			for(Carte carteT : trefle)
				if(carteP.getFaceValue() == carteT.getFaceValue())
					score += 2;
		
		return score;
	}
	
	private int scoreCoeur(HashSet<Carte> coeur) {
		int score = 0;
		//On commencera par vérifier si le jest comporte le Joker
				if(hasJoker()) {
				//Si on a le Joker alors on recuperera le nombre de carte Coeur
					int nbCartesCoeur = coeur.size();
					
					//Si on a 0 coeur, alors on ajoute 4 points
					if(nbCartesCoeur == 0)
						score += 4;
					
					//Si on a 4 coeur, on incrémente le score de 10
					else if(nbCartesCoeur == 4)
						score += 10;
					else	
						//Sinon on incrémente des faces Values de chacun
						for(Carte c : coeur)
							score += c.getFaceValue();
				}
		return score;
	}
	
	public boolean hasJoker() {
		for(Carte c : this.jest) 
			if(c.donnerCouleur().equals("Joker"))
				return true;
		return false;
	}
	
	public Carte removeJoker() {
		Carte joker;
		for(Carte c : this.jest)
			if(c.donnerCouleur().equals("Joker")) {
				joker = c;
				this.jest.remove(c);
				return joker;
			}
		return null;
	}
	
	public void generateSortJest() {
		//Si les listes n'ont pas été déjà générées
		if(this.cartesTri.isEmpty()) {
	
			//On crée une liste de cartes par couleur 
			HashSet<Carte> carreau = new HashSet<Carte>();
			HashSet<Carte> trefle = new HashSet<Carte>();
			HashSet<Carte> pique = new HashSet<Carte>();
			HashSet<Carte> coeur = new HashSet<Carte>();
			
			//En fonction de la couleur de chaque carte on range dans le bon paquet
			for(Carte c : this.jest) {
				switch(c.donnerCouleur()) {
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
	
	
	public HashSet<Carte> getCartesTriJest(Couleur coul) {
		return this.cartesTri.get(coul);
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
		if (this.jest.size()==0) {
			System.out.println("Votre jest est vide");
		}else {
			for (Carte c : this.jest) {
				System.out.println(c.toString());
			}
		}
		System.out.println("<======>");
	}
}
