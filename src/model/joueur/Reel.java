package model.joueur;

import java.util.ArrayList;
import java.util.Scanner;

import model.cards.Carte;

public class Reel implements IAStrategie {

	
	public void offrir(ArrayList<Carte> main, String pseudo) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Joueur "+ pseudo +" voici votre main : ");
		System.out.println((main.get(0)).afficher() + " ET " + (main.get(1)).afficher()  );
		System.out.println("Quelle carte voulez vous cacher dans votre offre ? (1 ou 2)");
		int carteARetourner= Integer.parseInt(scan.next());
		while (carteARetourner != 1 && carteARetourner != 2) {
			System.out.println("Vous devez choisir 1 ou 2 :");
			carteARetourner= Integer.parseInt(scan.next());
		}
		switch (carteARetourner) {
		case 1 : 
			main.get(0).cacherCarte();
			System.out.println("Vous avez choisi de cacher la carte " + (main.get(0)).afficher()+"\n");
			break;
		case 2 :
			main.get(1).cacherCarte();
			System.out.println("Vous avez choisi de cacher la carte " + (main.get(1)).afficher()+"\n");
			break;
		
		}
	}
	
	public Joueur choisir(ArrayList<Joueur> joueurs,Joueur joueurJouant) {
		Scanner scan = new Scanner(System.in);
		Carte cartePrise;
		Joueur joueurAPrendre;
		if (joueurs.size()==0) { //CAS ou le joueur est le dernier et doit prendre ses propres cartes
			System.out.println("Joueur "+joueurJouant.getNom()+", il ne reste plus que vos carte : ");
			System.out.println("1- "+ (joueurJouant.getVisibleCard()).afficher());
			System.out.println("2- Carte cach�e");
			System.out.println("Quelle carte voulez vous prendre dans votre jest ? (1 ou 2)");
			int choixJoueur= Integer.parseInt(scan.next());
			while (choixJoueur!=1 && choixJoueur!=2) {
				System.out.println("Vous devez choisir entre 1 et 2");
				choixJoueur= Integer.parseInt(scan.next());
			}
			joueurAPrendre = joueurJouant;
			if (choixJoueur==2) {
				
				cartePrise = joueurJouant.prendreCarte(true);
				
			}else {
				cartePrise = joueurJouant.prendreCarte(false);
			}
			
			
		} else { // cas ou il reste d'autres joueurs avec des offres disponibles
		System.out.println("Joueur "+joueurJouant.getNom()+", les cartes disponibles sont : ");
		int i = 1 ;
		for (Joueur j : joueurs) {
			System.out.println(i +"- "+ (j.getVisibleCard()).afficher());
			i++;
			System.out.println(i +"- Carte cach�e");
			i++;
		}
		System.out.println("Quelle carte voulez vous prendre dans votre jest ? (num�ro)");
		int choixJoueur= Integer.parseInt(scan.next());
		while (choixJoueur < 1 || choixJoueur >i) {
			System.out.println("Vous devez choisir entre 1 et "+i);
			choixJoueur= Integer.parseInt(scan.next());
		}
		i--;
		boolean cache;  // si le choix est pair c'est une carte cachee
		if (choixJoueur%2==0) {
			cache= true;
		}else {
			cache=false;
		}
		joueurAPrendre = joueurs.get((choixJoueur-1)/2);
		cartePrise = joueurAPrendre.prendreCarte(cache);
		}
		
		System.out.println("Vous avez pris la carte : "+ cartePrise.afficher());
		joueurJouant.ajouterDansJest(cartePrise);
		return joueurAPrendre; //on recupere la carte visible du joueur choisi
		
	}
}
