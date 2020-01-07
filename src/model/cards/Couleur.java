package model.cards;

/**
 * enumération sur les couleurs et différents types de carte
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
	 * donne la priorité de la carte par rapport aux autres couleurs
	 */
	private int priority;
	/**
	 * Le nom en String de la Couleur
	 */
	private String name;
	
	/**
	 * constructeur
	 * @param prio
	 * 		donne la priorité concernant la couleur voulue
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
	 * getteur de la priorité de la Couleur
	 * @return
	 */
	public int getPriority() {
		return this.priority;
	}
	/**
	 * getteur du nom de la carte 
	 * @return
	 */
	public String getName() {
		return this.name;
	}
}
