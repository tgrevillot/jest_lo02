package model.joueur;

import java.util.ArrayList;

import model.cards.Carte;

public interface IAStrategie {
	public void offrir(ArrayList<Carte> main, Joueur joueur);
	public Joueur choisir(ArrayList<Joueur> joueurs,Joueur joueur);
	public void nullifierCarte (ArrayList<Joueur> joueurs,Joueur pireJ,Joueur bestJ);
}
