/**
 * Sarà la classe una delle due classi di contatto con il sistema per quanto riguarda le operazioni diverse da quelle di ricerca
 * Ossia dalla creazione di un itinerario fino al suo salvataggio.
 */
package controller;

import decorator.*;
import composite.*;
import resources.*;


/**
 * @author utente
 *
 */
public class ManagementController {
	
	private AbstractUserComponent currentUser;
	
	public ManagementController(AbstractUserComponent user) {
		this.currentUser = user;
	}
	
	public void setUser (AbstractUserComponent auc) {
		currentUser = auc;
	}
	
	public AbstractUserComponent getCurrentUser() {
		return currentUser;
	}
	
	public void createItinerary(String nome, String descrizione, String categoria) {
		System.out.println("metodo create itinerary di management controller");
		currentUser.provideBasicInfo(nome, descrizione, categoria);
	}
	
	public void setStay(StayTemplate st) {
		currentUser.setStay(st);
		System.out.println("clone: "+currentUser.getStay().toString());
		
	}
	
	public void getMyItinerary(AbstractUserComponent user) {
		System.out.println("metodo getMyItinerary di managementController");
		user.myItinerary();
	}
	
	public void setOptionValue(int idOption, int idOptionValue, int idLeaf) {
		currentUser.setOptionValue(idOption, idOptionValue, idLeaf);
	}
	
	public void addActivity(Activity activity) {
		currentUser.addActivity(activity);
	}

	public void addStay() {
		currentUser.addStay();
	}
	
	/*
	 * TO_DO!!
	 * public void addTransferStay(int timeOffset, StayTemplate st) {
	 * 		currentUser.setTransferStay(timeOffset, st);		
	 * }
	 */
	
	public void deleteStay(int idStay) {
		currentUser.deleteStay(idStay);
	}
	
	public void modificaTappa(int idStay) {
		currentUser.modificaTappa(idStay);
	}
	
	
	
	

}
