package model;

import java.util.HashMap;
import java.util.HashSet;

import model.cards.Carte;
import model.cards.Couleur;
import model.joueur.Joueur;
import model.joueur.Visitable;

public class CompteurClassique implements Visiteur {
	
	private HashMap<Couleur, HashSet<Carte>> cartesTri;
	
	public CompteurClassique() {
		this.cartesTri = new HashMap<Couleur, HashSet<Carte>>();
	}
	
	public int visit(Joueur joueur) {
		int score = 0;
		
		//On récupère les cartes triées
		this.cartesTri = joueur.getCartesTri();
		
		//On va maintenant compter les points avec les combinaisons spéciales
		score += this.scoreCoeur(joueur, this.cartesTri.get(Couleur.COEUR));
		score += this.scorePairesNoires(this.cartesTri.get(Couleur.PIQUE), this.cartesTri.get(Couleur.TREFLE));
		//TODO : Attention au cas de l'as de coeur solo qui vaut -5 et non 5
		score += this.asToutSeul();
		
		//On additionne les scores des cartes
		for(Couleur coul : this.cartesTri.keySet())
			score += this.addFaceValues(this.cartesTri.get(coul), coul);
		
		//On vide la Map
		this.cartesTri.clear();
		
		return score;
	}
	
	@Override
	public int visit(Visitable visitable) {
		int score = 0;
		
		if(visitable instanceof Joueur)
			score = visit((Joueur) visitable);
		else
			throw new Error("Unknow Visitable");
		
		return score;
	}
	
	//Ci-dessous vont se trouver les méthodes privées de calcul de points
	private int asToutSeul() {
		int score = 0;
		
		//On va parcourir chaque liste triée et regarder s'il n'y a qu'une seule carte
		for(Couleur coul : this.cartesTri.keySet()) {
			HashSet<Carte> listeT = this.cartesTri.get(coul);
			
			//S'il y a qu'une seule carte 
			if(listeT.size() == 1) {
				//et que c'est un As on ajoute 5 points
				Carte c = listeT.iterator().next();
				if(c.getFaceValue() == 1) {
					//TODO CE N'EST PAS AUSSI SIMPLE, préfere plutôt changer la face value de l'as a 5 et le traiter plus tard !
					score += 5;
					//On enlève l'as de la collection pour ne pas compter les points 2 fois
					listeT.remove(c);
				}
			}
		}	
		return score;
	}
	
	private int addFaceValues(HashSet<Carte> cartes, Couleur coul) {
		int score = 0;
		
		//On va déterminer la boucle à utiliser en fonction de la couleur
		if(coul == Couleur.COEUR)
			for(Carte c : cartes)
				score -= c.envoyerPoints();
		else
			for(Carte c : cartes)
				score += c.envoyerPoints();
		
		return score;
	}
	
	private int scorePairesNoires(HashSet<Carte> pique, HashSet<Carte> trefle) {
		int score = 0;
		
		//On va tout boucler sur l'ensemble des cartes des deux collections et vérifier s'il y en a
		//possédant la même face value. Si c'est le cas on ajoute 2 points.
		for(Carte carteP : pique)
			for(Carte carteT : trefle)
				if(carteP.getFaceValue() == carteT.getFaceValue())
					score += 2;
		
		return score;
	}
	
	private int scoreCoeur(Joueur j, HashSet<Carte> coeur) {
		int score = 0;
		//On commencera par vérifier si le jest comporte le Joker
				if(j.hasJoker()) {
				//Si on a le Joker alors on recuperera le nombre de carte Coeur
					int nbCartesCoeur = coeur.size();
					
					//Si on a 0 coeur, alors on ajoute 4 points
					if(nbCartesCoeur == 0)
						score += 4;
					
					//Si on a 4 coeur, on incrémente le score de 10
					else if(nbCartesCoeur == 4)
						score += 10;
					else	
						//Sinon on cécrémente des faces Values de chacun
						for(Carte c : coeur)
							score -= c.getFaceValue(); 
				}
		return score;
	}
}
