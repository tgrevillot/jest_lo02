package model;

import model.joueur.Visitable;
/**
 * Interface Visiteur du patron de concetion eponyme
 * @author moras
 *
 */
public interface Visiteur {
	
	public int visit(Visitable visitable);
	
}
