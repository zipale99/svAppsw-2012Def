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
	
	private DecoratorUser currentUser;
	
	public ManagementController(DecoratorUser user) {
		this.currentUser = user;
	}
	
	public DecoratorUser getCurrentUser() {
		return currentUser;
	}
	
	public void createItinerary(String nome, String descrizione, String categoria) {
		System.out.println("metodo create itinerary di management controller");
		currentUser.createItinerary(nome, descrizione, categoria);
	}
	
	
	
	
	

}
