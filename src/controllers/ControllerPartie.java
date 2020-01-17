package controllers;

import java.util.LinkedList;

import model.PartieJest;
import model.cards.Carte;
import views.TextViewStep;
import views.VueTextuelle;

/**
 * Le controlleur de la partie en g�neral 
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
		 
		//On notifie le mod�le que tout est pr�t et qu'on peut commencer l'initialisation
		model.notifier();
		
		//On attend que l'initialisation soit termin�
		try {
			while(vt.getAvancement() != TextViewStep.AFFICHESCORE)
				wait();
		} catch(InterruptedException e) {
		}
			//On lance les tours de jeu
		model.faireUnTour(new LinkedList<Carte>());
		//On d�termine le gagnant
		model.notifier(model.determinerGagnant());
		//On arr�te tous les Thread
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
