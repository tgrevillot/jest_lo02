package model;

import java.util.ArrayList;
import java.util.HashSet;

public class Joueur implements Compteur {
	
	private boolean aJoue;
	private ArrayList<Carte> main;
	private HashSet<Carte> jest;
	private String nom;
	
	public Joueur() {
		
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
