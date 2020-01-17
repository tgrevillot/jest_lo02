package controllers;

import java.util.LinkedList;

import model.PartieJest;
import model.cards.Carte;
import views.TextViewStep;
import views.VueTextuelle;

/**
 * Le controlleur de la partie en géneral 
 * @author moras
 *
 */
public class ControllerPartie implements Runnable {

	/**
	 * Lance une partie
	 */
	@Override
	public synchronized void run() {
		PartieJest model = new PartieJest();
		VueTextuelle vt = new VueTextuelle(ControllerText.getControllerText(), model);
		model.addObserver(vt);
		 
		//On notifie le modèle que tout est prêt et qu'on peut commencer l'initialisation
		model.notifier();
		
		//On attend que l'initialisation soit terminé
		try {
			while(vt.getAvancement() != TextViewStep.AFFICHESCORE)
				wait();
		} catch(InterruptedException e) {
		}
			//On lance les tours de jeu
		model.faireUnTour(new LinkedList<Carte>());
		//On détermine le gagnant
		model.notifier(model.determinerGagnant());
		//On arrête tous les Thread
		System.exit(0);
	}
	
	/**
	 * Lance le jeu
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new ControllerPartie()).start();
	}
}
