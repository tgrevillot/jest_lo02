package model;

public class Trophee extends Carte {
	
	
	//TODO Voir de quelle manière on doit instancier trophées et conditions
	
	public Trophee(int val) {
		super(val);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void opererScore() {
		// TODO Auto-generated method stub

	}
	
	public String afficher() {
		//TODO a terminer quand on saura comment marche vraiment les trophées
		/*
		if (this.valeur==1) {
			return ("Trophee ...");
		} else {
			return (this.valeur+" ... ");
		}*/
		return "A IMPLEMENTER";
	}
}
