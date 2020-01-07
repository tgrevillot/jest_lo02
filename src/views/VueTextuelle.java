package views;

import java.util.Observable;
import java.util.Observer;

import controllers.ControllerText;
import model.PartieJest;
/**
 * Vue textuelle
 */
public class VueTextuelle implements Observer, Runnable {
	
	private ControllerText controller;
	private PartieJest model;
	private TextViewStep avancement;
	
	public VueTextuelle(ControllerText ct, PartieJest model) {
		if(ct == null)
			throw new Error("Le controllerText fournie est NULL");
		if(model == null)
			throw new Error("Le model fournie est NULL");
		this.controller = ct;
		this.avancement = TextViewStep.NBJOUEURS;
		this.model = model;
		//A VOIR PAR LA SUITE
		new Thread(this).start();
	}
	
	public void update(Observable obs, Object arg) {
		int nbJoueurs;
		int nbJoueursReels;
		switch(this.avancement) {
			case NBJOUEURS:
				//Initialisation du jeu, on affiche le d�but de partie
				System.out.println("Bienvenue dans le Jest !");
				System.out.println("Suivez les instructions suivantes pour configurer la partie et pouvoir jouer "+"\n");
				//choix du nombre de joueurs	
				System.out.println("Veuillez entrer le nombre de joueurs (3 ou 4) : ");
				
				nbJoueurs = initNBJoueurs();
				this.model.setNbJoueur(nbJoueurs);
				
				//On fait avancer l'initialisation
				this.avancement = TextViewStep.NBJOUEURSREELS;
				break;
			case NBJOUEURSREELS:
				//On demande le nombre de joueurs reels
				nbJoueurs = this.model.getNbJoueurs();
				System.out.println("Veuillez entrer le nombre de joueurs humains (entre 1 et "+nbJoueurs+" inclus) : ");
				nbJoueursReels = initNBHumains(nbJoueurs);
				
				this.model.setNbJoueurReel(nbJoueursReels);
				
				//On fait avancer l'initialisation
				this.avancement = TextViewStep.DIFFICULTE;
				break;
			case DIFFICULTE:
				//Choix de la difficult� 
				nbJoueurs = this.model.getNbJoueurs();
				nbJoueursReels = this.model.getNbJoueursReels();
				System.out.println("Veuillez entrer la difficult� souhait� : \n1 - Normal \n2 - Avanc�");
				int difficulte = initDifficulte(nbJoueurs, nbJoueursReels);
				this.model.setDifficulte(difficulte);
				//On fait avancer l'initialisation
				this.avancement = TextViewStep.PSEUDO;
				break;
			case PSEUDO:
				//On rel�ve les diff�rents pseudo et on cr�e les joueurs dans le mod�le.
				nbJoueursReels = this.model.getNbJoueursReels();				
				String[] tabPseudos = this.getTabPseudos(nbJoueursReels);
				this.model.ajouterJoueurs(tabPseudos);
				
				//On fait avancer l'initialisation
				this.avancement = TextViewStep.TROPHEE;
				break;
			case TROPHEE:
				//Choix des troph�es add
				System.out.println("Veuillez choisir le troph�e additionnel que vous voulez utiliser : \n0- Aucun \n1- Troph�e Nullifieur");
				int regles = initRegles();
				this.model.setRegle(regles);
				
				//On fait avancer l'initialisation
				this.avancement = TextViewStep.CONDIVICTOIRE;
				break;
			case CONDIVICTOIRE:
				//Choix des conditions de victoires add
				System.out.println("Veuillez choisir la r�gle additionnelle que vous voulez utiliser : \n0- Aucune \n1- A Coeur Ouvert");
				int conditionsVictoire = initCondiVictoires();
				this.model.setConditionsVictoire(conditionsVictoire);
				
				//On fait avancer l'initialisation
				this.avancement = TextViewStep.TOURDEJEU;
				break; 
			case TOURDEJEU:
				//TODO A COMPLETER
				break;
			case DETERGAGNANT:
				break;
		}
	}
	
	/**
	 * sous-m�thode pour determiner les pseudos des joueurs humains une interface textuelle
	 *  @return String[] tableauPseudos
	 *  	les pseudos des joueurs humains 
	 */

	private String[] getTabPseudos(int nbHumains) {
		//On demande le(s) pseudo(s) a l'utilisateur 
				String[] tableauPseudos = {"J1","J2","J3","J4"};
				//on regarde le nombre d'humains
				if (nbHumains==1) {
					System.out.println("Vous allez maintenant devoir entrer votre pseudo");
				} else {
					System.out.println("Vous allez maintenant devoir entrer les pseudos des joueurs humains");
				}
				//on demande nbHumains pseudos
				TextViewStep avancementPrecedent = this.avancement;
				for (int i=0; i<nbHumains;i++) {
					//Si la vue graphique a �t� compl�t� avant celle-ci on arr�te tout
					if(avancementPrecedent.ordinal() < avancement.ordinal())
						return tableauPseudos;
					System.out.println("Veuillez entrer le pseudo du joueur "+ (i+1));
					tableauPseudos[i]= getValeurUtilisee();
				}
				System.out.println("");
				return tableauPseudos;
	}
	
	/**
	 * sous-m�thode pour determiner le nombre de joueurs via une interface textuelle
	 * @return int nbJoueurs
	 * 		le nombre de joueurs compatibles choisi
	 */
	private int initNBJoueurs() {
		String nbJoueurs = getValeurUtilisee();
		try {
			if ((Integer.parseInt(nbJoueurs) != 4) && (Integer.parseInt(nbJoueurs) != 3)) {
				System.out.println("Entr�e incorrecte, vous devez choisir 3 ou 4 : ");
				return initNBJoueurs();
			}else {
				System.out.println("Vous avez choisi "+nbJoueurs+" joueurs \n");
				return Integer.parseInt(nbJoueurs);
			}
		}catch (Exception e) {
			System.out.println("Entr�e incorrecte, vous devez choisir 3 ou 4 : ");
			return initNBJoueurs();
		}
	}
	

	/**
	 * sous-m�thode pour determiner le nombre de joueurs humains via une interface textuelle
	 * @return int nbHumains
	 * 		le nombre de joueurs humains compatibles choisi
	 */
	private int initNBHumains(int nbJoueurs) {
		String lineSeparator = System.getProperty("line.separator");
		String nbHumains = getValeurUtilisee();
		//on v�rifie que le nombre entr� est bien compatible 
		try {
			Integer.parseInt(nbHumains);
			if ((Integer.parseInt(nbHumains) < 1) || (Integer.parseInt(nbHumains) > nbJoueurs)) {
				System.out.println("Entr�e incorrecte, vous devez choisir entre 1 et "+nbJoueurs+" inclus : ");
				return initNBHumains(nbJoueurs);
			} else {
				System.out.println("Il y aura donc "+nbHumains+" joueurs humains " + lineSeparator);
				return Integer.parseInt(nbHumains);
			}
		} catch (Exception e) {
			System.out.println("Entr�e incorrecte, vous devez choisir entre 1 et "+nbJoueurs+" inclus : ");
			return initNBHumains(nbJoueurs);
		}
	}
	
	/**
	 * sous-m�thode pour determiner le niveau des IA via une interface textuelle
	 * @return int difficulte
	 * 		le niveau des IA compatibles choisi
	 */
	private int initDifficulte(int nbJoueurs, int nbHumains) {
		String difficulte = getValeurUtilisee();
		try {//on v�rifie que le nombre entr� est bien compatible 
			if ((Integer.parseInt(difficulte) < 1) || (Integer.parseInt(difficulte) > 2)) {
				System.out.println("Entr�e incorrecte, vous devez choisir entre 1 et 2 ");
				return initDifficulte(nbJoueurs, nbHumains);
			}else {
				System.out.println("Le niveau est donc regl� sur "+difficulte+ "\n");
				return Integer.parseInt(difficulte);
			}
		} catch (Exception e) {
			System.out.println("Entr�e incorrecte, vous devez choisir entre 1 et 2 ");
			return initDifficulte(nbJoueurs, nbHumains);
		}
	}
	
	private String getValeurUtilisee() {
		//On attend qu'une valeur soit disponible ou que le mod�le soit chang�
		try {
			this.controller.enableModifModele();
			while(!this.controller.isValueDispo() && !this.model.hasChanged())
				Thread.sleep(1000);
			
		}catch(InterruptedException e) {
			System.err.append("TimeInterruptedException a l'appel de EntreeDispo");
		}
		
		//Si une valeur est disponible on la r�cup�re sinon on renvoie null pour indiquer que le modele
		//a d�j� chang�
		if(this.controller.isValueDispo())
			return this.controller.getEntree();
		else
			return null;
	}
	
	/**
	 * sous-m�thode pour determiner quelles r�gles utiliser pour la partie
	 * @return int choix
	 * 		0 pour les regles de base 
	 * 		1 pour la variante troph� nullifieur
	 */
	private int initRegles() {
		try {//on v�rifie que le nombre entr� est bien compatible
			String choix = getValeurUtilisee();
			switch (choix) {
			case "0" : 
				System.out.println("Vous allez jouer avec les troph�es de base !");
				return 0;
			case "1" :
				System.out.println("Vous allez jouer avec le troph�e : \"nullifieur\" ");
				return 1;
			default : 
				System.out.println("Entr�e incorrecte, vous devez choisir entre 0 et 1 ");
				return initRegles();
			}
		} catch (Exception e) {
			System.out.println("Entr�e incorrecte, vous devez choisir entre 0 et 1 ");
			return initRegles();
		}
	}
	
	/**
	 * sous-m�thode pour determiner quelles r�gles utiliser pour la partie
	 * @return int choix
	 * 		0 pour les regles de base 
	 * 		1 pour la variante "a coeur ouvert"
	 */
	private int initCondiVictoires() {
		String choix = getValeurUtilisee();
		try {//on v�rifie que le nombre entr� est bien compatible 
			switch (choix) {
			case "0" : 
				System.out.println("Vous allez jouer avec les r�gles de base !");
				return 0;
			case "1" :
				System.out.println("Vous allez jouer avec l'extension : \"A Coeur Ouvert\" ");
				return 1;
			default : 
				System.out.println("Entr�e incorrecte, vous devez choisir entre 0 et 1 ");
				return initCondiVictoires();
			}
		} catch (Exception e) {
			System.out.println("Entr�e incorrecte, vous devez choisir entre 0 et 1 ");
			return initCondiVictoires();
		}
	}
	
	@Override
	public void run() {
		new Thread(this).start();
	}

}
