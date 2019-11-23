package model.cards;

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
	
	
	public Trophee(Carte carte) {
		
		this.carte=carte;
		if (this.carte instanceof Coeur) {
			this.condition = Condition.detenteurJoker;
		} else {
			if (this.carte instanceof Carreau) {
				this.conditionCarreau();
			} else {
				if (this.carte instanceof Trefle) {
					this.conditionTrefle();
				} else {
					if (this.carte instanceof Pique) {
						this.conditionPique();
					} else {
						if (this.carte instanceof Joker) {
							this.condition=Condition.bestJest;
						} else {
							throw new Error("La carte en argument n'est pas instanciée en un sous-type de carte");
						}
					}
				}
			}
		}
	}
	
	
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

	
	public Condition getCondition() {
		return this.condition;
	}
	
	public String afficher() {
		return "Trophee de type : " +this.carte.afficher() + " et condition : " +this.condition;
	}
}
