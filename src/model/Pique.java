package model;

public class Pique extends Carte {
	/**Constructeur des cartes Pique
	 * Paramètre : val - La valeur de la carte (cf. Attribut valeur)
	 * @param val
	 */
	public Pique(int val) {
		super(val);
	}

	@Override
	public void opererScore() {
		
	}
	
	
	public String afficher() {
		if (this.valeur==1) {
			return "AS de Pique" ;
		} else {
			return (this.valeur+" de Pique");
		}
	}
}
