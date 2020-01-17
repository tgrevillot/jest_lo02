package model.cards;
/**
 * enumeration de toutes les condition d'obtention d'un trophee
 * @author moras
 *
 */
public enum Condition {
	detenteurJoker("DetenteurJoker"),
	bestJest("BestJest"),
	plusPetitPique("plusPetitPique"),
	plusPetitTrefle("plusPetitTrefle"),
	plusPetitCarreau("plusPetitCarreau"),
	plusPetitCoeur("plusPetitCoeur"),
	plusGrandPique("plusGrandPique"),
	plusGrandTrefle("plusGrandTrefle"),
	plusGrandCarreau("plusGrandCarreau"),
	plusGrandCoeur("plusGrandCoeur"),
	bestJestWithoutJoker("bestJestWithoutJoker"),
	plusDeCartes2("maximumDeCartes2"),
	plusDeCartes3("maximumDeCartes3"),
	plusDeCartes4("maximumDeCartes4"),
	//les regles pour les extensions / regles en plus 
	variantePireJest("pireJest");
	
	/**
	 * l'attribut du nom de la condition
	 */
	private String nomCondi;
	
	/**
	 * le constructeur
	 * @param nom
	 * 		le nom de la condition voulue
	 */
	private Condition(String nom) {
		this.nomCondi = nom;
	}
	/**
	 * transforme la condition en un String correspondant
	 * @return String
	 */
	public String toString() {
		return this.nomCondi;
	}
}