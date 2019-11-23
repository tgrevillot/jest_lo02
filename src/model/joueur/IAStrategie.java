package model.joueur;

import java.util.ArrayList;

import model.cards.Carte;

public interface IAStrategie {
	public void comportement();
	public void offre(ArrayList<Carte> main);
}
