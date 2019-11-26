package model.cards;

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
	 * Il sera a null pour toute carte n'�tant pas un troph�e
	 */
	private Condition condition;
	
	/**
	 * Attribut permettant de d�terminer l'ordre de la couleur de la carte.
	 * Utile lors de la selection de la carte lorsque les face values ont la meme valeur 
	 */
	private int priority;
	
	
	/**Constructeur des Carte
	 * Param�tre : val - La valeur de la carte (cf. Attribut valeur)
	 * @param val
	 * 		Ce param�tre permet de donner la valeu souhait�e a la carte 
	 */
	public Carte(int val, int ordre) {
		this.valeur=val;
		this.cache= false;
		if(ordre < 1)
			ordre = 1;
		this.priority = ordre;
	}
	
	public abstract int envoyerPoints();
	
	public abstract String donnerCouleur();
	/**
	 * afficher sert a afficher les cartes sous une jolie forme
	 * @return
	 * Cela renvois un String expliquant en Fran�ais quelle est la carte en question
	 */
	public String afficher() {
		String str = "";
		if(this.valeur == 1)
			str += "As de ";
		else
			str += String.valueOf(this.valeur) + " de ";
		return str;
	}
	
	
	/**
	 * Setter pour cacher une carte
	 */
	public void cacherCarte() {
		this.cache = true;
	}
	
	/**
	 * Setter pour "d�-cacher" une carte
	 *  
	 */
	public void antiCacherCarte() {
		this.cache = false;
	}
	
	public boolean isCacher() {
		return this.cache;
	}
	
	public int getFaceValue() {
		return this.valeur;
	}
	
	public int getOrdre() {
		return priority;
	}

	public Condition getCondition() {
		return this.condition;
	}
	public String toString() {
		return afficher();
	}
}
