package model.cards;

/**
 * enumeration sur les couleurs et differents types de carte
 * Utile pour avoir un ordre de priorite sur celles-ci et pouvoir faire des traitements relatifs a certaines couleurs en particulier
 * @author moras
 *
 */
public enum Couleur {
	JOKER(0, "Joker"),
	COEUR(1, "Coeur"),
	CARREAU(2, "Carreau"),
	TREFLE(3, "Trefle"),
	PIQUE(4, "Pique"),
	EXTENSION(0,"Extension"); //pour les cartes bonus de la fin
	/**
	 * donne la priorite de la carte par rapport aux autres couleurs
	 */
	private int priority;
	/**
	 * Le nom en String de la Couleur
	 */
	private String name;
	
	/**
	 * constructeur
	 * @param prio
	 * 		donne la priorite concernant la couleur voulue
	 * @param name
	 * 		le nom de la Couleur 
	 */
	private Couleur(int prio, String name) {
		if(prio < 0)
			prio = 0;
		if(name.equals("") || name.contentEquals(" "))
			name = "Unknow";
		
		this.priority = prio;
		this.name = name;
	}
	
	/**
	 * getteur de la priorite de la Couleur
	 * @return int
	 */
	public int getPriority() {
		return this.priority;
	}
	/**
	 * getteur du nom de la carte 
	 * @return String
	 */
	public String getName() {
		return this.name;
	}
}
