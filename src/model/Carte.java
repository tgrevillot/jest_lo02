package model;

public abstract class Carte {
	/**
	 * La valeur de la carte est le chiffre qui lui est associ�
	 * Les valeurs vont de 1 � 5. Par d�faut les As valent 1 et le Joker 4 
	 */
	protected int valeur;
	
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
	/**
	 * afficher sert a afficher les cartes sous une jolie forme
	 * TODO rajouter un moyen de differencier si elle est cach�e ou pas
	 * @return
	 * Cela renvois un String expliquant en Fran�ais quelle est la carte en question
	 */
	public abstract String afficher();
	
	
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
}
