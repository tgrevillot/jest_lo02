package model.joueur;

import java.util.ArrayList;

import model.cards.Carte;

/**
 * L'interface regulant les stratégies
 * @author moras
 *
 */
public interface IAStrategie {
	/**
	 * Pour choisir une carte face cachée
	 * @param main
	 * 		la main du joueur qui joue
	 * @param
	 * 		le joueur qui joue 
	 */
	public void offrir(ArrayList<Carte> main, Joueur joueur);
	/**
	 * La méthode pour choisir la carte a récuperer dans son jest
	 * @param joueurs
	 * 		les joueurs du plateau
	 * @param joueur
	 * 		le joueur qui joue
	 * @return Joueur
	 * 		le joueur qui joue
	 */
	public Joueur choisir(ArrayList<Joueur> joueurs,Joueur joueur);
	/**
	 * Méthode pour nullifier la carte voulue
	 * @param joueurs
	 * 		les joueurs du plateau
	 * @param pireJ
	 * 		le joueur avec le pire jest
	 * @param bestJ
	 * 		le joueur avec le meilleur jest
	 */
	public void nullifierCarte (ArrayList<Joueur> joueurs,Joueur pireJ,Joueur bestJ);
}
