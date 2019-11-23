package model;

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
	public void opererScore() {
		// TODO Auto-generated method stub

	}
	
	public String afficher() {
		return super.afficher() + "Carreau";
	}
	
	

}
