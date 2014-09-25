/**
 * Oggetto ConcreteComponent, implementa l'interfaccia di User e rappresenta la generalizzazione per i diversi tipi di utenti
 * Customer e TA.
 * 
 * TO-DO: Gerarchia degli utenti Customer e TA a partire da questa classe.
 *  
 */
package decorator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import resources.ServiceDB;
import composite.Itinerary;

/**
 * @author Alessandro
 *
 */
public class User extends AbstractUser{
	
	String username;
	String pwd;
	String ruolo;
	List<Itinerary> itineraryList;
	
	public User(String username, String pwd, String ruolo) {
		this.username = username;
		this.pwd = pwd;
		this.ruolo = ruolo;
		this.itineraryList = new ArrayList<Itinerary>();
	}
	
	public User() {
		username = null;
		pwd = null;
		ruolo = null;
	}
	
	@Override
	public List<Itinerary> getItineraryList() {
		return itineraryList;
	}
	
	@Override
	public void setItineraryList(List<Itinerary> itineraryList) {
		this.itineraryList = itineraryList;
	}

	/**
	 * chiama il metodo che recupera gli itinerari per un determinato utente
	 */
	@Override
	public void myItinerary() {
		this.itineraryList = ServiceDB.myItinerary(this);
	}

	public String getUsername() {
		return username;
	}
	
	public String getPwd() {
		// TODO Auto-generated method stub
		return pwd;
	}
	
	public String getRuolo() {
		// TODO Auto-generated method stub
		return ruolo;
	}
	
	public void setUsername(String username) {
		// TODO Auto-generated method stub
		this.username = username;
	}
	
	public void setPwd(String pwd) {
		// TODO Auto-generated method stub
		this.pwd = pwd;
	}
	
	public void setRuolo(String ruolo) {
		// TODO Auto-generated method stub
		this.ruolo = ruolo;
	}
	
}
