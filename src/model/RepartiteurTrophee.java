package model;

import java.util.ArrayList;

import model.cards.Condition;
import model.joueur.Joueur;

/**
 * Interface pour répartir les trophées
 * @author moras
 *
 */
public interface RepartiteurTrophee {
	public Joueur attribuer(Condition cond, ArrayList<Joueur> joueurs);
}
