package controller;

import java.util.ArrayList;
import decorator.*;

import decorator.User;
import composite.*;
import resources.*;

public class SearchController {

	/**
	 * riempie un ArrayList contenente tutte le attivita presenti nel DB
	 */
	/*public static ElencoAttivitaBean cercaAttivita(){		
		return ServiceDB.riempiAttDaDB();
	}*/
	
	/**
	 * riempie un ArrayList contenente tutti gli itinerari presenti nel DB aventi lo stesso username
	 * @param startLoc
	 * @param endLoc
	 * @return
	 */
	public static void searchMyItinerary(User user) {
		//return ServiceDB.riempiItDaDB(user);
		user.recuperaMyItinerary();
	}
	
	/**
	 * riempie un ArrayList contenente tutti gli itinerari presenti nel DB aventi lo stesso username
	 * @param startLoc
	 * @param endLoc
	 * @return
	 */
	public static ArrayList<Itinerary> searchItinerary() {
		return ServiceDB.searchItinerary();
	}
	
	/**
	 * riempie un ArrayList contenente tutti gli stayTemplate presenti nel DB aventi startLoc ed endLoc
	 * @param startLoc
	 * @param endLoc
	 * @return
	 */
	/*
	public static ElencoItineraryBean cercaStayTemplate(String startLoc,String endLoc) {
		return ServiceDB.riempiStDaDB(startLoc,endLoc);
	}
	*/
	
}
