package model;

import java.util.HashMap;
import java.util.HashSet;

import model.cards.Carte;
import model.cards.Couleur;
import model.joueur.Joueur;
import model.joueur.Visitable;
/**
 * classe correspondant au comptage des points dans la version initiale du jest
 * @author moras
 *
 */
public class CompteurClassique implements Visiteur {
	
	/**
	 * Deck trie de cartes par couleur 
	 * il va d'une map vide jusqu'a un deck complet
	 */
	private HashMap<Couleur, HashSet<Carte>> cartesTri;
	
	/**
	 * Constructeur de la classe
	 */
	public CompteurClassique() {
		this.cartesTri = new HashMap<Couleur, HashSet<Carte>>();
	}
	
	/**
	 * Methode du patron de conception visiteur
	 * compte le score d'un joueur mis en parametre selon les cartes de son jest et suivant les regles classiques
	 * @param joueur 
	 * 		Le joueur auquel on veut compter le score
	 * @return int score 
	 * 		renvois le score, un entier correspondant au score du joueur 
	 */
	public int visit(Joueur joueur) {
		int score = 0;
		
		//On recupere les cartes triees
		this.cartesTri = joueur.getCartesTri();
		
		//On va maintenant compter les points avec les combinaisons speciales
		score += this.scoreCoeur(joueur, this.cartesTri.get(Couleur.COEUR));
		score += this.scorePairesNoires(this.cartesTri.get(Couleur.PIQUE), this.cartesTri.get(Couleur.TREFLE));
		//TODO : Attention au cas de l'as de coeur solo qui vaut -5 et non 5
		score += this.asToutSeul();
		
		//On additionne les scores des cartes
		for(Couleur coul : this.cartesTri.keySet())
			score += this.addFaceValues(this.cartesTri.get(coul), coul);
		
		//On vide la Map
		this.cartesTri.clear();
		
		return score;
	}
	
	@Override
	public int visit(Visitable visitable) {
		int score = 0;
		
		if(visitable instanceof Joueur)
			score = visit((Joueur) visitable);
		else
			throw new Error("Unknow Visitable");
		
		return score;
	}
	
	//Ci-dessous vont se trouver les methodes privees de calcul de points
	
	/**
	 * Methode qui change les valeurs des AS s'ils sont les seuls de leur couleur dans le jest
	 * @return int score 
	 * 		le score des As
	 */
	private int asToutSeul() {
		int score = 0;
		
		//On va parcourir chaque liste triee et regarder s'il n'y a qu'une seule carte
		for(Couleur coul : this.cartesTri.keySet()) {
			HashSet<Carte> listeT = this.cartesTri.get(coul);
			
			//S'il y a qu'une seule carte 
			if(listeT.size() == 1) {
				//et que c'est un As on met sa face value a 5
				Carte c = listeT.iterator().next();
				if(c.getFaceValue() == 1)
					c.changeAsFaceValue();
			}
		}	
		return score;
	}
	
	/**
	 * on ajoute tous les points concernant les cas normaux 
	 * via les points sur les cartes
	 * @param cartes 
	 * 		un HashSet des cartes du Jest regardee 
	 * @param coul 
	 * 		la couleur des cartes donnees en parametre
	 * @return int score
	 * 		 correspondant a tous les points recoltes par ses cartes
	 */
	private int addFaceValues(HashSet<Carte> cartes, Couleur coul) {
		int score = 0;
		
		//On va determiner la boucle a utiliser en fonction de la couleur
		if(coul == Couleur.COEUR)
			// on ne fait rien car le cas est deja traite
			score = score;
		else {// si ce n'est pas du coeur
			if(coul == Couleur.CARREAU) // carreau : on enleve
				for(Carte c : cartes)
					score -= c.envoyerPoints();
			else { // noir : on ajoute ! 
				for(Carte c : cartes)
					score += c.envoyerPoints();
			}
		}
			
		
		return score;
	}
	
	/**
	 * Rajoute tous les points correspondant a la regle des paires noires
	 * @param pique 
	 * 		le hashset des cartes du Jest qui sont de la couleur PIQUE
	 * @param trefle 
	 * 		le hashset des cartes du Jest qui sont de la couleur TREFLE
	 * @return int score 
	 * 		le score cumule des paires que l'on va ajouter
	 */
	private int scorePairesNoires(HashSet<Carte> pique, HashSet<Carte> trefle) {
		int score = 0;
		
		//On va tout boucler sur l'ensemble des cartes des deux collections et verifier s'il y en a
		//possedant la meme face value. Si c'est le cas on ajoute 2 points.
		for(Carte carteP : pique)
			for(Carte carteT : trefle)
				if(carteP.getFaceValue() == carteT.getFaceValue() 
				||(carteP.getFaceValue() ==5 && carteT.getFaceValue()==1) // on regarde aussi le cas
				||(carteP.getFaceValue() ==1 && carteT.getFaceValue()==5))
					score += 2;
		
		return score;
	}
	/**
	 * Pour traiter le cas special des cartes COEUR
	 * on ajoute / enleve des points selon le nombres de cartes coeur et la presence ou non du Joker
	 * @param j 
	 * 		le joueur concerne
	 * @param coeur 
	 * 		le hashset des cartes de coeur du jest du joueur concerne
	 * @return int score
	 * 		le score obtenu par la liste en parametre
	 */
	private int scoreCoeur(Joueur j, HashSet<Carte> coeur) {
		int score = 0;
		//On commencera par verifier si le jest comporte le Joker
				if(j.hasJoker()) {
				//Si on a le Joker alors on recuperera le nombre de carte Coeur
					int nbCartesCoeur = coeur.size();
					
					//Si on a 0 coeur, alors on ajoute 4 points
					if(nbCartesCoeur == 0)
						score += 4;
					
					//Si on a 4 coeur, on incremente le score de 10
					else if(nbCartesCoeur == 4)
						score += 10;
					else	
						//Sinon on decremente des faces Values de chacun
						for(Carte c : coeur)
							score -= c.getFaceValue(); 
				}
		return score;
	}
}
