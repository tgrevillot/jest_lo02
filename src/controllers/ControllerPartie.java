package controllers;

import model.PartieJest;
import views.VueTextuelle;

public class ControllerPartie implements Runnable {

	@Override
	public void run() {
		PartieJest model = new PartieJest();
		VueTextuelle vt = new VueTextuelle(new ControllerText(), model);
		model.addObserver(vt);
		
	}
}
