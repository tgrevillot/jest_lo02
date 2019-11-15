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
	
	/**
	 * C'est l'attribut qui indique quelle est la condition pour obtenir cette carte
	 * Il sera a null pour toute carte n'étant pas un trophé 
	 */
	private Condition condition;
	
	
	
	/**Constructeur des Carte
	 * Paramètre : val - La valeur de la carte (cf. Attribut valeur)
	 * @param val
	 * 		Ce paramètre permet de donner la valeu souhaitée a la carte 
	 */
	public Carte(int val) {
		this.valeur=val;
		this.cache= false;
	}
	
	public abstract void opererScore();

}
