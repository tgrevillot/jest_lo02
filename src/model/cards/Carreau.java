package model.cards;

public class Carreau extends Carte {
	
	private final static int priority = 2;
	
	/**Constructeur des cartes Carreau 
	 * Paramètre : val - La valeur de la carte (cf. Attribut valeur)
	 * @param val
	 */
	public Carreau(int val) {
		super(val, priority);
	}
	
	
	@Override
	public int envoyerPoints() {
		return - this.getFaceValue();
	}
	
	public String afficher() {
		return super.afficher() + "Carreau";
	}
	
	@Override
	public String donnerCouleur() {
		return "Carreau";
	}

}
