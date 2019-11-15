package model;

public class Trefle extends Carte {
	/**Constructeur des cartes Trefle
	 * Paramètre : val - La valeur de la carte (cf. Attribut valeur)
	 * @param val
	 */
	public Trefle(int val) {
		super(val);
	}
	
	@Override
	public void opererScore() {
		
	}
	public String afficher() {
		if (this.valeur==1) {
			return "AS de Trefle" ;
		} else {
			return (this.valeur+" de Trefle");
		}
	}
}
