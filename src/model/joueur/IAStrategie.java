package model.joueur;

import java.util.ArrayList;

import model.cards.Carte;

/**
 * L'interface regulant les strategies de jeu pour differents joueurs 
 * @author moras
 *
 */
public interface IAStrategie {
	/**
	 * Pour choisir une carte a mettre en face cachee
	 * @param main
	 * 		la main du joueur qui joue
	 * @param
	 * 		le joueur qui joue 
	 */
	public void offrir(ArrayList<Carte> main, Joueur joueur);
	/**
	 * La methode pour choisir la carte a recuperer dans son jest
	 * @param joueurs
	 * 		les joueurs du plateau
	 * @param joueur
	 * 		le joueur qui joue
	 * @return Joueur
	 * 		le joueur qui joue
	 */
	public Joueur choisir(ArrayList<Joueur> joueurs,Joueur joueur);
	/**
	 * Methode pour nullifier la carte voulue
	 * @param joueurs
	 * 		les joueurs du plateau
	 * @param pireJ
	 * 		le joueur avec le pire jest
	 * @param bestJ
	 * 		le joueur avec le meilleur jest
	 */
	public void nullifierCarte (ArrayList<Joueur> joueurs,Joueur pireJ,Joueur bestJ);
}
