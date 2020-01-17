package controllers;

import java.awt.EventQueue;
import java.util.LinkedList;

import model.PartieJest;
import model.cards.Carte;
import views.InterfaceGraphiqueParametres;
import views.TextViewStep;
import views.VueTextuelle;

/**
 * Le controlleur de la partie en general 
 * @author moras
 *
 */
public class ControllerPartie implements Runnable {

	/**
	 * Lance une partie
	 */
	public synchronized void run() {
		PartieJest model = new PartieJest();
		VueTextuelle vt = new VueTextuelle(ControllerText.getControllerText(), model);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// * Creation du Controleur : lien entre le Modele et la Vue
					InterfaceGraphiqueParametres vg = new InterfaceGraphiqueParametres();
					vg.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		model.addObserver(vt);
		 
		//On notifie le modele que tout est pret et qu'on peut commencer l'initialisation
		model.notifier();
		
		//On attend que l'initialisation soit termine
		try {
			while(vt.getAvancement() != TextViewStep.AFFICHESCORE)
				wait();
		} catch(InterruptedException e) {
		}
			//On lance les tours de jeu
		model.faireUnTour(new LinkedList<Carte>());
		//On determine le gagnant
		model.notifier(model.determinerGagnant());
		//On arrete tous les Thread
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
