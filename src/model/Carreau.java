package model;

public class Carreau extends Carte {
	
	/**Constructeur des cartes Carreau 
	 * Paramètre : val - La valeur de la carte (cf. Attribut valeur)
	 * @param val
	 */
	public Carreau(int val) {
		super(val);
	}
	
	
	@Override
	public void opererScore() {
		// TODO Auto-generated method stub

	}
	
	public String afficher() {
		if (this.valeur==1) {
			return "AS de Carreau" ;
		} else {
			return (this.valeur+" de Carreau");
		}
	}
	
	

}
