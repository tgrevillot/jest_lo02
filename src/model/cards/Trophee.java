package model.cards;

/**
 * la classe correspondant aux trophées du jeu 
 * @author moras
 *
 */
public class Trophee {
	/**
	 * La condition a remplir pour obtenir le trophee
	 */
	private Condition condition;
	
	/**
	 * carte est la carte associée a la condition
	 * C'est ce que l'on renverra dans l'attribution des trophees
	 */
	private Carte carte;
	
	/**
	 * le constructeur de Trophee
	 * @param carte
	 * 		la carte a utiliser pour les parametres et la condition
	 */
	public Trophee(Carte carte) {
		
		this.carte=carte;
		switch(this.carte.getCouleur()) {
			case COEUR:
				this.condition = Condition.detenteurJoker;
				break;
				
			case CARREAU:
				this.conditionCarreau();
				break;
				
			case TREFLE:
				this.conditionTrefle();
				break;
				
			case PIQUE:
				this.conditionPique();
				break;
				
			case JOKER:
				this.condition=Condition.bestJest;
				break;
				
			case EXTENSION : 
				this.conditionVariante();
				break;
				
			default:
				throw new Error("La carte en argument n'est pas instanciée en un sous-type de carte");
		}

	}
	
	/**
	 * la méthode qui gère les conditions relatives aux cartes de carreau
	 */
	private void conditionCarreau() {
		switch(this.carte.getFaceValue()) {
		case 1 :
			this.condition= Condition.plusDeCartes4;
		case 2 :
			this.condition= Condition.plusGrandCarreau;
		case 3 :
			this.condition= Condition.plusPetitCarreau;
		case 4 :
			this.condition= Condition.bestJestWithoutJoker;
		}
	}
	/**
	 * la méthode qui gère les conditions relatives aux cartes de trefle
	 */
	private void conditionTrefle() {
		switch(this.carte.getFaceValue()) {
		case 1 :
			this.condition= Condition.plusGrandPique;
		case 2 :
			this.condition= Condition.plusPetitCoeur;
		case 3 :
			this.condition= Condition.plusGrandCoeur;
		case 4 :
			this.condition= Condition.plusPetitPique;
		}
		
	}
	/**
	 * la méthode qui gère les conditions relatives aux cartes de pique
	 */
	private void conditionPique() {
		switch(this.carte.getFaceValue()) {
		case 1 :
			this.condition= Condition.plusGrandTrefle;
		case 2 :
			this.condition= Condition.plusDeCartes3;
		case 3 :
			this.condition= Condition.plusDeCartes2;
		case 4 :
			this.condition= Condition.plusPetitTrefle;
		}
		
	}
	/**
	 * la méthode qui gère les conditions relatives aux cartes Variantes 
	 */
	private void conditionVariante() {
		switch(this.carte.getFaceValue()) {
		case 0 :
			this.condition= Condition.variantePireJest;
		}
	}

	/**
	 * getteur sur la condition d'attribution du trophée
	 * @return Condition
	 */
	public Condition getCondition() {
		return this.condition;
	}
	/**
	 * getteur sur la carte utilisée pour faire le trophée
	 * @return
	 */
	public Carte getCarte() {
		return this.carte;
	}
	/**
	 * methode de conversion du trophée en un strin lui correspondant
	 * @return String
	 */
	public String afficher() {
		return "Trophee de type : " +this.carte.afficher() + " ; condition : " +this.condition;
	}
}
