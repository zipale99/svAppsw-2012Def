/**
 * Oggetto Aggregate/Component, rappresenta l'interfaccia per le operazioni di Utente generico 
 * o per un utente arricchito con la funzionalità di Creator
 */
package decorator;

import java.util.List;

import resources.Activity;
import composite.Itinerary;
import composite.StayTemplate;

/**
 * @author Alessandro
 *
 */
public abstract class AbstractUserComponent {
	
	public abstract String getUsername();
	public abstract String getPwd();
	public abstract String getRuolo();
	public abstract void setUsername(String username);
	public abstract void setPwd(String pwd);
	public abstract void setRuolo(String ruolo);
	public void myItinerary() {}
	public void createItinerary() {}
	public void invalida() {}
	public void provideBasicInfo(String nome, String descrizione, String categoria) {}
	public void setStay(StayTemplate stay) {}
	public void setItineraryList(List<Itinerary> itineraryList) {}
	public void setItinerary(Itinerary itinerary) {}
	public void setOptionValue(int idOption, int idOptionValue, int idLeaf) {}
	public void addActivity(Activity activity) {}
	public void addStay() {}
	public void deleteStay(int idStay) {}
	public void modificaTappa(int idStay) {}
	public void setTransferStay(StayTemplate st, int x){}
	
	public Itinerary getItinerary() {
		return null;
	}
	
	public StayTemplate getStay() {
		return null;
	}
	
	public List<Itinerary> getItineraryList() {
		return null;
	}
	
}
