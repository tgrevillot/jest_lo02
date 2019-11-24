package model.joueur;

import java.util.ArrayList;
import java.util.Scanner;

import model.cards.Carte;

public class Reel implements IAStrategie {

	
	public void offrir(ArrayList<Carte> main, Joueur joueurJouant) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Joueur "+ joueurJouant.getNom() +" voici votre main : ");
		System.out.println((main.get(0)).afficher() + " ET " + (main.get(1)).afficher()  );
		System.out.println("Quelle carte voulez vous cacher dans votre offre ? (1 ou 2)");
		System.out.println("=> Commande disponible : 3-voir le Jest");
		int carteARetourner= Integer.parseInt(scan.next());
		while (carteARetourner != 1 && carteARetourner!=2) {
			//tant que la carte choisie n'est pas la 1 ou la 2
			if (carteARetourner == 3) { // si le joueur a fait la commande voirJest
				joueurJouant.afficherJest();
			}
			System.out.println("Vous devez choisir entre 1 et 2 (ou la commande : 3-voirJest)");
			carteARetourner= Integer.parseInt(scan.next());
		}
		switch (carteARetourner) { // on est sorti de la boucle et on cache la carte en question
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
		//TODO rajouter les options : voir offre et voir jest
		Scanner scan = new Scanner(System.in);
		Carte cartePrise; //la carte que l'on prend
		Joueur joueurAPrendre; //le joueur auquel on prend une carte
		if (joueurs.size()==0) { //CAS ou le joueur est le dernier et doit prendre ses propres cartes
			System.out.println("Joueur "+joueurJouant.getNom()+", il ne reste plus que vos carte : ");
			System.out.println("1- "+ (joueurJouant.getVisibleCard()).afficher());
			System.out.println("2- Carte cachée");
			System.out.println("Quelle carte voulez vous prendre pour mettre dans votre jest ? (1 ou 2)");
			System.out.println("=> Commandes disponibles : 3-voirJest, 4-voirMain");
			int choixJoueur= Integer.parseInt(scan.next());
			while (choixJoueur!=1 && choixJoueur!=2) { //tant que le joueur n'as pas choisi une carte valide
				if (choixJoueur==3) { //on verfie si le joueur fais une commande
					joueurJouant.afficherJest(); //comande Jest
				}
				if (choixJoueur==4) {
					joueurJouant.afficherMain(); //Commande main
				}
				System.out.println("Vous devez choisir entre 1 et 2 (ou les commandes : 3-voirJest, 4-voirMain)");
				choixJoueur= Integer.parseInt(scan.next());
			}
			joueurAPrendre = joueurJouant; //dans ce cas ci, le joueur qui joue se prend une care a lui-même
			if (choixJoueur==2) { //la carte 2 est la carte cachée
				cartePrise = joueurJouant.prendreCarte(true);
			}else {//la carte 1 est la carte visible
				cartePrise = joueurJouant.prendreCarte(false);
			}
		} else { // cas ou il reste d'autres joueurs avec des offres disponibles
			System.out.println("Joueur "+joueurJouant.getNom()+", les cartes disponibles sont : ");
			int i = 1 ; //initialisation
			for (Joueur j : joueurs) { // on affiche toutes les cartes disponibles (les cachées ne sont pas dévoilées)
				System.out.println(i +"- "+ (j.getVisibleCard()).afficher());
				i++;
				System.out.println(i +"- Carte cachée");
				i++;
			}
			i--; //on corrige le dernier up de i pour en faire le compteur de carte disponibles
			System.out.println("Quelle carte voulez vous prendre dans votre jest ? (numéro)");
			System.out.println("=> Commandes disponible : "+(i+1)+"- voirJest, "+(i+2)+"- voirMain");
			int choixJoueur= Integer.parseInt(scan.next());
			while (choixJoueur < 1 || choixJoueur >i) {
				if (choixJoueur==i+1) { //on verfie si le joueur fais une commande
						joueurJouant.afficherJest(); //comande Jest
				}
				if (choixJoueur==i+2) {
					joueurJouant.afficherMain(); //Commande main
				}
				System.out.println("Vous devez choisir entre 1 et "+i+" (ou les commandes : "+(i+1)+"- voirJest, "+(i+2)+"- voirMain)");
				choixJoueur= Integer.parseInt(scan.next());
			}
			boolean cache;  // si le choix est pair c'est une carte cachee
			if (choixJoueur%2==0) { // test de parité
				cache= true; // pair c'est une carte cachee
			}else { //impair et c'est une carte visible
				cache=false;
			}
			joueurAPrendre = joueurs.get((choixJoueur-1)/2); //un calcul simple pour definir a quel joueur on prend la carte
			cartePrise = joueurAPrendre.prendreCarte(cache); //on prend la carte choisie du joueurAPrendre
			}	
		System.out.println("Vous avez pris la carte : "+ cartePrise.afficher());
		joueurJouant.ajouterDansJest(cartePrise);//on met la carte que l'on a pris dans notre jest
		return joueurAPrendre; //on renvois le joueur a qui on a pris une carte
	}
}
