package model.cards;

public class Trefle extends Carte {
	
	private final static int priority = 3;
	
	/**Constructeur des cartes Trefle
	 * Param�tre : val - La valeur de la carte (cf. Attribut valeur)
	 * @param val
	 */
	public Trefle(int val) {
		super(val, priority);
	}
	
	@Override
	public int envoyerPoints() {
		return this.getFaceValue();
	}
	
	@Override
	public String donnerCouleur() {
		return "Trefle";
	}
	
	public String afficher() {
		if (this.isCacher()) {
			return super.afficher() + "Trefle [cach�e]";
		}else {
			return super.afficher() + "Trefle";
		}
		
	}
}
