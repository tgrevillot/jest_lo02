package views;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import controllers.ControllerText;
import model.PartieJest;
import model.joueur.Joueur;
/**
 * Vue textuelle montrant tout ce qui se passe sous forme de chaines de caracteres
 */
public class VueTextuelle implements Observer, Runnable, Vue {
	/**
	 * Le controlleur de vue textuelle associe
	 */
	private ControllerText controller;
	/**
	 * Le model utilise pour faire tourner cette vue 
	 */
	private PartieJest model;
	/**
	 * L'avancement de la partie
	 * Permet, en fonction de sa valeur de decider quelle action effectuer
	 */
	private TextViewStep avancement;
	
	/**
	 * Le constructeur de la vue textuelle 
	 * @param ct
	 * 		Le controlleur qui va la releir a la vue 
	 * @param model
	 * 		Le model utilise 
	 */
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


	/**
	 * methode venant de l'interface observable
	 * designe, en fonction de avancement de l'action a effectuer ensuite
	 */
	public void update(Observable obs, Object arg) {
		int nbJoueurs;
		int nbJoueursReels;
		String lineSeparator = System.getProperty("line.separator");
		
		switch(this.avancement) {
			case NBJOUEURS:
				//Initialisation du jeu, on affiche le debut de partie
				System.out.println("Bienvenue dans le Jest !");
				System.out.println("Suivez les instructions suivantes pour configurer la partie et pouvoir jouer "+"\n");
				//choix du nombre de joueurs	
				System.out.println("Veuillez entrer le nombre de joueurs (3 ou 4) : ");
				
				nbJoueurs = initNBJoueurs();
				
				//On fait avancer l'initialisation
				this.avancement = TextViewStep.NBJOUEURSREELS;
				this.model.setNbJoueur(nbJoueurs);
				break;
			case NBJOUEURSREELS:
				//On demande le nombre de joueurs reels
				nbJoueurs = this.model.getNbJoueurs();
				System.out.println("Veuillez entrer le nombre de joueurs humains (entre 1 et "+nbJoueurs+" inclus) : ");
				nbJoueursReels = initNBHumains(nbJoueurs);
				
				//On fait avancer l'initialisation
				this.avancement = TextViewStep.DIFFICULTE;
				this.model.setNbJoueurReel(nbJoueursReels);
				break;
			case DIFFICULTE:
				//Choix de la difficulte 
				nbJoueurs = this.model.getNbJoueurs();
				nbJoueursReels = this.model.getNbJoueursReels();
				System.out.println("Veuillez entrer la difficulte souhaite : \n1 - Normal \n2 - Avance");
				int difficulte = initDifficulte(nbJoueurs, nbJoueursReels);
				
				//On fait avancer l'initialisation
				this.avancement = TextViewStep.PSEUDO;
				this.model.setDifficulte(difficulte);
				break;
			case PSEUDO:
				//On releve les differents pseudo et on cree les joueurs dans le modele.
				nbJoueursReels = this.model.getNbJoueursReels();				
				String[] tabPseudos = this.getTabPseudos(nbJoueursReels);
				this.model.ajouterJoueurs(tabPseudos);
				
				//On fait avancer l'initialisation
				this.avancement = TextViewStep.TROPHEE;
				this.model.notifier();
				break;
			case TROPHEE:
				//Choix des trophees add
				System.out.println("Veuillez choisir le trophee additionnel que vous voulez utiliser : \n0- Aucun \n1- Trophee Nullifieur");
				int regles = initRegles();
				
				//On fait avancer l'initialisation
				this.avancement = TextViewStep.CONDIVICTOIRE;
				this.model.setRegle(regles);
				break;
			case CONDIVICTOIRE:
				//Choix des conditions de victoires add
				System.out.println("Veuillez choisir la regle additionnelle que vous voulez utiliser : \n0- Aucune \n1- A Coeur Ouvert");
				int conditionsVictoire = initCondiVictoires();
				
				//On fait avancer l'initialisation
				this.avancement = TextViewStep.AFFICHESCORE;
				this.model.setConditionsVictoire(conditionsVictoire);
				break; 
			case AFFICHESCORE:
				HashMap<Joueur, Integer> resultat = null;
				if(arg != null)
					if(arg instanceof HashMap<?, ?>)
						resultat = (HashMap<Joueur, Integer>) arg;
					
				//On annonce que c'est la fin de la partie
				System.out.println(lineSeparator + lineSeparator);
				System.out.println("La partie est terminee ! Faisons le point sur les scores : ");
				
				//On boucle sur les resultats des joueurs
				for(Joueur j : resultat.keySet())
					System.out.println("Le joueur " + j.getNom() + " a obtenu " + resultat.get(j) + " points.");
				
				this.avancement = TextViewStep.ANNONCEGAGNANT;
				break;
			case ANNONCEGAGNANT:
				//On annonce le gagnant
				Joueur gagnant = null;
				if(arg != null && arg instanceof Joueur)
					gagnant = (Joueur) arg;
				
				System.out.println(System.getProperty("line.separator"));
				System.out.println("Felicitation a " + gagnant.getNom() + " ! Vous remportez la partie !");
					
				break;
		}
	}
	
	/**
	 * Sous-methode pour determiner les pseudos des joueurs humains une interface textuelle
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
					//Si la vue graphique a ete complete avant celle-ci on arrete tout
					if(avancementPrecedent.ordinal() < avancement.ordinal())
						return tableauPseudos;
					System.out.println("Veuillez entrer le pseudo du joueur "+ (i+1));
					tableauPseudos[i]= getValeurUtilisee();
				}
				System.out.println("");
				return tableauPseudos;
	}
	
	/**
	 * sous-methode pour determiner le nombre de joueurs via une interface textuelle
	 * @return int nbJoueurs
	 * 		le nombre de joueurs compatibles choisi
	 */
	private int initNBJoueurs() {
		String nbJoueurs = getValeurUtilisee();
		try {
			if ((Integer.parseInt(nbJoueurs) != 4) && (Integer.parseInt(nbJoueurs) != 3)) {
				System.out.println("Entree incorrecte, vous devez choisir 3 ou 4 : ");
				return initNBJoueurs();
			}else {
				System.out.println("Vous avez choisi "+nbJoueurs+" joueurs \n");
				return Integer.parseInt(nbJoueurs);
			}
		}catch (Exception e) {
			System.out.println("Entree incorrecte, vous devez choisir 3 ou 4 : ");
			return initNBJoueurs();
		}
	}
	

	/**
	 * sous-methode pour determiner le nombre de joueurs humains via une interface textuelle
	 * @return int nbHumains
	 * 		le nombre de joueurs humains compatibles choisi
	 */
	private int initNBHumains(int nbJoueurs) {
		String lineSeparator = System.getProperty("line.separator");
		String nbHumains = getValeurUtilisee();
		//on verifie que le nombre entre est bien compatible 
		try {
			Integer.parseInt(nbHumains);
			if ((Integer.parseInt(nbHumains) < 1) || (Integer.parseInt(nbHumains) > nbJoueurs)) {
				System.out.println("Entree incorrecte, vous devez choisir entre 1 et "+nbJoueurs+" inclus : ");
				return initNBHumains(nbJoueurs);
			} else {
				System.out.println("Il y aura donc "+nbHumains+" joueurs humains " + lineSeparator);
				return Integer.parseInt(nbHumains);
			}
		} catch (Exception e) {
			System.out.println("Entree incorrecte, vous devez choisir entre 1 et "+nbJoueurs+" inclus : ");
			return initNBHumains(nbJoueurs);
		}
	}
	
	/**
	 * sous-methode pour determiner le niveau des IA via une interface textuelle
	 * @return int difficulte
	 * 		le niveau des IA compatibles choisi
	 */
	private int initDifficulte(int nbJoueurs, int nbHumains) {
		String difficulte = getValeurUtilisee();
		try {//on verifie que le nombre entre est bien compatible 
			if ((Integer.parseInt(difficulte) < 1) || (Integer.parseInt(difficulte) > 2)) {
				System.out.println("Entree incorrecte, vous devez choisir entre 1 et 2 ");
				return initDifficulte(nbJoueurs, nbHumains);
			}else {
				System.out.println("Le niveau est donc regle sur "+difficulte+ "\n");
				return Integer.parseInt(difficulte);
			}
		} catch (Exception e) {
			System.out.println("Entree incorrecte, vous devez choisir entre 1 et 2 ");
			return initDifficulte(nbJoueurs, nbHumains);
		}
	}
	/**
	 * Permet de recuperer une entree clavier tout en restant en concurrence 
	 * @return String
	 * 		La valeur (si elle existe) que l'utilisateur doit renvoyer 
	 */
	private String getValeurUtilisee() {
		//On attend qu'une valeur soit disponible ou que le modele soit change
		try {
			this.controller.enableModifModele();
			while(!this.controller.isValueDispo() && !this.model.hasChanged())
				Thread.sleep(100);
			
		}catch(InterruptedException e) {
			System.err.append("TimeInterruptedException a l'appel de getValeurUtilisee");
		}
		
		//Si une valeur est disponible on la recupere sinon on renvoie null pour indiquer que le modele
		//a deja change
		if(this.controller.isValueDispo())
			return this.controller.getEntree();
		else
			return null;
	}
	
	/**
	 * sous-methode pour determiner quelles regles utiliser pour la partie
	 * concernant l'utilisation ou non du trophee nullifieur 
	 * @return int choix
	 * 		0 pour les regles de base 
	 * 		1 pour la variante trophe nullifieur
	 */
	private int initRegles() {
		try {//on verifie que le nombre entre est bien compatible
			String choix = getValeurUtilisee();
			switch (choix) {
			case "0" : 
				System.out.println("Vous allez jouer avec les trophees de base !");
				return 0;
			case "1" :
				System.out.println("Vous allez jouer avec le trophee : \"nullifieur\" ");
				return 1;
			default : 
				System.out.println("Entree incorrecte, vous devez choisir entre 0 et 1 ");
				return initRegles();
			}
		} catch (Exception e) {
			System.out.println("Entree incorrecte, vous devez choisir entre 0 et 1 ");
			return initRegles();
		}
	}
	
	/**
	 * sous-methode pour determiner quelles regles utiliser pour la partie
	 * relativement aux conditions de victoire
	 * @return int choix
	 * 		0 pour les regles de base 
	 * 		1 pour la variante "a coeur ouvert"
	 */
	private int initCondiVictoires() {
		String choix = getValeurUtilisee();
		try {//on verifie que le nombre entre est bien compatible 
			switch (choix) {
			case "0" : 
				System.out.println("Vous allez jouer avec les regles de base !");
				return 0;
			case "1" :
				System.out.println("Vous allez jouer avec l'extension : \"A Coeur Ouvert\" ");
				return 1;
			default : 
				System.out.println("Entree incorrecte, vous devez choisir entre 0 et 1 ");
				return initCondiVictoires();
			}
		} catch (Exception e) {
			System.out.println("Entree incorrecte, vous devez choisir entre 0 et 1 ");
			return initCondiVictoires();
		}
	}
	
	/**
	 * getteur de l'avancement de la partie 
	 * @return TextViewStep
	 */
	public TextViewStep getAvancement() {
		return this.avancement;
	}
	
	/**
	 * Methode a effectuer lors de l'initialisation du thread 
	 */
	public void run() {
		new Thread(this).start();
	}

}
