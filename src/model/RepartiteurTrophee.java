package model;

import java.util.ArrayList;

import model.cards.Condition;
import model.joueur.Joueur;

public interface RepartiteurTrophee {
	public Joueur attribuer(Condition cond, ArrayList<Joueur> joueurs);
}
