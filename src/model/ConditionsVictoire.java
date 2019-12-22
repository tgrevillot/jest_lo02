package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import model.cards.Carte;
import model.cards.Couleur;
import model.joueur.Joueur;

public class ConditionsVictoire {
	
	/**
	 * Si un joueur possede toutes les cartes Coeur et le joker
	 * alors il gagne
	 * @param joueurs
	 * 		La liste des joueurs disponible
	 * @return jGagnant
	 * 		Le joueur gagnant ou null si aucun ne v�rifie la condition
	 */
	public static Joueur aCoeurOuvert(ArrayList<Joueur> joueurs) {
		Joueur jGagnant = null;
		Joueur jActu;
		Iterator<Joueur> ite = joueurs.iterator();
		HashSet<Carte> coeurs;
		//Tant qu'on a encore des joueurs � v�rifier et qu'on n'a pas trouver de gagnant
		while(ite.hasNext() && jGagnant == null) {
			//On r�cup�re le joueur
			jActu = ite.next();
			//On r�cup�re les cartes coeur
			coeurs = jActu.getCartesParCouleur(Couleur.COEUR);
			//On compte le nombre de cartes de cette couleur et on regarde s'il poss�de le Joker
			if(coeurs.size() == 4 && jActu.hasJoker())
				jGagnant = jActu;
		}
		return jGagnant;
	}
	
}
