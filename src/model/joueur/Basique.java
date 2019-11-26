package model.joueur;

import java.util.ArrayList;

import model.cards.Carte;
import model.cards.*;
public class Basique implements IAStrategie {

	
	public void offrir(ArrayList<Carte> main, Joueur joueurJouant) {
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
	 * note : pour l'instant la même methode qu epou random
	 * @param joueurs 
	 * la liste des joueurs disponibles
	 */
	public Joueur choisir(ArrayList<Joueur> joueurs,Joueur joueurJouant) {
		Carte cartePrise;
		Joueur joueurAPrendre;
		if (joueurs.size()==0) { //CAS ou le joueur est le dernier et doit prendre ses propres cartes
			joueurAPrendre = joueurJouant;
		}else {// Cas ou il reste d'autres personnes avec des offres valides
		int choixJoueur = (int) ((Math.random())*(joueurs.size())); // un int random entre 0 et nombre de joueurs-1 inclus
		joueurAPrendre = joueurs.get(choixJoueur);
		}
		double choixCache = Math.round(Math.random()); //on choisis aleatoirement si on veut la cachée ou la visible
		if (choixCache==0) {
			cartePrise = joueurAPrendre.prendreCarte(false); //on recupere la carte visible du joueur choisi
		}else {
			cartePrise = joueurAPrendre.prendreCarte(true); //on recupere la carte cachee du joueur choisi
		}
		String stringCarte = "";
		if (cartePrise.isCacher()) {
			stringCarte = "[carte cachée]";
		} else {
			stringCarte = cartePrise.afficher();
		}
		System.out.println("\n Le joueur "+joueurJouant.getNom()+" a pris la carte "+ stringCarte + "\n");
		joueurJouant.ajouterDansJest(cartePrise);
		return joueurAPrendre;
	}
}
