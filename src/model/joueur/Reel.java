package model.joueur;

import java.util.ArrayList;
import java.util.Scanner;

import model.cards.Carte;

public class Reel implements IAStrategie {

	
	public void offrir(ArrayList<Carte> main, Joueur joueurJouant) {
		System.out.println("Joueur "+ joueurJouant.getNom() +" voici votre main : ");
		System.out.println((main.get(0)).afficher() + " ET " + (main.get(1)).afficher()  );
		System.out.println("Quelle carte voulez vous cacher dans votre offre ? (1 ou 2)");
		System.out.println("=> Commande disponible : 3-voir le Jest");
		demanderOffre(main,joueurJouant);
		
	}
	
	
	public void demanderOffre(ArrayList<Carte> main, Joueur joueurJouant) {
		Scanner scan = new Scanner(System.in);
		String carteARetourner= scan.next();
		try {
			Integer.parseInt(carteARetourner);
			if (Integer.parseInt(carteARetourner) == 3) { // si le joueur a fait la commande voirJest
				joueurJouant.afficherJest();
			}
			if (Integer.parseInt(carteARetourner)!=2 && Integer.parseInt(carteARetourner)!=1)
			{
				System.out.println("/!\\ Vous devez choisir entre 1 et 2 (ou la commande : 3-voirJest)");
				demanderOffre(main,joueurJouant);
			} else {
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
		}catch (Exception e) {
			System.out.println("/!\\ Vous devez choisir entre 1 et 2 (ou la commande : 3-voirJest)");
			demanderOffre(main,joueurJouant);
		}
	}
	
	
	public Joueur choisir(ArrayList<Joueur> joueurs,Joueur joueurJouant) {
		if (joueurs.size()==0) { //CAS ou le joueur est le dernier et doit prendre ses propres cartes
			System.out.println("Joueur "+joueurJouant.getNom()+", il ne reste plus que vos carte : ");
			System.out.println("1- "+ (joueurJouant.getVisibleCard()).afficher());
			System.out.println("2- Carte cach�e");
			System.out.println("Quelle carte voulez vous prendre pour mettre dans votre jest ? (1 ou 2)");
			System.out.println("=> Commandes disponibles : 3-voirJest, 4-voirMain");
			return demanderChoixSeul(joueurs,joueurJouant);
		} else { // cas ou il reste d'autres joueurs avec des offres disponibles
			System.out.println("Joueur "+joueurJouant.getNom()+", les cartes disponibles sont : ");
			int i = 1 ; //initialisation
			for (Joueur j : joueurs) { // on affiche toutes les cartes disponibles (les cach�es ne sont pas d�voil�es)
				String lineSeparator = System.getProperty("line.separator");
				System.out.println(lineSeparator + "Cartes de l'offre du joueur : "+j.getNom());
				System.out.println(i +"- "+ (j.getVisibleCard()).afficher());
				i++;
				System.out.println(i +"- Carte cach�e");
				i++;
			}
			i--; //on corrige le dernier up de i pour en faire le compteur de carte disponibles
			System.out.println("Quelle carte voulez vous prendre dans votre jest ? (num�ro)");
			System.out.println("=> Commandes disponible : "+(i+1)+"- voirJest, "+(i+2)+"- voirMain");
			return demanderChoixPlusieurs(joueurs,joueurJouant,i);
		}
	}
	
	public Joueur demanderChoixSeul(ArrayList<Joueur> joueurs,Joueur joueurJouant) {
		Scanner scan = new Scanner(System.in);
		String choixJoueur= scan.next();
		Carte cartePrise; //la carte que l'on prend
		Joueur joueurAPrendre; //le joueur auquel on prend une carte) {
		try {
			Integer.parseInt(choixJoueur);
			if (Integer.parseInt(choixJoueur)==3) { //on verfie si le joueur fais une commande
				joueurJouant.afficherJest(); //comande Jest
			}
			if (Integer.parseInt(choixJoueur)==4) {
				joueurJouant.afficherMain(); //Commande main
			}
			if (Integer.parseInt(choixJoueur)!=1 && Integer.parseInt(choixJoueur)!=2) {
				System.out.println("/!\\ Vous devez choisir entre 1 et 2 (ou les commandes : 3-voirJest, 4-voirMain)");
				return demanderChoixSeul(joueurs,joueurJouant);
			} else {
				joueurAPrendre = joueurJouant; //dans ce cas ci, le joueur qui joue se prend une care a lui-m�me
				if (Integer.parseInt(choixJoueur)==2) { //la carte 2 est la carte cach�e
					cartePrise = joueurJouant.prendreCarte(true);
				}else {//la carte 1 est la carte visible
					cartePrise = joueurJouant.prendreCarte(false);
				}
				System.out.println("Vous avez pris la carte : "+ cartePrise.afficher());
				joueurJouant.ajouterDansJest(cartePrise);//on met la carte que l'on a pris dans notre jest
				return joueurAPrendre; //on renvois le joueur a qui on a pris une carte
			}
		}catch (Exception e) {
			System.out.println("/!\\ Vous devez choisir entre 1 et 2 (ou les commandes : 3-voirJest, 4-voirMain)");
			return demanderChoixSeul(joueurs,joueurJouant);
		}
	}
	
	public Joueur demanderChoixPlusieurs(ArrayList<Joueur> joueurs,Joueur joueurJouant, int i) {
		Scanner scan = new Scanner(System.in);
		String choixJoueur= scan.next();
		Carte cartePrise; //la carte que l'on prend
		Joueur joueurAPrendre; //le joueur auquel on prend une carte) {
		try {
			Integer.parseInt(choixJoueur);
			if (Integer.parseInt(choixJoueur)==i+1) { //on verfie si le joueur fais une commande
				joueurJouant.afficherJest(); //comande Jest
			}
			if (Integer.parseInt(choixJoueur)==i+2) {
				joueurJouant.afficherMain(); //Commande main
			}
			if (Integer.parseInt(choixJoueur) < 1 || Integer.parseInt(choixJoueur) >i) {
				System.out.println("/!\\ Vous devez choisir entre 1 et "+i+" (ou les commandes : "+(i+1)+"- voirJest, "+(i+2)+"- voirMain)");
				return demanderChoixPlusieurs(joueurs,joueurJouant,i);	
			}else {
				boolean cache;  // si le choix est pair c'est une carte cachee
				if (Integer.parseInt(choixJoueur)%2==0) { // test de parit�
					cache= true; // pair c'est une carte cachee
				}else { //impair et c'est une carte visible
					cache=false;
				}
				joueurAPrendre = joueurs.get((Integer.parseInt(choixJoueur)-1)/2); //un calcul simple pour definir a quel joueur on prend la carte
				cartePrise = joueurAPrendre.prendreCarte(cache); //on prend la carte choisie du joueurAPrendre
				System.out.println("Vous avez pris la carte : "+ cartePrise.afficher());
				joueurJouant.ajouterDansJest(cartePrise);//on met la carte que l'on a pris dans notre jest
				return joueurAPrendre; //on renvois le joueur a qui on a pris une carte
			}
		} catch (Exception e) {
			System.out.println(" /!\\ Vous devez choisir entre 1 et "+i+" (ou les commandes : "+(i+1)+"- voirJest, "+(i+2)+"- voirMain)");
			return demanderChoixPlusieurs(joueurs,joueurJouant,i);
		}
		
	}
	
	
	public void nullifierCarte (ArrayList<Joueur> joueurs,Joueur pireJ,Joueur bestJ) {
		System.out.println("Joueur "+pireJ.getNom()+ " vous recevez le troph�e bonus \"nullifieur\"");
		System.out.println("Veuillez choisir une carte du meilleur jest que vous voulez retirer");
		System.out.println("0- Aucunes");
		bestJ.afficherJest();
		demanderCarteANullifier(pireJ,bestJ); //on appelle la jolie fonction pour retirer la carte en question
		
	}
	private void demanderCarteANullifier(Joueur pireJ,Joueur bestJ) {
		Scanner scan = new Scanner(System.in);
		String choixJoueur= scan.next();
		int i =  bestJ.nombreCartesJest();
		Carte cartePrise; //la carte que l'on prend
		try { // en cas d'erreur de parseInt
			if (Integer.parseInt(choixJoueur) < 0 || Integer.parseInt(choixJoueur) > i) { //s'il est dans le bon intervalle
				System.out.println("/!\\ Vous devez choisir entre 0 et "+i);
				demanderCarteANullifier(pireJ,bestJ);
			}else {
				System.out.print(pireJ.getNom()+" ");
				if (Integer.parseInt(choixJoueur)!=0) {
					bestJ.jestRemoveCarte(Integer.parseInt(choixJoueur));
				}else {
					System.out.println("a choisi de ne nullifier aucune carte !");
				}
				
			}
		} catch (Exception e) {
			System.out.println("/!\\ Vous devez choisir entre 0 et "+i);
			demanderCarteANullifier(pireJ,bestJ);
		}
		
	}
}
	



