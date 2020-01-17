package model.cards;
/**
 * La classe qui permet de faire toutes les cartes du jeu
 * @author moras
 *
 */
public class Carte {
	/**
	 * La valeur de la carte est le chiffre qui lui est associe
	 * Les valeurs vont de 1 a 5. Par defaut les As valent 1 et le Joker 4 
	 */
	private int valeur;
	
	/**
	 * Cet attribut indique si la carte est face cachee ou non, i.e si n'importe quel joueur peut la voir une fois sur le plateau.
	 * Par defaut, toute carte est visible.
	 */
	private boolean cache;
	
	/**
	 * Attribut permettant de determiner l'ordre de la couleur de la carte.
	 * Utile lors de la selection de la carte lorsque les face values ont la meme valeur 
	 */
	private int priority;
	
	/**
	 * Couleur de la carte : Pique, Trefle, Carreau, Coeur, Joker , EXTENSION
	 */
	private Couleur couleur;
	
	/**Constructeur des Carte
	 * Parametre : val - La valeur de la carte (cf. Attribut valeur)
	 * @param val
	 * 		Ce parametre permet de donner la valeu souhaitee a la carte 
	 */
	public Carte(int val, Couleur couleur) {
		this.valeur=val;
		this.cache= false;
		this.couleur = couleur;
		
		this.priority = couleur.getPriority();
	}
	/**
	 * Un autre constructeur pour creer une copie d'une carte existante 
	 * @param c
	 * 		la carte a copier
	 */
	public Carte(Carte c) {
		this.valeur = c.getFaceValue();
		this.cache = false;
		this.couleur = c.getCouleur();
		this.priority = this.couleur.getPriority();
	}
	/**
	 * methode qui retourne true si les deux cartes ont les memes attributs
	 * @param c
	 * 		La carte a verifier 
	 * @return boolean 
	 */
	@Override
	public boolean equals(Object c) {
		if(c != null) 
			if(c instanceof Carte) {
				return ((Carte) c).getFaceValue() == this.valeur 
						&& ((Carte) c).getCouleur() == this.couleur;
			}
		return true;
	}
	/**
	 * donne la valeur de la carte
	 * @return int
	 */
	public int envoyerPoints() {
		return this.valeur;
	}
	/**
	 * donne un string correspondant a la couleur de la carte
	 * @return String
	 */
	public String donnerCouleurString() {
		return couleur.getName();
	}
	/**
	 * donne la couleur de la carte selon l'enumeration
	 * @return Couleur
	 */
	public Couleur getCouleur() {
		return this.couleur;
	}
	
	/**
	 * Affiche les cartes sous une jolie forme
	 * Si elle est cachee on ne voit que " [cache] "
	 * @return
	 * Cela renvois un String expliquant en Français quelle est la carte en question
	 */
	public String afficher() {
		String str = "";
		
		//On affiche le Joker differemment, sans valeur.
		if(this.couleur == Couleur.JOKER)
			str += "Joker";
		else if(this.valeur == 1)
			str += "As de " + this.couleur.getName();
		else
			str += String.valueOf(this.valeur) + " de "  + this.couleur.getName();
		//Si la carte est cachee, on indique qu'elle l'est dans le toString
		if(this.cache)
			str += " [cache] ";
		
		return str;
	}
	
	/**
	 * methode qui change la valeur d'un as de 1 a 5 
	 */
	public void changeAsFaceValue() {
		//Dans le cas, ou l'as est tout seul, il peut valoir 5 points.
		//C'est pourquoi, nous allons verifier qu'il s'agisse bien d'un As avant de changer effectivement 
		//Sa faceValue
		if(this.valeur == 1)
			this.valeur = 5;
	}
	
	
	/**
	 * Setter pour cacher une carte
	 * Influe directment sur son ToString 
	 */
	public void cacherCarte() {
		this.cache = true;
	}
	
	/**
	 * Setter pour "de-cacher" une carte
	 *  
	 */
	public void antiCacherCarte() {
		this.cache = false;
	}
	/**
	 * donne le staut de la carte (correspondant a sa visibilite)
	 */
	public boolean isCacher() {
		return this.cache;
	}
	/**
	 * retrourne la valeur d'une carte
	 * @return int
	 */
	public int getFaceValue() {
		return this.valeur;
	}
	/**
	 * renvois la priorite de la carte
	 * @return int
	 */
	public int getOrdre() {
		return priority;
	}

	/**
	 * affiche le String correspondant a la carte 
	 */
	public String toString() {
		return afficher();
	}
}
