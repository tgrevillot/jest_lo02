package model.joueur;

import java.util.ArrayList;

import model.cards.Carte;

public interface IAStrategie {
	public void offrir(ArrayList<Carte> main, String pseudo);
	public Joueur choisir(ArrayList<Joueur> joueurs,Joueur joueur);
}
