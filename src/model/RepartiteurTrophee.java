package model;

import java.util.ArrayList;

import model.cards.Condition;
import model.joueur.Joueur;

/**
 * Interface pour r�partir les troph�es
 * @author moras
 *
 */
public interface RepartiteurTrophee {
	public Joueur attribuer(Condition cond, ArrayList<Joueur> joueurs);
}
