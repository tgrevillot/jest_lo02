package model.cards;
/**
 * La classe qui permet de faire toutes les cartes du jeu
 * @author moras
 *
 */
public class Carte {
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
	 * Attribut permettant de déterminer l'ordre de la couleur de la carte.
	 * Utile lors de la selection de la carte lorsque les face values ont la meme valeur 
	 */
	private int priority;
	
	/**
	 * Couleur de la carte : Pique, Trefle, Carreau, Coeur, Joker , EXTENSION
	 */
	private Couleur couleur;
	
	/**Constructeur des Carte
	 * Paramètre : val - La valeur de la carte (cf. Attribut valeur)
	 * @param val
	 * 		Ce paramètre permet de donner la valeu souhaitée a la carte 
	 */
	public Carte(int val, Couleur couleur) {
		this.valeur=val;
		this.cache= false;
		this.couleur = couleur;
		
		this.priority = couleur.getPriority();
	}
	/**
	 * Un autre constructeur pour créer une copie d'une carte existante 
	 * @param c
	 */
	public Carte(Carte c) {
		this.valeur = c.getFaceValue();
		this.cache = false;
		this.couleur = c.getCouleur();
		this.priority = this.couleur.getPriority();
	}
	/**
	 * méthode qui retourne true si les deux cartes ont les mêmes attributs
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
	 * donne la couleur de la carte selon l'énumeration
	 * @return Couleur
	 */
	public Couleur getCouleur() {
		return this.couleur;
	}
	
	/**
	 * Affiche les cartes sous une jolie forme
	 * @return
	 * Cela renvois un String expliquant en Français quelle est la carte en question
	 */
	public String afficher() {
		String str = "";
		
		//On affiche le Joker différemment, sans valeur.
		if(this.couleur == Couleur.JOKER)
			str += "Joker";
		else if(this.valeur == 1)
			str += "As de " + this.couleur.getName();
		else
			str += String.valueOf(this.valeur) + " de "  + this.couleur.getName();
		//Si la carte est cachée, on indique qu'elle l'est dans le toString
		if(this.cache)
			str += " [cache] ";
		
		return str;
	}
	
	/**
	 * méthode qui change la valeur d'un as de 1 à 5 
	 */
	public void changeAsFaceValue() {
		//Dans le cas, où l'as est tout seul, il peut valoir 5 points.
		//C'est pourquoi, nous allons vérifier qu'il s'agisse bien d'un As avant de changer effectivement 
		//Sa faceValue
		if(this.valeur == 1)
			this.valeur = 5;
	}
	
	
	/**
	 * Setter pour cacher une carte
	 */
	public void cacherCarte() {
		this.cache = true;
	}
	
	/**
	 * Setter pour "dé-cacher" une carte
	 *  
	 */
	public void antiCacherCarte() {
		this.cache = false;
	}
	/**
	 * donne le staut de la carte (correspondant a sa visibilité)
	 */
	public boolean isCacher() {
		return this.cache;
	}
	/**
	 * retrourne la valeur d'une carte
	 * @return
	 */
	public int getFaceValue() {
		return this.valeur;
	}
	/**
	 * renvois la priorité de la carte
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
