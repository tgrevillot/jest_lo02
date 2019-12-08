package model.cards;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import model.joueur.Joueur;

public enum Condition {
	detenteurJoker,
	bestJest,
	plusPetitPique,
	plusPetitTrefle,
	plusPetitCarreau,
	plusPetitCoeur,
	plusGrandPique,
	plusGrandTrefle,
	plusGrandCarreau,
	plusGrandCoeur,
	bestJestWithoutJoker,
	plusDeCartes2,
	plusDeCartes3,
	plusDeCartes4;	
}