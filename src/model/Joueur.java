package model;

import java.util.ArrayList;
import java.util.HashSet;

public class Joueur implements Compteur {
	/**indique si le joueur a d�ja jou� pendant ce tour
	 * par d�faut a false 
	 */
	private boolean aJoue;
	
	/**La liste des cartes pr�sentes dans la main du joueur pendant un tour pr�cis
	 * Par d�faut vide. Contient 0, 1 ou 2 cartes selon le moment.
	 */
	private ArrayList<Carte> main;
	
	/**Correspond au jest du joueur, un ensemble de cartes qui lui sont propre
	 * Selon les r�gles officielles, il peut contenir entre 0 et 7 cartes selon le type et l'avanc�e de la partie.
	 */
	private HashSet<Carte> jest;
	
	
	//TODO la strat�gie de quel type ????
	
	
	/** Cha�ne de caract�re repr�sentant le pseudonyme du joueur affich� en jeu.
	 * Les Ia seront nomm�es de la sorte : strategie_id (o� l'id est unique de 0 jusqu'� 3)
	 */
	private String nom;
	
	public Joueur(String pseudo) {
		this.aJoue=false;
		this.main = new ArrayList<Carte>();
		this.jest = new HashSet<Carte>();
		this.nom = pseudo;
	}
	
	@Override
	public void compterPointsCarte() {
		throw new Error("A COMPLETER");
	}
	
	public void prendreOffre(Joueur j) {
		throw new Error("A COMPLETER");
	}
	
	public void montrer2emeCarte() {
		throw new Error("A COMPLETER");
	}
	
	public void choisirFaceCachee() {
		throw new Error("A COMPLETER");
	}
	
	public HashSet<Carte> consulterJest() {
		throw new Error("A COMPLETER");
	}
	
	public void ajouterCartesRestantesJest() {
		throw new Error("A COMPLETER");
	}
	
	public int compterScore() {
		throw new Error("A COMPLETER");
	}
}
