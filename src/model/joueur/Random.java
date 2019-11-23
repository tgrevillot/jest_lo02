package model.joueur;

import java.util.ArrayList;
import model.cards.Carte;


public class Random implements IAStrategie {
	
	public void offrir(ArrayList<Carte> main,String pseudo) {
		//on utilise une décision random pour ce choix 
		if (Math.random()<0.5) {
			main.get(0).cacherCarte();
		} else {
			main.get(1).cacherCarte();
		}
	}
	
	
	
	/**
	 * Cette méthode prend une liste de joueur dont on peut prendre des cartes 
	 * et elle choisis une carte aléatoirement parmis celles-ci pour la renvoyer
	 * @param joueurs 
	 * la liste des joueurs disponibles
	 */
	public Carte choisir(ArrayList<Joueur> joueurs,String pseudo) {
		int choixJoueur = (int) ((Math.random())*(joueurs.size())); // un int random entre 0 et nombre de joueurs-1 inclus

		double choixCache = Math.round(Math.random());
		if (choixCache==0) {
			return joueurs.get(choixJoueur).prendreCarte(false); //on recupere la carte visible du joueur choisi
		}else {
			return joueurs.get(choixJoueur).prendreCarte(true); //on recupere la carte cachee du joueur choisi
		}
		
		
	}

}
