package model.cards;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import model.joueur.Joueur;

public enum Condition {
	detenteurJoker,
	bestJest,
	plusPetitPique,
	plusPetitTrefle,
	plusPetitCarreau,
	plusPetitCoeur,
	plusGrandPique,
	plusGrandTrefle,
	plusGrandCarreau,
	plusGrandCoeur,
	bestJestWithoutJoker,
	plusDeCartes2,
	plusDeCartes3,
	plusDeCartes4;
	
	public static Joueur attribution(Condition cond, ArrayList<Joueur> joueurs) {
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
		}
		return j;
	}
		
	private static Joueur detenteurJoker(ArrayList<Joueur> joueurs) {
		for(Joueur j : joueurs)
			if(j.hasJoker())
				return j;
		throw new Error("Aucune Carte Joker n'a été trouvée");
	}
	
	private static Joueur bestJest(ArrayList<Joueur> joueurs) {
		Joueur jMax = joueurs.get(0);
		Joueur jActu;
		int scoreMax = joueurs.get(0).compterPointsCarte();
		int scoreActu;
		
		for(int i = 1; i < joueurs.size(); i++) {
			jActu = joueurs.get(i);
			scoreActu = jActu.compterPointsCarte();
			//Si le joueur a un meilleur score (sans appel)
			if(scoreActu > scoreMax) {
				//Alors il est celui qui a pour l'instant le meilleur jest
				scoreMax = scoreActu;
				jMax = jActu;				
			} else if(scoreActu == scoreMax) {
				//Dans le cas d'une égalité, on va tout d'abord checker la plus grande faceValue dans le Jest
				int fvActu = jActu.plusGrandeFaceValue();
				int fvMax = jMax.plusGrandeFaceValue();
				
				//Puis si on a encore une égalité
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
	
	private static Joueur bestJestWithoutJoker(ArrayList<Joueur> joueurs) {
		//On va tout d'abord enlever le Joker du joueur incriminé
		Joueur jJoker = null;
		for(int i = 0; i < joueurs.size(); i++)
			if(joueurs.get(i).hasJoker()) {
				i = joueurs.size();
				jJoker = joueurs.get(i);
			}
		
		Carte joker = jJoker.removeJoker();
				
		//Ensuite on va calculer les points
		Joueur bestJest = bestJest(joueurs);
		//Le meilleur Jest remportera le trophée
		//Puis nous allons remettre le joker dans le jest du joueur
		jJoker.accepterCarte(joker);
		return bestJest;
	}
	
	private static Joueur rechercheJoueurPlusGrand(ArrayList<Joueur> joueurs, Couleur coul) {
		//Pour les conditions de recherche des plus grands [insérer couleur] on boucle sur l'ensemble des
		//joueurs et dès qu'on trouve une meilleure carte on la met à jour
		//Les calculs se basent ici sur les valeurs des faces values
		Joueur jCherche = null;
		int cMax = -1, cActu;
		//Pour chaque joueur si on trouve une meilleure carte que la sienne on met à jour
		for(Joueur jo : joueurs) {
			cActu = rechercheCartePlusGrande(jo.getCartesTriJest(coul));
			if(cActu > cMax) {
				cMax = cActu;
				jCherche = jo;
			}
		}
		return jCherche;
	}
	
	private static Joueur rechercheJoueurPlusPetit(ArrayList<Joueur> joueurs, Couleur coul) {
		//Pour les conditions de recherche des plus petit [insérer couleur] on boucle sur l'ensemble des
		//joueurs et dès qu'on trouve une face value plus petite on met cMax à jour
		//Les calculs se basent ici sur les valeurs des faces values
		Joueur jCherche = null;
		int cMin = 6, cActu;
		//Pour chaque joueur si on trouve une meilleure carte que la sienne on met à jour
		for(Joueur jo : joueurs) {
			cActu = rechercheCartePlusGrande(jo.getCartesTriJest(coul));
			if(cActu < cMin) {
				cMin = cActu;
				jCherche = jo;
			}
		}
		return jCherche;
	}
	
	private static int rechercheCartePlusGrande(HashSet<Carte> cartes) {
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
	
	private static int rechercheCartePlusPetite(HashSet<Carte> cartes) {
		//La valeur des face values s'arrêtant à 4 pour le moment, il sera impossible de monter au-dessus de
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
	
	private static Joueur plusDeCartes(ArrayList<Joueur> joueurs, int faceValueToSearch) {
		Joueur jMax = null;
		int valueMax = 0;
		int valueAct;
		for(Joueur j : joueurs) {
			valueAct = j.compterNbCartesMemeValue(faceValueToSearch);
			if(valueAct > valueMax) {
				//On met à jour à la fois le joueur, la value et les cartesMax
				valueMax = valueAct;
				jMax = j;
			} else if(valueAct == valueMax) {
				//Dans le cas d'une égalité on doit déterminer parmi les cartes des 2 joueurs 
				//laquelle a la meilleure couleur
				Carte cAct = j.meilleurCartePlusDe(faceValueToSearch);
				Carte cMax = jMax.meilleurCartePlusDe(faceValueToSearch);
				
				//Si la priorité est plus importante sur la carteActu alors le nouveau joueur
				//devient le joueur Max
				if(cAct.getOrdre() > cMax.getOrdre()) {
					jMax = j;
					valueMax = valueAct;
				}
			}
		}
		return jMax;
	}
}