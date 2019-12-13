package model.cards;

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
	plusDeCartes4("maximumDeCartes4");	
	private String nomCondi;
	
	private Condition(String nom) {
		this.nomCondi = nom;
	}
	
	public String toString() {
		return this.nomCondi;
	}
}