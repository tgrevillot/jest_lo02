package model.joueur;

import java.util.ArrayList;

import model.cards.Carte;
import model.cards.*;

/**
 * La classe de l'ia BAsique (c'est en fait une IA random pour l'instant)
 * @author moras
 *
 */
public class Random implements IAStrategie {

	/**
	 * méthode qui permet à cette IA de cacher l'une de ses deux cartes
	 */
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
	 * @param joueurs 
	 * 		la liste des joueurs disponibles
	 * @param joueurJouant
	 * 		le joueur en train de jouer actuellement (utile car il n'y a pas de moyen de le trouver sinon)
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
	
	//meme que random
	public void nullifierCarte (ArrayList<Joueur> joueurs,Joueur pireJ,Joueur bestJ) {
		System.out.println("Joueur : "+pireJ.getNom()+ " vous recevez le trophée bonus \"nullifieur\"");
		int i =  bestJ.nombreCartesJest();
		int choixJoueur = (int) ((Math.random())*(i));
		System.out.print(pireJ.getNom()+" ");
		if (choixJoueur!=0) { //si le choix n'est pas "aucunes cartes"
			bestJ.jestRemoveCarte(choixJoueur);//on l'enlève
		} else {
			System.out.println("a choisi de ne nullifier aucune carte ! ");
		}
		
	}
}
