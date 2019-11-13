package model;

public abstract class Carte {
	/**
	 * La valeur de la carte est le chiffre qui lui est associé
	 * Les valeurs vont de 1 à 5. Par défaut les As valent 1 et le Joker 4 
	 */
	private int valeur;
	
	/**
	 * Cet attribut indique si la carte est face cachée où non, i.e si n'importe quel joueur peut la voir une fois sur le plateau.
	 * Par défaut, toute carte est visible.
	 */
	private boolean cache;
	
	
	/**Constructeur des Carte
	 * Paramètre : val - La valeur de la carte (cf. Attribut valeur)
	 * @param val
	 * 		Expliquer ici aussi à quoi sert le paramètre
	 */
	public Carte(int val) {
		this.valeur=val;
		this.cache= false;
	}
	
	public abstract void opererScore();

}
