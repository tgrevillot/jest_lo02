package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Jeu {
	
	/** Le deck est l'ensemble des cartes jouables du jeu. Il comporte 17 cartes diff�rentes au d�part. 
	 * Il contient un ensemble vide, partiel ou total des cartes disponibles sans redondance.
	 */
	private LinkedList<Carte> deck;
	
	/** joueurs est la liste des joueurs pr�sents dans la partie.
	 * Cette liste contient 3 ou 4 Joueurs 
	 */
	private ArrayList<Joueur> joueurs;
	
	
	private Trophee[] trophees;
	
	
	public Jeu(int nbJoueurs,int nbHumains, int difficulte, String[] tabPseudos) {
		//on cr��e le deck de cartes
		this.deck = new LinkedList<Carte>();
		
		this.deck.add(new Joker());
		for (int i = 1; i<5; i++) {
			this.deck.add(new Carreau(i));
			this.deck.add(new Coeur(i));
			this.deck.add(new Pique(i));
			this.deck.add(new Trefle(i));
		}
		//on m�lange le deck
		Collections.shuffle(this.deck);
		
		//On initialise maintenant les joueurs 
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		//on ajoute nbJoueurs joueurs
		// on ajoute d'abbord nbHumains Humains
		for (int j=0 ; j<nbHumains ; j++) {
			joueurs.add(new Joueur(tabPseudos[j],0));
		}
		//les joueurs restants sont des IA
		for (int k=0 ; k<(nbJoueurs-nbHumains) ; k++) {
			joueurs.add(new Joueur(""+k,difficulte));
		}
		this.joueurs= joueurs;
		//on choisi le(s) troph�(s)
		
		/*                  	      WIP 
		 * 		TODO Voir de quelle mani�re on doit instancier troph�es et conditions
		 * 
		Carte carteTrophee1 =  deck.pop();
		// s'il y a 4 joueurs on rajoute un deuxi�eme troph�e
		if (nbJoueurs==3) {
			
		}
		*/
		
	}
	
	private void attribuerTrophee(Trophee t) {
		throw new Error("A COMPLETER");
	}
	
	private int compterPoints() {
		throw new Error("A COMPLETER");
	}
	
	private void determinerTour(Joueur j) {
		throw new Error("A COMPLETER");
	}
	
	private Joueur determinerPlusGrand() {
		throw new Error("A COMPLETER");
	}

	private Joueur determinerGagnant() {
		throw new Error("A COMPLETER");
	}
	
	public void faireUnTour(LinkedList<Carte> cartesRestantes) {
		throw new Error("A COMPLETER");
	}
	
	public LinkedList<Carte> recupererCartesRestantes() {
		throw new Error("A COMPLETER");
	}
	
	public static void main(String[] args) {
		//On Lance la partie 
		Scanner scan = new Scanner(System.in);
		String lineSeparator = System.getProperty("line.separator");
		

		System.out.println("Bienvenue dans le Jest !");
		System.out.println("Suivez les instructions suivantes pour configurer la partie et pouvoir jouer" + lineSeparator);
		
		//choix du nombre de joueurs	
			System.out.println("Veuillez entrer le nombre de joueurs (3 ou 4) : ");
			int nbJoueurs = scan.nextInt();
			//on v�rifie que le nombre entr� est bien 3 ou 4 
			while ( (nbJoueurs != 4) && (nbJoueurs != 3)  ){
				System.out.println("Entr�e incorrecte, vous devez choisir 3 ou 4 : ");
				nbJoueurs = scan.nextInt();
			}
			System.out.println("Vous avez choisi "+nbJoueurs+" joueurs " + lineSeparator);
		
		//choix du nombre de joueur humains
			System.out.println("Veuillez entrer le nombre de joueurs humains (entre 1 et "+nbJoueurs+" inclus) : ");
			int nbHumains = scan.nextInt();
			//on v�rifie que le nombre entr� est bien compatible 
			while ( (nbHumains < 1) || (nbHumains > nbJoueurs)  ){
				System.out.println("Entr�e incorrecte, vous devez choisir entre 1 et "+nbJoueurs+" inclus : ");
				nbHumains = scan.nextInt();
			}
			System.out.println("Il y aura donc "+nbHumains+" joueurs humains " + lineSeparator);
		
		int difficulte = 0;
		//Choix de la difficult� 	
			//seulement si tous les joueurs ne sont pas humains
			if (nbJoueurs > nbHumains) {
				System.out.println("Veuillez choisir le niveau des IA (1 ou 2): ");
				difficulte = scan.nextInt();
				//on v�rifie que le nombre entr� est bien compatible 
				while ( (difficulte < 1) || (difficulte > 2)  ){
					System.out.println("Entr�e incorrecte, vous devez choisir entre 1 et 2 ");
					difficulte = scan.nextInt();
				}
				System.out.println("Le niveau est donc regl� sur "+difficulte+ lineSeparator);
			} else {
				System.out.println("Il n'y a pas d'IA dans cette partie");
			}
		//On demande le(s) pseudo(s) a l'utilisateur 
			String[] tableauPseudos = {"J1","J2","J3","J4"};
			//on regarde le nombre d'humains
			if (nbHumains==1) {
				System.out.println("Vous allez maintenant devoir entrer votre pseudo");
			} else {
				System.out.println("Vous allez maintenant devoir entrer les pseudos des joueurs humains");
			}
			//on demande nbHumains pseudos
			for (int i=0; i<nbHumains;i++) {
				System.out.println("Veuillez entrer le pseudo du joueur "+ (i+1));
				tableauPseudos[i]= scan.next();
			}
		
		new Jeu(nbJoueurs,nbHumains,difficulte, tableauPseudos);
		scan.close();
	}
	
	
	
	
}
