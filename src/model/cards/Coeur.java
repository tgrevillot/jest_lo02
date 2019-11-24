package model.cards;

public class Coeur extends Carte {
	
	private final static int priority = 1;
	
	/**Constructeur des cartes Coeur 
	 * Paramètre : val - La valeur de la carte (cf. Attribut valeur)
	 * @param val
	 */
	public Coeur(int val) {
		super(val, priority);
	}
	
	@Override
	public int envoyerPoints() {
		return - this.getFaceValue();
	}
	
	@Override
	public String donnerCouleur() {
		return "Coeur";
	}
	
	public String afficher() {
		return super.afficher() + "Coeur";
	}
}
