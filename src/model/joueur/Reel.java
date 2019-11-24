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
		System.out.println("=> Commande disponible : voirJest");
		String carteARetourner= scan.next();
		while (Integer.parseInt(carteARetourner) != 1 && Integer.parseInt(carteARetourner)!=2) {
			//tant que la carte choisie n'est pas la 1 ou la 2
			if (carteARetourner == "voirJest") { // si le joueur a fait la commande voirJest
				joueurJouant.afficherJest();
			}
			System.out.println("Vous devez choisir entre 1 et 2 (ou la commande : voirJest)");
			carteARetourner= scan.next();
		}
		switch (carteARetourner) { // on est sorti de la boucle et on cache la carte en question
		case "1" : 
			main.get(0).cacherCarte();
			System.out.println("Vous avez choisi de cacher la carte " + (main.get(0)).afficher()+"\n");
			break;
		case "2" :
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
			System.out.println("=> Commandes disponibles : voirJest, voirMain");
			String choixJoueur= scan.next();
			while (choixJoueur!="1" && choixJoueur!="2") { //tant que le joueur n'as pas choisi une carte valide
				switch (choixJoueur) { //on regarde si c'est une commande
					case "voirJest" : //commande Jest
						joueurJouant.afficherJest();
						break;
					case "voirMain" : //comande main
						joueurJouant.afficherMain();
						break;
					default : //autres cas
						break;
				}
				System.out.println("Vous devez choisir entre 1 et 2 (ou les commandes : voirJest, voirMain)");
				choixJoueur= scan.next();
			}
			joueurAPrendre = joueurJouant; //dans ce cas ci, le joueur qui joue se prend une care a lui-même
			if (choixJoueur=="2") { //la carte 2 est la carte cachée
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
			System.out.println("Quelle carte voulez vous prendre dans votre jest ? (numéro)");
			System.out.println("=> Commandes disponible : voirJest, voirMain");
			String choixJoueur= scan.next();
			while (Integer.parseInt(choixJoueur) < 1 || Integer.parseInt(choixJoueur) >i) {
				switch (choixJoueur) { //on verfie si le joueur fais une commande
					case "voirJest" : 
						joueurJouant.afficherJest();
						break;
					case "voirMain" :
						joueurJouant.afficherMain();
						break;
					default :
						break;
				}
				System.out.println("Vous devez choisir entre 1 et "+i+" (ou les commandes : voirJest, voirMain)");
				choixJoueur= scan.next();
			}
			i--; //on corrige le dernier up de i pour en faire le compteur de carte disponibles
			boolean cache;  // si le choix est pair c'est une carte cachee
			int choixJoueurInt =  Integer.parseInt(choixJoueur); //on met la variable en int pour faire les calculs
			if (choixJoueurInt%2==0) { // test de parité
				cache= true; // pair c'est une carte cachee
			}else { //impair et c'est une carte visible
				cache=false;
			}
			joueurAPrendre = joueurs.get((choixJoueurInt-1)/2); //un calcul simple pour definir a quel joueur on prend la carte
			cartePrise = joueurAPrendre.prendreCarte(cache); //on prend la carte choisie du joueurAPrendre
			}	
		System.out.println("Vous avez pris la carte : "+ cartePrise.afficher());
		joueurJouant.ajouterDansJest(cartePrise);//on met la carte que l'on a pris dans notre jest
		return joueurAPrendre; //on renvois le joueur a qui on a pris une carte
	}
}
