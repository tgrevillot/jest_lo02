package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import model.cards.Carte;
import model.cards.Condition;
import model.cards.Couleur;
import model.joueur.Joueur;

/**
 * Classe servant a repartir chaque troph�e a un joueur
 * @author moras
 *
 */
public class RepartiteurTropheeClassique implements RepartiteurTrophee {

	private Visiteur compteur;
	
	/**
	 * le constructeur 
	 * @param v
	 * 		pour le design patern visiteur
	 */
	public RepartiteurTropheeClassique(Visiteur v) {
		if(v == null)
			throw new Error("Le comptur de points n'est pas instanci�");
		this.compteur = v;
	}
	

	/**
	 * Determine quel joueur doit recevoir le troph� donn� via les fonctions auxiliaires
	 * @param cond 
	 * 		la condition pour obtenir le troph�
	 * @param joueurs 
	 * 		la liste compl�te des joueurs
	 * @return j
	 * 		le joueur auquel on attribuera le troph�e
	 */
	public Joueur attribuer(Condition cond, ArrayList<Joueur> joueurs) {
		Joueur j = null;
		switch (cond) {
			case detenteurJoker:
				j = detenteurJoker(joueurs);
				break;
			case bestJest:
				j = bestJest(joueurs);
				break;
			case plusPetitPique:
				j = rechercheJoueurPlusPetit(joueurs, Couleur.PIQUE);
				break;
			case plusPetitTrefle:
				j = rechercheJoueurPlusPetit(joueurs, Couleur.TREFLE);
				break;
			case plusPetitCarreau:
				j = rechercheJoueurPlusPetit(joueurs, Couleur.CARREAU);
				break;
			case plusPetitCoeur:
				j = rechercheJoueurPlusPetit(joueurs, Couleur.COEUR);
				break;
			case plusGrandPique:
				j = rechercheJoueurPlusGrand(joueurs, Couleur.PIQUE);
				break;
			case plusGrandTrefle:
				j = rechercheJoueurPlusGrand(joueurs, Couleur.TREFLE);
				break;
			case plusGrandCarreau:
				j = rechercheJoueurPlusGrand(joueurs, Couleur.CARREAU);
				break;
			case plusGrandCoeur:
				j = rechercheJoueurPlusGrand(joueurs, Couleur.COEUR);
				break;
			case bestJestWithoutJoker:
				j = bestJestWithoutJoker(joueurs);
				break;
			case plusDeCartes2:
				j = plusDeCartes(joueurs, 2);
				break;
			case plusDeCartes3:
				j = plusDeCartes(joueurs, 3);
				break;
			case plusDeCartes4:
				j = plusDeCartes(joueurs, 4);
				break;
			case variantePireJest:
				j = pireJest(joueurs);
				Joueur j2 = bestJest(joueurs);
				j.getStrat().nullifierCarte(joueurs,j,j2);
				break;
		}
		return j;
	}
	
	/**
	 * trouve le joueur qui poss�de le Joker
	 * @param joueurs
	 * 		la List de tous les joueurs
	 * @return Joueur j
	 * 		le joueur possedant le joker
	 */
	private Joueur detenteurJoker(ArrayList<Joueur> joueurs) {
		for(Joueur j : joueurs)
			if(j.hasJoker())
				return j;
		throw new Error("Aucune Carte Joker n'a �t� trouv�e");
	}
	
	/**
	 * trouve le joueur qui poss�de le jest avec le plus de score
	 * @param joueurs 
	 * 		la liste de tous les joueurs
	 * @return Joueur jMax
	 * 		le joueur qui a le meilleur score du jest
	 */
	private Joueur bestJest(ArrayList<Joueur> joueurs) {
		Joueur jMax = joueurs.get(0);
		Joueur jActu;
		int scoreMax = joueurs.get(0).accept(this.compteur);
		int scoreActu;
		
		for(int i = 1; i < joueurs.size(); i++) {
			jActu = joueurs.get(i);
			scoreActu = jActu.accept(this.compteur);
			//Si le joueur a un meilleur score (sans appel)
			if(scoreActu > scoreMax) {
				//Alors il est celui qui a pour l'instant le meilleur jest
				scoreMax = scoreActu;
				jMax = jActu;				
			} else if(scoreActu == scoreMax) {
				//Dans le cas d'une �galit�, on va tout d'abord checker la plus grande faceValue dans le Jest
				int fvActu = jActu.plusGrandeFaceValue();
				int fvMax = jMax.plusGrandeFaceValue();
				
				//Puis si on a encore une �galit�
				if(fvActu == fvMax) {
					//on va comparer les couleurs
					if(jActu.meilleurCartePlusDe(fvActu).getOrdre() > jMax.meilleurCartePlusDe(fvMax).getOrdre()) {
						jMax = jActu;
					}
						
				}
				//Si on obtient une faceValueMax plus important que Max alors on remplace le joueur
				//directement
				else if(fvActu > fvMax) {
					jMax = jActu;
				}
			}
		}
		return jMax;
	}
	/**
	 * Trouve le meilleur jest sans consid�rer le joker
	 * @param joueurs
	 * 		la list des joueurs a considerer
	 * @return Joueur bestJest
	 * 		le joueur avec le meilleur jest sans compter le joker
	 */
	private Joueur bestJestWithoutJoker(ArrayList<Joueur> joueurs) {
		//On va tout d'abord enlever le Joker du joueur incrimin�
		Joueur jJoker = null;
		for(int i = 0; i < joueurs.size(); i++)
			if(joueurs.get(i).hasJoker()) {
				jJoker = joueurs.get(i);
				i = joueurs.size();
			}
		//TODO Bizarre : on g�n�re bien un Joker � chaque fois ?
		if (jJoker !=null){
			Carte joker = jJoker.removeJoker();
			//Ensuite on va calculer les points
			Joueur bestJest = bestJest(joueurs);
			//Le meilleur Jest remportera le troph�e
			//Puis nous allons remettre le joker dans le jest du joueur
			jJoker.ajouterDansJest(joker);
			return bestJest;
		} else {
			Joueur bestJest = bestJest(joueurs);
			//Le meilleur Jest remportera le troph�e
			return bestJest;
		}
		
				
		
	}
	/**
	 * Cherche le joueur qui a la carte la plus grande de la couleur donn�e
	 * @param joueurs
	 * 		la liste des joueurs consid�r�s
	 * @param coul
	 * 		la couleur consid�r�e
	 * @return Joueur jCherche
	 * 		le joueur qui poss�de cette carte
	 */
	private Joueur rechercheJoueurPlusGrand(ArrayList<Joueur> joueurs, Couleur coul) {
		//Pour les conditions de recherche des plus grands [ins�rer couleur] on boucle sur l'ensemble des
		//joueurs et d�s qu'on trouve une meilleure carte on la met � jour
		//Les calculs se basent ici sur les valeurs des faces values
		Joueur jCherche = null;
		int cMax = -1, cActu;
		//Pour chaque joueur si on trouve une meilleure carte que la sienne on met � jour
		for(Joueur jo : joueurs) {
			cActu = rechercheCartePlusGrande(jo.getCartesParCouleur(coul));
			if(cActu > cMax) {
				cMax = cActu;
				jCherche = jo;
			}
		}
		return jCherche;
	}
	/**
	 * Cherche le joueur qui a la carte la plus petite de la couleur donn�e
	 * @param joueurs
	 * 		la liste des joueurs consid�r�s
	 * @param coul
	 * 		la couleur consid�r�e
	 * @return Joueur jCherche
	 * 		le joueur qui poss�de cette carte
	 */
	private Joueur rechercheJoueurPlusPetit(ArrayList<Joueur> joueurs, Couleur coul) {
		//Pour les conditions de recherche des plus petit [ins�rer couleur] on boucle sur l'ensemble des
		//joueurs et d�s qu'on trouve une face value plus petite on met cMax � jour
		//Les calculs se basent ici sur les valeurs des faces values
		Joueur jCherche = null;
		int cMin = 6, cActu;
		//Pour chaque joueur si on trouve une meilleure carte que la sienne on met � jour
		for(Joueur jo : joueurs) {
			HashSet<Carte> c = jo.getCartesParCouleur(coul);
			cActu = rechercheCartePlusPetite(c);
			if(cActu < cMin) {
				cMin = cActu;
				jCherche = jo;
			}
		}
		return jCherche;
	}
	
	/**
	 * cherche la plus grande carte dans le set donn�
	 * @param cartes
	 * 		le set de cartes dans lequel on recherche
	 * @return int
	 * 		retourne la face value de la plus grande carte
	 */
	private int rechercheCartePlusGrande(HashSet<Carte> cartes) {
		if(cartes.isEmpty())
			return -1;
		
		Iterator<Carte> ite = cartes.iterator();
		Carte cMax = ite.next();
		Carte c;
		while(ite.hasNext()) {
			c = ite.next();
			if(cMax.getFaceValue() > c.getFaceValue())
				cMax = c;
		}
		return cMax.getFaceValue();
	}
	/**
	 * cherche la plus petite carte dans le set donn�
	 * @param cartes
	 * 		le set de cartes dans lequel on recherche
	 * @return int
	 * 		retourne la face value de la plus petite carte
	 */
	private int rechercheCartePlusPetite(HashSet<Carte> cartes) {
		//La valeur des face values s'arr�tant � 4 pour le moment, il sera impossible de monter au-dessus de
		//6
		if(cartes.isEmpty())
			return 6;
		
		Iterator<Carte> ite = cartes.iterator();
		Carte cMax = ite.next();
		Carte c;
		while(ite.hasNext()) {
			c = ite.next();
			if(cMax.getFaceValue() < c.getFaceValue())
				cMax = c;
		}
		return cMax.getFaceValue();
	}
	/**
	 * recherche le joueur qui a le plus de cartes de la face value donn�e
	 * @param joueurs
	 * 		la liste des joueurs concern�s
	 * @param faceValueToSearch
	 * 		la valeur que l'on cherche
	 * @return Joueur
	 * 		le joueur qui correspond 
	 */
	private Joueur plusDeCartes(ArrayList<Joueur> joueurs, int faceValueToSearch) {
		Joueur jMax = null;
		int valueMax = 0;
		int valueAct;
		for(Joueur j : joueurs) {
			valueAct = j.compterNbCartesMemeValue(faceValueToSearch);
			if(valueAct > valueMax) {
				//On met � jour � la fois le joueur, la value et les cartesMax
				valueMax = valueAct;
				jMax = j;
			} else if(valueAct == valueMax) {
				//Dans le cas d'une �galit� on doit d�terminer parmi les cartes des 2 joueurs 
				//laquelle a la meilleure couleur
				Carte cAct = j.meilleurCartePlusDe(faceValueToSearch);
				Carte cMax = jMax.meilleurCartePlusDe(faceValueToSearch);
				
				//Si la priorit� est plus importante sur la carteActu alors le nouveau joueur
				//devient le joueur Max
				if(cAct.getOrdre() > cMax.getOrdre()) {
					jMax = j;
					valueMax = valueAct;
				}
			}
		}
		return jMax;
	}
	/**
	 * renvois le joueur avec le pire jest
	 * @param joueurs
	 * 		la liste des joueurs concern�s
	 * @return Joueur
	 */
	private Joueur pireJest(ArrayList<Joueur> joueurs) {
		Joueur jMin = joueurs.get(0);
		Joueur jActu;
		int scoreMax = joueurs.get(0).accept(this.compteur);
		int scoreActu;
		
		for(int i = 1; i < joueurs.size(); i++) {
			jActu = joueurs.get(i);
			scoreActu = jActu.accept(this.compteur);
			//Si le joueur a un pire score (sans appel)
			if(scoreActu < scoreMax) {
				//Alors il est celui qui a pour l'instant le pire jest
				scoreMax = scoreActu;
				jMin = jActu;				
			} else if(scoreActu == scoreMax) {
				//Dans le cas d'une �galit�, on va tout d'abord checker la plus grande faceValue dans le Jest
				int fvActu = jActu.plusGrandeFaceValue();
				int fvMin = jMin.plusGrandeFaceValue();
				
				//Puis si on a encore une �galit�
				if(fvActu == fvMin) {
					//on va comparer les couleurs
					if(jActu.meilleurCartePlusDe(fvActu).getOrdre() < jMin.meilleurCartePlusDe(fvMin).getOrdre()) {
						jMin = jActu;
					}
						
				}
				//Si on obtient une faceValueMax moins important que Min alors on remplace le joueur
				//directement
				else if(fvActu < fvMin) {
					jMin = jActu;
				}
			}
		}
		return jMin;
	}
	
	
	
	
	
}
