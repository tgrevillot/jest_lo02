package model.cards;

public enum Couleur {
	JOKER(0, "Joker"),
	COEUR(1, "Coeur"),
	CARREAU(2, "Carreau"),
	TREFLE(3, "Trefle"),
	PIQUE(4, "Pique"),
	EXTENSION(0,"Extension"); //pour les cartes bonus de la fin
	
	private int priority;
	private String name;
	
	private Couleur(int prio, String name) {
		if(prio < 0)
			prio = 0;
		if(name.equals("") || name.equals(" "))
			name = "Unknow";
		
		this.priority = prio;
		this.name = name;
	}
	
	public int getPriority() {
		return this.priority;
	}
	
	public String getName() {
		return this.name;
	}
}
