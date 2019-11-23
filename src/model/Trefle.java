package model;

public class Trefle extends Carte {
	
	private final static int priority = 3;
	
	/**Constructeur des cartes Trefle
	 * Paramètre : val - La valeur de la carte (cf. Attribut valeur)
	 * @param val
	 */
	public Trefle(int val) {
		super(val, priority);
	}
	
	@Override
	public void opererScore() {
		
	}
	public String afficher() {
		return super.afficher() + "Trefle";
	}
}
