package model.cards;

public class Pique extends Carte {
	
	private final static int priority = 4;
	
	/**Constructeur des cartes Pique
	 * Paramètre : val - La valeur de la carte (cf. Attribut valeur)
	 * @param val
	 */
	public Pique(int val) {
		super(val, priority);
	}

	@Override
	public int envoyerPoints() {
		return this.getFaceValue();
	}
	
	public String donnerCouleur() {
		return "Pique";
	}
	
	public String afficher() {
		return super.afficher() + "Pique";
	}
}
