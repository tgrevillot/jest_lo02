package model;

import java.util.ArrayList;

import model.cards.Condition;
import model.joueur.Joueur;

/**
 * Interface pour repartir les trophees
 * On pourrait ainsi changer facilement la repartition des trophees en creant plusieurs repartiteur
 * @author moras
 *
 */
public interface RepartiteurTrophee {
	public Joueur attribuer(Condition cond, ArrayList<Joueur> joueurs);
}
