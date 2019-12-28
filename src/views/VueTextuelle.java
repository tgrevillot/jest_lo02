package views;

import java.util.Observable;
import java.util.Observer;

import controllers.ControllerText;

public class VueTextuelle implements Observer, Runnable {
	
	private ControllerText controller;
	private int avancement;
	
	public VueTextuelle(ControllerText ct) {
		//TODO A COMPLETER
		if(ct == null)
			throw new Error("Le controllerText fournie est NULL");
		this.controller = ct;
		this.avancement = 0;
		//A VOIR PAR LA SUITE
		new Thread(this).start();
	}
	
	public void update(Observable obs, Object arg) {
		switch(this.avancement) {
			case 0:
				break;
			case 1:
				break;
			case 2:
				break;
			default:
				System.out.println("Je begaye frr c chaud");
		}
	}
	
	@Override
	public void run() {
		this.controller.enableModifModele();
	}

}
