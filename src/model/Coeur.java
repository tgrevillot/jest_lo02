package model;

public class Coeur extends Carte {
	
	/**Constructeur des cartes Coeur 
	 * Paramètre : val - La valeur de la carte (cf. Attribut valeur)
	 * @param val
	 */
	public Coeur(int val) {
		super(val);
	}
	
	@Override
	public void opererScore() {
		// TODO Auto-generated method stub

	}
	public String afficher() {
		if (this.valeur==1) {
			return "AS de Coeur" ;
		} else {
			return (this.valeur+" de Coeur");
		}
	}
}
