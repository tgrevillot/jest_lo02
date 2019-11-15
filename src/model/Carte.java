package model;

public abstract class Carte {
	/**
	 * La valeur de la carte est le chiffre qui lui est associ�
	 * Les valeurs vont de 1 � 5. Par d�faut les As valent 1 et le Joker 4 
	 */
	private int valeur;
	
	/**
	 * Cet attribut indique si la carte est face cach�e o� non, i.e si n'importe quel joueur peut la voir une fois sur le plateau.
	 * Par d�faut, toute carte est visible.
	 */
	private boolean cache;
	
	/**
	 * C'est l'attribut qui indique quelle est la condition pour obtenir cette carte
	 * Il sera a null pour toute carte n'�tant pas un troph� 
	 */
	private Condition condition;
	
	
	
	/**Constructeur des Carte
	 * Param�tre : val - La valeur de la carte (cf. Attribut valeur)
	 * @param val
	 * 		Ce param�tre permet de donner la valeu souhait�e a la carte 
	 */
	public Carte(int val) {
		this.valeur=val;
		this.cache= false;
	}
	
	public abstract void opererScore();

}
