package model.cards;

public class Joker extends Carte {
	
	private static final int priority = 0;
	
	/**Constructeur de la carte Joker
	 * Param�tre : aucun
	 *
	 */
	public Joker() {
		//4 comme la seule valeur a part 0 que peut prendre le joker (peut �tre remplac� dans une fonction)
		super(4, priority); 
	}

	@Override
	public int envoyerPoints() {
		return 0;
	}
	
	@Override
	public String donnerCouleur() {
		return "Joker";
	}

	@Override
	public String afficher() {
	if (this.isCacher()) {
		return "Joker [cach�]";
	}else {
		return "Joker";
	}
	}

}
