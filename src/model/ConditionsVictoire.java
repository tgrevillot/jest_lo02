package model;

import model.joueur.Joueur;
import model.cards.Couleur;
import model.cards.Carte;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
/**
 * La classe qui regroupe toutes les règles additionnelles 
 * @author moras
 *
 */
public class ConditionsVictoire {

	/**
	 * Si un joueur possede toutes les cartes Coeur et le joker alors il gagne la partie
	 * @param joueurs
	 * 		La liste des joueurs disponible
	 * @return jGagnant
	 * 		Le joueur gagnant ou null si aucun ne vérifie la condition
	 */
	public static Joueur aCoeurOuvert(ArrayList<Joueur> joueurs) {
		Joueur jGagnant = null;
		Joueur jActu;
		Iterator<Joueur> ite = joueurs.iterator();
		HashSet<Carte> coeurs;
		//Tant qu'on a encore des joueurs à vérifier et qu'on n'a pas trouver de gagnant
		while(ite.hasNext() && jGagnant == null) {
			//On récupère le joueur
			jActu = ite.next();
			//On récupère les cartes coeur
			coeurs = jActu.getCartesParCouleur(Couleur.COEUR);
			//On compte le nombre de cartes de cette couleur et on regarde s'il possède le Joker
			if(coeurs.size() == 4 && jActu.hasJoker())
				jGagnant = jActu;
		}
		return jGagnant;
	}
	
}
