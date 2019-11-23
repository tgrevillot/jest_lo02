package model.joueur;

import java.util.ArrayList;

import model.cards.Carte;

public class Basique implements IAStrategie {

	@Override
	public void comportement() {
		// TODO Auto-generated method stub
		
	}
	
	public void offre(ArrayList<Carte> main) {
		//on utilise une décision random pour ce choix 
		if (Math.random()<0.5) {
			main.get(0).cacherCarte();
		} else {
			main.get(1).cacherCarte();
		}
	}
	
}
