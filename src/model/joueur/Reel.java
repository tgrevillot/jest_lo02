package model.joueur;

import java.util.ArrayList;
import java.util.Scanner;

import model.cards.Carte;

public class Reel implements IAStrategie {

	
	public void offrir(ArrayList<Carte> main, String pseudo) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Joueur "+pseudo+" voici votre main : ");
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
	
	public Carte choisir(ArrayList<Joueur> joueurs,String pseudo) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Joueur "+pseudo+", les cartes disponibles sont : ");
		int i = 1 ;
		for (Joueur j : joueurs) {
			System.out.println(i +"- "+ (j.getVisibleCard()).afficher());
			i++;
			System.out.println(i +"- Carte cachée");
			i++;
		}
		System.out.println("Quelle carte voulez vousprendre dans votre jest ? (numéro)");
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
		int JoueurAPrendre = (choixJoueur-1)/2;
		Carte cartePrise = joueurs.get(JoueurAPrendre).prendreCarte(cache);
		System.out.println("Vous avez pris la carte : "+ cartePrise.afficher());
		return cartePrise; //on recupere la carte visible du joueur choisi
		
	}
}
