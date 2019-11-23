package model;

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
	public void opererScore() {
		
	}
	
	
	public String afficher() {
		return super.afficher() + "Pique";
	}
}
