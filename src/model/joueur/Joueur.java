package model.joueur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import model.Visiteur;
import model.cards.Carte;
import model.cards.Couleur;

/**
 * La classe correspondant a un joueur
 * Regroupe la plupart des manipulations possibles sur les cartes et les actions a faire
 * @author moras
 *
 */
public class Joueur implements Visitable {
	/**
	 * Indique si le joueur a deja joue pendant ce tour
	 * par defaut a false 
	 */
	private boolean aJoue;
	
	/**
	 * La liste des cartes presentes dans la main du joueur pendant un tour precis
	 * Par defaut vide. Contient 0, 1 ou 2 cartes selon le moment.
	 */
	private ArrayList<Carte> main;
	
	/**
	 * Correspond au jest du joueur, un ensemble de cartes qui lui sont propre
	 * Selon les regles officielles, il peut contenir entre 0 et 7 cartes selon le type et l'avancee de la partie.
	 * par defaut vide 
	 */
	private HashSet<Carte> jest;
	
	/**
	 * La strategie suivie par ce joueur 
	 * Reel,Random ou Basique
	 */
	private IAStrategie strat;
	
	/**
	 * les cartes triees du jest de ce joueur
	 */
	private HashMap<Couleur, HashSet<Carte>> cartesTri;
	
	
	/** 
	 * Chaîne de caractere representant le pseudonyme du joueur affiche en jeu.
	 * Les Ia seront nommees de la sorte : strategie_id (ou l'id est unique de 1 jusqu'a 3)
	 * Les humains donneront leur pseudos en entree
	 */
	private String nom;
	/**
	 * le constructeur
	 * @param pseudo
	 * 		le nom du joueur
	 * @param iaType
	 * 		le type de strategie 
	 */
	public Joueur(String pseudo, int iaType) {
		this.aJoue=false;
		this.main = new ArrayList<Carte>(2);
		this.jest = new HashSet<Carte>();
		this.cartesTri = new HashMap<Couleur, HashSet<Carte>>(4);
		
		//on associe le type de comportement selon l'entier iaType
		// De meme on associe pseudo aux reel et strategie_pseudo (ou pseudo est un chiffre) aux IA 
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
		//On envoie au visiteur le joueur actuel pour qu'il puisse avoir acces aux differentes methodes
		return visiteur.visit(this);
	}
	
	/**
	 * Cette fonction definie le choix de l'offre pour chaque joueur 
	 * S'il est reel : un choix entre les cartes des autres joueurs (qui n'ont pas deja donne une offre)
	 * S'il est une IA : une decision random parmis ces memes personnes
	 * @param lJoueurs
	 * 		lJoueurs est la liste des joueurs qui peuvent donner une carte (i.e qui en ont encore 2)
	 */
	public Joueur prendreOffre(ArrayList<Joueur> lJoueurs) {
		return this.strat.choisir(lJoueurs, this);
	}
	
	/**
	 * Cette fonction est le choix de l'offre par le joueur 
	 * S'il est reel : un choix entre la carte 1ere et la 2eme carte 
	 * S'il est une IA : une decision random
	 */
	public void choisirFaceCachee() {
		this.strat.offrir(this.main, this);
	}
	/**
	 * on recupere les cartes qui restes et on les ajoute dans le jest
	 * Utile surtout lors de la derniere phase du jeu 
	 */
	public void ajouterCartesRestantesJest() {
		Carte c = this.getCarteRestante();
		c.antiCacherCarte();
		this.jest.add(c);
	}
	/**
	 * on prend la carte et on l'ajoute dans notre jest
	 * @param c
	 * 		la carte a ajouter
	 */
	public void accepterCarte(Carte c) {
		if(c != null)
			this.main.add(c);
	}
	/**
	 * On retire la carte d' "indice i" 
	 * en realite on prend juste la i-eme carte que le trouve
	 * L'ordre des cartes etant toujours le meme c'est plus simple pour un joueur de donner la position de la carte qu'il veut 
	 * @param i
	 * 		l'indice de l'element a retirer
	 */
	public void jestRemoveCarte(int i) {
		int j = 1; //on initialise un compteur (il n'y en a pas dans les hashset ...)
		for(Carte c : this.jest) { //on parcoure toutes les cartes du jest
			if (j==i) { // si l'indice est le bon 
				System.out.println("a choisi de nullifier la carte : "+ c.afficher()+ " du jest du joueur "+this.nom +" !");
				this.jest.remove(c); //on enleve la carte indiquee
				break;
			}
			j++; //on incremente j
		}
	}
	/**
	 * renvois le nombre de cartes dans le jest
	 * @return int
	 */
	public int nombreCartesJest() {
		return this.jest.size(); 
	}
	/**
	 * dit si le jest possede le joker ou non 
	 * @return boolean
	 */
	public boolean hasJoker() {
		for(Carte c : this.jest) 
			if(c.getCouleur() == Couleur.JOKER)
				return true;
		return false;
	}
	
	/**
	 * retire le joker et le renvois si il est dans le jest
	 * @return Carte
	 * 		renvois null si il n'est pas dans le jest 
	 */
	public Carte removeJoker() {
		Carte joker;
		for(Carte c : this.jest)
			if(c.getCouleur() == Couleur.JOKER) {
				//On fait pointer la reference vers un autre objet, celui-ci allant etre detruit
				joker = new Carte(c);
				this.jest.remove(c);
				return joker;
			}
		return null;
	}
	
	/**
	 * trie toutes les cartes du jest du joueur 
	 */
	private void generateSortJest() {
		
		//Si les listes n'ont pas ete deja generees
		if(this.cartesTri.isEmpty()) {
	
			//On cree une liste de cartes par couleur 
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
			//On range les differents paquet dans la Map precedemment cree.
			this.cartesTri.put(Couleur.CARREAU, carreau);
			this.cartesTri.put(Couleur.PIQUE, pique);
			this.cartesTri.put(Couleur.TREFLE, trefle);
			this.cartesTri.put(Couleur.COEUR, coeur);
		}
	}
	/**
	 * on recupere la carte restante sur le plateau
	 * (La carte qui n'a pas ete prise)
	 * @return Carte
	 */
	public Carte getCarteRestante() {
		if(this.main.size() == 1)
			return this.main.remove(0);
		return null;
	}
	/**
	 * Getteur sur LA carte qui n'est pas cachee de la main
	 * note:  s'il y en a deux on prend la premiere trouvee
	 * @return Carte
	 */
	public Carte getVisibleCard() {
		for(Carte c : this.main)
			if(!c.isCacher())
				return c;
		
		return null;
	}
	/**
	 * getteur sur la strategie du joueur
	 * @return IAStrategie
	 */
	public IAStrategie getStrat() {
		return this.strat;
	}
	/**
	 * renvois la taille de la main du joueur
	 * @return
	 */
	public int tailleMain() {
		return this.main.size();
	}
	
	/**
	 * recupere les cartes du jest triees
	 * @return HashMap<Couleur, HashSet<Carte>>
	 */
	public HashMap<Couleur, HashSet<Carte>> getCartesTri() {
		if(this.cartesTri.isEmpty())
			generateSortJest();
		
		return this.cartesTri;
	}
	
	/**
	 * getteur des cartes triees dans la couleur donnee du jest du joueur
	 * @param coul
	 * 		la couleur choisie
	 * @return HashSet<Carte>
	 */
	public HashSet<Carte> getCartesParCouleur(Couleur coul) {
		if(this.cartesTri.isEmpty())
			generateSortJest();
		
		return this.cartesTri.get(coul);
	}
	/**
	 * Pour savoir si le joueur a deja joue durant ce tour
	 * (utile pour la gestion de l'ordre des joueurs dans un tour)
	 * @return
	 */
	public boolean aJoue() {
		return aJoue;
	}
	/**
	 * getteur du pseudo du joueur
	 * @return String
	 */
	public String getNom() {
		return this.nom;
	}
	/**
	 * Indique que le joueur a joue pendant ce tour
	 * (pour ne pas que l'on puisse lui re-demander de jouer ce tour ci)
	 */
	public void vientDeJouer() {
		this.aJoue = true;
	}
	/**
	 * indique que le joueur peut de nouveau jouer
	 */
	public void peutJouer() {
		this.aJoue = false;
	}
	
	/**
	 * trouve la carte avec le meilleur ordre avec une certaine valeur
	 * (utile pour les trophees)
	 * @param faceValueToCheck
	 * 		la valeur a trouver
	 * @return Carte
	 */
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
	/**
	 * on compte le nombre de cartes portant la valeur donnee dans le jest
	 * (utile pour les trophees)
	 * @param faceValueToSearch
	 * 		la valeur a chercher
	 * @return int
	 */
	public int compterNbCartesMemeValue(int faceValueToSearch) {
		int compteur = 0;
		for(Carte c : this.jest) {
			if(c.getFaceValue() == faceValueToSearch)
				compteur++;
		}
		return compteur;
	}
	/**
	 * on prend la plus grande valeur individuelle d'une carte dans le jest du joueur
	 * (utile pour les trophees)
	 * @return int
	 */
	public int plusGrandeFaceValue() {
		int faceValue = -1;
		for(Carte c : this.jest) 
			if(c.getFaceValue() > faceValue)
				faceValue = c.getFaceValue();
		return faceValue;
	}
	
	/**
	 * Cette methode renvoie la carte (cachee ou non) apres l'avoir enleve 
	 * @param cache
	 * 		signifie que l'on veut la carte cachee ou visible 
	 * @return 
	 * 		renvois la carte que l'on a enleve de la main
	 */
	public Carte prendreCarte(boolean cache) {
		
		for (Carte c : this.main) {
			if (c.isCacher()==cache) {
				this.main.remove(c);
				return c;
			}
		}
		
		throw new Error("Pas de carte correspondant au critere :" + cache + " dans " + this.main);
	}
	
	/**
	 * Cette fonction ajoute la crte en parametre dans le jest
	 * @param carte
	 * 		la carte a ajouter dans le jest
	 */
	public void ajouterDansJest(Carte carte) {
		carte.antiCacherCarte();
		this.jest.add(carte);
	}
	/**
	 * donne une vue textuele de la main du joueur 
	 * Si vide affiche "Votre main est vide"
	 */
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
	/**
	 * Donne une vue textuelle du joueur
	 * Si vide affiche :"Votre jest est vide"
	 */
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